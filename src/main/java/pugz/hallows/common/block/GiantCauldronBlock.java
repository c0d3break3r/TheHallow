package pugz.hallows.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.PotionUtils;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class GiantCauldronBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.FACING;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final EnumProperty<Liquid> LIQUID = EnumProperty.create("liquid", Liquid.class);
    private static final VoxelShape SOUTH = VoxelShapes.or(Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D), Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape SOUTH_FOOT = Block.box(12.0D, 0.0D, 12.0D, 16.0D, 4.0D, 16.0D);
    private static final VoxelShape NORTH = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D));
    private static final VoxelShape NORTH_FOOT = Block.box(0.0D, 0.0D, 0.0D, 4.0D, 4.0D, 4.0D);
    private static final VoxelShape EAST = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.box(4.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape EAST_FOOT = Block.box(0.0D, 0.0D, 12.0D, 4.0D, 4.0D, 16.0D);
    private static final VoxelShape WEST = VoxelShapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D), Block.box(12.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape WEST_FOOT = Block.box(12.0D, 0.0D, 0.0D, 16.0D, 4.0D, 4.0D);
    private static final VoxelShape BOTTOM = Block.box(0.0D, 4.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    private static final VoxelShape PORTAL = Block.box(0.0D, 12.0D, 0.0D, 16.0D, 14.0D, 16.0D);
    private List<EffectInstance> storedEffects;

    public GiantCauldronBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(HALF, Half.BOTTOM).setValue(LIQUID, Liquid.EMPTY));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack held = player.getItemInHand(handIn);

        if (state.getValue(LIQUID) == Liquid.EMPTY) {
            if (held.getItem() == Items.FLINT_AND_STEEL) {
                worldIn.setBlock(pos, state.setValue(LIQUID, Liquid.PORTAL), 3);
            } else if (held.getItem() instanceof PotionItem) {
                this.storedEffects = PotionUtils.getCustomEffects(held);
            } else if (held.getItem() == Items.WATER_BUCKET) {
                worldIn.setBlock(pos, state.setValue(LIQUID, Liquid.WATER), 3);
            } else if (held.getItem() == Items.LAVA_BUCKET) {
                //worldIn.setBlock(pos, state.setValue(LIQUID, Liquid.LAVA), 3);
            } else if (held.getItem() == Items.BUCKET) {
                if (state.getValue(LIQUID) == Liquid.WATER) {
                    player.setItemInHand(handIn, new ItemStack(Items.WATER_BUCKET));
                } else if (state.getValue(LIQUID) == Liquid.LAVA) {
                    player.setItemInHand(handIn, new ItemStack(Items.LAVA_BUCKET));
                }
                worldIn.setBlock(pos, state.setValue(LIQUID, Liquid.EMPTY), 3);
            } else if (held.getItem() == Items.GLASS_BOTTLE) {
                ItemStack potion = PotionUtils.setCustomEffects(new ItemStack(Items.POTION), storedEffects);
                potion.setHoverName(new StringTextComponent("Mixed Potion"));
                player.setItemInHand(handIn, potion);
            }
            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        } else return ActionResultType.PASS;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.getValue(FACING)) {
            default:
            case SOUTH:
                return state.getValue(HALF) == Half.BOTTOM ? VoxelShapes.or(SOUTH, BOTTOM, SOUTH_FOOT) : state.getValue(LIQUID) != Liquid.EMPTY ? VoxelShapes.or(SOUTH, PORTAL) : SOUTH;
            case NORTH:
                return state.getValue(HALF) == Half.BOTTOM ? VoxelShapes.or(NORTH, BOTTOM, NORTH_FOOT) : state.getValue(LIQUID) != Liquid.EMPTY ? VoxelShapes.or(NORTH, PORTAL) : NORTH;
            case WEST:
                return state.getValue(HALF) == Half.BOTTOM ? VoxelShapes.or(WEST, BOTTOM, WEST_FOOT) : state.getValue(LIQUID) != Liquid.EMPTY ? VoxelShapes.or(WEST, PORTAL) : WEST;
            case EAST:
                return state.getValue(HALF) == Half.BOTTOM ? VoxelShapes.or(EAST, BOTTOM, EAST_FOOT) : state.getValue(LIQUID) != Liquid.EMPTY ? VoxelShapes.or(EAST, PORTAL) : EAST;
        }
    }

    protected static Direction getFacing(BlockItemUseContext context) {
        float f = MathHelper.wrapDegrees(context.getRotation()) / 45.0F;

        if (f > -2.0F && f <= 0.0F) {
            return Direction.NORTH;
        } else if (f > 0.0F && f <= 2.0F) {
            return Direction.WEST;
        } else if (f > 2.0F) {
            return Direction.SOUTH;
        } else {
            return Direction.EAST;
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        Direction.Axis axis = state.getValue(FACING).getAxis();
        if (mirror != Mirror.NONE) {
            if ((mirror == Mirror.FRONT_BACK && axis == Direction.Axis.X) || (mirror == Mirror.LEFT_RIGHT && axis == Direction.Axis.Z)) {
                return state.rotate(Rotation.COUNTERCLOCKWISE_90);
            }
            else return state.rotate(Rotation.CLOCKWISE_90);
        } else return state;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getClickedPos();
        BlockState down = context.getLevel().getBlockState(pos.below());
        BlockState up = context.getLevel().getBlockState(pos.above());

        if (down.getBlock() instanceof GiantCauldronBlock && down.getValue(HALF) == Half.BOTTOM) {
            return this.defaultBlockState().setValue(FACING, down.getValue(FACING)).setValue(HALF, Half.TOP);
        } else if (up.getBlock() instanceof GiantCauldronBlock && up.getValue(HALF) == Half.TOP) {
            return this.defaultBlockState().setValue(FACING, up.getValue(FACING)).setValue(HALF, Half.BOTTOM);
        }

        return this.defaultBlockState().setValue(FACING, getFacing(context)).setValue(HALF, MathHelper.sin(context.getPlayer().getViewXRot(1.0F) * ((float) Math.PI / 180.0F)) > 0.0F ? Half.BOTTOM : Half.TOP);
    }


    @Override
    @SuppressWarnings("deprecation")
    public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if (!worldIn.isClientSide) {
            if (entityIn.isOnFire() && state.getValue(LIQUID) == Liquid.WATER) entityIn.clearFire();

            else if (state.getValue(LIQUID) == Liquid.LAVA) entityIn.setSecondsOnFire(8);

            else if (state.getValue(LIQUID) == Liquid.POTION && entityIn instanceof LivingEntity) {
                for (EffectInstance effect : storedEffects) {
                    if (effect.getEffect().isInstantenous()) {
                        effect.getEffect().applyInstantenousEffect(entityIn, entityIn, ((LivingEntity)entityIn), effect.getAmplifier(), 1.0D);
                    } else {
                        ((LivingEntity)entityIn).addEffect(new EffectInstance(effect));
                    }
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(LIQUID) == Liquid.PORTAL) {
            if (rand.nextInt(100) == 0) {
                worldIn.playLocalSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
            }

            for (int i = 0; i < 4; ++i) {
                double d0 = (double) pos.getX() + rand.nextDouble();
                double d1 = (double) pos.getY() + rand.nextDouble();
                double d2 = (double) pos.getZ() + rand.nextDouble();
                double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                int j = rand.nextInt(2) * 2 - 1;
                if (!worldIn.getBlockState(pos.west()).is(this) && !worldIn.getBlockState(pos.east()).is((this))) {
                    d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                    d3 = rand.nextFloat() * 2.0F * (float) j;
                } else {
                    d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                    d5 = rand.nextFloat() * 2.0F * (float) j;
                }

                worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, LIQUID);
    }

    public enum Liquid implements IStringSerializable {
        EMPTY("empty"),
        WATER("water"),
        PORTAL("portal"),
        LAVA("lava"),
        SNOW("snow"),
        POTION("potion");

        private final String name;

        Liquid(String name) {
            this.name = name;
        }

        @Nonnull
        public String getSerializedName() {
            return this.name;
        }
    }
}