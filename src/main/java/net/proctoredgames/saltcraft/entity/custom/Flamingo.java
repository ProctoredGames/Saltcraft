package net.proctoredgames.saltcraft.entity.custom;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.proctoredgames.saltcraft.entity.ModEntities;

import javax.annotation.Nullable;
import java.util.function.IntFunction;

public class Flamingo extends Animal implements VariantHolder<Flamingo.Variant> {
    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID;

    public Flamingo(EntityType<? extends Flamingo> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
    }

    private static final EntityDataAccessor<Boolean> STANDING_ON_ONE_LEG =
            SynchedEntityData.defineId(Flamingo.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState standOnOneLegAnimationState = new AnimationState();
    public int standOnOneLegAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    public void setStandingOnOneLeg(boolean standingOnOneLeg) {
        this.entityData.set(STANDING_ON_ONE_LEG, standingOnOneLeg);
    }

    public boolean isStandingOnOneLeg() {
        return this.entityData.get(STANDING_ON_ONE_LEG);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RandomSource $$5 = pLevel.getRandom();
        this.setVariant(Flamingo.Variant.getRandom($$5));
        if (pSpawnData == null) {
            pSpawnData = new AgeableMob.AgeableMobGroupData(false);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, (SpawnGroupData)pSpawnData, pDataTag);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        if(this.isStandingOnOneLeg()) {
            standOnOneLegAnimationState.startIfStopped(this.tickCount);
        } else {
            standOnOneLegAnimationState.stop();
        }

    }
    @Override
    public double getFluidJumpThreshold() {
        return 1.0;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, Ingredient.of(ItemTags.FISHES), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new FlamingoGoToWaterGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(8, new FlamingoStandOnOneLegGoal(this, 0.0));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0).add(Attributes.MOVEMENT_SPEED, 0.2);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.PARROT_AMBIENT;
    }

    protected net.minecraft.sounds.SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.PARROT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.PARROT_DEATH;
    }

    protected void playStepSound(BlockPos pPos, BlockState pBlock) {
        this.playSound(SoundEvents.CHICKEN_STEP, 0.15F, 1.0F);
    }

    protected float getSoundVolume() {
        return 0.4F;
    }

    public boolean isFood(ItemStack pStack) {
        return pStack.is(ItemTags.FISHES);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return this.isBaby() ? 0.95F : 1.90F;
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(STANDING_ON_ONE_LEG, false);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant().id);
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(Flamingo.Variant.byId(pCompound.getInt("Variant")));
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return (Flamingo) ModEntities.FLAMINGO.get().create(pLevel);
    }

    @Override
    public void setVariant(Variant pVariant) {
        this.entityData.set(DATA_VARIANT_ID, pVariant.id);
    }

    @Override
    public Variant getVariant() {
        return Flamingo.Variant.byId((Integer)this.entityData.get(DATA_VARIANT_ID));
    }

    static class FlamingoGoToWaterGoal extends MoveToBlockGoal {
        private static final int GIVE_UP_TICKS = 1200;
        private final Flamingo flamingo;

        public FlamingoGoToWaterGoal(Flamingo pFlamingo, double pSpeedModifier) {
            super(pFlamingo, pSpeedModifier, 24);
            this.flamingo = pFlamingo;
            this.verticalSearchStart = -1;
        }

        public boolean canContinueToUse() {
            return !this.flamingo.isInWater() && this.tryTicks <= 1200 && this.isValidTarget(this.flamingo.level(), this.blockPos);
        }

        public boolean canUse() {
            return !this.flamingo.isInWater() && !this.flamingo.isBaby() && super.canUse();
        }

        public boolean shouldRecalculatePath() {
            return this.tryTicks % 160 == 0;
        }

        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            return pLevel.getBlockState(pPos).is(Blocks.WATER);
        }
    }

    static class FlamingoStandOnOneLegGoal extends Goal {
        private final Flamingo flamingo;
        private int standOnOneLegAnimationTick;

        public FlamingoStandOnOneLegGoal(Flamingo pFlamingo, double pSpeedModifier){
            this.flamingo = pFlamingo;
        }

        public void start() {
            this.standOnOneLegAnimationTick = this.adjustedTickDelay(100+this.flamingo.getRandom().nextInt(400));
            this.flamingo.setStandingOnOneLeg(true);
        }

        public void stop() {
            this.flamingo.setStandingOnOneLeg(false);
        }

        public void tick() {
            this.standOnOneLegAnimationTick = Math.max(0, this.standOnOneLegAnimationTick - 1);
        }
        @Override
        public boolean canUse() {
            return flamingo.getRandom().nextInt(100) == 0 && !flamingo.isBaby() && !this.flamingo.walkAnimation.isMoving();
        }

        @Override
        public boolean canContinueToUse(){
            return (this.standOnOneLegAnimationTick >0 && flamingo.isStandingOnOneLeg()) && !this.flamingo.walkAnimation.isMoving();
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        this.setStandingOnOneLeg(false);
        return super.hurt(source, amount);
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        if(getColor() == Variant.PINK.getId()){
            return new ResourceLocation("saltcraft", "entities/flamingo");

        } else{
            return new ResourceLocation("saltcraft", "entities/white_flamingo");
        }

    }

    public int getColor() {
        return (this.entityData.get(DATA_VARIANT_ID) & 15);
    }

    static {
        DATA_VARIANT_ID = SynchedEntityData.defineId(Flamingo.class, EntityDataSerializers.INT);
    }

    public static enum Variant implements StringRepresentable {
        PINK(0, "pink"),
        WHITE(1, "white");

        public static final Codec<Variant> CODEC = StringRepresentable.fromEnum(Variant::values);
        private static final IntFunction<Variant> BY_ID = ByIdMap.continuous(Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        private Variant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public int getId() {
            return this.id;
        }

        public static Variant byId(int pId) {
            return (Variant)BY_ID.apply(pId);
        }

        public String getSerializedName() {
            return this.name;
        }

        public static Variant getRandom(RandomSource pRandom){
            int number = pRandom.nextInt(8);
            if(number == 0){
                return WHITE;
            } else{
                return PINK;
            }
        }

    }

}
