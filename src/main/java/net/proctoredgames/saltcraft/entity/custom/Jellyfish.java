package net.proctoredgames.saltcraft.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.VariantHolder;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.proctoredgames.saltcraft.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Jellyfish extends FishEntity implements VariantHolder<Jellyfish.Variant> {
    private static final TrackedData<Integer> VARIANT =
            DataTracker.registerData(Jellyfish.class, TrackedDataHandlerRegistry.INTEGER);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public Jellyfish(EntityType<? extends Jellyfish> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return FishEntity.createFishAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 20D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 24D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(VARIANT, 0);
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new FleeEntityGoal<>(this, TurtleEntity.class, 6F, 2, 1.4));
    }

    @Nullable
    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.setVariant(Variant.getRandom(world.getRandom()));
        return super.initialize(world, difficulty, spawnReason, entityData);
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

    @Override
    public void setVariant(Variant variant) {
        this.dataTracker.set(VARIANT, variant.id);
    }

    @Override
    public Variant getVariant() {
        return Variant.byId(this.dataTracker.get(VARIANT));
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return effect.getEffectType() != StatusEffects.POISON;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (source.getSource() instanceof TurtleEntity && this.distanceTo(source.getSource()) > 2) {
            return false;
        }
        return super.damage(source, amount);
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
    }

    @Override
    public void tickMovement() {
        super.tickMovement();
        if (this.isAlive() && !this.getWorld().isClient) {
            // The jellyfish stings everything except creative players and turtles
            List<MobEntity> nearby = this.getWorld().getEntitiesByClass(MobEntity.class, this.getBoundingBox().expand(0.3),
                    mob -> mob != this && mob.getType() != EntityType.TURTLE);
            for (MobEntity mob : nearby) {
                if (mob.isAlive()) {
                    sting(mob);
                }
            }
        }
    }

    private void sting(net.minecraft.entity.LivingEntity target) {
        if (target.getY() < this.getY() - 0.2) {
            target.damage(this.getDamageSources().mobAttack(this), 2.0F);
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0), this);
        }
    }

    @Override
    public void onPlayerCollision(net.minecraft.entity.player.PlayerEntity player) {
        if (player instanceof net.minecraft.server.network.ServerPlayerEntity && player.getY() < this.getY() - 0.2) {
            player.damage(this.getDamageSources().mobAttack(this), 2.0F);
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 60, 0), this);
        }
    }

    @Override
    public ItemStack getBucketItem() {
        return new ItemStack(ModItems.JELLYFISH_BUCKET);
    }

    @Override
    public SoundEvent getBucketFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL_AXOLOTL;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_PUFFER_FISH_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_SLIME_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_SLIME_DEATH;
    }

    @Override
    protected SoundEvent getFlopSound() {
        return SoundEvents.ENTITY_TADPOLE_FLOP;
    }

    @Override
    public boolean shouldRender(double distance) {
        return distance < 512;
    }

    public enum Variant implements StringIdentifiable {
        BLUE(0, "blue"),
        CYAN(1, "cyan"),
        ORANGE(2, "orange"),
        PINK(3, "pink"),
        PURPLE(4, "purple"),
        RED(5, "red");

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
            return byId(random.nextInt(values().length));
        }
    }
}
