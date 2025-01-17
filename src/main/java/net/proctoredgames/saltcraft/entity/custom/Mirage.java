//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.entity.custom;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.GameEvent.Context;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.fluids.FluidType;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.ai.goal.CrystidAttackGoal;
import net.proctoredgames.saltcraft.entity.ai.goal.MirageAttackGoal;
import net.proctoredgames.saltcraft.entity.ai.goal.MirageNearestAttackableTargetGoal;
import net.proctoredgames.saltcraft.thirst.PlayerThirstProvider;
import org.jetbrains.annotations.Nullable;

public class Mirage extends Monster{

    private static final EntityDataAccessor<Boolean> ATTACKING =
            SynchedEntityData.defineId(Mirage.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> HUNTING =
            SynchedEntityData.defineId(Mirage.class, EntityDataSerializers.BOOLEAN);

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState attackAnimationState = new AnimationState();
    public int attackAnimationTimeout = 0;

    public Mirage(EntityType<? extends Mirage> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACKING, false); // Set a default value for ATTACKING
        this.entityData.define(HUNTING, false);
    }

    protected void registerGoals() {
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new MirageAttackGoal(this, 1.5, false, 2.5));
        this.targetSelector.addGoal(3, new MirageNearestAttackableTargetGoal(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 80.0)
                .add(Attributes.FOLLOW_RANGE, 35.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 5.0);
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.BLAZE_SHOOT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.SAND_BREAK;
    }

    public void tick() {
        super.tick(); // Call to the parent class's tick method

        if (this.level().isClientSide) {
            this.setupAnimationStates();
            if(!isInvisible()){
                spawnAmbientParticles();
            }
        } else{
            if (this.level().isNight()) {
                // Despawn the entity if it's nighttime
                this.remove(Entity.RemovalReason.KILLED);
            }
        }

        // Get the nearest player
        Player nearestPlayer = this.level().getNearestPlayer(this, 100);
        if (nearestPlayer != null && !(nearestPlayer.isSpectator() || nearestPlayer.isCreative())) {
            this.getLookControl().setLookAt(nearestPlayer.getX(), nearestPlayer.getY(), nearestPlayer.getZ());
            // Check if the player is looking at the entity
            if(distanceTo(nearestPlayer)<40){
                if(!(this.isHunting())){
                    if (!isEntityLookingAtMe(nearestPlayer, this)) {
                        this.setInvisible(true); // Invisibility when not looking
                        this.setInvulnerable(true);
                        setHunting(false);
                    } else {
                        teleportTo(nearestPlayer, this);
                        this.setInvisible(false);
                        this.setInvulnerable(false);
                        setHunting(true);
                    }
                }
            } else{
                setHunting(false);
                this.setInvisible(false);
                this.setInvulnerable(false);
            }
        } else{
            setHunting(false);
            this.setInvisible(false);
            this.setInvulnerable(false);
        }
    }

    boolean isEntityLookingAtMe(LivingEntity pEntity, LivingEntity pCastingEntity) {
        Vec3 vec3 = pEntity.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(pCastingEntity.getX() - pEntity.getX(), pCastingEntity.getEyeY() - pEntity.getEyeY(), pCastingEntity.getZ() - pEntity.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? pEntity.hasLineOfSight(this) : false;
    }

    public void teleportTo(Entity pEntity, Entity pCastingEntity) {
        // Get the current position of the mob
        double selfX = pCastingEntity.getX();
        double selfY = pCastingEntity.getY();
        double selfZ = pCastingEntity.getZ();

        // Get the mob's yaw (horizontal rotation) and pitch (vertical rotation)
        float yaw = pCastingEntity.getYRot(); // This is the mob's horizontal rotation
        float pitch = pCastingEntity.getXRot(); // This is the mob's vertical rotation

        // Calculate the direction vector based on the mob's yaw and pitch
        double radYaw = Math.toRadians(yaw); // Convert yaw to radians
        double radPitch = Math.toRadians(pitch); // Convert pitch to radians

        // Use trigonometry to calculate the direction
        double dirX = -Math.sin(radYaw) * Math.cos(radPitch); // X direction
        double dirY = -Math.sin(radPitch); // Y direction (vertical)
        double dirZ = Math.cos(radYaw) * Math.cos(radPitch); // Z direction

        // Scale the direction to 5 blocks (this can be adjusted)
        double moveDistance = 5;
        double targetX = selfX + dirX * moveDistance;
        double targetY = selfY;
        double targetZ = selfZ + dirZ * moveDistance;

        // Start with the target position (adjust Y to ground level)
        BlockPos spawnPos = new BlockPos((int) targetX, (int) targetY, (int) targetZ);

        // Move the position down until we find a solid block (or hit the minimum build height)
        while (pEntity.level().getBlockState(spawnPos.below()).isAir() && spawnPos.below().getY() > pEntity.level().getMinBuildHeight()) {
            spawnPos = spawnPos.below();
        }

        // Move the position up until we find an air block or reach the maximum build height
        while (!pEntity.level().getBlockState(spawnPos).isAir() && spawnPos.getY() < pEntity.level().getMaxBuildHeight()) {
            spawnPos = spawnPos.above();
        }

        spawnTeleportParticles(pEntity);

        // Teleport the player to the new position
        pEntity.teleportTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
        spawnTeleportParticles(pEntity);
    }

    private void spawnTeleportParticles(Entity pEntity) {
        RandomSource random = RandomSource.create();
        Vec3 position = pEntity.position();
        for(int i = 0; i<20; i++){
            double x = position.x+random.nextDouble()-0.5;
            double y = position.y + pEntity.getBbHeight()*random.nextDouble();
            double z = position.z+random.nextDouble()-0.5;

            pEntity.level().addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), x, y, z, 0, 0, 0); // x, y, z, velocity (dx, dy, dz)
        }

    }

    private void spawnAmbientParticles() {
        Vec3 position = this.position();
        double x = position.x+random.nextDouble()-0.5;
        double y = position.y + this.getBbHeight()*0.25 + (this.getBbHeight()*0.5)*random.nextDouble(); // Position the particle slightly above the mob
        double z = position.z+random.nextDouble()-0.5;

        this.level().addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), x, y, z, 0, 0, 0); // x, y, z, velocity (dx, dy, dz)
    }

    public void setAttacking(boolean attacking) {
        this.entityData.set(ATTACKING, attacking);
    }

    public boolean isAttacking() {
        return this.entityData.get(ATTACKING);
    }

    public void setHunting(boolean attacking) {
        this.entityData.set(HUNTING, attacking);
    }

    public boolean isHunting() {
        return this.entityData.get(HUNTING);
    }

    @Override
    public boolean fireImmune() {
        return true;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return false;
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean canBeSeenAsEnemy() {
        return false;
    }

    @Override
    public boolean isSteppingCarefully() {
        return true;
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public boolean addEffect(MobEffectInstance pEffectInstance, @Nullable Entity pEntity) {
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
    public boolean isPushedByFluid(FluidType type) {
        return false;
    }

    @Override
    public boolean canFreeze() {
        return false;
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
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("saltcraft", "entities/mirage");
    }
}