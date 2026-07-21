package net.proctoredgames.saltcraft.worldgen.feature;

import com.mojang.serialization.Codec;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.proctoredgames.saltcraft.block.ModBlocks;

/**
 * Salt domes are up to ~160 blocks wide, far larger than the area a feature may write to
 * (the decorated chunk plus one neighbor). Generating the whole dome from one placement
 * origin silently loses every block outside that window, slicing the dome along chunk
 * borders. Instead, this feature runs for every chunk: the world is divided into
 * {@code CELL_SIZE} cells, each cell deterministically derives one dome (position and
 * shape from the cell's seed), and each chunk places only its own slice of that dome.
 * Every chunk recomputes identical dome parameters, so the slices line up seamlessly.
 */
public class SaltDomeFeature extends Feature<NoneFeatureConfiguration> {

    // One dome per 512x512 cell matches the old 1-in-1000-chunks rarity
    private static final int CELL_SIZE = 512;
    // Upper bound on how far dome blocks can reach from the dome center (max baseRadius
    // is 80 and noise widens it by at most ~2). Dome centers keep this margin from the
    // cell border, so a chunk only ever intersects its own cell's dome.
    private static final int MAX_REACH = 96;

    public SaltDomeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        ChunkPos chunkPos = new ChunkPos(context.origin());

        int cellX = Math.floorDiv(chunkPos.getMinBlockX(), CELL_SIZE);
        int cellZ = Math.floorDiv(chunkPos.getMinBlockZ(), CELL_SIZE);

        long cellSeed = world.getSeed() ^ (cellX * 341873128712L + cellZ * 132897987541L);
        RandomSource random = RandomSource.create(cellSeed);

        int centerX = cellX * CELL_SIZE + MAX_REACH + random.nextInt(CELL_SIZE - 2 * MAX_REACH);
        int centerZ = cellZ * CELL_SIZE + MAX_REACH + random.nextInt(CELL_SIZE - 2 * MAX_REACH);

        if (chunkPos.getMinBlockX() > centerX + MAX_REACH || chunkPos.getMaxBlockX() < centerX - MAX_REACH
                || chunkPos.getMinBlockZ() > centerZ + MAX_REACH || chunkPos.getMaxBlockZ() < centerZ - MAX_REACH) {
            return false;
        }

        BlockPos center = new BlockPos(centerX, 0, centerZ);

        int height = 50 + random.nextInt(30);
        int radius = 15 + random.nextInt(5);
        int baseHeight = 5 + random.nextInt(5);
        int baseRadius = 70 + random.nextInt(10);
        double shapeSharpnessFactor = 1.5 + random.nextDouble();

        int numberOfOilPools = 1 + random.nextInt(3);
        BlockPos[] oilPositions = new BlockPos[numberOfOilPools];
        int[] oilHeights = new int[numberOfOilPools];
        int[] oilRadii = new int[numberOfOilPools];
        for (int i = 0; i < numberOfOilPools; i++) {
            oilPositions[i] = new BlockPos(
                    centerX - 20 + random.nextInt(40),
                    Math.min(30 + random.nextInt(40), height - 10),
                    centerZ - 20 + random.nextInt(40)
            );
            oilHeights[i] = 3 + random.nextInt(4);
            oilRadii[i] = 5 + random.nextInt(10);
        }

        PerlinSimplexNoise noise = new PerlinSimplexNoise(random, List.of(0));

        // Salt first, oil after — the other way round the dome fill overwrites the pools
        boolean placed = generateSalt(world, chunkPos, center, noise, height, radius, baseHeight, baseRadius, shapeSharpnessFactor);
        for (int i = 0; i < numberOfOilPools; i++) {
            placed |= generateOil(world, chunkPos, oilPositions[i], oilHeights[i], oilRadii[i]);
        }

        return placed;
    }

    private boolean generateSalt(WorldGenLevel pWorld, ChunkPos pChunk, BlockPos pPos, PerlinSimplexNoise noise,
                                 int pHeight, int pRadius, int pBaseHeight, int pBaseRadius, double pShapeSharpnessFactor) {
        int minX = Math.max(pChunk.getMinBlockX(), pPos.getX() - pBaseRadius);
        int maxX = Math.min(pChunk.getMaxBlockX(), pPos.getX() + pBaseRadius);
        int minZ = Math.max(pChunk.getMinBlockZ(), pPos.getZ() - pBaseRadius);
        int maxZ = Math.min(pChunk.getMaxBlockZ(), pPos.getZ() + pBaseRadius);
        if (minX > maxX || minZ > maxZ) {
            return false;
        }

        BlockState rockSalt = ModBlocks.ROCK_SALT_BLOCK.get().defaultBlockState();
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos();
        boolean placed = false;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double distance = pPos.distToCenterSqr(x, pPos.getY(), z);
                double adjustedBaseRadiusDistance = pBaseRadius - 5 - (noise.getValue(x, z, true)) * 2;
                if (distance <= adjustedBaseRadiusDistance * adjustedBaseRadiusDistance) {
                    double targetHeight = (
                            (-(pHeight - pBaseHeight)) /
                                    (1 + Math.pow(pShapeSharpnessFactor, -(Math.sqrt(distance) - pRadius)))
                    ) + pHeight;

                    int top = pPos.getY() + (int) Math.floor(targetHeight);
                    for (int y = pPos.getY(); y <= top; y++) {
                        blockpos.set(x, y, z);
                        pWorld.setBlock(blockpos, rockSalt, 2);
                        placed = true;
                    }
                }
            }
        }
        return placed;
    }

    private boolean generateOil(WorldGenLevel pWorld, ChunkPos pChunk, BlockPos pPos, int pHeight, int pRadius) {
        int minX = Math.max(pChunk.getMinBlockX(), pPos.getX() - pRadius);
        int maxX = Math.min(pChunk.getMaxBlockX(), pPos.getX() + pRadius);
        int minZ = Math.max(pChunk.getMinBlockZ(), pPos.getZ() - pRadius);
        int maxZ = Math.min(pChunk.getMaxBlockZ(), pPos.getZ() + pRadius);
        if (minX > maxX || minZ > maxZ) {
            return false;
        }

        BlockState oil = ModBlocks.OIL_BLOCK.get().defaultBlockState();
        BlockPos.MutableBlockPos blockpos = new BlockPos.MutableBlockPos();
        boolean placed = false;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double distance = pPos.distToCenterSqr(x, pPos.getY(), z);
                for (int y = pPos.getY() - pHeight; y <= pPos.getY(); y++) {
                    int currentDepth = pPos.getY() - y;
                    int radiusDecreaseAmount = (int) (Math.floor(Math.pow(currentDepth, 2)) / (pHeight + 1));
                    if (distance < (double) (pRadius - radiusDecreaseAmount) * (pRadius - radiusDecreaseAmount)) {
                        blockpos.set(x, y, z);
                        pWorld.setBlock(blockpos, oil, 2);
                        placed = true;
                    }
                }
            }
        }
        return placed;
    }
}
