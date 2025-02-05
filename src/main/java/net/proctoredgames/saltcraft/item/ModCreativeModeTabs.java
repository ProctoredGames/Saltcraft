package net.proctoredgames.saltcraft.item;

import net.proctoredgames.saltcraft.Saltcraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Saltcraft.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TUTORIAL_TAB = CREATIVE_MODE_TABS.register("salt_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SALT.get()))
                    .title(Component.translatable("creativetab.salt_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModBlocks.CLUMPED_SALT_BLOCK.get());
                        pOutput.accept(ModBlocks.CLUMPED_SALT_STAIRS.get());
                        pOutput.accept(ModBlocks.CLUMPED_SALT_SLAB.get());
                        pOutput.accept(ModBlocks.CLUMPED_SALT_WALL.get());

                        pOutput.accept(ModBlocks.ROCK_SALT_BLOCK.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_STAIRS.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_SLAB.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_WALL.get());

                        pOutput.accept(ModBlocks.ROCK_SALT_BRICKS.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_BRICK_WALL.get());

                        pOutput.accept(ModBlocks.CHISELED_ROCK_SALT_BRICKS.get());
                        pOutput.accept(ModBlocks.ROCK_SALT_PILLAR.get());

                        pOutput.accept(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get());
                        pOutput.accept(ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get());

                        pOutput.accept(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get());
                        pOutput.accept(ModBlocks.CLUMPED_PINK_SALT_STAIRS.get());
                        pOutput.accept(ModBlocks.CLUMPED_PINK_SALT_SLAB.get());
                        pOutput.accept(ModBlocks.CLUMPED_PINK_SALT_WALL.get());

                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_BLOCK.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_STAIRS.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_SLAB.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_WALL.get());

                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_BRICKS.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get());

                        pOutput.accept(ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get());
                        pOutput.accept(ModBlocks.ROCK_PINK_SALT_PILLAR.get());

                        pOutput.accept(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get());
                        pOutput.accept(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS.get());
                        pOutput.accept(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB.get());
                        pOutput.accept(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get());

                        pOutput.accept(ModBlocks.SALT_BLOCK.get());
                        pOutput.accept(ModBlocks.PINK_SALT_BLOCK.get());
                        pOutput.accept(ModBlocks.SALT_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_SALT_ORE.get());
                        pOutput.accept(ModBlocks.PINK_SALT_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_PINK_SALT_ORE.get());

                        pOutput.accept(ModItems.SALT.get());
                        pOutput.accept(ModItems.PINK_SALT.get());

                        pOutput.accept(ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get());
                        pOutput.accept(ModItems.ROCK_SALT_FOSSIL.get());

                        pOutput.accept(ModBlocks.SUMMONING_PLINTH.get());
                        pOutput.accept(ModBlocks.SPAWNING_PLINTH.get());

                        pOutput.accept(ModItems.SALT_TOME.get());

                        pOutput.accept(ModItems.EYE_OF_THE_DESERT.get());
                        pOutput.accept(ModItems.ANCIENT_STAFF_FRAGMENT.get());
                        pOutput.accept(ModItems.UNCHARGED_STAFF_OF_THE_DESERT.get());
                        pOutput.accept(ModItems.STAFF_OF_THE_DESERT.get());

                        pOutput.accept(ModItems.FLAMINGO_FEATHER.get());
                        pOutput.accept(ModItems.WHITE_FLAMINGO_FEATHER.get());

                        pOutput.accept(ModItems.SALT_WATER_BUCKET.get());
                        pOutput.accept(ModItems.PINK_SALT_WATER_BUCKET.get());

                        pOutput.accept(ModItems.JELLYFISH_BUCKET.get());
                        pOutput.accept(ModItems.OIL_BUCKET.get());

                        pOutput.accept(ModItems.OIL_CAN.get());
                        pOutput.accept(ModItems.FILLED_OIL_CAN.get());

                        pOutput.accept(ModItems.SALT_MAGE_SPAWN_EGG.get());
                        pOutput.accept(ModItems.CRYSTID_SPAWN_EGG.get());
                        pOutput.accept(ModItems.JELLYFISH_SPAWN_EGG.get());
                        pOutput.accept(ModItems.FLAMINGO_SPAWN_EGG.get());
                        pOutput.accept(ModItems.MIRAGE_SPAWN_EGG.get());

                        pOutput.accept(ModItems.SALTED_CARROT_ON_A_STICK.get());
                        pOutput.accept(ModItems.PINK_SALTED_CARROT_ON_A_STICK.get());

                        // Vegetables
                        pOutput.accept(ModItems.SALTED_BEETROOT.get());
                        pOutput.accept(ModItems.PINK_SALTED_BEETROOT.get());

                        pOutput.accept(ModItems.SALTED_CARROT.get());
                        pOutput.accept(ModItems.PINK_SALTED_CARROT.get());

                        pOutput.accept(ModItems.SALTED_POTATO.get());
                        pOutput.accept(ModItems.PINK_SALTED_POTATO.get());
                        pOutput.accept(ModItems.SALTED_BAKED_POTATO.get());
                        pOutput.accept(ModItems.PINK_SALTED_BAKED_POTATO.get());

                        // Meats & Fish
                        pOutput.accept(ModItems.SALTED_BEEF.get());
                        pOutput.accept(ModItems.PINK_SALTED_BEEF.get());
                        pOutput.accept(ModItems.SALTED_COOKED_BEEF.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_BEEF.get());

                        pOutput.accept(ModItems.SALTED_CHICKEN.get());
                        pOutput.accept(ModItems.PINK_SALTED_CHICKEN.get());
                        pOutput.accept(ModItems.SALTED_COOKED_CHICKEN.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_CHICKEN.get());

                        pOutput.accept(ModItems.SALTED_COD.get());
                        pOutput.accept(ModItems.PINK_SALTED_COD.get());
                        pOutput.accept(ModItems.SALTED_COOKED_COD.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_COD.get());

                        pOutput.accept(ModItems.SALTED_MUTTON.get());
                        pOutput.accept(ModItems.PINK_SALTED_MUTTON.get());
                        pOutput.accept(ModItems.SALTED_COOKED_MUTTON.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_MUTTON.get());

                        pOutput.accept(ModItems.SALTED_PORKCHOP.get());
                        pOutput.accept(ModItems.PINK_SALTED_PORKCHOP.get());
                        pOutput.accept(ModItems.SALTED_COOKED_PORKCHOP.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_PORKCHOP.get());

                        pOutput.accept(ModItems.SALTED_PUFFERFISH.get());
                        pOutput.accept(ModItems.PINK_SALTED_PUFFERFISH.get());

                        pOutput.accept(ModItems.SALTED_RABBIT.get());
                        pOutput.accept(ModItems.PINK_SALTED_RABBIT.get());
                        pOutput.accept(ModItems.SALTED_COOKED_RABBIT.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_RABBIT.get());

                        pOutput.accept(ModItems.SALTED_SALMON.get());
                        pOutput.accept(ModItems.PINK_SALTED_SALMON.get());
                        pOutput.accept(ModItems.SALTED_COOKED_SALMON.get());
                        pOutput.accept(ModItems.PINK_SALTED_COOKED_SALMON.get());

                        pOutput.accept(ModItems.SALTED_TROPICAL_FISH.get());
                        pOutput.accept(ModItems.PINK_SALTED_TROPICAL_FISH.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}