package net.proctoredgames.saltcraft.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.proctoredgames.saltcraft.Saltcraft;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class Crystid extends HostileEntity {
    private static final Identifier SPEED_MODIFIER_BABY_ID = Identifier.of(Saltcraft.MOD_ID, "baby_speed_boost");
    private static final EntityAttributeModifier SPEED_MODIFIER_BABY =
            new EntityAttributeModifier(SPEED_MODIFIER_BABY_ID, 0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);

    private static final TrackedData<Boolean> BABY =
            DataTracker.registerData(Crystid.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> ATTACK_WINDUP =
            DataTracker.registerData(Crystid.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    private boolean hasLimitedLife;
    private int limitedLifeTicks;

    @Nullable
    private MobEntity owner;
    @Nullable
    private UUID ownerUuid;

    public Crystid(EntityType<? extends Crystid> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(BABY, false);
        builder.add(ATTACK_WINDUP, false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(4, new net.proctoredgames.saltcraft.entity.ai.goal.CrystidAttackGoal(this, 1.5, false));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class).setGroupRevenge());
        this.targetSelector.add(2, new CrystidCopyOwnerTargetGoal(this));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("LifeTicks")) {
            this.setLimitedLife(nbt.getInt("LifeTicks"));
        }
        this.setBaby(nbt.getBoolean("IsBaby"));
        if (nbt.containsUuid("OwnerUUID")) {
            this.ownerUuid = nbt.getUuid("OwnerUUID");
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.hasLimitedLife) {
            nbt.putInt("LifeTicks", this.limitedLifeTicks);
        }
        nbt.putBoolean("IsBaby", this.isBaby());
        UUID ownerId = this.owner != null ? this.owner.getUuid() : this.ownerUuid;
        if (ownerId != null) {
            nbt.putUuid("OwnerUUID", ownerId);
        }
    }

    public boolean isBaby() {
        return this.dataTracker.get(BABY);
    }

    @Override
    protected int getXpToDrop() {
        int base = super.getXpToDrop();
        return this.isBaby() ? (int) (base * 2.5) : base;
    }

    public void setBaby(boolean baby) {
        this.dataTracker.set(BABY, baby);
        if (!this.getWorld().isClient) {
            EntityAttributeInstance speed = this.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
            speed.removeModifier(SPEED_MODIFIER_BABY);
            if (baby) {
                speed.addTemporaryModifier(SPEED_MODIFIER_BABY);
            }
        }
    }

    public void setOwner(MobEntity owner) {
        this.owner = owner;
        this.ownerUuid = owner.getUuid();
    }

    public void setLimitedLife(int limitedLifeTicks) {
        this.hasLimitedLife = true;
        this.limitedLifeTicks = limitedLifeTicks;
    }

    @Nullable
    public MobEntity getOwner() {
        // Re-resolve after a chunk/server reload, when setOwner() was never called on this instance
        if (this.owner == null && this.ownerUuid != null && this.getWorld() instanceof ServerWorld serverWorld) {
            Entity entity = serverWorld.getEntity(this.ownerUuid);
            if (entity instanceof MobEntity mob) {
                this.owner = mob;
            }
        }
        return this.owner;
    }

    @Override
    protected void updateLimbs(float posDelta) {
        float f = this.getPose() == EntityPose.STANDING ? Math.min(posDelta * 6.0F, 1.0F) : 0.0F;
        this.limbAnimator.updateLimbs(f, 0.2F);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttackWindup() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10;
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttackWindup()) {
            attackAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            setupAnimationStates();
            return;
        }
        if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20;
            this.damage(this.getDamageSources().starve(), 1.0F);
        }
    }

    public void setAttackWindup(boolean windup) {
        this.dataTracker.set(ATTACK_WINDUP, windup);
    }

    public boolean isAttackWindup() {
        return this.dataTracker.get(ATTACK_WINDUP);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (BABY.equals(data)) {
            this.calculateDimensions();
        }
        super.onTrackedDataSet(data);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.BLOCK_GLASS_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.BLOCK_SUSPICIOUS_GRAVEL_BREAK;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_GLASS_BREAK;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.BLOCK_GLASS_PLACE;
    }

    @Override
    protected void playStepSound(BlockPos pos, net.minecraft.block.BlockState state) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    @Override
    protected EntityDimensions getBaseDimensions(EntityPose pose) {
        return super.getBaseDimensions(pose).withEyeHeight(this.isBaby() ? 0.45F : 0.90F);
    }

    private static class CrystidCopyOwnerTargetGoal extends TrackTargetGoal {
        private final TargetPredicate copyOwnerTargeting = TargetPredicate.createNonAttackable().ignoreVisibility().ignoreDistanceScalingFactor();
        private final Crystid crystid;

        public CrystidCopyOwnerTargetGoal(Crystid crystid) {
            super(crystid, false);
            this.crystid = crystid;
        }

        @Override
        public boolean canStart() {
            MobEntity owner = this.crystid.getOwner();
            return owner != null && owner.getTarget() != null && this.canTrack(owner.getTarget(), this.copyOwnerTargeting);
        }

        @Override
        public void start() {
            this.crystid.setTarget(this.crystid.getOwner().getTarget());
            super.start();
        }
    }

}
