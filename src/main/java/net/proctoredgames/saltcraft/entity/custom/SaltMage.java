package net.proctoredgames.saltcraft.entity.custom;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpellcastingIllagerEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.block.custom.SpawningPlinthBlock;
import net.proctoredgames.saltcraft.block.custom.SummoningPlinthBlock;
import net.proctoredgames.saltcraft.entity.ModEntities;
import org.jetbrains.annotations.Nullable;

public class SaltMage extends SpellcastingIllagerEntity {
    private final ServerBossBar bossBar = new ServerBossBar(this.getDisplayName(), BossBar.Color.WHITE, BossBar.Style.NOTCHED_12);

    @Nullable
    private BlockPos spawningPlinthPosition;

    public SaltMage(EntityType<? extends SaltMage> entityType, World world) {
        super(entityType, world);
        this.experiencePoints = 10;
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 150.0);
    }

    public void setSpawningPlinthPosition(@Nullable BlockPos pos) {
        this.spawningPlinthPosition = pos;
    }

    @Nullable
    public BlockPos getSpawningPlinthPosition() {
        return spawningPlinthPosition;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new SaltMageCastingSpellGoal());
        this.goalSelector.add(2, new FleeEntityGoal<>(this, PlayerEntity.class, 8.0F, 0.6, 1.0));
        this.goalSelector.add(4, new SaltMageSummonSpellGoal());
        this.goalSelector.add(5, new SaltMageWallSpellGoal());
        this.goalSelector.add(8, new WanderAroundGoal(this, 0.6));
        this.goalSelector.add(9, new LookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
        this.targetSelector.add(1, new RevengeGoal(this, RaiderEntity.class).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, Mirage.class, false));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, MerchantEntity.class, false).setMaxTimeWithoutVisibility(300));
        this.targetSelector.add(4, new ActiveTargetGoal<>(this, IronGolemEntity.class, false));
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.spawningPlinthPosition != null) {
            nbt.put("SpawningPlinthPos", net.minecraft.nbt.NbtHelper.fromBlockPos(spawningPlinthPosition));
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("SpawningPlinthPos")) {
            this.spawningPlinthPosition = net.minecraft.nbt.NbtHelper.toBlockPos(nbt, "SpawningPlinthPos").orElse(null);
        }
    }

    @Override
    public boolean isTeammate(Entity other) {
        if (other == this) {
            return true;
        } else if (super.isTeammate(other)) {
            return true;
        } else if (other instanceof Crystid crystid) {
            return crystid.getOwner() != null && this.isTeammate(crystid.getOwner());
        } else {
            return other instanceof net.minecraft.entity.mob.IllagerEntity && this.getScoreboardTeam() == null && other.getScoreboardTeam() == null;
        }
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_EVOKER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_EVOKER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_EVOKER_HURT;
    }

    @Override
    protected SoundEvent getCastSpellSound() {
        return SoundEvents.ENTITY_EVOKER_CAST_SPELL;
    }

    @Override
    public void addBonusForWave(ServerWorld world, int wave, boolean unused) {
    }

    @Override
    public SoundEvent getCelebratingSound() {
        return SoundEvents.ENTITY_EVOKER_CELEBRATE;
    }

    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
    }

    class SaltMageCastingSpellGoal extends SpellcastingIllagerEntity.LookAtTargetGoal {
        @Override
        public void tick() {
            if (SaltMage.this.getTarget() != null) {
                SaltMage.this.getLookControl().lookAt(SaltMage.this.getTarget(), (float) SaltMage.this.getMaxHeadRotation(), (float) SaltMage.this.getMaxLookPitchChange());
            }
        }
    }

    class SaltMageSummonSpellGoal extends SpellcastingIllagerEntity.CastSpellGoal {
        private final TargetPredicate crystidCountTargeting = TargetPredicate.createNonAttackable().setBaseMaxDistance(16.0).ignoreVisibility().ignoreDistanceScalingFactor();

        @Override
        public boolean canStart() {
            if (!super.canStart()) {
                return false;
            } else {
                int i = SaltMage.this.getWorld().getTargets(Crystid.class, this.crystidCountTargeting, SaltMage.this, SaltMage.this.getBoundingBox().expand(16.0)).size();
                return SaltMage.this.random.nextInt(8) + 1 > i;
            }
        }

        @Override
        protected int getInitialCooldown() {
            return 100;
        }

        @Override
        protected int getSpellTicks() {
            return 100;
        }

        @Override
        protected int startTimeDelay() {
            return 340;
        }

        @Override
        protected void castSpell() {
            ServerWorld serverWorld = (ServerWorld) SaltMage.this.getWorld();
            SaltMage.this.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, this.getSpellTicks(), 0));

            for (int i = 0; i < 3; ++i) {
                BlockPos pos = SaltMage.this.getBlockPos().add(-2 + SaltMage.this.random.nextInt(5), 1, -2 + SaltMage.this.random.nextInt(5));
                Crystid crystid = ModEntities.CRYSTID.create(SaltMage.this.getWorld());
                if (crystid != null) {
                    crystid.refreshPositionAndAngles(pos, 0.0F, 0.0F);
                    crystid.initialize(serverWorld, SaltMage.this.getWorld().getLocalDifficulty(pos), SpawnReason.MOB_SUMMONED, null);
                    crystid.setOwner(SaltMage.this);
                    crystid.setLimitedLife(20 * (30 + SaltMage.this.random.nextInt(90)));
                    serverWorld.spawnEntityAndPassengers(crystid);
                }
            }
        }

        @Override
        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_SUMMON;
        }

        @Override
        protected SpellcastingIllagerEntity.Spell getSpell() {
            return SpellcastingIllagerEntity.Spell.SUMMON_VEX;
        }
    }

    class SaltMageWallSpellGoal extends SpellcastingIllagerEntity.CastSpellGoal {
        @Override
        public boolean canStart() {
            if (!SaltMage.this.getWorld().getGameRules().getBoolean(net.minecraft.world.GameRules.DO_MOB_GRIEFING) || !super.canStart()
                    || SaltMage.this.getHealth() > SaltMage.this.getMaxHealth() / 2) {
                return false;
            } else {
                return SaltMage.this.getRandom().nextInt(30) == 1;
            }
        }

        @Override
        protected int getInitialCooldown() {
            return 40;
        }

        @Override
        protected int getSpellTicks() {
            return 40;
        }

        @Override
        protected int startTimeDelay() {
            return 100;
        }

        @Override
        protected void castSpell() {
            Random random = SaltMage.this.getRandom();
            World world = SaltMage.this.getWorld();
            int xRoot = MathHelper.floor(SaltMage.this.getX());
            int yRoot = MathHelper.floor(SaltMage.this.getY());
            int zRoot = MathHelper.floor(SaltMage.this.getZ());

            int wallHeight = 3;
            int wallHalfLength = 3;

            boolean doWallRotation;
            double wallSummonDistance;

            LivingEntity target = SaltMage.this.getTarget();
            // The target can die or be cleared during the cast warmup
            if (target == null) {
                return;
            }

            doWallRotation = Math.abs(SaltMage.this.getZ() - target.getZ()) > Math.abs(SaltMage.this.getX() - target.getX());

            if ((doWallRotation && Math.abs(SaltMage.this.getZ() - target.getZ()) < 7) ||
                    (!doWallRotation && Math.abs(SaltMage.this.getX() - target.getX()) < 7)) {
                wallSummonDistance = 2.0;
            } else {
                if (doWallRotation) {
                    wallSummonDistance = Math.floor(Math.abs(SaltMage.this.getZ() - target.getZ())) -
                            (2.0 + Math.round(random.nextDouble() * 2.0));
                } else {
                    wallSummonDistance = Math.floor(Math.abs(SaltMage.this.getX() - target.getX())) -
                            (2.0 + Math.round(random.nextDouble() * 2.0));
                }
            }

            if (doWallRotation) {
                if (SaltMage.this.getZ() - target.getZ() > 0) {
                    zRoot = MathHelper.floor(zRoot - wallSummonDistance);
                } else {
                    zRoot = MathHelper.floor(zRoot + wallSummonDistance);
                }
            } else {
                if (SaltMage.this.getX() - target.getX() > 0) {
                    xRoot = MathHelper.floor(xRoot - wallSummonDistance);
                } else {
                    xRoot = MathHelper.floor(xRoot + wallSummonDistance);
                }
            }

            for (int i = 0; i <= yRoot + 64; i++) {
                net.minecraft.block.BlockState blockState = world.getBlockState(new BlockPos(xRoot, yRoot, zRoot).down(i));
                if (!blockState.isAir()) {
                    yRoot = yRoot - i;
                    break;
                }
            }

            if (!world.isClient) {
                world.createExplosion(SaltMage.this, xRoot, yRoot, zRoot, 6.0F, World.ExplosionSourceType.NONE);
            }

            SaltMage.this.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, this.getSpellTicks() * 2, 3));

            for (int length = -wallHalfLength; length <= wallHalfLength; length++) {
                for (int height = 0; height <= wallHeight; height++) {
                    BlockPos blockPos;
                    if (doWallRotation) {
                        blockPos = new BlockPos(xRoot + length, yRoot + height, zRoot);
                    } else {
                        blockPos = new BlockPos(xRoot, yRoot + height, zRoot + length);
                    }
                    net.minecraft.block.BlockState blockState = world.getBlockState(blockPos);
                    net.minecraft.block.BlockState newState = height != wallHeight
                            ? ModBlocks.ROCK_SALT_BRICKS.getDefaultState()
                            : ModBlocks.CHISELED_ROCK_SALT_BRICKS.getDefaultState();
                    newState = Block.postProcessState(newState, world, blockPos);
                    if (this.canPlaceBlock(blockState)) {
                        world.setBlockState(blockPos, newState, 3);
                        world.emitGameEvent(SaltMage.this, GameEvent.BLOCK_PLACE, blockPos);
                    }
                }
            }
        }

        private boolean canPlaceBlock(net.minecraft.block.BlockState destinationState) {
            return destinationState.isAir();
        }

        @Override
        protected SoundEvent getSoundPrepare() {
            return SoundEvents.ENTITY_EVOKER_PREPARE_WOLOLO;
        }

        @Override
        protected SpellcastingIllagerEntity.Spell getSpell() {
            return SpellcastingIllagerEntity.Spell.FANGS;
        }
    }

    @Override
    public void onStartedTrackingBy(net.minecraft.server.network.ServerPlayerEntity player) {
        super.onStartedTrackingBy(player);
        this.bossBar.addPlayer(player);
    }

    @Override
    public void onStoppedTrackingBy(net.minecraft.server.network.ServerPlayerEntity player) {
        super.onStoppedTrackingBy(player);
        this.bossBar.removePlayer(player);
    }

    @Override
    public void tick() {
        super.tick();
        this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        if (spawningPlinthPosition != null) {
            // The plinth may have been broken or replaced since the boss spawned
            net.minecraft.block.BlockState plinthState = this.getWorld().getBlockState(spawningPlinthPosition);
            if (plinthState.isOf(ModBlocks.SPAWNING_PLINTH)) {
                this.getWorld().setBlockState(spawningPlinthPosition, plinthState.with(SpawningPlinthBlock.LIT, false), 3);
            }
            if (!this.getWorld().isClient) {
                this.getWorld().createExplosion(null, spawningPlinthPosition.getX(),
                        spawningPlinthPosition.getY(), spawningPlinthPosition.getZ(), 3.0F, World.ExplosionSourceType.NONE);
            }
            unlightSummoningPlinths(this.getWorld(), spawningPlinthPosition);
        }
        super.onDeath(damageSource);
    }

    public void unlightSummoningPlinths(World world, BlockPos position) {
        net.minecraft.block.BlockState blockState;
        BlockPos blockPos;
        int relativeYLayer = 0;
        boolean checkingLayers = true;
        int radius = SpawningPlinthBlock.PLINTH_SEARCH_RADIUS;
        while (checkingLayers) {
            for (int z = -radius; z <= radius; z++) {
                for (int x = -radius; x <= radius; x++) {
                    blockPos = new BlockPos(position.getX() + x, position.getY() + relativeYLayer, position.getZ() + z);
                    if (!world.isChunkLoaded(blockPos)) {
                        continue;
                    }
                    blockState = world.getBlockState(blockPos);
                    if (blockState.isOf(ModBlocks.SUMMONING_PLINTH)) {
                        world.setBlockState(blockPos, blockState.with(SummoningPlinthBlock.CRACKED, true).with(SummoningPlinthBlock.LIT, false), 3);
                        if (!world.isClient) {
                            world.createExplosion(null, position.getX(), position.getY(), position.getZ(), 2.0F, World.ExplosionSourceType.NONE);
                        }
                    }
                }
            }
            if (relativeYLayer == 0) {
                relativeYLayer = 8;
            } else if (relativeYLayer == 8) {
                relativeYLayer = 10;
            } else if (relativeYLayer == 10) {
                relativeYLayer = 12;
            } else {
                checkingLayers = false;
            }
        }
    }
}
