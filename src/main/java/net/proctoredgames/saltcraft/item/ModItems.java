package net.proctoredgames.saltcraft.item;

import net.minecraft.entity.EntityType;
import net.minecraft.item.BucketItem;
import net.minecraft.item.EnchantedBookItem;
import net.minecraft.item.EntityBucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.OnAStickItem;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.item.custom.FilledOilCanItem;
import net.proctoredgames.saltcraft.item.custom.OilCanItem;
import net.proctoredgames.saltcraft.item.custom.SaltItem;
import net.proctoredgames.saltcraft.item.custom.StaffOfTheDesertItem;

public class ModItems {
    public static final Item SALT = register("salt", new SaltItem(new Item.Settings().food(ModFoods.SALT)));
    public static final Item PINK_SALT = register("pink_salt", new SaltItem(new Item.Settings().food(ModFoods.PINK_SALT)));

    public static final Item FLAMINGO_FEATHER = register("flamingo_feather", new Item(new Item.Settings()));
    public static final Item WHITE_FLAMINGO_FEATHER = register("white_flamingo_feather", new Item(new Item.Settings()));

    // Vegetables
    public static final Item SALTED_POTATO = register("salted_potato", new Item(new Item.Settings().food(ModFoods.SALTED_POTATO)));
    public static final Item SALTED_BAKED_POTATO = register("salted_baked_potato", new Item(new Item.Settings().food(ModFoods.SALTED_BAKED_POTATO)));
    public static final Item PINK_SALTED_POTATO = register("pink_salted_potato", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_POTATO)));
    public static final Item PINK_SALTED_BAKED_POTATO = register("pink_salted_baked_potato", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_BAKED_POTATO)));

    public static final Item SALTED_BEETROOT = register("salted_beetroot", new Item(new Item.Settings().food(ModFoods.SALTED_BEETROOT)));
    public static final Item PINK_SALTED_BEETROOT = register("pink_salted_beetroot", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_BEETROOT)));

    public static final Item SALTED_CARROT = register("salted_carrot", new Item(new Item.Settings().food(ModFoods.SALTED_CARROT)));
    public static final Item PINK_SALTED_CARROT = register("pink_salted_carrot", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_CARROT)));

    // Meats & Fish
    public static final Item SALTED_BEEF = register("salted_beef", new Item(new Item.Settings().food(ModFoods.SALTED_BEEF)));
    public static final Item PINK_SALTED_BEEF = register("pink_salted_beef", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_BEEF)));

    public static final Item SALTED_CHICKEN = register("salted_chicken", new Item(new Item.Settings().food(ModFoods.SALTED_CHICKEN)));
    public static final Item PINK_SALTED_CHICKEN = register("pink_salted_chicken", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_CHICKEN)));

    public static final Item SALTED_COD = register("salted_cod", new Item(new Item.Settings().food(ModFoods.SALTED_COD)));
    public static final Item PINK_SALTED_COD = register("pink_salted_cod", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COD)));

    public static final Item SALTED_COOKED_BEEF = register("salted_cooked_beef", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_BEEF)));
    public static final Item PINK_SALTED_COOKED_BEEF = register("pink_salted_cooked_beef", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_BEEF)));

    public static final Item SALTED_COOKED_CHICKEN = register("salted_cooked_chicken", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_CHICKEN)));
    public static final Item PINK_SALTED_COOKED_CHICKEN = register("pink_salted_cooked_chicken", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_CHICKEN)));

    public static final Item SALTED_COOKED_COD = register("salted_cooked_cod", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_COD)));
    public static final Item PINK_SALTED_COOKED_COD = register("pink_salted_cooked_cod", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_COD)));

    public static final Item SALTED_COOKED_MUTTON = register("salted_cooked_mutton", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_MUTTON)));
    public static final Item PINK_SALTED_COOKED_MUTTON = register("pink_salted_cooked_mutton", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_MUTTON)));

    public static final Item SALTED_COOKED_PORKCHOP = register("salted_cooked_porkchop", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_PORKCHOP)));
    public static final Item PINK_SALTED_COOKED_PORKCHOP = register("pink_salted_cooked_porkchop", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_PORKCHOP)));

    public static final Item SALTED_COOKED_RABBIT = register("salted_cooked_rabbit", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_RABBIT)));
    public static final Item PINK_SALTED_COOKED_RABBIT = register("pink_salted_cooked_rabbit", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_RABBIT)));

    public static final Item SALTED_COOKED_SALMON = register("salted_cooked_salmon", new Item(new Item.Settings().food(ModFoods.SALTED_COOKED_SALMON)));
    public static final Item PINK_SALTED_COOKED_SALMON = register("pink_salted_cooked_salmon", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_COOKED_SALMON)));

    public static final Item SALTED_MUTTON = register("salted_mutton", new Item(new Item.Settings().food(ModFoods.SALTED_MUTTON)));
    public static final Item PINK_SALTED_MUTTON = register("pink_salted_mutton", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_MUTTON)));

    public static final Item SALTED_PORKCHOP = register("salted_porkchop", new Item(new Item.Settings().food(ModFoods.SALTED_PORKCHOP)));
    public static final Item PINK_SALTED_PORKCHOP = register("pink_salted_porkchop", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_PORKCHOP)));

    public static final Item SALTED_PUFFERFISH = register("salted_pufferfish", new Item(new Item.Settings().food(ModFoods.SALTED_PUFFERFISH)));
    public static final Item PINK_SALTED_PUFFERFISH = register("pink_salted_pufferfish", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_PUFFERFISH)));

    public static final Item SALTED_RABBIT = register("salted_rabbit", new Item(new Item.Settings().food(ModFoods.SALTED_RABBIT)));
    public static final Item PINK_SALTED_RABBIT = register("pink_salted_rabbit", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_RABBIT)));

    public static final Item SALTED_SALMON = register("salted_salmon", new Item(new Item.Settings().food(ModFoods.SALTED_SALMON)));
    public static final Item PINK_SALTED_SALMON = register("pink_salted_salmon", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_SALMON)));

    public static final Item SALTED_TROPICAL_FISH = register("salted_tropical_fish", new Item(new Item.Settings().food(ModFoods.SALTED_TROPICAL_FISH)));
    public static final Item PINK_SALTED_TROPICAL_FISH = register("pink_salted_tropical_fish", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_TROPICAL_FISH)));

    public static final Item SALTED_KELP = register("salted_kelp", new Item(new Item.Settings()));
    public static final Item PINK_SALTED_KELP = register("pink_salted_kelp", new Item(new Item.Settings()));

    public static final Item SALTED_DRIED_KELP = register("salted_dried_kelp", new Item(new Item.Settings().food(ModFoods.SALTED_DRIED_KELP)));
    public static final Item PINK_SALTED_DRIED_KELP = register("pink_salted_dried_kelp", new Item(new Item.Settings().food(ModFoods.PINK_SALTED_DRIED_KELP)));

    public static final Item JELLYFISH_BUCKET = register("jellyfish_bucket",
            new EntityBucketItem(ModEntities.JELLYFISH, net.minecraft.fluid.Fluids.WATER, SoundEvents.ITEM_BUCKET_EMPTY_FISH, new Item.Settings().maxCount(1)));
    public static final Item OIL_BUCKET = register("oil_bucket",
            new BucketItem(ModFluids.OIL, new Item.Settings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Item SALT_TOME = register("salt_tome", new EnchantedBookItem(new Item.Settings().rarity(Rarity.RARE).fireproof()));

    public static final Item JELLYFISH_SPAWN_EGG = register("jellyfish_spawn_egg",
            new SpawnEggItem(ModEntities.JELLYFISH, 0x821e64, 0xc84aa6, new Item.Settings()));
    public static final Item SALT_MAGE_SPAWN_EGG = register("salt_mage_spawn_egg",
            new SpawnEggItem(ModEntities.SALT_MAGE, 0x8e9494, 0x38003e, new Item.Settings()));
    public static final Item CRYSTID_SPAWN_EGG = register("crystid_spawn_egg",
            new SpawnEggItem(ModEntities.CRYSTID, 0xffffff, 0xf9c2ff, new Item.Settings()));
    public static final Item FLAMINGO_SPAWN_EGG = register("flamingo_spawn_egg",
            new SpawnEggItem(ModEntities.FLAMINGO, 0xf389f5, 0x000000, new Item.Settings()));
    public static final Item MIRAGE_SPAWN_EGG = register("mirage_spawn_egg",
            new SpawnEggItem(ModEntities.MIRAGE, 0xdacfa3, 0xdadedf, new Item.Settings()));

    public static final Item OIL_CAN = register("oil_can", new OilCanItem(new Item.Settings()));

    public static final Item FILLED_OIL_CAN = register("filled_oil_can", new FilledOilCanItem(new Item.Settings().maxCount(1)));

    public static final Item ROCK_SALT_FOSSIL = register("rock_salt_fossil", new Item(new Item.Settings()));

    public static final Item EYE_OF_THE_DESERT = register("eye_of_the_desert", new Item(new Item.Settings().rarity(Rarity.RARE)));

    public static final Item ANCIENT_STAFF_FRAGMENT = register("ancient_staff_fragment", new Item(new Item.Settings().rarity(Rarity.RARE)));

    public static final Item STAFF_OF_THE_DESERT = register("staff_of_the_desert", new StaffOfTheDesertItem(
            new Item.Settings().rarity(Rarity.EPIC).fireproof().maxDamage(8).attributeModifiers(StaffOfTheDesertItem.createAttributeModifiers())));
    public static final Item UNCHARGED_STAFF_OF_THE_DESERT = register("uncharged_staff_of_the_desert",
            new Item(new Item.Settings().rarity(Rarity.RARE).fireproof()));

    public static final Item SALTED_CARROT_ON_A_STICK = register("salted_carrot_on_a_stick",
            new OnAStickItem<>(new Item.Settings().maxDamage(25), EntityType.PIG, 7));
    public static final Item PINK_SALTED_CARROT_ON_A_STICK = register("pink_salted_carrot_on_a_stick",
            new OnAStickItem<>(new Item.Settings().maxDamage(25), EntityType.PIG, 7));

    private static Item register(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Saltcraft.MOD_ID, name), item);
    }

    public static void register() {
    }
}
