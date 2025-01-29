package net.proctoredgames.saltcraft.entity.custom;

import com.mojang.serialization.Codec;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;

public class Jellyfish extends AbstractFish implements VariantHolder<Jellyfish.Variant>{

    private static final EntityDataAccessor<Integer> DATA_VARIANT_ID;

    private static final EntityDataAccessor<Boolean> FROM_BUCKET;
    private static final Predicate<LivingEntity> SCARY_MOB;
    static final TargetingConditions targetingConditions;

    public Jellyfish(EntityType<? extends Jellyfish> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 1.0F, 0F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    @javax.annotation.Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @javax.annotation.Nullable SpawnGroupData pSpawnData, @javax.annotation.Nullable CompoundTag pDataTag) {
        RandomSource $$5 = pLevel.getRandom();
        this.setVariant(Jellyfish.Variant.getRandom($$5));
        if (pSpawnData == null) {
            pSpawnData = new AgeableMob.AgeableMobGroupData(false);
        }

        return super.finalizeSpawn(pLevel, pDifficulty, pReason, (SpawnGroupData)pSpawnData, pDataTag);
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant().id);
        pCompound.putBoolean("FromBucket", this.fromBucket());
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(Jellyfish.Variant.byId(pCompound.getInt("Variant")));
        this.setFromBucket(pCompound.getBoolean("FromBucket"));
    }

    @Override
    public void setVariant(Variant pVariant) {
        this.entityData.set(DATA_VARIANT_ID, pVariant.id);
    }

    @Override
    public Jellyfish.Variant getVariant() {
        return Jellyfish.Variant.byId((Integer)this.entityData.get(DATA_VARIANT_ID));
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Turtle.class, 6F, 2, 1.4));
        this.goalSelector.addGoal(2, new FollowBoatGoal(this));}

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 20D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }


    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_VARIANT_ID, 0);
        this.entityData.define(FROM_BUCKET, false);
    }

    public void aiStep() {
        super.aiStep();
        if (this.isAlive()) {
            List<Mob> $$0 = this.level().getEntitiesOfClass(Mob.class, this.getBoundingBox().inflate(0.3), (p_149013_) -> {
                return targetingConditions.test(this, p_149013_);
            });
            Iterator var2 = $$0.iterator();

            while(var2.hasNext()) {
                Mob $$1 = (Mob)var2.next();
                if ($$1.isAlive()) {
                    //the jellyfish stings SCARY_MOB mobs. That means everything except for creative mode players
                    this.touch($$1);
                }
            }
        }

    }

    private void touch(Mob pMob) {
        if (pMob.position().y<this.position().y-0.2) {
            pMob.hurt(this.damageSources().mobAttack(this), (float)(2));
            pMob.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }

    }

    public void playerTouch(Player pEntity) {
        if (pEntity instanceof ServerPlayer && pEntity.position().y<this.position().y-0.2) {
            pEntity.hurt(this.damageSources().mobAttack(this), (float)(2));
            pEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 60, 0), this);
        }

    }

    @Override
    public double getFluidJumpThreshold() {
        return 1.0;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.PUFFER_FISH_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.SLIME_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SLIME_DEATH;
    }

    public boolean canBreatheUnderwater() {
        return true;
    }

    public boolean isPushedByFluid() {
        return true;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.JELLYFISH_BUCKET.get());
    }

    @Override
    public SoundEvent getPickupSound() {
        return SoundEvents.BUCKET_FILL_AXOLOTL;
    }

    protected SoundEvent getFlopSound() {
        return SoundEvents.TADPOLE_FLOP;
    }

    @Override
    public boolean shouldRenderAtSqrDistance(double pDistance) {
        return pDistance < 512;
    }

    private void usePlayerItem(Player pPlayer, ItemStack pStack) {
        if (!pPlayer.getAbilities().instabuild) {
            pStack.shrink(1);
        }

    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("saltcraft", "entities/jellyfish");
    }

    static {
        FROM_BUCKET = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.BOOLEAN);
        SCARY_MOB = (p_289442_) -> {
            if (p_289442_ instanceof Player && ((Player)p_289442_).isCreative()) {
                return false;
            } else {
                return p_289442_.getType() != EntityType.TURTLE;
            }
        };
        targetingConditions = TargetingConditions.forNonCombat().ignoreInvisibilityTesting().ignoreLineOfSight().selector(SCARY_MOB);
        DATA_VARIANT_ID = SynchedEntityData.defineId(Jellyfish.class, EntityDataSerializers.INT);
    }

    public static enum Variant implements StringRepresentable {
        BLUE(0, "blue"),
        CYAN(1, "cyan"),
        ORANGE(2, "orange"),
        PINK(3, "pink"),
        PURPLE(4, "purple"),
        RED(5, "red");

        public static final Codec<Jellyfish.Variant> CODEC = StringRepresentable.fromEnum(Jellyfish.Variant::values);
        private static final IntFunction<Jellyfish.Variant> BY_ID = ByIdMap.continuous(Jellyfish.Variant::getId, values(), ByIdMap.OutOfBoundsStrategy.CLAMP);
        final int id;
        private final String name;

        private Variant(int pId, String pName) {
            this.id = pId;
            this.name = pName;
        }

        public int getId() {
            return this.id;
        }

        public static Jellyfish.Variant byId(int pId) {
            return (Jellyfish.Variant)BY_ID.apply(pId);
        }

        public String getSerializedName() {
            return this.name;
        }

        public static Jellyfish.Variant getRandom(RandomSource pRandom){
            int number = pRandom.nextInt(0,6);
            return switch (number) {
                case 0 -> BLUE;
                case 1 -> CYAN;
                case 2 -> ORANGE;
                case 3 -> PINK;
                case 4 -> PURPLE;
                case 5 -> RED;
                default -> BLUE;
            };
        }

    }
}