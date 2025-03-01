package net.proctoredgames.saltcraft.worldgen.biome.surface;

import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.synth.PerlinNoise;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;

public class ModSurfaceRules {

    // Constants for blocks
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource CLUMPED_SALT = makeStateRule(ModBlocks.CLUMPED_SALT_BLOCK.get());
    private static final SurfaceRules.RuleSource ROCK_SALT = makeStateRule(ModBlocks.ROCK_SALT_BLOCK.get());
    private static final SurfaceRules.RuleSource AIR = makeStateRule(Blocks.AIR);

    public static final int SALT_FLAT_GROUND_LEVEL = 65;
    public static final double FULL_FOSSIL_NOISE_RANGE = 0.05;


    private static final PerlinSimplexNoise noise = new PerlinSimplexNoise(RandomSource.create(), new IntArrayList(new int[]{8, 6, 4, 2}));

    public static SurfaceRules.RuleSource makeRules() {
        // Check if the block is at or above water level
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);

        // Define a grass surface as the default for other biomes
        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(
                SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK),
                DIRT
        );

        // Main sequence of rules for the SALT_FLAT biome
        return SurfaceRules.sequence(
                // Create a flat terrain appearance by setting blocks to AIR above the ground level
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SALT_FLAT.getKey()),
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(SALT_FLAT_GROUND_LEVEL), 0), AIR)
                ),

                // Set clumped salt on the floor of caves below the ground level
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SALT_FLAT.getKey()),
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(SALT_FLAT_GROUND_LEVEL), -10000),
                                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, CLUMPED_SALT)
                        )
                ),

                // Set rock salt on the ceiling of caves
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SALT_FLAT.getKey()),
                        SurfaceRules.ifTrue(SurfaceRules.ON_CEILING, ROCK_SALT)
                ),

                // Generate a layer of clumped salt near the surface
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SALT_FLAT.getKey()),
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(SALT_FLAT_GROUND_LEVEL), -2), CLUMPED_SALT)
                ),


                SurfaceRules.ifTrue(
                        SurfaceRules.noiseCondition(
                                ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                -1*(FULL_FOSSIL_NOISE_RANGE/2), FULL_FOSSIL_NOISE_RANGE/2
                        ),
                        SurfaceRules.ifTrue(
                                SurfaceRules.yBlockCheck(
                                        VerticalAnchor.absolute(SALT_FLAT_GROUND_LEVEL), -15 // Range: 80 ± 5
                                ),
                                SurfaceRules.sequence(
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(
                                                        ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                                        -1*(FULL_FOSSIL_NOISE_RANGE/2), -1*(FULL_FOSSIL_NOISE_RANGE/2)*(2/3)
                                                ),
                                                SurfaceRules.state(
                                                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.DOWN)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(
                                                        ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                                        -1*(FULL_FOSSIL_NOISE_RANGE/2)*(2/3), -1*(FULL_FOSSIL_NOISE_RANGE/2)*(1/3)
                                                ),
                                                SurfaceRules.state(
                                                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.UP)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(
                                                        ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                                        -1*(FULL_FOSSIL_NOISE_RANGE/2)*(1/3), 0.0
                                                ),
                                                SurfaceRules.state(
                                                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.NORTH)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(
                                                        ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                                        0.0, (FULL_FOSSIL_NOISE_RANGE/2)*(1/3)
                                                ),
                                                SurfaceRules.state(
                                                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.EAST)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(
                                                        ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                                        (FULL_FOSSIL_NOISE_RANGE/2)*(1/3), (FULL_FOSSIL_NOISE_RANGE/2)*(2/3)
                                                ),
                                                SurfaceRules.state(
                                                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.SOUTH)
                                                )
                                        ),
                                        SurfaceRules.ifTrue(
                                                SurfaceRules.noiseCondition(
                                                        ResourceKey.create(Registries.NOISE, new ResourceLocation("minecraft", "offset")),
                                                        (FULL_FOSSIL_NOISE_RANGE/2)*(2/3), (FULL_FOSSIL_NOISE_RANGE/2)
                                                ),
                                                SurfaceRules.state(
                                                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get().defaultBlockState().setValue(BlockStateProperties.FACING, Direction.WEST)
                                                )
                                        )
                                )
                        )
                ),


                // Generate a layer of rock salt below the surface
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.SALT_FLAT.getKey()),
                        SurfaceRules.ifTrue(SurfaceRules.yBlockCheck(VerticalAnchor.absolute(SALT_FLAT_GROUND_LEVEL), -15), ROCK_SALT)
                ),

                // Default to a grass and dirt surface for other biomes
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
        );
    }

    // Helper method to create a state rule for a block
    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
