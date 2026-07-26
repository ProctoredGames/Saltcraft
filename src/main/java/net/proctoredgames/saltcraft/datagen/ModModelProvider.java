package net.proctoredgames.saltcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    private static final Model SPAWN_EGG_MODEL = new Model(Optional.of(Identifier.of("item/template_spawn_egg")), Optional.empty());

    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        // Plain cube-all blocks with no derived shapes
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SALT_BLOCK);
        blockStateModelGenerator.registerItemModel(ModBlocks.SALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_SALT_BLOCK);
        blockStateModelGenerator.registerItemModel(ModBlocks.PINK_SALT_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SALT_ORE);
        blockStateModelGenerator.registerItemModel(ModBlocks.SALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.PINK_SALT_ORE);
        blockStateModelGenerator.registerItemModel(ModBlocks.PINK_SALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SALT_ORE);
        blockStateModelGenerator.registerItemModel(ModBlocks.DEEPSLATE_SALT_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_PINK_SALT_ORE);
        blockStateModelGenerator.registerItemModel(ModBlocks.DEEPSLATE_PINK_SALT_ORE);

        // Block families: base block + stairs/slab/wall sharing the base block's texture
        registerFamily(blockStateModelGenerator, ModBlocks.CLUMPED_SALT_BLOCK, ModBlocks.CLUMPED_SALT_STAIRS, ModBlocks.CLUMPED_SALT_SLAB, ModBlocks.CLUMPED_SALT_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.CLUMPED_PINK_SALT_BLOCK, ModBlocks.CLUMPED_PINK_SALT_STAIRS, ModBlocks.CLUMPED_PINK_SALT_SLAB, ModBlocks.CLUMPED_PINK_SALT_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.ROCK_SALT_BLOCK, ModBlocks.ROCK_SALT_STAIRS, ModBlocks.ROCK_SALT_SLAB, ModBlocks.ROCK_SALT_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.ROCK_SALT_BRICKS, ModBlocks.ROCK_SALT_BRICK_STAIRS, ModBlocks.ROCK_SALT_BRICK_SLAB, ModBlocks.ROCK_SALT_BRICK_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.CRACKED_ROCK_SALT_BRICKS, ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS, ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB, ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.ROCK_PINK_SALT_BLOCK, ModBlocks.ROCK_PINK_SALT_STAIRS, ModBlocks.ROCK_PINK_SALT_SLAB, ModBlocks.ROCK_PINK_SALT_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.ROCK_PINK_SALT_BRICKS, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB, ModBlocks.ROCK_PINK_SALT_BRICK_WALL);
        registerFamily(blockStateModelGenerator, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL);

        // Chiseled bricks, pillars, plinths, the fossil block, and the oil fluid block keep
        // their hand-authored blockstate/model JSON (matches the Forge datagen's scope, which
        // never covered these either).
    }

    private void registerFamily(BlockStateModelGenerator blockStateModelGenerator,
                                 net.minecraft.block.Block base,
                                 net.minecraft.block.Block stairs,
                                 net.minecraft.block.Block slab,
                                 net.minecraft.block.Block wall) {
        BlockStateModelGenerator.BlockTexturePool pool = blockStateModelGenerator.registerCubeAllModelTexturePool(base);
        blockStateModelGenerator.registerItemModel(base);
        pool.stairs(stairs);
        pool.slab(slab);
        pool.wall(wall);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SALT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALT, Models.GENERATED);

        // Vegetables
        itemModelGenerator.register(ModItems.SALTED_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_BAKED_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_BAKED_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_BEETROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_BEETROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_CARROT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_CARROT, Models.GENERATED);

        // Meat & Fish
        itemModelGenerator.register(ModItems.SALTED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COD, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_COD, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_COD, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_MUTTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_MUTTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_PORKCHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_PORKCHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_RABBIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_RABBIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_COOKED_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_COOKED_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_MUTTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_MUTTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_PORKCHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_PORKCHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_PUFFERFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_PUFFERFISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_RABBIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_RABBIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_TROPICAL_FISH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_TROPICAL_FISH, Models.GENERATED);

        itemModelGenerator.register(ModItems.SALTED_KELP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_KELP, Models.GENERATED);
        itemModelGenerator.register(ModItems.SALTED_DRIED_KELP, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_DRIED_KELP, Models.GENERATED);

        itemModelGenerator.register(ModItems.SALT_TOME, Models.GENERATED);
        itemModelGenerator.register(ModItems.FLAMINGO_FEATHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.WHITE_FLAMINGO_FEATHER, Models.GENERATED);

        itemModelGenerator.register(ModItems.JELLYFISH_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.OIL_BUCKET, Models.GENERATED);

        itemModelGenerator.register(ModItems.OIL_CAN, Models.GENERATED);
        itemModelGenerator.register(ModItems.FILLED_OIL_CAN, Models.GENERATED);

        itemModelGenerator.register(ModItems.SALTED_CARROT_ON_A_STICK, Models.GENERATED);
        itemModelGenerator.register(ModItems.PINK_SALTED_CARROT_ON_A_STICK, Models.GENERATED);

        itemModelGenerator.register(ModItems.EYE_OF_THE_DESERT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANCIENT_STAFF_FRAGMENT, Models.GENERATED);
        itemModelGenerator.register(ModItems.UNCHARGED_STAFF_OF_THE_DESERT, Models.GENERATED);

        itemModelGenerator.register(ModItems.ROCK_SALT_FOSSIL, Models.GENERATED);

        itemModelGenerator.register(ModItems.JELLYFISH_SPAWN_EGG, SPAWN_EGG_MODEL);
        itemModelGenerator.register(ModItems.SALT_MAGE_SPAWN_EGG, SPAWN_EGG_MODEL);
        itemModelGenerator.register(ModItems.CRYSTID_SPAWN_EGG, SPAWN_EGG_MODEL);
        itemModelGenerator.register(ModItems.FLAMINGO_SPAWN_EGG, SPAWN_EGG_MODEL);
        itemModelGenerator.register(ModItems.MIRAGE_SPAWN_EGG, SPAWN_EGG_MODEL);

        // STAFF_OF_THE_DESERT, and blocks without a generated blockstate above (chiseled
        // bricks, pillars, plinths, fossil block), keep their hand-authored item models.
    }
}
