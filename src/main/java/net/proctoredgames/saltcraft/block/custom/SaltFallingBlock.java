package net.proctoredgames.saltcraft.block.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.proctoredgames.saltcraft.item.custom.SaltItem;

public class SaltFallingBlock extends FallingBlock {
    public static final MapCodec<SaltFallingBlock> CODEC = createCodec(SaltFallingBlock::new);

    public SaltFallingBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        for (int i = 0; i < random.nextInt(6); i++) {
            BlockPos adjacentPos = pos.offset(Direction.random(random));
            BlockState adjacentState = world.getBlockState(adjacentPos);
            Block replacementBlock = SaltItem.getBlockTransformations().get(adjacentState.getBlock());
            if (replacementBlock != null && adjacentState.getBlock() != Blocks.POWDER_SNOW_CAULDRON) {
                world.setBlockState(adjacentPos, replacementBlock.getDefaultState(), 3);
            }
        }
    }
}
