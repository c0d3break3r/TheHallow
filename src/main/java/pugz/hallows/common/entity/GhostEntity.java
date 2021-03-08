package pugz.hallows.common.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Random;

public class GhostEntity extends MonsterEntity {
    protected static final DataParameter<Byte> GHOST_FLAGS = EntityDataManager.createKey(GhostEntity.class, DataSerializers.BYTE);

    public GhostEntity(EntityType<? extends GhostEntity> entity, World world) {
        super(entity, world);
        this.moveController = new GhostEntity.MoveHelperController(this);
        this.experienceValue = 2;
        this.setPathPriority(PathNodeType.WATER, -1.0F);
    }

    @Override
    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);
        this.doBlockCollisions();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new GhostEntity.ChargeAttackGoal());
        this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(9, new LookAtGoal(this, LivingEntity.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new GhostEntity.MoveRandomGoal());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this, PlayerEntity.class).setCallsForHelp(GhostEntity.class));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 4.0D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    protected void registerData() {
        super.registerData();
        this.dataManager.register(GHOST_FLAGS, (byte)0);
    }

    @Nonnull
    public EntitySize getSize(Pose poseIn) {
        return super.getSize(poseIn).scale(1.2F);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (super.attackEntityAsMob(entityIn)) {
            if (entityIn instanceof LivingEntity) ((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.BLINDNESS, 25, 0));
            return true;
        } else return false;
    }

    @Override
    public void tick() {
        this.noClip = true;
        super.tick();
        this.noClip = false;
        this.setNoGravity(true);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VEX_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_GHAST_SCREAM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_GHAST_SCREAM;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        super.playSound(SoundEvents.ENTITY_VEX_AMBIENT, 0.15F, 1.0F);
    }

    @Nonnull
    @Override
    public CreatureAttribute getCreatureAttribute() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public boolean canBePushed() {
        return false;
    }

    public boolean isWaterSensitive() {
        return true;
    }

    private boolean getGhostFlag(int mask) {
        int i = this.dataManager.get(GHOST_FLAGS);
        return (i & mask) != 0;
    }

    private void setGhostFlag(int mask, boolean value) {
        int i = this.dataManager.get(GHOST_FLAGS);
        if (value) {
            i = i | mask;
        } else {
            i = i & ~mask;
        }

        this.dataManager.set(GHOST_FLAGS, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.getGhostFlag(1);
    }

    public void setCharging(boolean charging) {
        this.setGhostFlag(1, charging);
    }

    protected class ChargeAttackGoal extends Goal {
        public ChargeAttackGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            if (GhostEntity.this.getAttackTarget() != null && !GhostEntity.this.getMoveHelper().isUpdating() && GhostEntity.this.rand.nextInt(7) == 0) {
                return GhostEntity.this.getDistanceSq(GhostEntity.this.getAttackTarget()) > 4.0D;
            } else {
                return false;
            }
        }

        public boolean shouldContinueExecuting() {
            return GhostEntity.this.getMoveHelper().isUpdating() && GhostEntity.this.isCharging() && GhostEntity.this.getAttackTarget() != null && GhostEntity.this.getAttackTarget().isAlive();
        }

        public void startExecuting() {
            LivingEntity livingentity = GhostEntity.this.getAttackTarget();
            Vector3d vector3d = livingentity.getEyePosition(1.0F);
            GhostEntity.this.moveController.setMoveTo(vector3d.x, vector3d.y, vector3d.z, 1.0D);
            GhostEntity.this.setCharging(true);
            GhostEntity.this.playSound(SoundEvents.ENTITY_GHAST_SCREAM, 1.0F, 1.0F);
        }

        public void resetTask() {
            GhostEntity.this.setCharging(false);
        }

        public void tick() {
            LivingEntity livingentity = GhostEntity.this.getAttackTarget();
            if (GhostEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                GhostEntity.this.attackEntityAsMob(livingentity);
                GhostEntity.this.setCharging(false);
            } else {
                double d0 = GhostEntity.this.getDistanceSq(livingentity);
                if (d0 < 9.0D) {
                    Vector3d vector3d = livingentity.getEyePosition(1.0F);
                    GhostEntity.this.moveController.setMoveTo(vector3d.x, vector3d.y, vector3d.z, 1.0D);
                }

                if (d0 < 6.0D) {
                    GhostEntity.this.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 25, 0, true, false));
                    // JUST ONCE
                    //spawnParticles(GhostEntity.this.world, GhostEntity.this.getPosition(), GhostEntity.this.rand);
                }
            }
        }
    }

    public void spawnParticles(World worldIn, BlockPos pos, Random rand) {
        for(int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble();
            double d2 = (double)pos.getZ() + rand.nextDouble();
            double d3 = ((double)rand.nextFloat() - 0.5D) * 1.5D;
            double d4 = ((double)rand.nextFloat() - 0.5D) * 1.5D;
            double d5 = ((double)rand.nextFloat() - 0.5D) * 1.5D;
            worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
        }
    }

    protected class MoveHelperController extends MovementController {
        public MoveHelperController(GhostEntity ghost) {
            super(ghost);
        }

        public void tick() {
            if (this.action == MovementController.Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.posX - GhostEntity.this.getPosX(), this.posY - GhostEntity.this.getPosY(), this.posZ - GhostEntity.this.getPosZ());
                double d0 = vector3d.length();
                if (d0 < GhostEntity.this.getBoundingBox().getAverageEdgeLength()) {
                    this.action = MovementController.Action.WAIT;
                    GhostEntity.this.setMotion(GhostEntity.this.getMotion().scale(0.5D));
                } else {
                    GhostEntity.this.setMotion(GhostEntity.this.getMotion().add(vector3d.scale(this.speed * 0.05D / d0)));
                    if (GhostEntity.this.getAttackTarget() == null) {
                        Vector3d vector3d1 = GhostEntity.this.getMotion();
                        GhostEntity.this.rotationYaw = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                    } else {
                        double d2 = GhostEntity.this.getAttackTarget().getPosX() - GhostEntity.this.getPosX();
                        double d1 = GhostEntity.this.getAttackTarget().getPosZ() - GhostEntity.this.getPosZ();
                        GhostEntity.this.rotationYaw = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                    }
                    GhostEntity.this.renderYawOffset = GhostEntity.this.rotationYaw;
                }
            }
        }
    }

    protected class MoveRandomGoal extends Goal {
        public MoveRandomGoal() {
            this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            return !GhostEntity.this.getMoveHelper().isUpdating() && GhostEntity.this.rand.nextInt(7) == 0;
        }

        public boolean shouldContinueExecuting() {
            return false;
        }

        public void tick() {
            BlockPos blockpos = GhostEntity.this.getPosition();
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.add(GhostEntity.this.rand.nextInt(15) - 7, GhostEntity.this.rand.nextInt(11) - 5, GhostEntity.this.rand.nextInt(15) - 7);
                if (GhostEntity.this.world.isAirBlock(blockpos1)) {
                    GhostEntity.this.moveController.setMoveTo((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);
                    if (GhostEntity.this.getAttackTarget() == null) {
                        GhostEntity.this.getLookController().setLookPosition((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }
        }
    }
}