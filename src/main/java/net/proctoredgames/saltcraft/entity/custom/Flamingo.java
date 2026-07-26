package net.proctoredgames.saltcraft.entity.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.ai.goal.EscapeDangerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class Flamingo extends AnimalEntity implements VariantHolder<Flamingo.Variant> {
    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(Flamingo.class, TrackedDataHandlerRegistry.INTEGER);
    private static final TrackedData<Boolean> STANDING_ON_ONE_LEG =
            DataTracker.registerData(Flamingo.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState standOnOneLegAnimationState = new AnimationState();

    public Flamingo(EntityType<? extends Flamingo> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
        builder.add(STANDING_ON_ONE_LEG, false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));
        this.goalSelector.add(2, new AnimalMateGoal(this, 1.0));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, stack -> stack.isIn(ItemTags.FISHES), false));
        this.goalSelector.add(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.add(5, new FlamingoGoToWaterGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(7, new LookAroundGoal(this));
        this.goalSelector.add(8, new WanderAroundGoal(this, 1.0));
        this.goalSelector.add(8, new FlamingoStandOnOneLegGoal(this));
    }

    public void setStandingOnOneLeg(boolean standingOnOneLeg) {
        this.dataTracker.set(STANDING_ON_ONE_LEG, standingOnOneLeg);
    }

    public boolean isStandingOnOneLeg() {
        return this.dataTracker.get(STANDING_ON_ONE_LEG);
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.setVariant(Variant.getRandom(world.getRandom()));
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isStandingOnOneLeg()) {
            standOnOneLegAnimationState.startIfNotRunning(this.age);
        } else {
            standOnOneLegAnimationState.stop();
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PARROT_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_PARROT_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_PARROT_DEATH;
    }

    @Override
    protected void playStepSound(BlockPos pos, net.minecraft.block.BlockState state) {
        this.playSound(SoundEvents.ENTITY_CHICKEN_STEP, 0.15F, 1.0F);
    }

    @Override
    protected float getSoundVolume() {
        return 0.4F;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isIn(ItemTags.FISHES);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("Variant", this.getVariant().id);
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.setVariant(Variant.byId(nbt.getInt("Variant")));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.FLAMINGO.create(world);
    }

    @Override
    public void setVariant(Variant variant) {
        this.dataTracker.set(VARIANT, variant.id);
    }

    @Override
    public Variant getVariant() {
        return Variant.byId(this.dataTracker.get(VARIANT));
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        this.setStandingOnOneLeg(false);
        return super.damage(source, amount);
    }

    @Override
    protected RegistryKey<LootTable> getLootTableId() {
        if (getVariant() == Variant.PINK) {
            return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Saltcraft.MOD_ID, "entities/flamingo"));
        } else {
            return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Saltcraft.MOD_ID, "entities/white_flamingo"));
        }
    }

    static class FlamingoGoToWaterGoal extends MoveToTargetPosGoal {
        private final Flamingo flamingo;

        public FlamingoGoToWaterGoal(Flamingo flamingo, double speed) {
            super(flamingo, speed, 24);
            this.flamingo = flamingo;
            this.lowestY = -1;
        }

        @Override
        public boolean shouldContinue() {
            return !this.flamingo.isTouchingWater() && this.tryingTime <= 1200 && this.isTargetPos(this.flamingo.getWorld(), this.targetPos);
        }

        @Override
        public boolean canStart() {
            return !this.flamingo.isTouchingWater() && !this.flamingo.isBaby() && super.canStart();
        }

        @Override
        public boolean shouldResetPath() {
            return this.tryingTime % 160 == 0;
        }

        @Override
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isOf(Blocks.WATER);
        }
    }

    static class FlamingoStandOnOneLegGoal extends Goal {
        private final Flamingo flamingo;
        private int standOnOneLegAnimationTick;

        public FlamingoStandOnOneLegGoal(Flamingo flamingo) {
            this.flamingo = flamingo;
        }

        @Override
        public void start() {
            this.standOnOneLegAnimationTick = this.getTickCount(100 + this.flamingo.getRandom().nextInt(400));
            this.flamingo.setStandingOnOneLeg(true);
        }

        @Override
        public void stop() {
            this.flamingo.setStandingOnOneLeg(false);
        }

        @Override
        public void tick() {
            this.standOnOneLegAnimationTick = Math.max(0, this.standOnOneLegAnimationTick - 1);
        }

        @Override
        public boolean canStart() {
            return flamingo.getRandom().nextInt(100) == 0 && !flamingo.isBaby() && !this.flamingo.limbAnimator.isLimbMoving();
        }

        @Override
        public boolean shouldContinue() {
            return (this.standOnOneLegAnimationTick > 0 && flamingo.isStandingOnOneLeg()) && !this.flamingo.limbAnimator.isLimbMoving();
        }
    }

    public enum Variant implements StringIdentifiable {
        PINK(0, "pink"),
        WHITE(1, "white");

        final int id;
        private final String name;

        Variant(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return this.id;
        }

        public static Variant byId(int id) {
            Variant[] values = values();
            return values[Math.max(0, Math.min(id, values.length - 1))];
        }

        @Override
        public String asString() {
            return this.name;
        }

        public static Variant getRandom(Random random) {
            return random.nextInt(8) == 0 ? WHITE : PINK;
        }
    }
}
