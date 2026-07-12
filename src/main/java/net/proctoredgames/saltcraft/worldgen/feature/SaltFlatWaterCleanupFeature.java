package net.proctoredgames.saltcraft.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;

/**
 * The salt flat is flattened by a surface rule that replaces terrain above
 * {@link ModSurfaceRules#SALT_FLAT_GROUND_LEVEL} with air, but surface rules cannot
 * replace fluids, so aquifer pockets that generated inside the removed terrain are
 * left floating in midair. This feature runs in the last decoration step and clears
 * any fluid above ground level in salt flat columns.
 */
public class SaltFlatWaterCleanupFeature extends Feature<NoneFeatureConfiguration> {

    public SaltFlatWaterCleanupFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        ChunkPos chunkPos = new ChunkPos(context.origin());
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        BlockState air = Blocks.AIR.defaultBlockState();
        boolean changed = false;

        for (int x = chunkPos.getMinBlockX(); x <= chunkPos.getMaxBlockX(); ++x) {
            for (int z = chunkPos.getMinBlockZ(); z <= chunkPos.getMaxBlockZ(); ++z) {
                int top = level.getHeight(Heightmap.Types.WORLD_SURFACE_WG, x, z);
                if (top <= ModSurfaceRules.SALT_FLAT_GROUND_LEVEL) {
                    continue;
                }

                pos.set(x, ModSurfaceRules.SALT_FLAT_GROUND_LEVEL, z);
                if (!level.getBiome(pos).is(ModBiomes.SALT_FLAT)) {
                    continue;
                }

                for (int y = ModSurfaceRules.SALT_FLAT_GROUND_LEVEL; y < top; ++y) {
                    pos.setY(y);
                    if (!level.getBlockState(pos).getFluidState().isEmpty()) {
                        level.setBlock(pos, air, 2);
                        changed = true;
                    }
                }
            }
        }

        return changed;
    }
}
