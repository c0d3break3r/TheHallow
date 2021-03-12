package pugz.hallows.common.entity;

import com.minecraftabnormals.abnormals_core.core.endimator.Endimation;
import com.minecraftabnormals.abnormals_core.core.endimator.entity.IEndimatedEntity;
import com.minecraftabnormals.abnormals_core.core.util.NetworkUtil;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumSet;

public class HauntEntity extends MonsterEntity implements IEndimatedEntity {
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.defineId(HauntEntity.class, DataSerializers.BOOLEAN);
    public static final Endimation ANGRY_ANIMATION = new Endimation(80);
    private int attackTimer;
    private int animationTick;
    public int teleportCooldown;
    private Endimation endimation = BLANK_ANIMATION;

    public HauntEntity(EntityType<? extends HauntEntity> entity, World world) {
        super(entity, world);
        this.xpReward = 4;
        this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
    }

    @Override
    public Endimation[] getEndimations() {
        return new Endimation[] {
                ANGRY_ANIMATION
        };
    }

    @Override
    public Endimation getPlayingEndimation() {
        return this.endimation;
    }

    @Override
    public void setPlayingEndimation(Endimation endimationToPlay) {
        this.endimation = endimationToPlay;
        this.setAnimationTick(0);
    }

    @Override
    public int getAnimationTick() {
        return this.animationTick;
    }

