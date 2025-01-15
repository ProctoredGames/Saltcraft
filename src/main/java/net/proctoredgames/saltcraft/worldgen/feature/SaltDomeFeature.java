//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.worldgen.feature;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.GeodeBlockSettings;
import net.minecraft.world.level.levelgen.GeodeCrackSettings;
import net.minecraft.world.level.levelgen.GeodeLayerSettings;
import net.minecraft.world.level.levelgen.LegacyRandomSource;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.level.levelgen.synth.PerlinSimplexNoise;
import net.minecraft.world.level.material.FluidState;
import net.proctoredgames.saltcraft.block.ModBlocks;

import static java.lang.Math.pow;


public class SaltDomeFeature extends Feature<NoneFeatureConfiguration> {
    private static final Direction[] DIRECTIONS = Direction.values();

    public SaltDomeFeature(Codec<NoneFeatureConfiguration> p_159834_) {
        super(p_159834_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        BlockPos pos = context.origin();
        RandomSource random = context.random();

        PerlinSimplexNoise noise = new PerlinSimplexNoise(random, List.of(0));

        int height = 50 + random.nextInt(30);
        int radius = 15 + random.nextInt(5);
        int baseHeight = 5 + random.nextInt(5);
        int baseRadius = 70 + random.nextInt(10);
        double shapeSharpnessFactor = 1.5 + random.nextDouble()*1;

        int oilHeight;
        int oilRadius;

        BlockPos oilPosition;

        int numberOfOilPools = 1+random.nextInt(3); //between 1 and 3

        for(int oilPoolIndex = 1; oilPoolIndex<=numberOfOilPools; oilPoolIndex++) {
            oilPosition = new BlockPos(
                    pos.getX() - 20 + random.nextInt(40),
                    pos.getY() + Math.min(30+random.nextInt(40), height-10),
                    pos.getZ() - 20 + random.nextInt(40)
            );
            oilHeight = 3 + random.nextInt(4);
            oilRadius  = 5 + random.nextInt(10);

            generateOil(world,oilPosition,noise,oilHeight,oilRadius);
        }

        generateSalt(world,pos,noise,height,radius,baseHeight,baseRadius,shapeSharpnessFactor);

        return true;
    }

    public void generateSalt(WorldGenLevel pWorld, BlockPos pPos, PerlinSimplexNoise noise,
                             int pHeight, int pRadius, int pBaseHeight, int pBaseRadius, double pShapeSharpnessFactor){
        BlockState placedBlock;
        double distance;
        double adjustedBaseRadiusDistance;
        double targetHeight;

        for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-pBaseRadius, pHeight, -pBaseRadius),
                pPos.offset(pBaseRadius, 0, pBaseRadius))) {
            distance = pPos.distToCenterSqr(blockpos.getX(),
                    pPos.getY(), blockpos.getZ());

            adjustedBaseRadiusDistance = pBaseRadius-5-(noise.getValue(blockpos.getX(), blockpos.getZ(), true))*2;
            if(distance<=pow(adjustedBaseRadiusDistance,2)) {
                targetHeight = (
                        (-(pHeight - pBaseHeight)) /
                                (1 + pow(pShapeSharpnessFactor, -(Math.sqrt(distance) - pRadius)))
                ) + pHeight;

                if (blockpos.getY() <= pPos.getY() + targetHeight) {
                    placedBlock = ModBlocks.ROCK_SALT_BLOCK.get().defaultBlockState();
                    pWorld.setBlock(blockpos, placedBlock, 2);
                }
            }
        }
    }

    public void generateOil(WorldGenLevel pWorld, BlockPos pPos, PerlinSimplexNoise noise,
                            int pHeight, int pRadius){
        BlockState placedBlock;
        double distance;
        int currentDepth;
        int radiusDecreaseAmount;
        for (BlockPos blockpos : BlockPos.betweenClosed(pPos.offset(-pRadius, 0, -pRadius),
                pPos.offset(pRadius, -pHeight, pRadius))) {
            distance = pPos.distToCenterSqr(blockpos.getX(),
                    pPos.getY(), blockpos.getZ());
            currentDepth = pPos.getY()-blockpos.getY();
            radiusDecreaseAmount = (int)(Math.floor(Math.pow(currentDepth, 2))/(pHeight+1));
            if (distance<(pRadius-radiusDecreaseAmount)*(pRadius-radiusDecreaseAmount)) {
                placedBlock = ModBlocks.OIL_BLOCK.get().defaultBlockState();
                pWorld.setBlock(blockpos, placedBlock, 2);
            }

        }
    }

    public BlockState getSaltBlockToPlace(RandomSource randomSource){
        if(randomSource.nextInt(10)<3){
            return ModBlocks.ROCK_SALT_BLOCK.get().defaultBlockState();
        } else if(randomSource.nextInt(10)<7){
            return ModBlocks.CLUMPED_SALT_BLOCK.get().defaultBlockState();
        }
        return ModBlocks.SALT_BLOCK.get().defaultBlockState();
    }
}
