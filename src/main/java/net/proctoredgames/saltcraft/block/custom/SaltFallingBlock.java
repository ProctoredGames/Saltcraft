package net.proctoredgames.saltcraft.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FallingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.proctoredgames.saltcraft.item.custom.SaltItem;

import java.util.Map;

public class SaltFallingBlock extends FallingBlock {
    public SaltFallingBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        for(int i = 0; i< pRandom.nextInt(6); i++){
            BlockPos adjacentPos = pPos.relative(Direction.getRandom(pRandom));
            BlockState adjacentState = pLevel.getBlockState(adjacentPos);
            Block replacementBlock = SaltItem.getBlockTransformations().get(adjacentState.getBlock());
            if (replacementBlock != null && adjacentState.getBlock() != Blocks.POWDER_SNOW_CAULDRON) {
                pLevel.setBlock(adjacentPos, replacementBlock.defaultBlockState(), 3);
            }
        }
    }
}
