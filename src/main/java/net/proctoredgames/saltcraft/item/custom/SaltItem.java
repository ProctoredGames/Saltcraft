package net.proctoredgames.saltcraft.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class SaltItem extends Item {
    public SaltItem(Settings settings) {
        super(settings);
    }

    public static Map<Block, Block> getBlockTransformations() {
        return Map.ofEntries(
                Map.entry(Blocks.WET_SPONGE, Blocks.SPONGE),
                Map.entry(Blocks.SNOW, Blocks.AIR),
                Map.entry(Blocks.SNOW_BLOCK, Blocks.WATER),
                Map.entry(Blocks.ICE, Blocks.WATER),
                Map.entry(Blocks.PACKED_ICE, Blocks.ICE),
                Map.entry(Blocks.POWDER_SNOW, Blocks.AIR),
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
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient) {
            ItemStack thisItemStack = context.getStack();
            World world = context.getWorld();
            BlockPos positionClicked = context.getBlockPos();
            BlockState blockState = world.getBlockState(positionClicked);

            Block replacementBlock = getBlockTransformations().get(blockState.getBlock());
            if (replacementBlock != null) {
                world.setBlockState(positionClicked, replacementBlock.getDefaultState(), 3);
                thisItemStack.decrement(1);
                return ActionResult.CONSUME;
            }
        }
        return ActionResult.SUCCESS;
    }
}
