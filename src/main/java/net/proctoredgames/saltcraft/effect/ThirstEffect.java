package net.proctoredgames.saltcraft.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.registry.tag.EntityTypeTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncPayload;
import net.proctoredgames.saltcraft.thirst.PlayerThirst;
import net.proctoredgames.saltcraft.util.SandTeleport;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;

import java.util.List;

public class ThirstEffect extends StatusEffect {
    public ThirstEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        double baseRandomTickThreshold = 0.005 * (amplifier + 1);
        double acceleratedRandomTickThreshold = 0.01 * (amplifier + 1);
        double extraAcceleratedRandomTickThreshold = 0.015 * (amplifier + 1);

        if (entity instanceof ServerPlayerEntity player) {
            PlayerThirst thirst = player.getAttachedOrCreate(PlayerThirst.THIRST);

            RegistryEntry<Biome> entityWhatBiome = player.getWorld().getBiome(BlockPos.ofFloored(player.getPos()));
            boolean isUsingMoreEnergy = player.isSprinting() || player.isSwimming();
            boolean isInHotBiome = entityWhatBiome.isIn(BiomeTags.SPAWNS_WARM_VARIANT_FROGS) || entityWhatBiome.matchesKey(ModBiomes.SALT_FLAT);

            double randomTickThreshold;
            if (isUsingMoreEnergy && isInHotBiome) {
                randomTickThreshold = extraAcceleratedRandomTickThreshold;
            } else if (isUsingMoreEnergy) {
                randomTickThreshold = acceleratedRandomTickThreshold;
            } else {
                randomTickThreshold = baseRandomTickThreshold;
            }

            if (player.getRandom().nextFloat() < randomTickThreshold && !(player.isSpectator() || player.isCreative())) {
                if (thirst.getThirst() > 0) {
                    thirst.subThirst(1);
                    ModMessages.sendToPlayer(new ThirstDataSyncPayload(thirst.getThirst()), player);
                }
                if (thirst.getThirst() == 0) {
                    player.damage(player.getWorld().getDamageSources().dryOut(), 1);
                }
            }

            // Only attempt the Mirage spawn every 100 ticks; the biome ring search and
            // entity query below are far too expensive to run every effect tick
            if (thirst.getThirst() <= 5 && player.age % 100 == 0
                    && entityWhatBiome.matchesKey(BiomeKeys.DESERT) && player.getWorld().isDay()) {
                double spawnAngle = 0;

                int offsetX = (int) (Math.sin(spawnAngle) * 70);
                int offsetZ = (int) (Math.cos(spawnAngle) * 70);
                BlockPos spawnPos = player.getBlockPos().add(offsetX, player.getWorld().getTopY(), offsetZ);

                while (spawnAngle < 2 * Math.PI) {
                    if (player.getWorld().getBiome(spawnPos).matchesKey(BiomeKeys.DESERT)) {
                        break;
                    }
                    offsetX = (int) (Math.sin(spawnAngle) * 70);
                    offsetZ = (int) (Math.cos(spawnAngle) * 70);
                    spawnPos = player.getBlockPos().add(offsetX, player.getWorld().getTopY(), offsetZ);
                    spawnAngle += Math.PI * 0.3;
                }

                if (player.getWorld().getBiome(spawnPos).matchesKey(BiomeKeys.DESERT)) {
                    spawnPos = SandTeleport.determineGroundAdjustedPosition(spawnPos, player.getWorld());

                    int radius = 200;
                    List<Mirage> nearbyMirages = player.getWorld().getEntitiesByClass(Mirage.class,
                            new Box(spawnPos).expand(radius),
                            mirage -> true);

                    if (nearbyMirages.isEmpty()) {
                        Mirage mirage = ModEntities.MIRAGE.create(player.getWorld());
                        if (mirage != null) {
                            mirage.setPosition(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
                            player.getWorld().spawnEntity(mirage);
                        }
                    }
                }
            }
        }

        if (entity instanceof SlimeEntity || entity.getType().isIn(EntityTypeTags.UNDEAD)) {
            if (entity.getRandom().nextFloat() < acceleratedRandomTickThreshold) {
                entity.damage(entity.getWorld().getDamageSources().dryOut(), 1);
            }
        }

        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
