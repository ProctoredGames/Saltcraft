//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.entity.custom;

import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.proctoredgames.saltcraft.entity.ai.goal.CrystidAttackGoal;

public class Crystid extends Monster {
    private static final UUID SPEED_MODIFIER_BABY_UUID = UUID.fromString("B9766B59-9566-4402-BC1F-2EE2A276D836");
    private static final AttributeModifier SPEED_MODIFIER_BABY;
    private static final EntityDataAccessor<Boolean> DATA_BABY_ID;

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(Crystid.class, EntityDataSerializers.BOOLEAN);
//    private static final EntityDataAccessor<Integer> DATA_ID_TYPE_VARIANT =
//            SynchedEntityData.defineId(Crystid.class, EntityDataSerializers.INT);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    private boolean hasLimitedLife;
    private int limitedLifeTicks;


    @Nullable
    Mob owner;
    @Nullable
    private UUID ownerUUID;
    @Nullable


    public Crystid(EntityType<? extends Crystid> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new CrystidAttackGoal(this, 1.5, false));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Raider.class})).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, new CrystidCopyOwnerTargetGoal(this));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Player.class, true));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 15.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.2)
                .add(Attributes.ATTACK_DAMAGE, 3.0);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.getEntityData().define(DATA_BABY_ID, false);
        this.entityData.define(ATTACKING, false);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        if (pCompound.contains("LifeTicks")) {
            this.setLimitedLife(pCompound.getInt("LifeTicks"));
        }
        this.setBaby(pCompound.getBoolean("IsBaby"));
        if (pCompound.hasUUID("OwnerUUID")) {
            this.ownerUUID = pCompound.getUUID("OwnerUUID");
        }

    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        if (this.hasLimitedLife) {
            pCompound.putInt("LifeTicks", this.limitedLifeTicks);
        }
        pCompound.putBoolean("IsBaby", this.isBaby());
        UUID ownerId = this.owner != null ? this.owner.getUUID() : this.ownerUUID;
        if (ownerId != null) {
            pCompound.putUUID("OwnerUUID", ownerId);
        }

    }

    public boolean isBaby() {
        return (Boolean)this.getEntityData().get(DATA_BABY_ID);
    }

    public int getExperienceReward() {
        if (this.isBaby()) {
            this.xpReward = (int)((double)this.xpReward * 2.5);
        }

        return super.getExperienceReward();
    }

    public void setBaby(boolean pChildCrystid) {
        this.getEntityData().set(DATA_BABY_ID, pChildCrystid);
        if (!this.level().isClientSide) {
            AttributeInstance attributeinstance = this.getAttribute(Attributes.MOVEMENT_SPEED);
            assert attributeinstance != null;
            attributeinstance.removeModifier(SPEED_MODIFIER_BABY);
            if (pChildCrystid) {
                attributeinstance.addTransientModifier(SPEED_MODIFIER_BABY);
            }
        }

    }

    public void setOwner(Mob pOwner) {
        this.owner = pOwner;
        this.ownerUUID = pOwner.getUUID();
    }
    public void setLimitedLife(int pLimitedLifeTicks) {
        this.hasLimitedLife = true;
        this.limitedLifeTicks = pLimitedLifeTicks;
    }

    @Nullable
    public Mob getOwner() {
        // Re-resolve after a chunk/server reload, when setOwner() was never called on this instance
        if (this.owner == null && this.ownerUUID != null && this.level() instanceof ServerLevel serverLevel) {
            Entity entity = serverLevel.getEntity(this.ownerUUID);
            if (entity instanceof Mob mob) {
                this.owner = mob;
            }
        }
        return this.owner;
    }

    protected void updateWalkAnimation(float v) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(v * 6.0F, 1.0F);
        } else {
            f = 0.0F;
        }

        this.walkAnimation.update(f, 0.2F);
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isAttacking() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10; // Length in ticks of your animation
            attackAnimationState.start(this.tickCount);
        } else {
            --this.attackAnimationTimeout;
        }

        if(!this.isAttacking()) {
            attackAnimationState.stop();
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            this.setupAnimationStates();
            return;
        }
        if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20;
            this.hurt(this.damageSources().starve(), 1.0F);
        }
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void onSyncedDataUpdated(EntityDataAccessor<?> pKey) {
        if (DATA_BABY_ID.equals(pKey)) {
            this.refreshDimensions();
        }

        super.onSyncedDataUpdated(pKey);
    }

//    public boolean isSensitiveToWater() {
//        return true;
//    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.GLASS_STEP;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SUSPICIOUS_GRAVEL_BREAK;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.GLASS_BREAK;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.GLASS_PLACE;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return this.isBaby() ? 0.45F : 0.90F;
    }

    private class CrystidCopyOwnerTargetGoal extends TargetGoal {
        private final TargetingConditions copyOwnerTargeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();

        public CrystidCopyOwnerTargetGoal(PathfinderMob pMob) {
            super(pMob, false);
        }

        public boolean canUse() {
            Mob owner = Crystid.this.getOwner();
            return owner != null && owner.getTarget() != null && this.canAttack(owner.getTarget(), this.copyOwnerTargeting);
        }

        public void start() {
            Crystid.this.setTarget(Crystid.this.getOwner().getTarget());
            super.start();
        }
    }

    static {
        SPEED_MODIFIER_BABY = new AttributeModifier(SPEED_MODIFIER_BABY_UUID, "Baby speed boost", 0.5, Operation.MULTIPLY_BASE);
        DATA_BABY_ID = SynchedEntityData.defineId(Crystid.class, EntityDataSerializers.BOOLEAN);
    }
}
