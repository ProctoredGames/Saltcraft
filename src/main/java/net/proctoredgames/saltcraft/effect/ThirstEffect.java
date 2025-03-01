package net.proctoredgames.saltcraft.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fml.LogicalSide;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncS2CPacket;
import net.proctoredgames.saltcraft.thirst.PlayerThirstProvider;
import net.proctoredgames.saltcraft.util.SandTeleport;
import net.proctoredgames.saltcraft.worldgen.ModBiomeModifiers;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;

import java.util.List;

public class ThirstEffect extends MobEffect {
    public ThirstEffect(MobEffectCategory mobEffectCategory, int color) {
        super(mobEffectCategory, color);
    }

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        double baseRandomTickThreshold = 0.005 * (pAmplifier+1);
        double acceleratedRandomTickThreshold = 0.01 * (pAmplifier+1);
        double extraAcceleratedRandomTickThreshold = 0.015 * (pAmplifier+1);
        if(pLivingEntity instanceof ServerPlayer) {
            pLivingEntity.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                double randomTickThreshold = 0;
                Holder<Biome> entityWhatBiome = pLivingEntity.level().getBiome(BlockPos.containing(pLivingEntity.position()));
                boolean isUsingMoreEnergy = pLivingEntity.isSprinting() || pLivingEntity.isSwimming();
                boolean isInHotBiome = entityWhatBiome.is(BiomeTags.SPAWNS_WARM_VARIANT_FROGS) || entityWhatBiome.is(ModBiomes.SALT_FLAT.getId());
                if(isUsingMoreEnergy && isInHotBiome){
                    randomTickThreshold = extraAcceleratedRandomTickThreshold;
                } else if(isUsingMoreEnergy){
                    randomTickThreshold = acceleratedRandomTickThreshold;
                } else{
                    randomTickThreshold = baseRandomTickThreshold;
                }
                if (pLivingEntity.getRandom().nextFloat() < randomTickThreshold && !(pLivingEntity.isSpectator() || ((ServerPlayer) pLivingEntity).isCreative())) {
                    if (thirst.getThirst() > 0) {
                        thirst.subThirst(1);
                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), (ServerPlayer) pLivingEntity);
                    }
                    if (thirst.getThirst() == 0) {
                        pLivingEntity.hurt(pLivingEntity.level().damageSources().dryOut(), 1);
                    }
                }
                if (thirst.getThirst() <= 5 && entityWhatBiome.is(Biomes.DESERT) && pLivingEntity.level().isDay()) {
                    RandomSource random = RandomSource.create();
                    double spawnAngle = 0;

                    // Calculate initial spawn position offset
                    int offsetX = (int) (Math.sin(spawnAngle) * 70);
                    int offsetZ = (int) (Math.cos(spawnAngle) * 70);
                    BlockPos spawnPos = pLivingEntity.blockPosition().offset(offsetX, pLivingEntity.level().getMaxBuildHeight(), offsetZ);

                    // Ensure the position is in the desert biome
                    while (spawnAngle < 2*Math.PI) {
                        if(pLivingEntity.level().getBiome(spawnPos).is(Biomes.DESERT)){
                            break;
                        }
                        offsetX = (int) (Math.sin(spawnAngle) * 70);
                        offsetZ = (int) (Math.cos(spawnAngle) * 70);
                        spawnPos = pLivingEntity.blockPosition().offset(offsetX, pLivingEntity.level().getMaxBuildHeight(), offsetZ);
                        spawnAngle += Math.PI * 0.3;

                    }

                    // If valid desert biome found
                    if (pLivingEntity.level().getBiome(spawnPos).is(Biomes.DESERT)) {
                        spawnPos = SandTeleport.determineGroundAdjustedPosition(spawnPos, pLivingEntity.level());

                        // Check for existing Mirages within a 200-block radius
                        int radius = 200;
                        List<Mirage> nearbyMirages = pLivingEntity.level().getEntitiesOfClass(Mirage.class,
                                new AABB(spawnPos.offset(-radius, -radius, -radius), spawnPos.offset(radius, radius, radius)));

                        // Spawn the Mirage only if no other Mirage exists within the range and it's day
                        if (nearbyMirages.isEmpty()) {
                            Mirage entity = ModEntities.MIRAGE.get().create(pLivingEntity.level());
                            if (entity != null) {
                                entity.setPos(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                                pLivingEntity.level().addFreshEntity(entity);
                            }
                        }
                    }
                }

            });
        }

        if(pLivingEntity instanceof Slime || pLivingEntity.getMobType() == MobType.UNDEAD){
            if(pLivingEntity.getRandom().nextFloat() < acceleratedRandomTickThreshold){
                pLivingEntity.hurt(pLivingEntity.level().damageSources().dryOut(), 1);
            }
        }

    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return true;
    }
}