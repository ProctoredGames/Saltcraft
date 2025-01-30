package net.proctoredgames.saltcraft.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModBiomes {
    public static final ResourceKey<Biome> SALT_FLAT = ResourceKey.create(Registries.BIOME,
            new ResourceLocation(Saltcraft.MOD_ID, "salt_flat"));

    public static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(Saltcraft.MOD_ID, name));
    }
}
