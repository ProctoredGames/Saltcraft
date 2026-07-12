package net.proctoredgames.saltcraft.worldgen;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.proctoredgames.saltcraft.Saltcraft;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;
import net.proctoredgames.saltcraft.util.ModTags;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;

import java.util.List;
import java.util.Optional;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_SALT_ORE = registerKey("add_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_PINK_SALT_ORE = registerKey("add_pink_salt_ore");
    public static final ResourceKey<BiomeModifier> ADD_OIL_VEIN = registerKey("add_oil_vein");

    public static final ResourceKey<BiomeModifier> ADD_SALT_DOME = registerKey("add_salt_dome");
    public static final ResourceKey<BiomeModifier> ADD_SALT_LAKE = registerKey("add_salt_lake");
    public static final ResourceKey<BiomeModifier> ADD_SALT_FLAT_WATER_CLEANUP = registerKey("add_salt_flat_water_cleanup");

    public static void bootstrap(BootstapContext<BiomeModifier> context) {
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        List <Holder.Reference<Biome>> hasSaltOre = List.of(
                biomes.get(Biomes.OCEAN).get(),
                biomes.get(Biomes.DEEP_OCEAN).get(),
                biomes.get(Biomes.FROZEN_OCEAN).get(),
                biomes.get(Biomes.COLD_OCEAN).get(),
                biomes.get(Biomes.LUKEWARM_OCEAN).get(),
                biomes.get(Biomes.DEEP_COLD_OCEAN).get(),
                biomes.get(Biomes.DEEP_FROZEN_OCEAN).get(),
                biomes.get(Biomes.DEEP_LUKEWARM_OCEAN).get(),
                biomes.get(Biomes.WARM_OCEAN).get(),
                biomes.get(Biomes.RIVER).get(),
                biomes.get(Biomes.FROZEN_RIVER).get(),
                biomes.get(Biomes.SWAMP).get(),
                biomes.get(Biomes.MANGROVE_SWAMP).get(),
                biomes.get(ModBiomes.SALT_FLAT).get()
        );

//        List <Holder.Reference<Biome>> drainsExtraThirst = List.of(
//                biomes.get(Biomes.DESERT).get(),
//                biomes.get(Biomes.JUNGLE).get(),
//                biomes.get(Biomes.BAMBOO_JUNGLE).get(),
//                biomes.get(Biomes.SPARSE_JUNGLE).get(),
//                biomes.get(Biomes.BADLANDS).get(),
//                biomes.get(Biomes.ERODED_BADLANDS).get(),
//                biomes.get(Biomes.WOODED_BADLANDS).get(),
//                biomes.get(ModBiomes.SALT_FLAT).get()
//        );

        Optional<Holder.Reference<Biome>> isSaltFlat = biomes.get(ModBiomes.SALT_FLAT);


        context.register(ADD_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(hasSaltOre),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_PINK_SALT_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_MOUNTAIN),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PINK_SALT_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));

        context.register(ADD_OIL_VEIN, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OIL_VEIN_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES));


        context.register(ADD_SALT_DOME, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_DOME_PLACED_KEY)),
                GenerationStep.Decoration.LOCAL_MODIFICATIONS));

        context.register(ADD_SALT_LAKE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(isSaltFlat.get()),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_LAKE_PLACED_KEY)),
                GenerationStep.Decoration.LAKES));

        // Last decoration step, so it also catches water placed by springs and lakes
        context.register(ADD_SALT_FLAT_WATER_CLEANUP, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(isSaltFlat.get()),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SALT_FLAT_WATER_CLEANUP_PLACED_KEY)),
                GenerationStep.Decoration.TOP_LAYER_MODIFICATION));

    }

    private static ResourceKey<BiomeModifier> registerKey(String name) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, new ResourceLocation(Saltcraft.MOD_ID, name));
    }
}