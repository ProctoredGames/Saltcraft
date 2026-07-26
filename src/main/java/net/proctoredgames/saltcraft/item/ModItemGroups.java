package net.proctoredgames.saltcraft.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;

public class ModItemGroups {
    public static final RegistryKey<ItemGroup> SALT_TAB_KEY =
            RegistryKey.of(RegistryKeys.ITEM_GROUP, Identifier.of(Saltcraft.MOD_ID, "salt_tab"));

    public static final ItemGroup SALT_TAB = FabricItemGroup.builder()
            .displayName(Text.translatable("creativetab.salt_tab"))
            .icon(() -> new ItemStack(ModItems.SALT))
            .entries((context, entries) -> {
                entries.add(ModBlocks.CLUMPED_SALT_BLOCK);
                entries.add(ModBlocks.CLUMPED_SALT_STAIRS);
                entries.add(ModBlocks.CLUMPED_SALT_SLAB);
                entries.add(ModBlocks.CLUMPED_SALT_WALL);

                entries.add(ModBlocks.ROCK_SALT_BLOCK);
                entries.add(ModBlocks.ROCK_SALT_STAIRS);
                entries.add(ModBlocks.ROCK_SALT_SLAB);
                entries.add(ModBlocks.ROCK_SALT_WALL);

                entries.add(ModBlocks.ROCK_SALT_BRICKS);
                entries.add(ModBlocks.ROCK_SALT_BRICK_STAIRS);
                entries.add(ModBlocks.ROCK_SALT_BRICK_SLAB);
                entries.add(ModBlocks.ROCK_SALT_BRICK_WALL);

                entries.add(ModBlocks.CHISELED_ROCK_SALT_BRICKS);
                entries.add(ModBlocks.ROCK_SALT_PILLAR);

                entries.add(ModBlocks.CRACKED_ROCK_SALT_BRICKS);
                entries.add(ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS);
                entries.add(ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB);
                entries.add(ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL);

                entries.add(ModBlocks.CLUMPED_PINK_SALT_BLOCK);
                entries.add(ModBlocks.CLUMPED_PINK_SALT_STAIRS);
                entries.add(ModBlocks.CLUMPED_PINK_SALT_SLAB);
                entries.add(ModBlocks.CLUMPED_PINK_SALT_WALL);

                entries.add(ModBlocks.ROCK_PINK_SALT_BLOCK);
                entries.add(ModBlocks.ROCK_PINK_SALT_STAIRS);
                entries.add(ModBlocks.ROCK_PINK_SALT_SLAB);
                entries.add(ModBlocks.ROCK_PINK_SALT_WALL);

                entries.add(ModBlocks.ROCK_PINK_SALT_BRICKS);
                entries.add(ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS);
                entries.add(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB);
                entries.add(ModBlocks.ROCK_PINK_SALT_BRICK_WALL);

                entries.add(ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS);
                entries.add(ModBlocks.ROCK_PINK_SALT_PILLAR);

                entries.add(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS);
                entries.add(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS);
                entries.add(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB);
                entries.add(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL);

                entries.add(ModBlocks.SALT_BLOCK);
                entries.add(ModBlocks.PINK_SALT_BLOCK);
                entries.add(ModBlocks.SALT_ORE);
                entries.add(ModBlocks.DEEPSLATE_SALT_ORE);
                entries.add(ModBlocks.PINK_SALT_ORE);
                entries.add(ModBlocks.DEEPSLATE_PINK_SALT_ORE);

                entries.add(ModItems.SALT);
                entries.add(ModItems.PINK_SALT);

                entries.add(ModBlocks.ROCK_SALT_FOSSIL_BLOCK);
                entries.add(ModItems.ROCK_SALT_FOSSIL);

                entries.add(ModBlocks.SUMMONING_PLINTH);
                entries.add(ModBlocks.SPAWNING_PLINTH);

                entries.add(ModItems.SALT_TOME);

                entries.add(ModItems.EYE_OF_THE_DESERT);
                entries.add(ModItems.ANCIENT_STAFF_FRAGMENT);
                entries.add(ModItems.UNCHARGED_STAFF_OF_THE_DESERT);
                entries.add(ModItems.STAFF_OF_THE_DESERT);

                entries.add(ModItems.FLAMINGO_FEATHER);
                entries.add(ModItems.WHITE_FLAMINGO_FEATHER);

                entries.add(ModItems.JELLYFISH_BUCKET);
                entries.add(ModItems.OIL_BUCKET);

                entries.add(ModItems.OIL_CAN);
                entries.add(ModItems.FILLED_OIL_CAN);

                entries.add(ModItems.SALT_MAGE_SPAWN_EGG);
                entries.add(ModItems.CRYSTID_SPAWN_EGG);
                entries.add(ModItems.JELLYFISH_SPAWN_EGG);
                entries.add(ModItems.FLAMINGO_SPAWN_EGG);
                entries.add(ModItems.MIRAGE_SPAWN_EGG);

                entries.add(ModItems.SALTED_CARROT_ON_A_STICK);
                entries.add(ModItems.PINK_SALTED_CARROT_ON_A_STICK);

                // Vegetables
                entries.add(ModItems.SALTED_BEETROOT);
                entries.add(ModItems.PINK_SALTED_BEETROOT);

                entries.add(ModItems.SALTED_CARROT);
                entries.add(ModItems.PINK_SALTED_CARROT);

                entries.add(ModItems.SALTED_POTATO);
                entries.add(ModItems.PINK_SALTED_POTATO);
                entries.add(ModItems.SALTED_BAKED_POTATO);
                entries.add(ModItems.PINK_SALTED_BAKED_POTATO);

                // Meats & Fish
                entries.add(ModItems.SALTED_BEEF);
                entries.add(ModItems.PINK_SALTED_BEEF);
                entries.add(ModItems.SALTED_COOKED_BEEF);
                entries.add(ModItems.PINK_SALTED_COOKED_BEEF);

                entries.add(ModItems.SALTED_CHICKEN);
                entries.add(ModItems.PINK_SALTED_CHICKEN);
                entries.add(ModItems.SALTED_COOKED_CHICKEN);
                entries.add(ModItems.PINK_SALTED_COOKED_CHICKEN);

                entries.add(ModItems.SALTED_COD);
                entries.add(ModItems.PINK_SALTED_COD);
                entries.add(ModItems.SALTED_COOKED_COD);
                entries.add(ModItems.PINK_SALTED_COOKED_COD);

                entries.add(ModItems.SALTED_MUTTON);
                entries.add(ModItems.PINK_SALTED_MUTTON);
                entries.add(ModItems.SALTED_COOKED_MUTTON);
                entries.add(ModItems.PINK_SALTED_COOKED_MUTTON);

                entries.add(ModItems.SALTED_PORKCHOP);
                entries.add(ModItems.PINK_SALTED_PORKCHOP);
                entries.add(ModItems.SALTED_COOKED_PORKCHOP);
                entries.add(ModItems.PINK_SALTED_COOKED_PORKCHOP);

                entries.add(ModItems.SALTED_PUFFERFISH);
                entries.add(ModItems.PINK_SALTED_PUFFERFISH);

                entries.add(ModItems.SALTED_RABBIT);
                entries.add(ModItems.PINK_SALTED_RABBIT);
                entries.add(ModItems.SALTED_COOKED_RABBIT);
                entries.add(ModItems.PINK_SALTED_COOKED_RABBIT);

                entries.add(ModItems.SALTED_SALMON);
                entries.add(ModItems.PINK_SALTED_SALMON);
                entries.add(ModItems.SALTED_COOKED_SALMON);
                entries.add(ModItems.PINK_SALTED_COOKED_SALMON);

                entries.add(ModItems.SALTED_TROPICAL_FISH);
                entries.add(ModItems.PINK_SALTED_TROPICAL_FISH);

                entries.add(ModItems.SALTED_KELP);
                entries.add(ModItems.PINK_SALTED_KELP);
                entries.add(ModItems.SALTED_DRIED_KELP);
                entries.add(ModItems.PINK_SALTED_DRIED_KELP);
            })
            .build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, SALT_TAB_KEY, SALT_TAB);
    }
}
