package net.proctoredgames.saltcraft.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.MonsterRoomFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.fml.common.Mod;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.worldgen.feature.ModFeatures;
import net.proctoredgames.saltcraft.worldgen.feature.SaltLakeFeature;
//import net.proctoredgames.saltcraft.worldgen.feature.configurations.SaltDomeBlockSettings;
//import net.proctoredgames.saltcraft.worldgen.feature.configurations.SaltDomeConfiguration;
//import net.proctoredgames.saltcraft.worldgen.feature.ModFeatures;
//import net.proctoredgames.saltcraft.worldgen.feature.SaltDomeFeature;

import java.sql.Blob;
import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> SALT_ORE_KEY = registerKey("salt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PINK_SALT_ORE_KEY = registerKey("pink_salt_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OIL_VEIN_KEY = registerKey("oil_vein");

    public static final ResourceKey<ConfiguredFeature<?, ?>> SALT_DOME_KEY = registerKey("salt_dome");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SALT_LAKE_KEY = registerKey("salt_lake");


    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest rockSaltReplaceable = new BlockMatchTest(ModBlocks.ROCK_SALT_BLOCK.get());
//        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

        register(context, SALT_ORE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceable,
                ModBlocks.SALT_ORE.get().defaultBlockState(), 9));
        register(context, PINK_SALT_ORE_KEY, Feature.ORE, new OreConfiguration(stoneReplaceable,
                ModBlocks.PINK_SALT_ORE.get().defaultBlockState(), 9));
        register(context, OIL_VEIN_KEY, Feature.ORE, new OreConfiguration(stoneReplaceable,
                ModBlocks.OIL_BLOCK.get().defaultBlockState(), 20));

        register(context, SALT_DOME_KEY, ModFeatures.SALT_DOME.get(),
                new NoneFeatureConfiguration());
        register(context, SALT_LAKE_KEY, ModFeatures.SALT_LAKE.get(),
                new NoneFeatureConfiguration());

    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Saltcraft.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}