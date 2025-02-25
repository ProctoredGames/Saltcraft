package net.proctoredgames.saltcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.potion.ModPotions;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Saltcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.SALT);
        simpleItem(ModItems.PINK_SALT);

        // Potato variants (existing)
        simpleItem(ModItems.SALTED_POTATO);
        simpleItem(ModItems.SALTED_BAKED_POTATO);
        simpleItem(ModItems.PINK_SALTED_POTATO);
        simpleItem(ModItems.PINK_SALTED_BAKED_POTATO);

        // New vegetable registrations
        simpleItem(ModItems.SALTED_BEETROOT);
        simpleItem(ModItems.PINK_SALTED_BEETROOT);
        simpleItem(ModItems.SALTED_CARROT);
        simpleItem(ModItems.PINK_SALTED_CARROT);

        // Meat & Fish registrations
        simpleItem(ModItems.SALTED_BEEF);
        simpleItem(ModItems.PINK_SALTED_BEEF);
        simpleItem(ModItems.SALTED_CHICKEN);
        simpleItem(ModItems.PINK_SALTED_CHICKEN);
        simpleItem(ModItems.SALTED_COD);
        simpleItem(ModItems.PINK_SALTED_COD);
        simpleItem(ModItems.SALTED_COOKED_BEEF);
        simpleItem(ModItems.PINK_SALTED_COOKED_BEEF);
        simpleItem(ModItems.SALTED_COOKED_CHICKEN);
        simpleItem(ModItems.PINK_SALTED_COOKED_CHICKEN);
        simpleItem(ModItems.SALTED_COOKED_COD);
        simpleItem(ModItems.PINK_SALTED_COOKED_COD);
        simpleItem(ModItems.SALTED_COOKED_MUTTON);
        simpleItem(ModItems.PINK_SALTED_COOKED_MUTTON);
        simpleItem(ModItems.SALTED_COOKED_PORKCHOP);
        simpleItem(ModItems.PINK_SALTED_COOKED_PORKCHOP);
        simpleItem(ModItems.SALTED_COOKED_RABBIT);
        simpleItem(ModItems.PINK_SALTED_COOKED_RABBIT);
        simpleItem(ModItems.SALTED_COOKED_SALMON);
        simpleItem(ModItems.PINK_SALTED_COOKED_SALMON);
        simpleItem(ModItems.SALTED_MUTTON);
        simpleItem(ModItems.PINK_SALTED_MUTTON);
        simpleItem(ModItems.SALTED_PORKCHOP);
        simpleItem(ModItems.PINK_SALTED_PORKCHOP);
        simpleItem(ModItems.SALTED_PUFFERFISH);
        simpleItem(ModItems.PINK_SALTED_PUFFERFISH);
        simpleItem(ModItems.SALTED_RABBIT);
        simpleItem(ModItems.PINK_SALTED_RABBIT);
        simpleItem(ModItems.SALTED_SALMON);
        simpleItem(ModItems.PINK_SALTED_SALMON);
        simpleItem(ModItems.SALTED_TROPICAL_FISH);
        simpleItem(ModItems.PINK_SALTED_TROPICAL_FISH);

        simpleItem(ModItems.SALTED_KELP);
        simpleItem(ModItems.PINK_SALTED_KELP);
        simpleItem(ModItems.SALTED_DRIED_KELP);
        simpleItem(ModItems.PINK_SALTED_DRIED_KELP);

        simpleItem(ModItems.PINK_SALTED_POTATO);
        simpleItem(ModItems.PINK_SALTED_BAKED_POTATO);

        simpleItem(ModItems.SALT_TOME);
        simpleItem(ModItems.FLAMINGO_FEATHER);
        simpleItem(ModItems.WHITE_FLAMINGO_FEATHER);

        simpleItem(ModItems.SALT_WATER_BUCKET);
        simpleItem(ModItems.PINK_SALT_WATER_BUCKET);
        simpleItem(ModItems.JELLYFISH_BUCKET);
        simpleItem(ModItems.OIL_BUCKET);

        simpleItem(ModItems.OIL_CAN);
        simpleItem(ModItems.FILLED_OIL_CAN);

        simpleItem(ModItems.SALTED_CARROT_ON_A_STICK);
        simpleItem(ModItems.PINK_SALTED_CARROT_ON_A_STICK);

        simpleItem(ModItems.EYE_OF_THE_DESERT);
        simpleItem(ModItems.ANCIENT_STAFF_FRAGMENT);
        simpleItem(ModItems.UNCHARGED_STAFF_OF_THE_DESERT);

        simpleItem(ModItems.ROCK_SALT_FOSSIL);

        withExistingParent(ModItems.JELLYFISH_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SALT_MAGE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.CRYSTID_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.FLAMINGO_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.MIRAGE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        simpleBlockItem(ModBlocks.CLUMPED_SALT_STAIRS);
        simpleBlockItem(ModBlocks.CLUMPED_PINK_SALT_STAIRS);
        simpleBlockItem(ModBlocks.ROCK_SALT_STAIRS);
        simpleBlockItem(ModBlocks.ROCK_SALT_BRICK_STAIRS);
        simpleBlockItem(ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS);
        simpleBlockItem(ModBlocks.ROCK_PINK_SALT_STAIRS);
        simpleBlockItem(ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS);
        simpleBlockItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS);
        simpleBlockItem(ModBlocks.CLUMPED_SALT_SLAB);
        simpleBlockItem(ModBlocks.CLUMPED_PINK_SALT_SLAB);
        simpleBlockItem(ModBlocks.ROCK_SALT_SLAB);
        simpleBlockItem(ModBlocks.ROCK_SALT_BRICK_SLAB);
        simpleBlockItem(ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB);
        simpleBlockItem(ModBlocks.ROCK_PINK_SALT_SLAB);
        simpleBlockItem(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB);
        simpleBlockItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB);

        simpleBlockItem(ModBlocks.ROCK_SALT_FOSSIL_BLOCK);

        simpleBlockItem(ModBlocks.CHISELED_ROCK_SALT_BRICKS);
        simpleBlockItem(ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS);

        simpleBlockItem(ModBlocks.ROCK_SALT_PILLAR);
        simpleBlockItem(ModBlocks.ROCK_PINK_SALT_PILLAR);

        simpleBlockItem(ModBlocks.SUMMONING_PLINTH);
        simpleBlockItem(ModBlocks.SPAWNING_PLINTH);

        wallItem(ModBlocks.CLUMPED_SALT_WALL, ModBlocks.CLUMPED_SALT_BLOCK);
        wallItem(ModBlocks.CLUMPED_PINK_SALT_WALL, ModBlocks.CLUMPED_PINK_SALT_BLOCK);
        wallItem(ModBlocks.ROCK_SALT_WALL, ModBlocks.ROCK_SALT_BLOCK);
        wallItem(ModBlocks.ROCK_SALT_BRICK_WALL, ModBlocks.ROCK_SALT_BRICKS);
        wallItem(ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL, ModBlocks.CRACKED_ROCK_SALT_BRICKS);
        wallItem(ModBlocks.ROCK_PINK_SALT_WALL, ModBlocks.ROCK_PINK_SALT_BLOCK);
        wallItem(ModBlocks.ROCK_PINK_SALT_BRICK_WALL, ModBlocks.ROCK_PINK_SALT_BRICKS);
        wallItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS);

    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item){
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(Saltcraft.MOD_ID, "item/" + item.getId().getPath()));
    }

    public void simpleBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(Saltcraft.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  new ResourceLocation(Saltcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  new ResourceLocation(Saltcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  new ResourceLocation(Saltcraft.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

//    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
//        return withExistingParent(item.getId().getPath(),
//                new ResourceLocation("item/generated")).texture("layer0",
//                new ResourceLocation(Saltcraft.MOD_ID,"item/" + item.getId().getPath()));
//    }
}
