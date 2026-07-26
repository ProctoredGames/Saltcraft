package net.proctoredgames.saltcraft.entity.custom;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.proctoredgames.saltcraft.entity.ai.goal.MirageAttackGoal;
import net.proctoredgames.saltcraft.entity.ai.goal.MirageNearestAttackableTargetGoal;
import net.proctoredgames.saltcraft.util.SandTeleport;
import org.jetbrains.annotations.Nullable;

public class Mirage extends HostileEntity {

    private static final TrackedData<Boolean> ATTACKING =
            DataTracker.registerData(Mirage.class, TrackedDataHandlerRegistry.BOOLEAN);

    private static final TrackedData<Boolean> HUNTING =
            DataTracker.registerData(Mirage.class, TrackedDataHandlerRegistry.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public Mirage(EntityType<? extends Mirage> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(ATTACKING, false);
        builder.add(HUNTING, false);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new MirageAttackGoal(this, 1.5, false, 2.5));
        this.targetSelector.add(2, new MirageNearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, SaltMage.class, false));
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 80.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 35.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_BLAZE_SHOOT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.BLOCK_SAND_BREAK;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            this.setupAnimationStates();
            if (!isInvisible()) {
                spawnAmbientParticles();
            }
            return;
        }

        if (this.getWorld().isNight()) {
            // Despawn the entity if it's nighttime
            this.discard();
            return;
        }

        PlayerEntity nearestPlayer = this.getWorld().getClosestPlayer(this, 100);
        if (nearestPlayer != null && !(nearestPlayer.isSpectator() || nearestPlayer.isCreative())) {
            this.getLookControl().lookAt(nearestPlayer.getX(), nearestPlayer.getY(), nearestPlayer.getZ());
            // The mirage waits invisible and invulnerable; looking directly at it triggers
            // it to yank the player towards itself and start hunting
            if (distanceTo(nearestPlayer) < 40) {
                if (!this.isHunting()) {
                    if (!SandTeleport.isEntityValidTarget(nearestPlayer, this)) {
                        this.setInvisible(true);
                        this.setInvulnerable(true);
                        setHunting(false);
                    } else {
                        teleportTowardsSelf(nearestPlayer);
                        this.setInvisible(false);
                        this.setInvulnerable(false);
                        setHunting(true);
                    }
                }
            } else {
                setHunting(false);
                this.setInvisible(false);
                this.setInvulnerable(false);
            }
        } else {
            setHunting(false);
            this.setInvisible(false);
            this.setInvulnerable(false);
        }
    }

    private void teleportTowardsSelf(net.minecraft.entity.Entity target) {
        double selfX = this.getX();
        double selfY = this.getY();
        double selfZ = this.getZ();

        float yaw = this.getYaw();
        float pitch = this.getPitch();

        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);

        double dirX = -Math.sin(radYaw) * Math.cos(radPitch);
        double dirZ = Math.cos(radYaw) * Math.cos(radPitch);

        double moveDistance = 5;
        BlockPos spawnPos = new BlockPos((int) (selfX + dirX * moveDistance), (int) selfY, (int) (selfZ + dirZ * moveDistance));

        spawnPos = SandTeleport.determineGroundAdjustedPosition(spawnPos, target.getWorld());

        if (target.getWorld().isClient) {
            spawnTeleportParticles(target);
        }
        target.setInvisible(true);

        target.requestTeleport(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
        if (target.getWorld().isClient) {
            spawnTeleportParticles(target);
        }
        target.setInvisible(false);
    }

    private void spawnTeleportParticles(net.minecraft.entity.Entity entity) {
        Vec3d position = entity.getPos();
        for (int i = 0; i < entity.getWidth() * entity.getHeight() * 10; i++) {
            double x = position.x + random.nextDouble() * entity.getWidth() - (entity.getWidth()) / 2;
            double y = position.y + entity.getHeight() * random.nextDouble();
            double z = position.z + random.nextDouble() * entity.getWidth() - (entity.getWidth()) / 2;

            entity.getWorld().addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, Blocks.SAND.getDefaultState()), x, y, z, 0, 0, 0);
        }
    }

    private void spawnAmbientParticles() {
        Vec3d position = this.getPos();
        double x = position.x + random.nextDouble() - 0.5;
        double y = position.y + this.getHeight() * 0.25 + (this.getHeight() * 0.5) * random.nextDouble();
        double z = position.z + random.nextDouble() - 0.5;

        this.getWorld().addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, Blocks.SAND.getDefaultState()), x, y, z, 0, 0, 0);
    }

    public void setAttackWindup(boolean attacking) {
        this.dataTracker.set(ATTACKING, attacking);
    }

    public boolean isAttackWindup() {
        return this.dataTracker.get(ATTACKING);
    }

    public void setHunting(boolean hunting) {
        this.dataTracker.set(HUNTING, hunting);
    }

    public boolean isHunting() {
        return this.dataTracker.get(HUNTING);
    }

    @Override
    public boolean isFireImmune() {
        return true;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public boolean canHaveStatusEffect(StatusEffectInstance effect) {
        return false;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean canBeHitByProjectile() {
        return this.isHunting();
    }

    @Override
    public boolean isPushedByFluids() {
        return false;
    }

    @Override
    public boolean canFreeze() {
        return false;
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            --this.idleAnimationTimeout;
        }

        if (this.isAttackWindup() && attackAnimationTimeout <= 0) {
            attackAnimationTimeout = 10; // Length in ticks of the animation
            attackAnimationState.start(this.age);
        } else {
            --this.attackAnimationTimeout;
        }

        if (!this.isAttackWindup()) {
            attackAnimationState.stop();
        }
    }
}
