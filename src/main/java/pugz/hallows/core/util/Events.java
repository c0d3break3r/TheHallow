package pugz.hallows.core.util;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.IDataManager;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.world.BlockEvent;
import pugz.hallows.common.block.GiantCauldronBlock;
import pugz.hallows.common.item.HallowsArmorMaterial;
import pugz.hallows.common.item.HallowsItemTier;
import pugz.hallows.common.world.HallowsTeleporter;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.HallowsDimensions;
import pugz.hallows.core.registry.HallowsItems;
import pugz.hallows.core.registry.other.HallowsData;

import java.util.List;

public class Events {
    public static class Teleport {
        public static void onProjectileImpact(ProjectileImpactEvent event) {
            Entity entity = event.getEntity();
            RayTraceResult result = event.getRayTraceResult();
            if (entity instanceof EnderPearlEntity && result.getType() == RayTraceResult.Type.BLOCK) {
                BlockPos hitPos = new BlockPos(result.getHitVec());
                BlockState state = entity.world.getBlockState(hitPos);

                if (state.isIn(HallowsBlocks.GIANT_CAULDRON.get())) {
                    if (state.get(GiantCauldronBlock.LIQUID) == GiantCauldronBlock.Liquid.PORTAL) {

                        Entity thrower = ((EnderPearlEntity)entity).func_234616_v_();
                        if (!thrower.isPassenger() && !thrower.isBeingRidden() && thrower.isNonBoss() && thrower instanceof ServerPlayerEntity) {
                            teleportPlayer((ServerPlayerEntity)thrower, hitPos);
                        }
                    }
                }
            }
        }

        public static void teleportPlayer(ServerPlayerEntity player, BlockPos pos) {
            if (player.getRidingEntity() != null || player.isBeingRidden()) return;

            if (player.world.getDimensionKey().equals(HallowsDimensions.DIMENSION)) {
                ServerWorld teleportWorld = player.server.func_241755_D_();
                player.changeDimension(teleportWorld, new HallowsTeleporter(pos));
            } else {
                ServerWorld server = player.server.getWorld(HallowsDimensions.DIMENSION);
                if (server == null) return;
                player.changeDimension(server, new HallowsTeleporter(pos));
            }
        }
    }

    public static class Charge {
        public static void onItemTooltip(ItemTooltipEvent event) {
            ItemStack stack = event.getItemStack();
            if (stack.getItem() instanceof ArmorItem) {
                if (((ArmorItem)stack.getItem()).getArmorMaterial() == HallowsItems.STYGIAN_MATERIAL) {
                    HallowsArmorMaterial material = (HallowsArmorMaterial)((ArmorItem)stack.getItem()).getArmorMaterial();
                    List<ITextComponent> tooltip = event.getToolTip();
                    tooltip.add(new StringTextComponent("+" + material.getCharge(MobEntity.getSlotForItemStack(stack)) + " Charge").mergeStyle(TextFormatting.BLUE));
                }
            } else if (stack.getItem() instanceof ToolItem) {
                if (((ToolItem)stack.getItem()).getTier() == HallowsItems.STYGIAN_TIER) {
                    HallowsItemTier material = (HallowsItemTier)((ToolItem)stack.getItem()).getTier();
                    List<ITextComponent> tooltip = event.getToolTip();
                    tooltip.add(new StringTextComponent("+" + material.getCharge() + " Charge").mergeStyle(TextFormatting.BLUE));
                }
            }
        }

        public static void onBlockBreak(BlockEvent.BreakEvent event) {
            PlayerEntity player = event.getPlayer();
            World world = player.getEntityWorld();
            ItemStack held = player.getHeldItem(player.getActiveHand());
            if (held.getItem() instanceof ToolItem) {
                if (((ToolItem)held.getItem()).getTier() == HallowsItems.STYGIAN_TIER) {
                    IDataManager manager = ((IDataManager) player);
                    HallowsItemTier material = (HallowsItemTier)((ToolItem)held.getItem()).getTier();
                    manager.setValue(HallowsData.PLAYER_CHARGE, manager.getValue(HallowsData.PLAYER_CHARGE) + material.getCharge() * world.getRandom().nextInt());
                    if (manager.getValue(HallowsData.PLAYER_CHARGE) >= 100) {
                        world.playSound(player, player.getPosition(), SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.BLOCKS, 2.0F, 1.0F);
                        for (BlockPos pos : (BlockPos[]) BlockPos.getAllInBox(new AxisAlignedBB(player.getPosX() - 5.0D, player.getPosY() - 5.0D, player.getPosZ() - 5.0D, player.getPosX() + 5.0D, player.getPosY() + 5.0D, player.getPosZ() + 5.0D)).toArray()) {
                            world.sendBlockBreakProgress(player.getEntityId(), pos, 10 * (int)world.getBlockState(pos).getBlockHardness(world, pos));
                        }
                        manager.setValue(HallowsData.PLAYER_CHARGE, 0);
                    }
                }
            }
        }

        public static void onLivingHurt(LivingHurtEvent event) {
            LivingEntity living = event.getEntityLiving();

            if (living instanceof PlayerEntity && event.getSource() == DamageSource.GENERIC) {
                PlayerEntity player = (PlayerEntity)event.getEntityLiving();
                World world = player.getEntityWorld();
                IDataManager manager = ((IDataManager) player);

                for (ItemStack stack : player.getArmorInventoryList()) {
                    if (stack.getItem() instanceof ArmorItem) {
                        if (((ArmorItem)stack.getItem()).getArmorMaterial() == HallowsItems.STYGIAN_MATERIAL) {
                            HallowsArmorMaterial material = (HallowsArmorMaterial)((ArmorItem)stack.getItem()).getArmorMaterial();
                            manager.setValue(HallowsData.PLAYER_CHARGE, manager.getValue(HallowsData.PLAYER_CHARGE) + material.getCharge(((ArmorItem) stack.getItem()).getEquipmentSlot()) * world.getRandom().nextInt());
                        }
                    }
                }

                if (manager.getValue(HallowsData.PLAYER_CHARGE) >= 100) {
                    world.playSound(player, player.getPosition(), SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.BLOCKS, 2.0F, 1.0F);
                    for (Entity entity : world.getEntitiesInAABBexcluding(player, new AxisAlignedBB(player.getPosX() - 5.0D, player.getPosY() - 5.0D, player.getPosZ() - 5.0D, player.getPosX() + 5.0D, player.getPosY() + 5.0D, player.getPosZ() + 5.0D), Entity::isAlive)) {
                        entity.attackEntityFrom(DamageSource.MAGIC, 6.0F);
                    }
                    manager.setValue(HallowsData.PLAYER_CHARGE, 0);
                }
            }
        }
    }
}