package net.proctoredgames.saltcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class SaltFallingBlock extends FallingBlock {
    public SaltFallingBlock(Properties pProperties) {
        super(pProperties);
    }
    Map<Block, Block> BLOCK_TRANSFORMATIONS = Map.ofEntries(
            Map.entry(Blocks.WET_SPONGE, Blocks.SPONGE),
            Map.entry(Blocks.SNOW, Blocks.AIR),
            Map.entry(Blocks.SNOW_BLOCK, Blocks.WATER),
            Map.entry(Blocks.ICE, Blocks.WATER),
            Map.entry(Blocks.PACKED_ICE, Blocks.ICE),
            Map.entry(Blocks.POWDER_SNOW, Blocks.WATER),
//            Map.entry(Blocks.POWDER_SNOW_CAULDRON, Blocks.WATER_CAULDRON),
            Map.entry(Blocks.MUD, Blocks.DIRT),
            Map.entry(Blocks.MOSSY_COBBLESTONE, Blocks.COBBLESTONE),
            Map.entry(Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.COBBLESTONE_SLAB),
            Map.entry(Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.COBBLESTONE_STAIRS),
            Map.entry(Blocks.MOSSY_COBBLESTONE_WALL, Blocks.COBBLESTONE_WALL),
            Map.entry(Blocks.MOSSY_STONE_BRICKS, Blocks.STONE_BRICKS),
            Map.entry(Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.STONE_BRICK_SLAB),
            Map.entry(Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.STONE_BRICK_STAIRS),
            Map.entry(Blocks.MOSSY_STONE_BRICK_WALL, Blocks.STONE_BRICK_WALL),
            Map.entry(Blocks.INFESTED_MOSSY_STONE_BRICKS, Blocks.INFESTED_STONE_BRICKS)
    );

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        for(int i = 0; i< pRandom.nextInt(6); i++){
            BlockPos adjacentPos = pPos.relative(Direction.getRandom(pRandom));
            BlockState adjacentState = pLevel.getBlockState(adjacentPos);
            Block replacementBlock = BLOCK_TRANSFORMATIONS.get(adjacentState.getBlock());
            if (replacementBlock != null) {
                pLevel.setBlock(adjacentPos, replacementBlock.defaultBlockState(), 3);
            }
        }
    }
}
