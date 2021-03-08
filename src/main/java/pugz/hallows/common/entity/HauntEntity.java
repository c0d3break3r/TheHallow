package pugz.hallows.common.entity;

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

public class HauntEntity extends MonsterEntity {
    private static final DataParameter<Boolean> SCREAMING = EntityDataManager.createKey(HauntEntity.class, DataSerializers.BOOLEAN);
    private int attackTimer;

    public HauntEntity(EntityType<? extends HauntEntity> entity, World world) {
        super(entity, world);
        this.experienceValue = 4;
        this.setPathPriority(PathNodeType.WATER, -1.0F);
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
        this.dataManager.set(SCREAMING, living != null);
        super.setAttackTarget(living);
    }

    protected void registerData() {
        super.registerData();
        this.dataManager.register(SCREAMING, false);
    }

    @Nonnull
    public EntitySize getSize(Pose poseIn) {
        return super.getSize(poseIn).scale(1.2F);
    }

    public static AttributeModifierMap.MutableAttribute registerAttributes() {
        return MonsterEntity.func_234295_eP_()
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 30.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D)
                .createMutableAttribute(Attributes.ARMOR, 8.0D)
                .createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
                .createMutableAttribute(Attributes.MAX_HEALTH, 60.0D);
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

    public boolean isWaterSensitive() {
        return true;
    }

    protected void updateAITasks() {
        if (this.world.isDaytime()) {
            float f = this.getBrightness();
            if (f > 0.5F && this.world.canSeeSky(this.getPosition()) && this.rand.nextFloat() * 30.0F < (f - 0.4F) * 2.0F) {
                this.setAttackTarget(null);
                this.teleportRandomly();
            }
        }

        super.updateAITasks();
    }

