package net.proctoredgames.saltcraft.worldgen.biome.surface;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;

public class ModSurfaceRules {

    private static final MaterialRules.MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRules.MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRules.MaterialRule CLUMPED_SALT = makeStateRule(ModBlocks.CLUMPED_SALT_BLOCK);
    private static final MaterialRules.MaterialRule ROCK_SALT = makeStateRule(ModBlocks.ROCK_SALT_BLOCK);
    private static final MaterialRules.MaterialRule AIR = makeStateRule(Blocks.AIR);

    public static final int SALT_FLAT_GROUND_LEVEL = 65;
    public static final double FULL_FOSSIL_NOISE_RANGE = 0.05;

    public static MaterialRules.MaterialRule makeRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);

        MaterialRules.MaterialRule grassSurface = MaterialRules.sequence(
                MaterialRules.condition(isAtOrAboveWaterLevel, GRASS_BLOCK),
                DIRT
        );

        return MaterialRules.sequence(
                // Create a flat terrain appearance by setting blocks to AIR above the ground level
                MaterialRules.condition(MaterialRules.biome(ModBiomes.SALT_FLAT),
                        MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(SALT_FLAT_GROUND_LEVEL), 0), AIR)
                ),

                // Set clumped salt on the floor of caves below the ground level
                MaterialRules.condition(MaterialRules.biome(ModBiomes.SALT_FLAT),
                        MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(SALT_FLAT_GROUND_LEVEL), -10000),
                                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, CLUMPED_SALT)
                        )
                ),

                // Set rock salt on the ceiling of caves
                MaterialRules.condition(MaterialRules.biome(ModBiomes.SALT_FLAT),
                        MaterialRules.condition(MaterialRules.STONE_DEPTH_CEILING, ROCK_SALT)
                ),

                // Generate a layer of clumped salt near the surface
                MaterialRules.condition(MaterialRules.biome(ModBiomes.SALT_FLAT),
                        MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(SALT_FLAT_GROUND_LEVEL), -2), CLUMPED_SALT)
                ),

                // Noise-banded rock salt fossils, oriented by which slice of the noise band they land in
                MaterialRules.condition(MaterialRules.biome(ModBiomes.SALT_FLAT),
                        MaterialRules.condition(
                                MaterialRules.noiseThreshold(NoiseParametersKeys.OFFSET,
                                        -1 * (FULL_FOSSIL_NOISE_RANGE / 2), FULL_FOSSIL_NOISE_RANGE / 2),
                                MaterialRules.condition(
                                        MaterialRules.aboveY(YOffset.fixed(SALT_FLAT_GROUND_LEVEL), -15),
                                        MaterialRules.sequence(
                                                fossilBand(-1 * (FULL_FOSSIL_NOISE_RANGE / 2), -1 * (FULL_FOSSIL_NOISE_RANGE / 2) * (2.0 / 3), Direction.DOWN),
                                                fossilBand(-1 * (FULL_FOSSIL_NOISE_RANGE / 2) * (2.0 / 3), -1 * (FULL_FOSSIL_NOISE_RANGE / 2) * (1.0 / 3), Direction.UP),
                                                fossilBand(-1 * (FULL_FOSSIL_NOISE_RANGE / 2) * (1.0 / 3), 0.0, Direction.NORTH),
                                                fossilBand(0.0, (FULL_FOSSIL_NOISE_RANGE / 2) * (1.0 / 3), Direction.EAST),
                                                fossilBand((FULL_FOSSIL_NOISE_RANGE / 2) * (1.0 / 3), (FULL_FOSSIL_NOISE_RANGE / 2) * (2.0 / 3), Direction.SOUTH),
                                                fossilBand((FULL_FOSSIL_NOISE_RANGE / 2) * (2.0 / 3), FULL_FOSSIL_NOISE_RANGE / 2, Direction.WEST)
                                        )
                                )
                        )
                ),

                // Generate a layer of rock salt below the surface
                MaterialRules.condition(MaterialRules.biome(ModBiomes.SALT_FLAT),
                        MaterialRules.condition(MaterialRules.aboveY(YOffset.fixed(SALT_FLAT_GROUND_LEVEL), -15), ROCK_SALT)
                ),

                // Default to a grass and dirt surface for other biomes
                MaterialRules.condition(MaterialRules.STONE_DEPTH_FLOOR, grassSurface)
        );
    }

    private static MaterialRules.MaterialRule fossilBand(double min, double max, Direction facing) {
        return MaterialRules.condition(
                MaterialRules.noiseThreshold(NoiseParametersKeys.OFFSET, min, max),
                MaterialRules.block(ModBlocks.ROCK_SALT_FOSSIL_BLOCK.getDefaultState().with(Properties.FACING, facing))
        );
    }

    private static MaterialRules.MaterialRule makeStateRule(Block block) {
        return MaterialRules.block(block.getDefaultState());
    }
}
