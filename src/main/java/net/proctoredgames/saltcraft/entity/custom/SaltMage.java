
package net.proctoredgames.saltcraft.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.SpellcasterIllager;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.block.custom.SpawningPlinthBlock;
import net.proctoredgames.saltcraft.block.custom.SummoningPlinthBlock;
import net.proctoredgames.saltcraft.entity.ModEntities;

public class SaltMage extends SpellcasterIllager {

    private BlockPos spawningPlinthPosition = null;

    /* BOSS BAR */
    private final ServerBossEvent bossEvent =
             new ServerBossEvent(Component.literal("Salt Mage"), BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.NOTCHED_12);


    public SaltMage(EntityType<? extends SaltMage> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 10;
    }
    public void SetSpawningPlinthPosition(BlockPos pSpawningPlinthPosition){
        this.spawningPlinthPosition = pSpawningPlinthPosition;
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SaltMageCastingSpellGoal());
        this.goalSelector.addGoal(2, new AvoidEntityGoal(this, Player.class, 8.0F, 0.6, 1.0));
        this.goalSelector.addGoal(4, new SaltMageSummonSpellGoal());
        this.goalSelector.addGoal(5, new SaltMageWallSpellGoal());
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, new Class[]{Raider.class})).setAlertOthers(new Class[0]));
        this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal(this, Player.class, true)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, Mirage.class, false));
        this.targetSelector.addGoal(4, (new NearestAttackableTargetGoal(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolem.class, false));

    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.FOLLOW_RANGE, 12.0)
                .add(Attributes.MAX_HEALTH, 150.0);
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
    }

    public SoundEvent getCelebrateSound() {
        return SoundEvents.EVOKER_CELEBRATE;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
    }

    protected void customServerAiStep() {
        super.customServerAiStep();
    }

    public boolean isAlliedTo(Entity pEntity) {
        if (pEntity == this) {
            return true;
        } else if (super.isAlliedTo(pEntity)) {
            return true;
        } else if (pEntity instanceof Crystid) {
            return this.isAlliedTo(((Crystid)pEntity).getOwner());
        } else if (pEntity instanceof LivingEntity && ((LivingEntity)pEntity).getMobType() == MobType.ILLAGER) {
            return this.getTeam() == null && pEntity.getTeam() == null;
        } else {
            return false;
        }
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.EVOKER_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOKER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.EVOKER_HURT;
    }

    protected SoundEvent getCastingSoundEvent() {
        return SoundEvents.EVOKER_CAST_SPELL;
    }

    public void applyRaidBuffs(int pWave, boolean pUnusedFalse) {
    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean removeWhenFarAway(double pDistanceToClosestPlayer) {
        return false;
    }

    class SaltMageCastingSpellGoal extends SpellcasterIllager.SpellcasterCastingSpellGoal {
        SaltMageCastingSpellGoal() {
            super();
        }

        public void tick() {
            if (SaltMage.this.getTarget() != null) {
                SaltMage.this.getLookControl().setLookAt(SaltMage.this.getTarget(), (float)SaltMage.this.getMaxHeadYRot(), (float)SaltMage.this.getMaxHeadXRot());
            }

        }
    }

    class SaltMageSummonSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {
        private final TargetingConditions crystidCountTargeting = TargetingConditions.forNonCombat().range(16.0).ignoreLineOfSight().ignoreInvisibilityTesting();

        SaltMageSummonSpellGoal() {
            super();
        }

        public boolean canUse() {
            if (!super.canUse()) {
                return false;
            } else {
                int i = SaltMage.this.level().getNearbyEntities(Crystid.class, this.crystidCountTargeting, SaltMage.this, SaltMage.this.getBoundingBox().inflate(16.0)).size();
                return SaltMage.this.random.nextInt(8) + 1 > i;
            }
        }

        protected int getCastingTime() {
            return 100;
        }

        protected int getCastingInterval() {
            return 340;
        }

        protected void performSpellCasting() {
            ServerLevel serverlevel = (ServerLevel)SaltMage.this.level();
            SaltMage.this.addEffect(new MobEffectInstance(MobEffects.LEVITATION, getCastingTime(), 0));

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos = SaltMage.this.blockPosition().offset(-2 + SaltMage.this.random.nextInt(5), 1, -2 + SaltMage.this.random.nextInt(5));
                Crystid crystid = (Crystid) ModEntities.CRYSTID.get().create(SaltMage.this.level());
                if (crystid != null) {
                    crystid.moveTo(blockpos, 0.0F, 0.0F);
                    crystid.finalizeSpawn(serverlevel, SaltMage.this.level().getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
                    crystid.setOwner(SaltMage.this);
                    crystid.setLimitedLife(20 * (30 + SaltMage.this.random.nextInt(90)));
                    serverlevel.addFreshEntityWithPassengers(crystid);
                }
            }

        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOKER_PREPARE_SUMMON;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return IllagerSpell.SUMMON_VEX;
        }
    }

    class SaltMageWallSpellGoal extends SpellcasterIllager.SpellcasterUseSpellGoal {

        public SaltMageWallSpellGoal() {
            super();
        }

        public boolean canUse() {
            if (!ForgeEventFactory.getMobGriefingEvent(SaltMage.this.level(), SaltMage.this) || !super.canUse()
            || (SaltMage.this.getHealth()>(SaltMage.this.getMaxHealth()/2))) {
                return false;
            } else {
                return SaltMage.this.getRandom().nextInt(30) == 1;
            }
        }

        protected int getCastingTime() {
            return 40;
        }

        protected int getCastingInterval() {
            return 100;
        }

        public void performSpellCasting() {
            RandomSource randomsource = SaltMage.this.getRandom();
            Level level = SaltMage.this.level();
            int xRoot = Mth.floor(SaltMage.this.getX());
            int yRoot = Mth.floor(SaltMage.this.getY());
            int zRoot = Mth.floor(SaltMage.this.getZ());
            BlockPos blockpos;
            BlockState blockstate;
            BlockPos blockpos1;
            BlockState blockstate1;
            BlockState blockstate2;

            int wallHeight=3;
            int wallHalfLength=3;

            boolean doWallRotation;
            double wallSummonDistance;

            LivingEntity livingentity = SaltMage.this.getTarget();

            doWallRotation = Math.abs(SaltMage.this.getZ()-livingentity.getZ()) > Math.abs(SaltMage.this.getX()-livingentity.getX());

            if((doWallRotation && Math.abs(SaltMage.this.getZ()-livingentity.getZ())<7) ||
                    (!doWallRotation && Math.abs(SaltMage.this.getX()-livingentity.getX())<7)){
                wallSummonDistance = 2.0;
            } else{
                if(doWallRotation){
                    wallSummonDistance = Math.floor(Math.abs(SaltMage.this.getZ()-livingentity.getZ()))-
                            (2.0+Math.round(randomsource.nextDouble()*2.0));
                } else{
                    wallSummonDistance = Math.floor(Math.abs(SaltMage.this.getX()-livingentity.getX()))-
                            (2.0+Math.round(randomsource.nextDouble()*2.0));
                }
            }

            if(doWallRotation){
                if(SaltMage.this.getZ()-livingentity.getZ()>0){
                    zRoot = Mth.floor(zRoot- (wallSummonDistance));
                } else{
                    zRoot = Mth.floor(zRoot+ (wallSummonDistance));
                }
            } else{
                if(SaltMage.this.getX()-livingentity.getX()>0){
                    xRoot = Mth.floor(xRoot- (wallSummonDistance));
                } else{
                    xRoot = Mth.floor(xRoot+ (wallSummonDistance));
                }
            }

            for(int i = 0; i <= yRoot + 64; i++) {
                BlockState blockState = level.getBlockState(new BlockPos(xRoot, yRoot, zRoot).below(i));
                if (!blockState.isAir()) {
                    yRoot = yRoot - i;
                    break;
                }
            }

            if (!level.isClientSide) {
                DamageSource $$2 = SaltMage.this.damageSources().explosion(SaltMage.this, livingentity);
                level.explode(SaltMage.this, null, (ExplosionDamageCalculator)null, xRoot, yRoot, zRoot, 6.0F, false, Level.ExplosionInteraction.NONE);

            }

            SaltMage.this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, getCastingTime()*2, 3));

            for(int length = -1*wallHalfLength; length<=wallHalfLength; length++) {
                for (int height = 0; height <= wallHeight; height++) {
                    if(doWallRotation){
                        blockpos = new BlockPos(xRoot+length, yRoot + height, zRoot);
                    } else{
                        blockpos = new BlockPos(xRoot, yRoot + height, zRoot+length);
                    }
                    blockstate = level.getBlockState(blockpos);
                    blockpos1 = blockpos.below();
                    if(height!=wallHeight){
                        blockstate2 = ModBlocks.ROCK_SALT_BRICKS.get().defaultBlockState();
                    } else{
                        blockstate2 = ModBlocks.CHISELED_ROCK_SALT_BRICKS.get().defaultBlockState();
                    }
                    blockstate2 = Block.updateFromNeighbourShapes(blockstate2, SaltMage.this.level(), blockpos);
                    if (this.canPlaceBlock(blockstate) && !ForgeEventFactory.onBlockPlace(SaltMage.this, BlockSnapshot.create(level.dimension(), level, blockpos1), Direction.UP)) {
                        level.setBlock(blockpos, blockstate2, 3);
                        level.gameEvent(GameEvent.BLOCK_PLACE, blockpos, GameEvent.Context.of(SaltMage.this, blockstate2));
                    }
                }
            }
        }

        private boolean canPlaceBlock(BlockState pDestinationState) {
            return pDestinationState.isAir();
        }

        protected SoundEvent getSpellPrepareSound() {
            return SoundEvents.EVOKER_PREPARE_WOLOLO;
        }

        protected SpellcasterIllager.IllagerSpell getSpell() {
            return IllagerSpell.FANGS;
        }
    }

    @Override
    public void startSeenByPlayer(ServerPlayer pServerPlayer) {
        super.startSeenByPlayer(pServerPlayer);
        this.bossEvent.addPlayer(pServerPlayer);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer pServerPlayer) {
        super.stopSeenByPlayer(pServerPlayer);
        this.bossEvent.removePlayer(pServerPlayer);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.bossEvent.setProgress(this.getHealth() / this.getMaxHealth());
    }

    @Override
    public void die(DamageSource pCause) {
        if(spawningPlinthPosition != null){
            this.level().setBlock(spawningPlinthPosition, this.level().getBlockState(spawningPlinthPosition).setValue(SpawningPlinthBlock.LIT, false), 3);
            if (!this.level().isClientSide) {
                this.level().explode(null, null, (ExplosionDamageCalculator)null, spawningPlinthPosition.getX(),
                        spawningPlinthPosition.getY(), spawningPlinthPosition.getZ(), 3.0F, false, Level.ExplosionInteraction.NONE);
            }
            unlightSummoningPlinths(this.level(),spawningPlinthPosition);
        }
        super.die(pCause);
    }

    @Override
    protected ResourceLocation getDefaultLootTable() {
        return new ResourceLocation("saltcraft", "entities/salt_mage");
    }

    public void unlightSummoningPlinths(Level level, BlockPos position){
        BlockState blockState;
        BlockPos blockPos;
        int relativeYLayer = 0;
        boolean checkingLayers = true;
        while(checkingLayers) {
            for (int z = -100; z <= 100; z++) {
                for (int x = -100; x <= 100; x++) {
                    blockPos = new BlockPos((int) position.getX() + x, (int) position.getY()+relativeYLayer, (int) position.getZ() + z);
                    blockState = level.getBlockState(blockPos);
                    if (blockState.is(ModBlocks.SUMMONING_PLINTH.get())) {
                        level.setBlock(blockPos, blockState.setValue(SummoningPlinthBlock.CRACKED, true).setValue(SummoningPlinthBlock.LIT, false), 3);
                        if (!level.isClientSide) {
                            level.explode(null, null, (ExplosionDamageCalculator)null, position.getX(),
                                    position.getY(), position.getZ(), 2.0F, false, Level.ExplosionInteraction.NONE);
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
            } else{
                checkingLayers = false;
            }
        }
    }
}