    @Override
    public void livingTick() {
        super.livingTick();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    public boolean attackEntityFrom(DamageSource source, float amount) {
        this.attackTimer = 10;

        if (this.isInvulnerableTo(source)) return false;
        else if (source instanceof IndirectEntityDamageSource) {
            for (int i = 0; i < 64; ++i) {
                if (this.teleportRandomly()) return true;
            }

            return false;
        } else {
            boolean flag = super.attackEntityFrom(source, amount);
            if (!this.world.isRemote() && !(source.getTrueSource() instanceof LivingEntity) && this.rand.nextInt(10) != 0) {
                this.teleportRandomly();
            }
            return flag;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 4) {
            this.attackTimer = 10;
            this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
        } else {
            super.handleStatusUpdate(id);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public int getAttackTimer() {
        return this.attackTimer;
    }

    public boolean isScreaming() {
        return this.dataManager.get(SCREAMING);
    }

    public void setScreaming(boolean screaming) {
        this.dataManager.set(SCREAMING, screaming);
    }

    protected boolean teleportRandomly() {
        if (!this.world.isRemote() && this.isAlive()) {
            double d0 = this.getPosX() + (this.rand.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getPosY() + (double)(this.rand.nextInt(64) - 32);
            double d2 = this.getPosZ() + (this.rand.nextDouble() - 0.5D) * 64.0D;
            return this.teleportTo(d0, d1, d2);
        } else return false;
    }

    private boolean teleportToEntity(Entity entity) {
        Vector3d vector3d = new Vector3d(this.getPosX() - entity.getPosX(), this.getPosYHeight(0.5D) - entity.getPosYEye(), this.getPosZ() - entity.getPosZ());
        vector3d = vector3d.normalize();
        double d1 = this.getPosX() + (this.rand.nextDouble() - 0.5D) * 8.0D - vector3d.x * 16.0D;
        double d2 = this.getPosY() + (double)(this.rand.nextInt(16) - 8) - vector3d.y * 16.0D;
        double d3 = this.getPosZ() + (this.rand.nextDouble() - 0.5D) * 8.0D - vector3d.z * 16.0D;
        return this.teleportTo(d1, d2, d3);
    }

    private boolean teleportTo(double x, double y, double z) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

        while (blockpos$mutable.getY() > 0 && !this.world.getBlockState(blockpos$mutable).getMaterial().blocksMovement()) {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.world.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMovement();
        boolean flag1 = blockstate.getFluidState().isTagged(FluidTags.WATER);

        if (flag && !flag1) {
            if (!this.isSilent()) {
                this.world.playSound(null, this.prevPosX, this.prevPosY, this.prevPosZ, SoundEvents.ENTITY_ENDERMAN_TELEPORT, this.getSoundCategory(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            } return true;
        } else return false;
    }

    private static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity> {
        private final HauntEntity haunt;
        private PlayerEntity player;
        private int aggroTime;
        private int teleportTime;
        private final EntityPredicate field_220791_m;
        private final EntityPredicate field_220792_n = (new EntityPredicate()).setLineOfSiteRequired();

        public FindPlayerGoal(HauntEntity haunt) {
            super(haunt, PlayerEntity.class, false, false);
            this.haunt = haunt;
            this.field_220791_m = new EntityPredicate().setDistance(this.getTargetDistance()).setCustomPredicate((target) -> {
                return target.canEntityBeSeen(haunt);
            });
        }

        public boolean shouldExecute() {
            this.player = this.haunt.world.getClosestPlayer(this.field_220791_m, this.haunt);
            return this.player != null;
        }

        public void startExecuting() {
            this.aggroTime = 5;
            this.teleportTime = 0;
            this.haunt.setScreaming(true);
        }

        public void resetTask() {
            this.player = null;
            this.haunt.setScreaming(false);
            super.resetTask();
        }

        public boolean shouldContinueExecuting() {
            if (this.player != null) {
                if (!this.player.canEntityBeSeen(haunt)) {
                    return false;
                } else {
                    this.haunt.faceEntity(this.player, 15.0F, 15.0F);
                    return true;
                }
            } else {
                this.haunt.setScreaming(false);
                return this.nearestTarget != null && this.field_220792_n.canTarget(this.haunt, this.nearestTarget) || super.shouldContinueExecuting();
            }
        }

        public void tick() {
            if (this.haunt.getAttackTarget() == null) {
                super.setNearestTarget(null);
            }

            if (this.player != null) {
                if (--this.aggroTime <= 0) {
                    this.nearestTarget = this.player;
                    this.player = null;
                    super.startExecuting();
                }
            } else {
                if (this.nearestTarget != null && !this.haunt.isPassenger()) {
                    if (this.nearestTarget.canEntityBeSeen(haunt)) {
                        if (this.nearestTarget.getDistanceSq(this.haunt) < 16.0D) {
                            this.haunt.teleportRandomly();
                        }

                        this.teleportTime = 0;
                    } else if (this.nearestTarget.getDistanceSq(this.haunt) > 256.0D && this.teleportTime++ >= 30 && this.haunt.teleportToEntity(this.nearestTarget)) {
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
            this.setMutexFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
        }

        public boolean shouldExecute() {
            this.targetPlayer = this.haunt.getAttackTarget();
            if (!(this.targetPlayer instanceof PlayerEntity)) {
                return false;
            } else {
                double d0 = this.targetPlayer.getDistanceSq(this.haunt);
                return !(d0 > 256.0D) && this.targetPlayer.canEntityBeSeen(haunt);
            }
        }

        public void startExecuting() {
            this.haunt.getNavigator().clearPath();
        }

        public void tick() {
            this.haunt.getLookController().setLookPosition(this.targetPlayer.getPosX(), this.targetPlayer.getPosYEye(), this.targetPlayer.getPosZ());
            this.haunt.getNavigator().tryMoveToXYZ(this.targetPlayer.getPosX(), this.targetPlayer.getPosYEye(), this.targetPlayer.getPosZ(), 0.1F);
        }
    }
}