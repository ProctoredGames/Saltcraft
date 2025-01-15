//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.item.custom;

import java.util.List;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.item.ModItems;

public class OilCanItem extends Item {
    public OilCanItem(Item.Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {

        ItemStack whatHandItem = pPlayer.getItemInHand(pHand);

        BlockHitResult $$7 = getPlayerPOVHitResult(pLevel, pPlayer, Fluid.SOURCE_ONLY);
        if ($$7.getType() == Type.MISS) {
            return InteractionResultHolder.pass(whatHandItem);
        } else {
            if ($$7.getType() == Type.BLOCK) {
                BlockPos $$8 = $$7.getBlockPos();
                if (!pLevel.mayInteract(pPlayer, $$8)) {
                    return InteractionResultHolder.pass(whatHandItem);
                }

                if (pLevel.getFluidState($$8).getType() == ModFluids.SOURCE_OIL.get()) {
                    pLevel.playSound(pPlayer, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);
                    pLevel.gameEvent(pPlayer, GameEvent.FLUID_PICKUP, $$8);
                    return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(whatHandItem, pPlayer, new ItemStack(ModItems.FILLED_OIL_CAN.get())), pLevel.isClientSide());
                }
            }

            return InteractionResultHolder.pass(whatHandItem);
        }
    }

    protected ItemStack turnBottleIntoItem(ItemStack pBottleStack, Player pPlayer, ItemStack pFilledBottleStack) {
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(pBottleStack, pPlayer, pFilledBottleStack);
    }
}
