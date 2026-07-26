package net.proctoredgames.saltcraft.event;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityWorldChangeEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.component.type.PotionContentsComponent;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.mixin.MobEntityAccessor;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncPayload;
import net.proctoredgames.saltcraft.potion.ModPotions;
import net.proctoredgames.saltcraft.thirst.PlayerThirst;

public class ModEvents {
    public static void register() {
        registerWanderingTrades();

        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if (entity instanceof ServerPlayerEntity player) {
                PlayerThirst thirst = player.getAttachedOrCreate(PlayerThirst.THIRST);
                ModMessages.sendToPlayer(new ThirstDataSyncPayload(thirst.getThirst()), player);
            }
            if (entity instanceof TurtleEntity turtle) {
                MobEntityAccessor accessor = (MobEntityAccessor) turtle;
                accessor.saltcraft$getGoalSelector().add(2, new MeleeAttackGoal(turtle, 1.0, true));
                accessor.saltcraft$getTargetSelector().add(2, new ActiveTargetGoal<>(turtle, Jellyfish.class, true));
            }
        });

        ServerEntityWorldChangeEvents.AFTER_PLAYER_CHANGE_WORLD.register((player, origin, destination) -> {
            PlayerThirst thirst = player.getAttachedOrCreate(PlayerThirst.THIRST);
            ModMessages.sendToPlayer(new ThirstDataSyncPayload(thirst.getThirst()), player);
        });
    }

    private static void registerWanderingTrades() {
        // Level 1 = the generic trade pool, level 2 = the rare pool
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 16),
                    new ItemStack(ModItems.JELLYFISH_BUCKET, 1),
                    10, 2, 0.2f));

            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 6),
                    new ItemStack(ModBlocks.ROCK_SALT_STAIRS, 3),
                    10, 2, 0.2f));

            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 8),
                    new ItemStack(ModBlocks.CLUMPED_SALT_BLOCK, 6),
                    10, 2, 0.2f));
        });

        TradeOfferHelper.registerWanderingTraderOffers(2, factories -> {
            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 12),
                    new ItemStack(ModBlocks.PINK_SALT_BLOCK, 1),
                    10, 2, 0.2f));

            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 10),
                    new ItemStack(ModItems.OIL_BUCKET, 2),
                    10, 2, 0.2f));

            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    new ItemStack(ModItems.OIL_CAN, 1),
                    10, 2, 0.2f));

            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 5),
                    PotionContentsComponent.createStack(Items.POTION, Registries.POTION.getEntry(ModPotions.SALT_WATER_BOTTLE)),
                    10, 2, 0.2f));

            factories.add((trader, random) -> new TradeOffer(
                    new TradedItem(Items.EMERALD, 20),
                    PotionContentsComponent.createStack(Items.POTION, Registries.POTION.getEntry(ModPotions.PINK_SALT_WATER_BOTTLE)),
                    10, 2, 0.2f));
        });
    }
}
