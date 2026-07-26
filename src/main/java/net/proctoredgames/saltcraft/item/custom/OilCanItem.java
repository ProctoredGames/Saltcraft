package net.proctoredgames.saltcraft.item.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.item.ModItems;

public class OilCanItem extends Item {
    public OilCanItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handItem = player.getStackInHand(hand);

        BlockHitResult hit = raycast(world, player, RaycastContext.FluidHandling.SOURCE_ONLY);
        if (hit.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(handItem);
        } else {
            if (hit.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = hit.getBlockPos();
                if (!world.canPlayerModifyAt(player, pos)) {
                    return TypedActionResult.pass(handItem);
                }

                if (world.getFluidState(pos).getFluid() == ModFluids.OIL) {
                    world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                    world.emitGameEvent(player, GameEvent.FLUID_PICKUP, pos);
                    return TypedActionResult.success(this.turnCanIntoItem(handItem, player, new ItemStack(ModItems.FILLED_OIL_CAN)), world.isClient);
                }
            }

            return TypedActionResult.pass(handItem);
        }
    }

    protected ItemStack turnCanIntoItem(ItemStack canStack, PlayerEntity player, ItemStack filledCanStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(canStack, player, filledCanStack);
    }
}
