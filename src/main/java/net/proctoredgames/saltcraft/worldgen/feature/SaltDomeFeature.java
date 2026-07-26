package net.proctoredgames.saltcraft.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.noise.OctaveSimplexNoiseSampler;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.proctoredgames.saltcraft.block.ModBlocks;

import java.util.List;

/**
 * Salt domes are up to ~160 blocks wide, far larger than the area a feature may write to
 * (the decorated chunk plus one neighbor). Generating the whole dome from one placement
 * origin silently loses every block outside that window, slicing the dome along chunk
 * borders. Instead, this feature runs for every chunk: the world is divided into
 * {@code CELL_SIZE} cells, each cell deterministically derives one dome (position and
 * shape from the cell's seed), and each chunk places only its own slice of that dome.
 * Every chunk recomputes identical dome parameters, so the slices line up seamlessly.
 */
public class SaltDomeFeature extends Feature<DefaultFeatureConfig> {

    // One dome per 512x512 cell matches the old 1-in-1000-chunks rarity
    private static final int CELL_SIZE = 512;
    // Upper bound on how far dome blocks can reach from the dome center (max baseRadius
    // is 80 and noise widens it by at most ~2). Dome centers keep this margin from the
    // cell border, so a chunk only ever intersects its own cell's dome.
    private static final int MAX_REACH = 96;

    public SaltDomeFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess world = context.getWorld();
        ChunkPos chunkPos = new ChunkPos(context.getOrigin());

        int cellX = Math.floorDiv(chunkPos.getStartX(), CELL_SIZE);
        int cellZ = Math.floorDiv(chunkPos.getStartZ(), CELL_SIZE);

        long cellSeed = world.getSeed() ^ (cellX * 341873128712L + cellZ * 132897987541L);
        Random random = Random.create(cellSeed);

        int centerX = cellX * CELL_SIZE + MAX_REACH + random.nextInt(CELL_SIZE - 2 * MAX_REACH);
        int centerZ = cellZ * CELL_SIZE + MAX_REACH + random.nextInt(CELL_SIZE - 2 * MAX_REACH);

        if (chunkPos.getStartX() > centerX + MAX_REACH || chunkPos.getEndX() < centerX - MAX_REACH
                || chunkPos.getStartZ() > centerZ + MAX_REACH || chunkPos.getEndZ() < centerZ - MAX_REACH) {
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

        OctaveSimplexNoiseSampler noise = new OctaveSimplexNoiseSampler(random, List.of(0));

        // Salt first, oil after — the other way round the dome fill overwrites the pools
        boolean placed = generateSalt(world, chunkPos, center, noise, height, radius, baseHeight, baseRadius, shapeSharpnessFactor);
        for (int i = 0; i < numberOfOilPools; i++) {
            placed |= generateOil(world, chunkPos, oilPositions[i], oilHeights[i], oilRadii[i]);
        }

        return placed;
    }

    private boolean generateSalt(StructureWorldAccess world, ChunkPos chunk, BlockPos pos, OctaveSimplexNoiseSampler noise,
                                 int height, int radius, int baseHeight, int baseRadius, double shapeSharpnessFactor) {
        int minX = Math.max(chunk.getStartX(), pos.getX() - baseRadius);
        int maxX = Math.min(chunk.getEndX(), pos.getX() + baseRadius);
        int minZ = Math.max(chunk.getStartZ(), pos.getZ() - baseRadius);
        int maxZ = Math.min(chunk.getEndZ(), pos.getZ() + baseRadius);
        if (minX > maxX || minZ > maxZ) {
            return false;
        }

        BlockState rockSalt = ModBlocks.ROCK_SALT_BLOCK.getDefaultState();
        BlockPos.Mutable blockPos = new BlockPos.Mutable();
        boolean placed = false;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double distance = pos.getSquaredDistanceFromCenter(x, pos.getY(), z);
                double adjustedBaseRadiusDistance = baseRadius - 5 - (noise.sample(x, z, true)) * 2;
                if (distance <= adjustedBaseRadiusDistance * adjustedBaseRadiusDistance) {
                    double targetHeight = (
                            (-(height - baseHeight)) /
                                    (1 + Math.pow(shapeSharpnessFactor, -(Math.sqrt(distance) - radius)))
                    ) + height;

                    int top = pos.getY() + (int) Math.floor(targetHeight);
                    for (int y = pos.getY(); y <= top; y++) {
                        blockPos.set(x, y, z);
                        world.setBlockState(blockPos, rockSalt, 2);
                        placed = true;
                    }
                }
            }
        }
        return placed;
    }

    private boolean generateOil(StructureWorldAccess world, ChunkPos chunk, BlockPos pos, int height, int radius) {
        int minX = Math.max(chunk.getStartX(), pos.getX() - radius);
        int maxX = Math.min(chunk.getEndX(), pos.getX() + radius);
        int minZ = Math.max(chunk.getStartZ(), pos.getZ() - radius);
        int maxZ = Math.min(chunk.getEndZ(), pos.getZ() + radius);
        if (minX > maxX || minZ > maxZ) {
            return false;
        }

        BlockState oil = ModBlocks.OIL_BLOCK.getDefaultState();
        BlockPos.Mutable blockPos = new BlockPos.Mutable();
        boolean placed = false;

        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                double distance = pos.getSquaredDistanceFromCenter(x, pos.getY(), z);
                for (int y = pos.getY() - height; y <= pos.getY(); y++) {
                    int currentDepth = pos.getY() - y;
                    int radiusDecreaseAmount = (int) (Math.floor(Math.pow(currentDepth, 2)) / (height + 1));
                    if (distance < (double) (radius - radiusDecreaseAmount) * (radius - radiusDecreaseAmount)) {
                        blockPos.set(x, y, z);
                        world.setBlockState(blockPos, oil, 2);
                        placed = true;
                    }
                }
            }
        }
        return placed;
    }
}
