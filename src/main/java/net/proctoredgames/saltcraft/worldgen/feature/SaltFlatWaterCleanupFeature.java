package net.proctoredgames.saltcraft.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;

/**
 * The salt flat is flattened by a surface rule that replaces terrain above
 * {@link ModSurfaceRules#SALT_FLAT_GROUND_LEVEL} with air, but surface rules cannot
 * replace fluids, so aquifer pockets that generated inside the removed terrain are
 * left floating in midair. This feature runs in the last decoration step and clears
 * any fluid above ground level in salt flat columns.
 */
public class SaltFlatWaterCleanupFeature extends Feature<DefaultFeatureConfig> {

    public SaltFlatWaterCleanupFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        ChunkPos chunkPos = new ChunkPos(context.getOrigin());
        BlockPos.Mutable pos = new BlockPos.Mutable();
        BlockState air = Blocks.AIR.getDefaultState();
        boolean changed = false;

        for (int x = chunkPos.getStartX(); x <= chunkPos.getEndX(); ++x) {
            for (int z = chunkPos.getStartZ(); z <= chunkPos.getEndZ(); ++z) {
                int top = world.getTopY(Heightmap.Type.WORLD_SURFACE_WG, x, z);
                if (top <= ModSurfaceRules.SALT_FLAT_GROUND_LEVEL) {
                    continue;
                }

                pos.set(x, ModSurfaceRules.SALT_FLAT_GROUND_LEVEL, z);
                if (!world.getBiome(pos).matchesKey(ModBiomes.SALT_FLAT)) {
                    continue;
                }

                for (int y = ModSurfaceRules.SALT_FLAT_GROUND_LEVEL; y < top; ++y) {
                    pos.setY(y);
                    if (!world.getBlockState(pos).getFluidState().isEmpty()) {
                        world.setBlockState(pos, air, 2);
                        changed = true;
                    }
                }
            }
        }

        return changed;
    }
}