    @Override
    public void setAnimationTick(int animationTick) {
        this.animationTick = animationTick;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        //this.goalSelector.addGoal(0, new HauntEntity.StareGoal(this));
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 0.25D, false));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.5D, 32.0F));
        this.goalSelector.addGoal(4, new RandomWalkingGoal(this, 0.25D));
        this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
        this.goalSelector.addGoal(5, new LookAtGoal(this, LivingEntity.class, 3.0F, 1.0F));
        this.targetSelector.addGoal(1, new HauntEntity.FindPlayerGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, false));
    }

    public void setAttackTarget(@Nullable LivingEntity living) {
        this.entityData.set(SCREAMING, living != null);
        NetworkUtil.setPlayingAnimationMessage(this, HauntEntity.ANGRY_ANIMATION);
        super.setTarget(living);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.set(SCREAMING, false);
    }

    @Nonnull
    @Override
    public EntitySize getDimensions(Pose poseIn) {
        return super.getDimensions(poseIn).scale(1.2F);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.createMonsterAttributes()
                .add(Attributes.FOLLOW_RANGE, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.ARMOR, 8.0D)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .add(Attributes.MAX_HEALTH, 80.0D);
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
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    protected void customServerAiStep() {
        if (this.level.isDay()) {
            float f = this.getBrightness();
            if (f > 0.5F && this.level.canSeeSky(this.blockPosition()) && this.random.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setAttackTarget(null);
                this.teleportRandomly();
            }
        }

        super.customServerAiStep();
    }

    @Override
    public void baseTick() {
        super.baseTick();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    public boolean hurt(DamageSource source, float amount) {
        this.attackTimer = 10;

        if (this.isInvulnerableTo(source)) return false;
        else if (source instanceof IndirectEntityDamageSource) {
            for (int i = 0; i < 64; ++i) {
                if (this.teleportRandomly()) return true;
            }

            return false;
        } else {
            boolean flag = super.hurt(source, amount);
            if (!this.level.isClientSide() && !(source.getDirectEntity() instanceof LivingEntity) && this.random.nextInt(10) != 0) {
                this.teleportRandomly();
            }
            return flag;
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleEntityEvent(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleEntityEvent(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    public boolean isScreaming() {
        return this.entityData.get(SCREAMING);
    }

    public void setScreaming(boolean screaming) {
        this.entityData.set(SCREAMING, screaming);
    }

    protected boolean teleportRandomly() {
        if (!this.level.isClientSide() && this.isAlive()) {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (double)(this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        } else return false;
    }

    private boolean teleportToEntity(Entity entity) {
        Vector3d vector3d = new Vector3d(this.getX() - entity.getX(), this.getY(0.5D) - entity.getEyeY(), this.getZ() - entity.getZ());
        vector3d = vector3d.normalize();
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vector3d.x * 16.0D;
        double d2 = this.getY() + (double)(this.random.nextInt(16) - 8) - vector3d.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vector3d.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    private boolean teleport(double x, double y, double z) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

        while (blockpos$mutable.getY() > 0 && !this.level.getBlockState(blockpos$mutable).getMaterial().blocksMotion()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);

        if (flag && !flag1) {
            if (!this.isSilent()) {
                this.level.playSound(null, this.xOld, this.yOld, this.zOld, SoundEvents.ENDERMAN_TELEPORT, this.getSoundSource(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            } return true;
        } else return false;
    }

    private static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        private final HauntEntity haunt;
        private PlayerEntity player;
        private int aggroTime;
        private int teleportTime;
        private final EntityPredicate field_220791_m;
        private final EntityPredicate field_220792_n = new EntityPredicate().allowUnseeable();

        public FindPlayerGoal(HauntEntity haunt) {
            super(haunt, PlayerEntity.class, false, false);
            this.haunt = haunt;
            this.field_220791_m = new EntityPredicate().range(this.getFollowDistance()).selector((target) -> {
                return target.canSee(haunt);
            });
        }

        public boolean canUse() {
            this.player = this.haunt.level.getNearestPlayer(this.field_220791_m, this.haunt);
            return this.player != null;
        }

        public void start() {
            this.aggroTime = 5;
            this.teleportTime = 0;
            this.haunt.setScreaming(true);
        }

        public void stop() {
            this.player = null;
            this.haunt.setScreaming(false);
            super.stop();
        }

        public boolean canContinueToUse() {
            if (this.player != null) {
                if (!this.player.canSee(haunt)) {
                    return false;
                } else {
                    this.haunt.lookAt(this.player, 15.0F, 15.0F);
                    return true;
                }
            } else {
                this.haunt.setScreaming(false);
                return this.target != null && this.field_220792_n.test(this.haunt, this.target) || super.canUse();
            }
        }

        public void tick() {
            if (this.haunt.getTarget() == null) {
                super.setTarget(null);
            }

            if (this.player != null) {
                if (--this.aggroTime <= 0) {
                    this.target = this.player;
                    this.player = null;
                    super.start();
                }
            } else {
                if (this.target != null && !this.haunt.isPassenger()) {
                    if (this.target.canSee(haunt)) {
                        if (this.target.distanceToSqr(this.haunt) < 16.0D) {
                            this.haunt.teleportRandomly();
                        }

                        this.teleportTime = 0;
                    } else if (this.target.distanceToSqr(this.haunt) > 256.0D && this.teleportTime++ >= 30 && this.haunt.teleportToEntity(this.target)) {
                        this.teleportTime = 0;
                    }
                }
                super.tick();
            }
        }
    }

    private static class StareGoal extends Goal {
        private final HauntEntity haunt;
        private LivingEntity targetPlayer;

        public StareGoal(HauntEntity haunt) {
            this.haunt = haunt;
            this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean canUse() {
            this.targetPlayer = this.haunt.getTarget();
            if (!(this.targetPlayer instanceof PlayerEntity)) {
                return false;
            } else {
                double d0 = this.targetPlayer.distanceToSqr(this.haunt);
                return !(d0 > 256.0D) && this.targetPlayer.canSee(haunt);
            }
        }

        public void start() {
            this.haunt.getNavigation().recomputePath();
        }

        public void tick() {
            this.haunt.getLookControl().setLookAt(this.targetPlayer.getX(), this.targetPlayer.getEyeY(), this.targetPlayer.getZ());
            this.haunt.getNavigation().moveTo(this.targetPlayer.getX(), this.targetPlayer.getEyeY(), this.targetPlayer.getZ(), 0.1F);
        }
    }
}