package net.proctoredgames.saltcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.util.ModTags;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        offerSmelting(exporter, List.of(ModBlocks.SALT_ORE), RecipeCategory.MISC, ModItems.SALT, 0.25f, 200, "salt");
        offerSmelting(exporter, List.of(ModBlocks.PINK_SALT_ORE), RecipeCategory.MISC, ModItems.PINK_SALT, 0.25f, 200, "pink_salt");
        offerBlasting(exporter, List.of(ModBlocks.SALT_ORE), RecipeCategory.MISC, ModItems.SALT, 0.25f, 200, "salt");
        offerBlasting(exporter, List.of(ModBlocks.PINK_SALT_ORE), RecipeCategory.MISC, ModItems.PINK_SALT, 0.25f, 200, "pink_salt");

        addSmeltingPair(exporter, ModItems.SALTED_POTATO, ModItems.SALTED_BAKED_POTATO, ModItems.PINK_SALTED_POTATO, ModItems.PINK_SALTED_BAKED_POTATO);

        addSmeltingPair(exporter, ModItems.SALTED_BEEF, ModItems.SALTED_COOKED_BEEF, ModItems.PINK_SALTED_BEEF, ModItems.PINK_SALTED_COOKED_BEEF);
        addSmeltingPair(exporter, ModItems.SALTED_CHICKEN, ModItems.SALTED_COOKED_CHICKEN, ModItems.PINK_SALTED_CHICKEN, ModItems.PINK_SALTED_COOKED_CHICKEN);
        addSmeltingPair(exporter, ModItems.SALTED_COD, ModItems.SALTED_COOKED_COD, ModItems.PINK_SALTED_COD, ModItems.PINK_SALTED_COOKED_COD);
        addSmeltingPair(exporter, ModItems.SALTED_MUTTON, ModItems.SALTED_COOKED_MUTTON, ModItems.PINK_SALTED_MUTTON, ModItems.PINK_SALTED_COOKED_MUTTON);
        addSmeltingPair(exporter, ModItems.SALTED_PORKCHOP, ModItems.SALTED_COOKED_PORKCHOP, ModItems.PINK_SALTED_PORKCHOP, ModItems.PINK_SALTED_COOKED_PORKCHOP);
        addSmeltingPair(exporter, ModItems.SALTED_RABBIT, ModItems.SALTED_COOKED_RABBIT, ModItems.PINK_SALTED_RABBIT, ModItems.PINK_SALTED_COOKED_RABBIT);
        addSmeltingPair(exporter, ModItems.SALTED_SALMON, ModItems.SALTED_COOKED_SALMON, ModItems.PINK_SALTED_SALMON, ModItems.PINK_SALTED_COOKED_SALMON);
        addSmeltingPair(exporter, ModItems.SALTED_KELP, ModItems.SALTED_DRIED_KELP, ModItems.PINK_SALTED_KELP, ModItems.PINK_SALTED_DRIED_KELP);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_SALT_SLAB, ModBlocks.CLUMPED_SALT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_SALT_STAIRS, ModBlocks.CLUMPED_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.CLUMPED_SALT_WALL, ModBlocks.CLUMPED_SALT_BLOCK);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_PINK_SALT_SLAB, ModBlocks.CLUMPED_PINK_SALT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_PINK_SALT_STAIRS, ModBlocks.CLUMPED_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.CLUMPED_PINK_SALT_WALL, ModBlocks.CLUMPED_PINK_SALT_BLOCK);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_SLAB, ModBlocks.ROCK_SALT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_STAIRS, ModBlocks.ROCK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_PILLAR, ModBlocks.ROCK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_SALT_WALL, ModBlocks.ROCK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICKS, ModBlocks.ROCK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_SLAB, ModBlocks.ROCK_SALT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_STAIRS, ModBlocks.ROCK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_SALT_BRICK_WALL, ModBlocks.ROCK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_SALT_BRICKS, ModBlocks.ROCK_SALT_BLOCK);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_SLAB, ModBlocks.ROCK_PINK_SALT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_STAIRS, ModBlocks.ROCK_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_PILLAR, ModBlocks.ROCK_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_PINK_SALT_WALL, ModBlocks.ROCK_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICKS, ModBlocks.ROCK_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB, ModBlocks.ROCK_PINK_SALT_BLOCK, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS, ModBlocks.ROCK_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_PINK_SALT_BRICK_WALL, ModBlocks.ROCK_PINK_SALT_BLOCK);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS, ModBlocks.ROCK_PINK_SALT_BLOCK);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_SLAB, ModBlocks.ROCK_SALT_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_STAIRS, ModBlocks.ROCK_SALT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_SALT_BRICK_WALL, ModBlocks.ROCK_SALT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_SALT_BRICKS, ModBlocks.ROCK_SALT_BRICKS);

        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB, ModBlocks.ROCK_PINK_SALT_BRICKS, 2);
        offerStonecuttingRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS, ModBlocks.ROCK_PINK_SALT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_PINK_SALT_BRICK_WALL, ModBlocks.ROCK_PINK_SALT_BRICKS);
        offerStonecuttingRecipe(exporter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS, ModBlocks.ROCK_PINK_SALT_BRICKS);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.SALT_BLOCK)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModItems.SALT)
                .criterion(hasItem(ModItems.SALT), conditionsFromItem(ModItems.SALT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SALT, 9)
                .input(ModBlocks.SALT_BLOCK)
                .criterion(hasItem(ModBlocks.SALT_BLOCK), conditionsFromItem(ModBlocks.SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.CLUMPED_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.CLUMPED_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.CLUMPED_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BRICKS), conditionsFromItem(ModBlocks.ROCK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BRICKS), conditionsFromItem(ModBlocks.ROCK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BRICKS), conditionsFromItem(ModBlocks.ROCK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.CRACKED_ROCK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS), conditionsFromItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.CRACKED_ROCK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS), conditionsFromItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.CRACKED_ROCK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS), conditionsFromItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_BLOCK)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.SALT_BLOCK)
                .criterion(hasItem(ModBlocks.SALT_BLOCK), conditionsFromItem(ModBlocks.SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BLOCK, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.CLUMPED_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.ROCK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHISELED_ROCK_SALT_BRICKS)
                .pattern("S")
                .pattern("S")
                .input('S', ModBlocks.ROCK_SALT_BRICK_SLAB)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BRICK_SLAB), conditionsFromItem(ModBlocks.ROCK_SALT_BRICK_SLAB))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_PILLAR, 2)
                .pattern("S")
                .pattern("S")
                .input('S', ModBlocks.ROCK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_SALT_BLOCK))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.PINK_SALT_BLOCK)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModItems.PINK_SALT)
                .criterion(hasItem(ModItems.PINK_SALT), conditionsFromItem(ModItems.PINK_SALT))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_SALT, 9)
                .input(ModBlocks.PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BRICKS), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BRICKS), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BRICKS), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS, 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .input('S', ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS), conditionsFromItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB, 6)
                .pattern("SSS")
                .input('S', ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS), conditionsFromItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL, 6)
                .pattern("SSS")
                .pattern("SSS")
                .input('S', ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS)
                .criterion(hasItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS), conditionsFromItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_BLOCK)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BLOCK)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICKS, 4)
                .pattern("SS")
                .pattern("SS")
                .input('S', ModBlocks.ROCK_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BLOCK))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS)
                .pattern("S")
                .pattern("S")
                .input('S', ModBlocks.ROCK_PINK_SALT_BRICK_SLAB)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_PILLAR, 2)
                .pattern("S")
                .pattern("S")
                .input('S', ModBlocks.ROCK_PINK_SALT_BLOCK)
                .criterion(hasItem(ModBlocks.ROCK_PINK_SALT_BLOCK), conditionsFromItem(ModBlocks.ROCK_PINK_SALT_BLOCK))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.POTATO)
                .input(ModTags.Items.SALT_OR_PINK_SALT)
                .input(Items.POISONOUS_POTATO)
                .criterion(hasItem(ModItems.SALT), conditionsFromItem(ModItems.SALT))
                .criterion(hasItem(Items.POISONOUS_POTATO), conditionsFromItem(Items.POISONOUS_POTATO))
                .offerTo(exporter);

        // Vegetables
        addRecipePair(exporter, Items.POTATO, ModItems.SALTED_POTATO, ModItems.PINK_SALTED_POTATO);
        addRecipePair(exporter, Items.BAKED_POTATO, ModItems.SALTED_BAKED_POTATO, ModItems.PINK_SALTED_BAKED_POTATO);
        addRecipePair(exporter, Items.BEETROOT, ModItems.SALTED_BEETROOT, ModItems.PINK_SALTED_BEETROOT);
        addRecipePair(exporter, Items.CARROT, ModItems.SALTED_CARROT, ModItems.PINK_SALTED_CARROT);

        // Raw Meats & Fish
        addRecipePair(exporter, Items.BEEF, ModItems.SALTED_BEEF, ModItems.PINK_SALTED_BEEF);
        addRecipePair(exporter, Items.CHICKEN, ModItems.SALTED_CHICKEN, ModItems.PINK_SALTED_CHICKEN);
        addRecipePair(exporter, Items.COD, ModItems.SALTED_COD, ModItems.PINK_SALTED_COD);
        addRecipePair(exporter, Items.MUTTON, ModItems.SALTED_MUTTON, ModItems.PINK_SALTED_MUTTON);
        addRecipePair(exporter, Items.PORKCHOP, ModItems.SALTED_PORKCHOP, ModItems.PINK_SALTED_PORKCHOP);
        addRecipePair(exporter, Items.RABBIT, ModItems.SALTED_RABBIT, ModItems.PINK_SALTED_RABBIT);
        addRecipePair(exporter, Items.SALMON, ModItems.SALTED_SALMON, ModItems.PINK_SALTED_SALMON);
        addRecipePair(exporter, Items.PUFFERFISH, ModItems.SALTED_PUFFERFISH, ModItems.PINK_SALTED_PUFFERFISH);
        addRecipePair(exporter, Items.TROPICAL_FISH, ModItems.SALTED_TROPICAL_FISH, ModItems.PINK_SALTED_TROPICAL_FISH);

        // Cooked Meats & Fish
        addRecipePair(exporter, Items.COOKED_BEEF, ModItems.SALTED_COOKED_BEEF, ModItems.PINK_SALTED_COOKED_BEEF);
        addRecipePair(exporter, Items.COOKED_CHICKEN, ModItems.SALTED_COOKED_CHICKEN, ModItems.PINK_SALTED_COOKED_CHICKEN);
        addRecipePair(exporter, Items.COOKED_COD, ModItems.SALTED_COOKED_COD, ModItems.PINK_SALTED_COOKED_COD);
        addRecipePair(exporter, Items.COOKED_MUTTON, ModItems.SALTED_COOKED_MUTTON, ModItems.PINK_SALTED_COOKED_MUTTON);
        addRecipePair(exporter, Items.COOKED_PORKCHOP, ModItems.SALTED_COOKED_PORKCHOP, ModItems.PINK_SALTED_COOKED_PORKCHOP);
        addRecipePair(exporter, Items.COOKED_RABBIT, ModItems.SALTED_COOKED_RABBIT, ModItems.PINK_SALTED_COOKED_RABBIT);
        addRecipePair(exporter, Items.COOKED_SALMON, ModItems.SALTED_COOKED_SALMON, ModItems.PINK_SALTED_COOKED_SALMON);

        addRecipePair(exporter, Items.KELP, ModItems.SALTED_KELP, ModItems.PINK_SALTED_KELP);
        addRecipePair(exporter, Items.DRIED_KELP, ModItems.SALTED_DRIED_KELP, ModItems.PINK_SALTED_DRIED_KELP);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.PINK_DYE, 2)
                .input(ModItems.FLAMINGO_FEATHER)
                .criterion(hasItem(ModItems.FLAMINGO_FEATHER), conditionsFromItem(ModItems.FLAMINGO_FEATHER))
                .offerTo(exporter);
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, Items.WHITE_DYE, 2)
                .input(ModItems.WHITE_FLAMINGO_FEATHER)
                .criterion(hasItem(ModItems.WHITE_FLAMINGO_FEATHER), conditionsFromItem(ModItems.WHITE_FLAMINGO_FEATHER))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.OIL_CAN)
                .pattern("   ")
                .pattern("##S")
                .pattern("  S")
                .input('S', Items.IRON_INGOT)
                .input('#', Items.IRON_NUGGET)
                .criterion(hasItem(Items.IRON_INGOT), conditionsFromItem(Items.IRON_INGOT))
                .criterion(hasItem(Items.IRON_NUGGET), conditionsFromItem(Items.IRON_NUGGET))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.STAFF_OF_THE_DESERT)
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', ModItems.ANCIENT_STAFF_FRAGMENT)
                .input('#', ModItems.EYE_OF_THE_DESERT)
                .criterion(hasItem(ModItems.ANCIENT_STAFF_FRAGMENT), conditionsFromItem(ModItems.ANCIENT_STAFF_FRAGMENT))
                .criterion(hasItem(ModItems.EYE_OF_THE_DESERT), conditionsFromItem(ModItems.EYE_OF_THE_DESERT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.UNCHARGED_STAFF_OF_THE_DESERT)
                .pattern("   ")
                .pattern(" S ")
                .pattern(" S ")
                .input('S', ModItems.ANCIENT_STAFF_FRAGMENT)
                .criterion(hasItem(ModItems.ANCIENT_STAFF_FRAGMENT), conditionsFromItem(ModItems.ANCIENT_STAFF_FRAGMENT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SALTED_CARROT_ON_A_STICK)
                .pattern("# ")
                .pattern(" X")
                .input('#', Items.FISHING_ROD)
                .input('X', ModItems.SALTED_CARROT)
                .criterion(hasItem(Items.FISHING_ROD), conditionsFromItem(Items.FISHING_ROD))
                .criterion(hasItem(ModItems.SALTED_CARROT), conditionsFromItem(ModItems.SALTED_CARROT))
                .offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.PINK_SALTED_CARROT_ON_A_STICK)
                .pattern("# ")
                .pattern(" X")
                .input('#', Items.FISHING_ROD)
                .input('X', ModItems.PINK_SALTED_CARROT)
                .criterion(hasItem(Items.FISHING_ROD), conditionsFromItem(Items.FISHING_ROD))
                .criterion(hasItem(ModItems.PINK_SALTED_CARROT), conditionsFromItem(ModItems.PINK_SALTED_CARROT))
                .offerTo(exporter);
    }

    private void addRecipePair(RecipeExporter exporter, ItemConvertible baseItem, Item saltedResult, Item pinkSaltedResult) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, saltedResult)
                .input(ModItems.SALT)
                .input(baseItem)
                .criterion(hasItem(ModItems.SALT), conditionsFromItem(ModItems.SALT))
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, pinkSaltedResult)
                .input(ModItems.PINK_SALT)
                .input(baseItem)
                .criterion(hasItem(ModItems.PINK_SALT), conditionsFromItem(ModItems.PINK_SALT))
                .criterion(hasItem(baseItem), conditionsFromItem(baseItem))
                .offerTo(exporter);
    }

    private void addSmeltingPair(RecipeExporter exporter, Item saltedRaw, Item saltedCooked, Item pinkSaltedRaw, Item pinkSaltedCooked) {
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(saltedRaw), RecipeCategory.FOOD, saltedCooked, 0.35F, 200)
                .criterion(hasItem(saltedRaw), conditionsFromItem(saltedRaw))
                .offerTo(exporter, getItemPath(saltedCooked) + "_from_smelting_" + getItemPath(saltedRaw));

        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItems(pinkSaltedRaw), RecipeCategory.FOOD, pinkSaltedCooked, 0.35F, 200)
                .criterion(hasItem(pinkSaltedRaw), conditionsFromItem(pinkSaltedRaw))
                .offerTo(exporter, getItemPath(pinkSaltedCooked) + "_from_smelting_" + getItemPath(pinkSaltedRaw));
    }
}
