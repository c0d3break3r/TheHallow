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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.EnumSet;
import java.util.Random;

public class GhostEntity extends MonsterEntity {
    protected static final DataParameter<Byte> GHOST_FLAGS = EntityDataManager.defineId(GhostEntity.class, DataSerializers.BYTE);

    public GhostEntity(EntityType<? extends GhostEntity> entity, World world) {
        super(entity, world);
        this.moveControl = new GhostEntity.MoveHelperController(this);
        this.xpReward = 2;
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
    }

    @Override
    public void move(MoverType typeIn, Vector3d pos) {
        super.move(typeIn, pos);
        this.checkInsideBlocks();
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
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this, PlayerEntity.class).setAlertOthers(GhostEntity.class));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.2D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.set(GHOST_FLAGS, (byte)0);
    }

    @Nonnull
    @Override
    public EntitySize getDimensions(Pose poseIn) {
        return super.getDimensions(poseIn).scale(1.2F);
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (super.doHurtTarget(entityIn)) {
            if (entityIn instanceof LivingEntity) ((LivingEntity) entityIn).addEffect(new EffectInstance(Effects.BLINDNESS, 25, 0));
            return true;
        } else return false;
    }

    @Override
    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
    }

    public static boolean canGhostSpawn(EntityType<? extends GhostEntity> typeIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn) {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.VEX_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.GHAST_SCREAM;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_SCREAM;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        super.playSound(SoundEvents.VEX_AMBIENT, 0.15F, 1.0F);
    }

    @Nonnull
    @Override
    public CreatureAttribute getMobType() {
        return CreatureAttribute.UNDEAD;
    }

    @Override
    public float getBrightness() {
        return 1.0F;
    }

    @Override
    public boolean causeFallDamage(float distance, float damageMultiplier) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    private boolean getGhostFlag(int mask) {
        int i = this.entityData.get(GHOST_FLAGS);
        return (i & mask) != 0;
    }

    private void setGhostFlag(int mask, boolean value) {
        int i = this.entityData.get(GHOST_FLAGS);
        if (value) {
            i = i | mask;
        } else {
            i = i & ~mask;
        }

        this.entityData.set(GHOST_FLAGS, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.getGhostFlag(1);
    }

    public void setCharging(boolean charging) {
        this.setGhostFlag(1, charging);
    }

    protected class ChargeAttackGoal extends Goal {
        public ChargeAttackGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            if (GhostEntity.this.getTarget() != null && !GhostEntity.this.getMoveControl().hasWanted() && GhostEntity.this.random.nextInt(7) == 0) {
                return GhostEntity.this.distanceToSqr(GhostEntity.this.getTarget()) > 4.0D;
            } else {
                return false;
            }
        }

        public boolean canUse() {
            return GhostEntity.this.getMoveControl().hasWanted() && GhostEntity.this.isCharging() && GhostEntity.this.getTarget() != null && GhostEntity.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = GhostEntity.this.getTarget();
            Vector3d vector3d = livingentity.getEyePosition(1.0F);
            GhostEntity.this.getMoveControl().setWantedPosition(vector3d.x, vector3d.y, vector3d.z, 1.0D);
            GhostEntity.this.setCharging(true);
            GhostEntity.this.playSound(SoundEvents.GHAST_SCREAM, 1.0F, 1.0F);
        }

        public void stop() {
            GhostEntity.this.setCharging(false);
        }

        public void tick() {
            LivingEntity livingentity = GhostEntity.this.getTarget();
            if (GhostEntity.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                GhostEntity.this.doHurtTarget(livingentity);
                GhostEntity.this.setCharging(false);
            } else {
                double d0 = GhostEntity.this.distanceToSqr(livingentity);
                if (d0 < 9.0D) {
                    Vector3d vector3d = livingentity.getEyePosition(1.0F);
                    GhostEntity.this.getMoveControl().setWantedPosition(vector3d.x, vector3d.y, vector3d.z, 1.0D);
                }

                if (d0 < 6.0D) {
                    GhostEntity.this.addEffect(new EffectInstance(Effects.INVISIBILITY, 25, 0, true, false));
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
            if (this.operation == MovementController.Action.MOVE_TO) {
                Vector3d vector3d = new Vector3d(this.getWantedX() - GhostEntity.this.getX(), this.getWantedY() - GhostEntity.this.getY(), this.getWantedZ() - GhostEntity.this.getZ());
                double d0 = vector3d.length();
                if (d0 < GhostEntity.this.getBoundingBox().getSize()) {
                    this.operation = MovementController.Action.WAIT;
                    GhostEntity.this.setDeltaMovement(GhostEntity.this.getDeltaMovement().scale(0.5D));
                } else {
                    GhostEntity.this.setDeltaMovement(GhostEntity.this.getDeltaMovement().add(vector3d.scale(this.speedModifier * 0.05D / d0)));
                    if (GhostEntity.this.getTarget() == null) {
                        Vector3d vector3d1 = GhostEntity.this.getDeltaMovement();
                        GhostEntity.this.yRot = -((float) MathHelper.atan2(vector3d1.x, vector3d1.z)) * (180F / (float)Math.PI);
                    } else {
                        double d2 = GhostEntity.this.getTarget().getX() - GhostEntity.this.getX();
                        double d1 = GhostEntity.this.getTarget().getZ() - GhostEntity.this.getZ();
                        GhostEntity.this.yRot = -((float)MathHelper.atan2(d2, d1)) * (180F / (float)Math.PI);
                    }
                    GhostEntity.this.yo = GhostEntity.this.yRot;
                }
            }
        }
    }

    protected class MoveRandomGoal extends Goal {
        public MoveRandomGoal() {
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        public boolean canUse() {
            return !GhostEntity.this.getMoveControl().hasWanted() && GhostEntity.this.random.nextInt(7) == 0;
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void tick() {
            BlockPos blockpos = GhostEntity.this.blockPosition();
            for (int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.offset(GhostEntity.this.random.nextInt(15) - 7, GhostEntity.this.random.nextInt(11) - 5, GhostEntity.this.random.nextInt(15) - 7);
                if (GhostEntity.this.level.isEmptyBlock(blockpos1)) {
                    GhostEntity.this.getMoveControl().setWantedPosition((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 0.25D);
                    if (GhostEntity.this.getTarget() == null) {
                        GhostEntity.this.getLookControl().setLookAt((double) blockpos1.getX() + 0.5D, (double) blockpos1.getY() + 0.5D, (double) blockpos1.getZ() + 0.5D, 180.0F, 20.0F);
                    }
                    break;
                }
            }
        }
    }
}