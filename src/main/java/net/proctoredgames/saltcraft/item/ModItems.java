package net.proctoredgames.saltcraft.item;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.capability.wrappers.BucketPickupHandlerWrapper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.item.custom.*;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Saltcraft.MOD_ID);

    public static final RegistryObject<Item> SALT = ITEMS.register("salt",
            () -> new SaltItem(new Item.Properties().food(ModFoods.SALT)));
    public static final RegistryObject<Item> PINK_SALT = ITEMS.register("pink_salt",
            () -> new SaltItem(new Item.Properties().food(ModFoods.PINK_SALT)));

    public static final RegistryObject<Item> FLAMINGO_FEATHER = ITEMS.register("flamingo_feather",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WHITE_FLAMINGO_FEATHER = ITEMS.register("white_flamingo_feather",
            () -> new Item(new Item.Properties()));

    // Vegetables
    public static final RegistryObject<Item> SALTED_POTATO = ITEMS.register("salted_potato",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_POTATO)));
    public static final RegistryObject<Item> SALTED_BAKED_POTATO = ITEMS.register("salted_baked_potato",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_BAKED_POTATO)));
    public static final RegistryObject<Item> PINK_SALTED_POTATO = ITEMS.register("pink_salted_potato",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_POTATO)));
    public static final RegistryObject<Item> PINK_SALTED_BAKED_POTATO = ITEMS.register("pink_salted_baked_potato",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_BAKED_POTATO)));

    public static final RegistryObject<Item> SALTED_BEETROOT = ITEMS.register("salted_beetroot",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_BEETROOT)));
    public static final RegistryObject<Item> PINK_SALTED_BEETROOT = ITEMS.register("pink_salted_beetroot",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_BEETROOT)));

    public static final RegistryObject<Item> SALTED_CARROT = ITEMS.register("salted_carrot",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_CARROT)));
    public static final RegistryObject<Item> PINK_SALTED_CARROT = ITEMS.register("pink_salted_carrot",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_CARROT)));

    // Meats & Fish
    public static final RegistryObject<Item> SALTED_BEEF = ITEMS.register("salted_beef",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_BEEF)));
    public static final RegistryObject<Item> PINK_SALTED_BEEF = ITEMS.register("pink_salted_beef",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_BEEF)));

    public static final RegistryObject<Item> SALTED_CHICKEN = ITEMS.register("salted_chicken",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_CHICKEN)));
    public static final RegistryObject<Item> PINK_SALTED_CHICKEN = ITEMS.register("pink_salted_chicken",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_CHICKEN)));

    public static final RegistryObject<Item> SALTED_COD = ITEMS.register("salted_cod",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COD)));
    public static final RegistryObject<Item> PINK_SALTED_COD = ITEMS.register("pink_salted_cod",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COD)));

    public static final RegistryObject<Item> SALTED_COOKED_BEEF = ITEMS.register("salted_cooked_beef",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_BEEF)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_BEEF = ITEMS.register("pink_salted_cooked_beef",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_BEEF)));

    public static final RegistryObject<Item> SALTED_COOKED_CHICKEN = ITEMS.register("salted_cooked_chicken",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_CHICKEN)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_CHICKEN = ITEMS.register("pink_salted_cooked_chicken",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_CHICKEN)));

    public static final RegistryObject<Item> SALTED_COOKED_COD = ITEMS.register("salted_cooked_cod",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_COD)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_COD = ITEMS.register("pink_salted_cooked_cod",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_COD)));

    public static final RegistryObject<Item> SALTED_COOKED_MUTTON = ITEMS.register("salted_cooked_mutton",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_MUTTON)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_MUTTON = ITEMS.register("pink_salted_cooked_mutton",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_MUTTON)));

    public static final RegistryObject<Item> SALTED_COOKED_PORKCHOP = ITEMS.register("salted_cooked_porkchop",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_PORKCHOP)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_PORKCHOP = ITEMS.register("pink_salted_cooked_porkchop",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_PORKCHOP)));

    public static final RegistryObject<Item> SALTED_COOKED_RABBIT = ITEMS.register("salted_cooked_rabbit",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_RABBIT)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_RABBIT = ITEMS.register("pink_salted_cooked_rabbit",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_RABBIT)));

    public static final RegistryObject<Item> SALTED_COOKED_SALMON = ITEMS.register("salted_cooked_salmon",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_COOKED_SALMON)));
    public static final RegistryObject<Item> PINK_SALTED_COOKED_SALMON = ITEMS.register("pink_salted_cooked_salmon",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_COOKED_SALMON)));

    public static final RegistryObject<Item> SALTED_MUTTON = ITEMS.register("salted_mutton",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_MUTTON)));
    public static final RegistryObject<Item> PINK_SALTED_MUTTON = ITEMS.register("pink_salted_mutton",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_MUTTON)));

    public static final RegistryObject<Item> SALTED_PORKCHOP = ITEMS.register("salted_porkchop",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_PORKCHOP)));
    public static final RegistryObject<Item> PINK_SALTED_PORKCHOP = ITEMS.register("pink_salted_porkchop",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_PORKCHOP)));

    public static final RegistryObject<Item> SALTED_PUFFERFISH = ITEMS.register("salted_pufferfish",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_PUFFERFISH)));
    public static final RegistryObject<Item> PINK_SALTED_PUFFERFISH = ITEMS.register("pink_salted_pufferfish",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_PUFFERFISH)));

    public static final RegistryObject<Item> SALTED_RABBIT = ITEMS.register("salted_rabbit",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_RABBIT)));
    public static final RegistryObject<Item> PINK_SALTED_RABBIT = ITEMS.register("pink_salted_rabbit",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_RABBIT)));

    public static final RegistryObject<Item> SALTED_SALMON = ITEMS.register("salted_salmon",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_SALMON)));
    public static final RegistryObject<Item> PINK_SALTED_SALMON = ITEMS.register("pink_salted_salmon",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_SALMON)));

    public static final RegistryObject<Item> SALTED_TROPICAL_FISH = ITEMS.register("salted_tropical_fish",
            () -> new Item(new Item.Properties().food(ModFoods.SALTED_TROPICAL_FISH)));
    public static final RegistryObject<Item> PINK_SALTED_TROPICAL_FISH = ITEMS.register("pink_salted_tropical_fish",
            () -> new Item(new Item.Properties().food(ModFoods.PINK_SALTED_TROPICAL_FISH)));

    public static final RegistryObject<Item> SALT_WATER_BUCKET = ITEMS.register("salt_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_SALT_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> PINK_SALT_WATER_BUCKET = ITEMS.register("pink_salt_water_bucket",
            () -> new BucketItem(ModFluids.SOURCE_PINK_SALT_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
    public static final RegistryObject<Item> JELLYFISH_BUCKET = ITEMS.register(
            "jellyfish_bucket",
            () -> new MobBucketItem(
                    (Supplier<? extends EntityType<?>>) () -> ModEntities.JELLYFISH.get(),
                    (Supplier<? extends Fluid>) () -> Fluids.WATER,
                    (Supplier<? extends SoundEvent>) () -> SoundEvents.BUCKET_EMPTY_FISH,
                    new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketFuelItem(ModFluids.SOURCE_OIL, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1), 10000));

    public static final RegistryObject<Item> SALT_TOME = ITEMS.register("salt_tome",
            () -> new EnchantedBookItem(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> JELLYFISH_SPAWN_EGG = ITEMS.register("jellyfish_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.JELLYFISH, 0x821e64, 0xc84aa6, new Item.Properties()));
    public static final RegistryObject<Item> SALT_MAGE_SPAWN_EGG = ITEMS.register("salt_mage_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SALT_MAGE, 0x8e9494, 0x38003e, new Item.Properties()));
    public static final RegistryObject<Item> CRYSTID_SPAWN_EGG = ITEMS.register("crystid_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.CRYSTID, 0xffffff, 0xf9c2ff, new Item.Properties()));
    public static final RegistryObject<Item> FLAMINGO_SPAWN_EGG = ITEMS.register("flamingo_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.FLAMINGO, 0xf389f5, 0x000000, new Item.Properties()));
    public static final RegistryObject<Item> MIRAGE_SPAWN_EGG = ITEMS.register("mirage_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MIRAGE, 0xdacfa3, 0xdadedf, new Item.Properties()));

    public static final RegistryObject<Item> OIL_CAN = ITEMS.register("oil_can",
            () -> new OilCanItem(new Item.Properties()));

    public static final RegistryObject<Item> FILLED_OIL_CAN = ITEMS.register("filled_oil_can",
            () -> new FilledOilCanItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> ROCK_SALT_FOSSIL = ITEMS.register("rock_salt_fossil",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EYE_OF_THE_DESERT = ITEMS.register("eye_of_the_desert",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ANCIENT_STAFF_FRAGMENT = ITEMS.register("ancient_staff_fragment",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));

    public static final RegistryObject<Item> STAFF_OF_THE_DESERT = ITEMS.register("staff_of_the_desert",
            () -> new StaffOfTheDesertItem(new Item.Properties().rarity(Rarity.EPIC).fireResistant().durability(8)));
    public static final RegistryObject<Item> UNCHARGED_STAFF_OF_THE_DESERT = ITEMS.register("uncharged_staff_of_the_desert",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE).fireResistant()));

    public static final RegistryObject<Item> THIRST_ICON_ITEM = ITEMS.register("z_thirst_icon_item",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
