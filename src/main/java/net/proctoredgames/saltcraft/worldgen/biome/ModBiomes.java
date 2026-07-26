package net.proctoredgames.saltcraft.worldgen.biome;

import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModBiomes {
    public static final RegistryKey<Biome> SALT_FLAT = RegistryKey.of(RegistryKeys.BIOME, Identifier.of(Saltcraft.MOD_ID, "salt_flat"));
}
