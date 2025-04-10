package net.proctoredgames.saltcraft.worldgen.biome;

import net.proctoredgames.saltcraft.Saltcraft;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiomes() {
        Regions.register(new ModOverworldRegion(new ResourceLocation(Saltcraft.MOD_ID, "overworld"), 10));
    }
}