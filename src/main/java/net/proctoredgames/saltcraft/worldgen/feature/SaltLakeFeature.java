package net.proctoredgames.saltcraft.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;

@Deprecated
public class SaltLakeFeature extends Feature<DefaultFeatureConfig> {
    private static final BlockState AIR = Blocks.CAVE_AIR.getDefaultState();

    public SaltLakeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos origin = context.getOrigin();
        StructureWorldAccess world = context.getWorld();
        Random random = context.getRandom();
        if (origin.getY() <= world.getBottomY() + 2) {
            return false;
        }

        // Features may only place blocks in the decorated chunk and its direct neighbors.
        // The 32x32 area must be anchored to the chunk rather than the random origin,
        // or its far edge lands outside that range where setBlockState silently fails and
        // the lake gets cut off along chunk borders.
        ChunkPos chunkPos = new ChunkPos(origin);
        origin = new BlockPos(chunkPos.getStartX() - 8, origin.getY(), chunkPos.getStartZ() - 8).down(2);
        boolean[] shape = new boolean[8192];
        int blobs = random.nextInt(8) + 8;

        for (int blob = 0; blob < blobs; ++blob) {
            double sizeX = random.nextDouble() * 12.0 + 6.0;
            double sizeY = random.nextDouble() * 8.0 + 4.0;
            double sizeZ = random.nextDouble() * 12.0 + 6.0;
            double offX = random.nextDouble() * (32.0 - sizeX - 4.0) + 2.0 + sizeX / 2.0;
            double offY = random.nextDouble() * (16.0 - sizeY - 8.0) + 4.0 + sizeY / 2.0;
            double offZ = random.nextDouble() * (32.0 - sizeZ - 4.0) + 2.0 + sizeZ / 2.0;

            for (int x = 1; x < 31; ++x) {
                for (int z = 1; z < 31; ++z) {
                    for (int y = 1; y < 7; ++y) {
                        double dx = ((double) x - offX) / (sizeX / 2.0);
                        double dy = ((double) y - offY) / (sizeY / 2.0);
                        double dz = ((double) z - offZ) / (sizeZ / 2.0);
                        double d = dx * dx + dy * dy + dz * dz;
                        if (d < 3.0) {
                            shape[(x * 32 + z) * 8 + y] = true;
                        }
                    }
                }
            }
        }

        BlockState waterloggedSlab = ModBlocks.ROCK_SALT_SLAB.getDefaultState().with(Properties.WATERLOGGED, true);

        // make sure we have room to place the structure
        for (int x = 0; x < 32; ++x) {
            for (int z = 0; z < 32; ++z) {
                for (int y = 0; y < 8; ++y) {
                    boolean isBorder = !shape[(x * 32 + z) * 8 + y]
                            && (x < 31 && shape[((x + 1) * 32 + z) * 8 + y]
                            || x > 0 && shape[((x - 1) * 32 + z) * 8 + y]
                            || z < 31 && shape[(x * 32 + z + 1) * 8 + y]
                            || z > 0 && shape[(x * 32 + (z - 1)) * 8 + y]
                            || y < 7 && shape[(x * 32 + z) * 8 + y + 1]
                            || y > 0 && shape[(x * 32 + z) * 8 + (y - 1)]);
                    if (isBorder) {
                        BlockState state = world.getBlockState(origin.add(x, y, z));
                        if (y >= 2 && state.isLiquid()) {
                            return false;
                        }

                        if (y < 2 && !state.isSolid() && world.getBlockState(origin.add(x, y, z)) != waterloggedSlab) {
                            return false;
                        }
                    }
                }
            }
        }

        // places filler blocks
        for (int x = 0; x < 32; ++x) {
            for (int z = 0; z < 32; ++z) {
                for (int y = 0; y < 8; ++y) {
                    if (shape[(x * 32 + z) * 8 + y]) {
                        BlockPos pos = origin.add(x, y, z);
                        if (this.canReplaceBlock(world.getBlockState(pos))) {
                            boolean isTop = y >= 2;
                            world.setBlockState(pos, isTop ? AIR : waterloggedSlab, 2);
                            if (isTop) {
                                world.scheduleBlockTick(pos, AIR.getBlock(), 0);
                                this.markBlocksAboveForPostProcessing(world, pos);
                            }
                            if (!isTop && y == 1 && random.nextInt(50) == 0) {
                                Flamingo flamingo = new Flamingo(ModEntities.FLAMINGO, world.toServerWorld());
                                flamingo.setPosition(origin.add(x, y + 1, z).toCenterPos());
                                world.spawnEntity(flamingo);
                            }
                        }
                    }
                }
            }
        }

        BlockState border = ModBlocks.ROCK_SALT_BLOCK.getDefaultState();
        // places border blocks
        if (!border.isAir()) {
            for (int x = 0; x < 32; ++x) {
                for (int z = 0; z < 32; ++z) {
                    for (int y = 0; y < 8; ++y) {
                        boolean isBorder = !shape[(x * 32 + z) * 8 + y]
                                && (x < 31 && shape[((x + 1) * 32 + z) * 8 + y]
                                || x > 0 && shape[((x - 1) * 32 + z) * 8 + y]
                                || z < 31 && shape[(x * 32 + z + 1) * 8 + y]
                                || z > 0 && shape[(x * 32 + (z - 1)) * 8 + y]
                                || y < 7 && shape[(x * 32 + z) * 8 + y + 1]
                                || y > 0 && shape[(x * 32 + z) * 8 + (y - 1)]);
                        if (isBorder && (y < 2 || random.nextInt(2) != 0)) {
                            BlockState state = world.getBlockState(origin.add(x, y, z));
                            if (state.isSolid() && !state.isIn(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE)) {
                                BlockPos pos = origin.add(x, y, z);
                                world.setBlockState(pos, border, 2);
                                this.markBlocksAboveForPostProcessing(world, pos);
                            }
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean canReplaceBlock(BlockState state) {
        return !state.isIn(BlockTags.FEATURES_CANNOT_REPLACE);
    }
}
