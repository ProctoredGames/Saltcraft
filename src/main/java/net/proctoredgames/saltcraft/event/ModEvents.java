package net.proctoredgames.saltcraft.event;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.Foods;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.EnchantedBookItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.ModEntities;
//import net.proctoredgames.saltcraft.entity.custom.CustomTurtle;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncS2CPacket;
import net.proctoredgames.saltcraft.potion.ModPotions;
import net.proctoredgames.saltcraft.thirst.PlayerThirstProvider;
import net.proctoredgames.saltcraft.util.ModTags;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;

import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Saltcraft.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 16),
                new ItemStack(ModItems.JELLYFISH_BUCKET.get(), 1),
                10, 2, 0.2f));

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 6),
                new ItemStack(ModBlocks.ROCK_SALT_STAIRS.get(), 3),
                10, 2, 0.2f));

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 8),
                new ItemStack(ModBlocks.CLUMPED_SALT_BLOCK.get(), 6),
                10, 2, 0.2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 12),
                new ItemStack(ModBlocks.PINK_SALT_BLOCK.get(), 1),
                10, 2, 0.2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 10),
                new ItemStack(ModItems.OIL_BUCKET.get(), 2),
                10, 2, 0.2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(ModItems.OIL_CAN.get(), 1),
                10, 2, 0.2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 5),
                PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.SALT_WATER_BOTTLE.get()),
                10, 2, 0.2f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 20),
                PotionUtils.setPotion(new ItemStack(Items.POTION), ModPotions.PINK_SALT_WATER_BOTTLE.get()),
                10, 2, 0.2f));

    }
    @SubscribeEvent
    public static void onAnvilUpdate(AnvilUpdateEvent event) {
        // Get input slots
        ItemStack leftInput = event.getLeft();
        ItemStack rightInput = event.getRight();

        if (leftInput.getItem() instanceof EnchantedBookItem && (rightInput.getItem() == ModItems.SALT_TOME.get())) {
            // Get the enchantments from the enchanted book
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(leftInput);

            ItemStack result = new ItemStack(Items.ENCHANTED_BOOK);

            for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
                Enchantment enchantment = entry.getKey();
                int currentLevel = entry.getValue();
                int maxLevel = enchantment.getMaxLevel();

                int newLevel = maxLevel;

                enchantments.put(enchantment, newLevel);
            }

            // Apply the updated enchantments to the resulting enchanted book
            EnchantmentHelper.setEnchantments(enchantments, result);

            event.setCost(5);
            event.setOutput(result);
        }
    }

    @SubscribeEvent
    public static void onAnvilClose(PlayerContainerEvent.Close event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        // Check if the container is an anvil
        if (player.containerMenu instanceof AnvilMenu anvilMenu) {
            // Get the result slot
            ItemStack result = getResultSlot(anvilMenu);

            // Check if the result is not empty and is the expected max-level book
            if (!result.isEmpty() && result.getItem() instanceof EnchantedBookItem) {

                // Check if the recipe involved your special item
                ItemStack leftInput = getInputSlot(anvilMenu,0);
                ItemStack rightInput = getInputSlot(anvilMenu,1);

                if (rightInput.getItem() == ModItems.SALT_TOME.get()) {
                    // Trigger advancement
                    Advancement advancement = player.server.getAdvancements()
                            .getAdvancement(new ResourceLocation("saltcraft", "max_out_enchanted_book"));
                    if (advancement != null) {
                        player.getAdvancements().award(advancement, "max_out_enchant");
                    }
                }
            }
        }
    }

    // Anvil menu slots: 0 and 1 are the inputs, 2 is the result. Accessing them through
    // getSlot avoids reflection on field names, which breaks in obfuscated production builds.
    public static ItemStack getResultSlot(AnvilMenu anvilMenu) {
        return anvilMenu.getSlot(2).getItem();
    }

    public static ItemStack getInputSlot(AnvilMenu anvilMenu, int slot) {
        return anvilMenu.getSlot(slot).getItem();
    }


    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerThirstProvider.PLAYER_THIRST).isPresent()) {
                event.addCapability(new ResourceLocation(Saltcraft.MOD_ID, "properties"), new PlayerThirstProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            // The dead player's capabilities are invalidated by the time this fires
            event.getOriginal().reviveCaps();
            event.getOriginal().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
            event.getOriginal().invalidateCaps();
        }
    }

    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), player);
                });
            }
            if(event.getEntity() instanceof Turtle turtle) {
                turtle.goalSelector.addGoal(2, new MeleeAttackGoal(turtle, 1.0, true));
                turtle.targetSelector.addGoal(2, new NearestAttackableTargetGoal(turtle, Jellyfish.class, true));
            }
        }
    }

    @SubscribeEvent
    public static void onItemUseFinish(LivingEntityUseItemEvent.Finish event) {
        // Check if the user is a player
        if (event.getEntity() instanceof Player player) {
            ItemStack usedItem = event.getItem();

            // Determine thirst points based on tags
            int thirstPointValue = usedItem.is(ModTags.Items.QUENCHES_THIRST_3_POINTS) ? 3
                    : (usedItem.is(ModTags.Items.QUENCHES_THIRST_5_POINTS) ? 5 : 0);

            if (usedItem.isEdible() && thirstPointValue > 0) {
                // Add thirst points to the player's thirst capability
                player.getCapability(PlayerThirstProvider.PLAYER_THIRST).ifPresent(thirst -> {
                    thirst.addThirst(thirstPointValue);
                    if (player instanceof ServerPlayer serverPlayer) {
                        ModMessages.sendToPlayer(new ThirstDataSyncS2CPacket(thirst.getThirst()), serverPlayer);
                    }
                });
            }
        }
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(
                ModEntities.JELLYFISH.get(),  // Your entity
                SpawnPlacements.Type.IN_WATER, // Spawns in water
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, // Uses water surface height
                (entityType, level, spawnType, pos, random) -> true, // Use a proper spawn rule here
                SpawnPlacementRegisterEvent.Operation.REPLACE
        );
    }

//    @SubscribeEvent
//    public static void onSurfaceRuleData(SurfaceRuleDataEvent event) {
//        if (event.getDimensionType().equals(Level.OVERWORLD)) {
//            event.getRules().add(0, ModSurfaceRules.makeRules());
//        }
//    }

}
