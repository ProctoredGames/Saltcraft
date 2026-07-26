package net.proctoredgames.saltcraft.item.custom;

import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import net.proctoredgames.saltcraft.item.ModItems;

import java.util.List;

public class FilledOilCanItem extends Item {
    public FilledOilCanItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack handItem = player.getStackInHand(hand);

        List<IronGolemEntity> ironGolems = world.getEntitiesByClass(IronGolemEntity.class, player.getBoundingBox().expand(2.0),
                golem -> golem != null && golem.isAlive());
        List<AbstractMinecartEntity> minecarts = world.getEntitiesByClass(AbstractMinecartEntity.class, player.getBoundingBox().expand(2.0),
                cart -> cart != null && cart.isAlive());

        if (!ironGolems.isEmpty()) {
            IronGolemEntity golem = ironGolems.get(0);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(player, GameEvent.ENTITY_INTERACT, player.getPos());
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayer, handItem, golem);
                golem.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 120, 2));
            }

            return TypedActionResult.success(this.turnCanIntoItem(handItem, player, new ItemStack(ModItems.OIL_CAN)), world.isClient);

        } else if (!minecarts.isEmpty()) {
            AbstractMinecartEntity minecart = minecarts.get(0);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ITEM_BOTTLE_EMPTY, SoundCategory.NEUTRAL, 1.0F, 1.0F);
            world.emitGameEvent(player, GameEvent.ENTITY_INTERACT, player.getPos());
            if (player instanceof ServerPlayerEntity serverPlayer) {
                Criteria.PLAYER_INTERACTED_WITH_ENTITY.trigger(serverPlayer, handItem, minecart);
                double speedMultiplier = 1.5;
                Vec3d currentVelocity = minecart.getVelocity();
                Vec3d newVelocity = currentVelocity.multiply(speedMultiplier, 1, speedMultiplier);
                minecart.setVelocity(newVelocity);
            }

            return TypedActionResult.success(this.turnCanIntoItem(handItem, player, new ItemStack(ModItems.OIL_CAN)), world.isClient);

        } else {
            return TypedActionResult.pass(handItem);
        }
    }

    protected ItemStack turnCanIntoItem(ItemStack canStack, PlayerEntity player, ItemStack filledCanStack) {
        player.incrementStat(Stats.USED.getOrCreateStat(this));
        return ItemUsage.exchangeStack(canStack, player, filledCanStack);
    }
}
