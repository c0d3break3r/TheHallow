package pugz.hallows.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import pugz.hallows.common.block.GiantCauldronBlock;
import pugz.hallows.common.item.HallowsArmorMaterial;
import pugz.hallows.common.world.HallowsTeleporter;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.HallowsDimensions;
import pugz.hallows.core.registry.HallowsItems;

import java.util.List;
import java.util.UUID;

public class Events {
    public static class Teleport {
        public static void onProjectileImpact(ProjectileImpactEvent event) {
            Entity entity = event.getEntity();
            RayTraceResult result = event.getRayTraceResult();
            if (entity instanceof EnderPearlEntity && result.getType() == RayTraceResult.Type.BLOCK) {
                BlockState state = entity.world.getBlockState(new BlockPos(result.getHitVec()));
                if (state.isIn(HallowsBlocks.GIANT_CAULDRON.get())) {
                    if (state.get(GiantCauldronBlock.ACTIVATED)) {
                        Entity thrower = ((EnderPearlEntity)entity).func_234616_v_();
                        if (!thrower.isPassenger() && !thrower.isBeingRidden() && thrower.isNonBoss()) {
                            if (thrower.world instanceof ServerWorld) {
                                RegistryKey<World> key = thrower.world.getDimensionKey() == HallowsDimensions.DIMENSION ? World.OVERWORLD : HallowsDimensions.DIMENSION;
                                ServerWorld serverWorld = thrower.world.getServer().getWorld(key);
                                if (serverWorld != null) {
                                    thrower.func_242279_ag();
                                    thrower.changeDimension(serverWorld, new HallowsTeleporter(serverWorld));
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static class Charge {
        private static int charge = 0;

        public static void onItemTooltip(ItemTooltipEvent event) {
            ItemStack stack = event.getItemStack();
            if (stack.getItem() instanceof ArmorItem) {
                if (((ArmorItem)stack.getItem()).getArmorMaterial() == HallowsItems.STYGIAN_TIER) {
                    HallowsArmorMaterial material = (HallowsArmorMaterial)((ArmorItem)stack.getItem()).getArmorMaterial();
                    List<ITextComponent> tooltip = event.getToolTip();
                    tooltip.add(new StringTextComponent("+" + material.getCharge(MobEntity.getSlotForItemStack(stack)) + " Charge").mergeStyle(TextFormatting.BLUE));
                }
            }
        }

        public static void onLivingHurt(LivingHurtEvent event) {
            LivingEntity living = event.getEntityLiving();

            if (living instanceof PlayerEntity && event.getSource() == DamageSource.GENERIC) {
                PlayerEntity player = (PlayerEntity)event.getEntityLiving();
                World world = player.getEntityWorld();

                for (ItemStack stack : player.getArmorInventoryList()) {
                    if (stack.getItem() instanceof ArmorItem) {
                        if (((ArmorItem)stack.getItem()).getArmorMaterial() == HallowsItems.STYGIAN_TIER) {
                            HallowsArmorMaterial material = (HallowsArmorMaterial)((ArmorItem)stack.getItem()).getArmorMaterial();
                            charge += material.getCharge(((ArmorItem) stack.getItem()).getEquipmentSlot()) * world.getRandom().nextFloat();
                        }
                    }
                }

                if (charge >= 100) {
                    world.playSound(player, player.getPosition(), SoundEvents.ENTITY_GHAST_SCREAM, SoundCategory.BLOCKS, 2.0F, 1.0F);
                    for (Entity entity : world.getEntitiesInAABBexcluding(player, new AxisAlignedBB(player.getPosX() - 5.0D, player.getPosY() - 5.0D, player.getPosZ() - 5.0D, player.getPosX() + 5.0D, player.getPosY() + 5.0D, player.getPosZ() + 5.0D), Entity::isAlive)) {
                        entity.attackEntityFrom(DamageSource.MAGIC, 6.0F);
                        spawnParticles(ParticleTypes.CRIT, entity);
                    }
                    charge = 0;
                }
            }
        }

        @OnlyIn(Dist.CLIENT)
        private static void spawnParticles(IParticleData particleData, Entity entity) {
            World world = entity.world;
            for(int i = 0; i < 8; ++i) {
                double d0 = world.getRandom().nextGaussian() * 0.02D;
                double d1 = world.getRandom().nextGaussian() * 0.02D;
                double d2 = world.getRandom().nextGaussian() * 0.02D;
                world.addParticle(particleData, entity.getPosXRandom(1.0D), entity.getPosYRandom() + 1.0D, entity.getPosZRandom(1.0D), d0, d1, d2);
            }
        }
    }
}