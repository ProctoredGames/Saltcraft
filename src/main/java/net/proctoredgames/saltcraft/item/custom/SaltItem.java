package net.proctoredgames.saltcraft.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;

public class SaltItem extends Item {
    public SaltItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()) {
            Player player = pContext.getPlayer();
            ItemStack thisItemStack = pContext.getItemInHand();
            Level level = player.level();
            BlockPos positionClicked = pContext.getClickedPos();
            BlockState blockState = pContext.getLevel().getBlockState(positionClicked);

            // Define the transformations using a map
            Map<Block, Block> BLOCK_TRANSFORMATIONS = Map.ofEntries(
                    Map.entry(Blocks.WET_SPONGE, Blocks.SPONGE),
                    Map.entry(Blocks.SNOW, Blocks.AIR),
                    Map.entry(Blocks.SNOW_BLOCK, Blocks.WATER),
                    Map.entry(Blocks.ICE, Blocks.WATER),
                    Map.entry(Blocks.PACKED_ICE, Blocks.ICE),
                    Map.entry(Blocks.POWDER_SNOW, Blocks.WATER),
                    Map.entry(Blocks.POWDER_SNOW_CAULDRON, Blocks.WATER_CAULDRON),
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

            // Check if the current block can be transformed
            Block replacementBlock = BLOCK_TRANSFORMATIONS.get(blockState.getBlock());
            if (replacementBlock != null) {
                level.setBlock(positionClicked, replacementBlock.defaultBlockState(), 3);
                thisItemStack.shrink(1); // Reduce the item stack by 1
                return InteractionResult.CONSUME; // Indicate the item was used
            }
        }
        return InteractionResult.SUCCESS; // Default result if no transformation occurred
    }
}
