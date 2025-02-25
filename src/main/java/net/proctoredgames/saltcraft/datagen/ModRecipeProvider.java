package net.proctoredgames.saltcraft.datagen;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.proctoredgames.saltcraft.util.ModTags;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> SALT_ORE_SMELTABLES = List.of(
            ModBlocks.SALT_ORE.get());
    private static final List<ItemLike> PINK_SALT_ORE_SMELTABLES = List.of(
            ModBlocks.PINK_SALT_ORE.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, SALT_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.SALT.get(), 0.25f, 200, "salt");
        oreSmelting(pWriter, PINK_SALT_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_SALT.get(), 0.25f, 200, "pink_salt");
        oreBlasting(pWriter, SALT_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.SALT.get(), 0.25f, 200, "salt");
        oreBlasting(pWriter, PINK_SALT_ORE_SMELTABLES, RecipeCategory.MISC, ModItems.PINK_SALT.get(), 0.25f, 200, "pink_salt");

        addSmeltingPair(
                ModItems.SALTED_POTATO.get(), ModItems.SALTED_BAKED_POTATO.get(),
                ModItems.PINK_SALTED_POTATO.get(), ModItems.PINK_SALTED_BAKED_POTATO.get(),
                pWriter
        );

// Meat & Fish Smelting Pairs
        addSmeltingPair(
                ModItems.SALTED_BEEF.get(), ModItems.SALTED_COOKED_BEEF.get(),
                ModItems.PINK_SALTED_BEEF.get(), ModItems.PINK_SALTED_COOKED_BEEF.get(),
                pWriter
        );
        addSmeltingPair(
                ModItems.SALTED_CHICKEN.get(), ModItems.SALTED_COOKED_CHICKEN.get(),
                ModItems.PINK_SALTED_CHICKEN.get(), ModItems.PINK_SALTED_COOKED_CHICKEN.get(),
                pWriter
        );
        addSmeltingPair(
                ModItems.SALTED_COD.get(), ModItems.SALTED_COOKED_COD.get(),
                ModItems.PINK_SALTED_COD.get(), ModItems.PINK_SALTED_COOKED_COD.get(),
                pWriter
        );
        addSmeltingPair(
                ModItems.SALTED_MUTTON.get(), ModItems.SALTED_COOKED_MUTTON.get(),
                ModItems.PINK_SALTED_MUTTON.get(), ModItems.PINK_SALTED_COOKED_MUTTON.get(),
                pWriter
        );
        addSmeltingPair(
                ModItems.SALTED_PORKCHOP.get(), ModItems.SALTED_COOKED_PORKCHOP.get(),
                ModItems.PINK_SALTED_PORKCHOP.get(), ModItems.PINK_SALTED_COOKED_PORKCHOP.get(),
                pWriter
        );
        addSmeltingPair(
                ModItems.SALTED_RABBIT.get(), ModItems.SALTED_COOKED_RABBIT.get(),
                ModItems.PINK_SALTED_RABBIT.get(), ModItems.PINK_SALTED_COOKED_RABBIT.get(),
                pWriter
        );
        addSmeltingPair(
                ModItems.SALTED_SALMON.get(), ModItems.SALTED_COOKED_SALMON.get(),
                ModItems.PINK_SALTED_SALMON.get(), ModItems.PINK_SALTED_COOKED_SALMON.get(),
                pWriter
        );

        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_SALT_SLAB.get(), ModBlocks.CLUMPED_SALT_BLOCK.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_SALT_STAIRS.get(), ModBlocks.CLUMPED_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.CLUMPED_SALT_WALL.get(), ModBlocks.CLUMPED_SALT_BLOCK.get());

        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_PINK_SALT_SLAB.get(), ModBlocks.CLUMPED_PINK_SALT_BLOCK.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.CLUMPED_PINK_SALT_STAIRS.get(), ModBlocks.CLUMPED_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.CLUMPED_PINK_SALT_WALL.get(), ModBlocks.CLUMPED_PINK_SALT_BLOCK.get());

        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_SLAB.get(), ModBlocks.ROCK_SALT_BLOCK.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_STAIRS.get(), ModBlocks.ROCK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_PILLAR.get(), ModBlocks.ROCK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_SALT_WALL.get(), ModBlocks.ROCK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICKS.get(), ModBlocks.ROCK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_SLAB.get(), ModBlocks.ROCK_SALT_BLOCK.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_STAIRS.get(), ModBlocks.ROCK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_SALT_BRICK_WALL.get(), ModBlocks.ROCK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_SALT_BRICKS.get(), ModBlocks.ROCK_SALT_BLOCK.get());

        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_SLAB.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_STAIRS.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_PILLAR.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_PINK_SALT_WALL.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICKS.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get(), ModBlocks.ROCK_PINK_SALT_BLOCK.get());

        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_SLAB.get(), ModBlocks.ROCK_SALT_BRICKS.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_BRICK_STAIRS.get(), ModBlocks.ROCK_SALT_BRICKS.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_SALT_BRICK_WALL.get(), ModBlocks.ROCK_SALT_BRICKS.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_SALT_BRICKS.get(), ModBlocks.ROCK_SALT_BRICKS.get());

        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get(), ModBlocks.ROCK_PINK_SALT_BRICKS.get(), 2);
        stonecutterResultFromBase(pWriter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get(), ModBlocks.ROCK_PINK_SALT_BRICKS.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get(), ModBlocks.ROCK_PINK_SALT_BRICKS.get());
        stonecutterResultFromBase(pWriter, RecipeCategory.DECORATIONS, ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get(), ModBlocks.ROCK_PINK_SALT_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SALT_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.SALT.get())
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT.get(), 9)
                .requires(ModBlocks.SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SALT_BLOCK.get()), has(ModBlocks.SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.CLUMPED_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.CLUMPED_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.CLUMPED_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BLOCK.get()), has(ModBlocks.ROCK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BLOCK.get()), has(ModBlocks.ROCK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BLOCK.get()), has(ModBlocks.ROCK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICK_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BRICKS.get()), has(ModBlocks.ROCK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICK_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BRICKS.get()), has(ModBlocks.ROCK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICK_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BRICKS.get()), has(ModBlocks.ROCK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.CRACKED_ROCK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()), has(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.CRACKED_ROCK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()), has(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.CRACKED_ROCK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()), has(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_SALT_BLOCK.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SALT_BLOCK.get()), has(ModBlocks.SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BLOCK.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.CLUMPED_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_SALT_BRICKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.ROCK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BLOCK.get()), has(ModBlocks.ROCK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_ROCK_SALT_BRICKS.get())
                .pattern("S")
                .pattern("S")
                .define('S', ModBlocks.ROCK_SALT_BRICK_SLAB.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BRICK_SLAB.get()), has(ModBlocks.ROCK_SALT_BRICK_SLAB.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_SALT_PILLAR.get(), 2)
                .pattern("S")
                .pattern("S")
                .define('S', ModBlocks.ROCK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_SALT_BLOCK.get()), has(ModBlocks.ROCK_SALT_BLOCK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PINK_SALT_BLOCK.get())
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModItems.PINK_SALT.get())
                .unlockedBy(getHasName(ModItems.PINK_SALT.get()), has(ModItems.PINK_SALT.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_SALT.get(), 9)
                .requires(ModBlocks.PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PINK_SALT_BLOCK.get()), has(ModBlocks.PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BLOCK.get()), has(ModBlocks.ROCK_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BLOCK.get()), has(ModBlocks.ROCK_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BLOCK.get()), has(ModBlocks.ROCK_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BRICKS.get()), has(ModBlocks.ROCK_PINK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BRICKS.get()), has(ModBlocks.ROCK_PINK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BRICKS.get()), has(ModBlocks.ROCK_PINK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS.get(), 4)
                .pattern("S  ")
                .pattern("SS ")
                .pattern("SSS")
                .define('S', ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()), has(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB.get(), 6)
                .pattern("SSS")
                .define('S', ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()), has(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get(), 6)
                .pattern("SSS")
                .pattern("SSS")
                .define('S', ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get())
                .unlockedBy(getHasName(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()), has(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CLUMPED_PINK_SALT_BLOCK.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PINK_SALT_BLOCK.get()), has(ModBlocks.PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BLOCK.get())
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.CLUMPED_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()), has(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ROCK_PINK_SALT_BRICKS.get(), 4)
                .pattern("SS")
                .pattern("SS")
                .define('S', ModBlocks.ROCK_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BLOCK.get()), has(ModBlocks.ROCK_PINK_SALT_BLOCK.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get())
                .pattern("S")
                .pattern("S")
                .define('S', ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get()), has(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ROCK_PINK_SALT_PILLAR.get(), 2)
                .pattern("S")
                .pattern("S")
                .define('S', ModBlocks.ROCK_PINK_SALT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ROCK_PINK_SALT_BLOCK.get()), has(ModBlocks.ROCK_PINK_SALT_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.POTATO)
                .requires(ModTags.Items.SALT_OR_PINK_SALT)
                .requires(Items.POISONOUS_POTATO)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .unlockedBy(getHasName(Items.POISONOUS_POTATO), has(Items.POISONOUS_POTATO))
                .save(pWriter);

// Vegetables
        addRecipePair(Items.POTATO, ModItems.SALTED_POTATO.get(), ModItems.PINK_SALTED_POTATO.get(), pWriter);
        addRecipePair(Items.BAKED_POTATO, ModItems.SALTED_BAKED_POTATO.get(), ModItems.PINK_SALTED_BAKED_POTATO.get(), pWriter);
        addRecipePair(Items.BEETROOT, ModItems.SALTED_BEETROOT.get(), ModItems.PINK_SALTED_BEETROOT.get(), pWriter);
        addRecipePair(Items.CARROT, ModItems.SALTED_CARROT.get(), ModItems.PINK_SALTED_CARROT.get(), pWriter);

// Raw Meats & Fish
        addRecipePair(Items.BEEF, ModItems.SALTED_BEEF.get(), ModItems.PINK_SALTED_BEEF.get(), pWriter);
        addRecipePair(Items.CHICKEN, ModItems.SALTED_CHICKEN.get(), ModItems.PINK_SALTED_CHICKEN.get(), pWriter);
        addRecipePair(Items.COD, ModItems.SALTED_COD.get(), ModItems.PINK_SALTED_COD.get(), pWriter);
        addRecipePair(Items.MUTTON, ModItems.SALTED_MUTTON.get(), ModItems.PINK_SALTED_MUTTON.get(), pWriter);
        addRecipePair(Items.PORKCHOP, ModItems.SALTED_PORKCHOP.get(), ModItems.PINK_SALTED_PORKCHOP.get(), pWriter);
        addRecipePair(Items.RABBIT, ModItems.SALTED_RABBIT.get(), ModItems.PINK_SALTED_RABBIT.get(), pWriter);
        addRecipePair(Items.SALMON, ModItems.SALTED_SALMON.get(), ModItems.PINK_SALTED_SALMON.get(), pWriter);
        addRecipePair(Items.PUFFERFISH, ModItems.SALTED_PUFFERFISH.get(), ModItems.PINK_SALTED_PUFFERFISH.get(), pWriter);
        addRecipePair(Items.TROPICAL_FISH, ModItems.SALTED_TROPICAL_FISH.get(), ModItems.PINK_SALTED_TROPICAL_FISH.get(), pWriter);

// Cooked Meats & Fish
        addRecipePair(Items.COOKED_BEEF, ModItems.SALTED_COOKED_BEEF.get(), ModItems.PINK_SALTED_COOKED_BEEF.get(), pWriter);
        addRecipePair(Items.COOKED_CHICKEN, ModItems.SALTED_COOKED_CHICKEN.get(), ModItems.PINK_SALTED_COOKED_CHICKEN.get(), pWriter);
        addRecipePair(Items.COOKED_COD, ModItems.SALTED_COOKED_COD.get(), ModItems.PINK_SALTED_COOKED_COD.get(), pWriter);
        addRecipePair(Items.COOKED_MUTTON, ModItems.SALTED_COOKED_MUTTON.get(), ModItems.PINK_SALTED_COOKED_MUTTON.get(), pWriter);
        addRecipePair(Items.COOKED_PORKCHOP, ModItems.SALTED_COOKED_PORKCHOP.get(), ModItems.PINK_SALTED_COOKED_PORKCHOP.get(), pWriter);
        addRecipePair(Items.COOKED_RABBIT, ModItems.SALTED_COOKED_RABBIT.get(), ModItems.PINK_SALTED_COOKED_RABBIT.get(), pWriter);
        addRecipePair(Items.COOKED_SALMON, ModItems.SALTED_COOKED_SALMON.get(), ModItems.PINK_SALTED_COOKED_SALMON.get(), pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALT_WATER_BUCKET.get())
                .requires(ModItems.SALT.get())
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_SALT_WATER_BUCKET.get())
                .requires(ModItems.PINK_SALT.get())
                .requires(Items.WATER_BUCKET)
                .unlockedBy(getHasName(ModItems.PINK_SALT.get()), has(ModItems.PINK_SALT.get()))
                .unlockedBy(getHasName(Items.WATER_BUCKET), has(Items.WATER_BUCKET))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.PINK_DYE,2)
                .requires(ModItems.FLAMINGO_FEATHER.get())
                .unlockedBy(getHasName(ModItems.FLAMINGO_FEATHER.get()), has(ModItems.FLAMINGO_FEATHER.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.WHITE_DYE,2)
                .requires(ModItems.WHITE_FLAMINGO_FEATHER.get())
                .unlockedBy(getHasName(ModItems.WHITE_FLAMINGO_FEATHER.get()), has(ModItems.WHITE_FLAMINGO_FEATHER.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OIL_CAN.get())
                .pattern("   ")
                .pattern("##S")
                .pattern("  S")
                .define('S', Items.IRON_INGOT)
                .define('#', Items.IRON_NUGGET)
                .unlockedBy(getHasName(Items.IRON_INGOT), has(Items.IRON_INGOT))
                .unlockedBy(getHasName(Items.IRON_NUGGET), has(Items.IRON_NUGGET))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STAFF_OF_THE_DESERT.get())
                .pattern(" # ")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', ModItems.ANCIENT_STAFF_FRAGMENT.get())
                .define('#', ModItems.EYE_OF_THE_DESERT.get())
                .unlockedBy(getHasName(ModItems.ANCIENT_STAFF_FRAGMENT.get()), has(ModItems.ANCIENT_STAFF_FRAGMENT.get()))
                .unlockedBy(getHasName(ModItems.EYE_OF_THE_DESERT.get()), has(ModItems.EYE_OF_THE_DESERT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.UNCHARGED_STAFF_OF_THE_DESERT.get())
                .pattern("   ")
                .pattern(" S ")
                .pattern(" S ")
                .define('S', ModItems.ANCIENT_STAFF_FRAGMENT.get())
                .unlockedBy(getHasName(ModItems.ANCIENT_STAFF_FRAGMENT.get()), has(ModItems.ANCIENT_STAFF_FRAGMENT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SALTED_CARROT_ON_A_STICK.get())
                .pattern("# ")
                .pattern(" X")
                .define('#', Items.FISHING_ROD)
                .define('X', ModItems.SALTED_CARROT.get())
                .unlockedBy(getHasName(Items.FISHING_ROD), has(Items.FISHING_ROD))
                .unlockedBy(getHasName(ModItems.SALTED_CARROT.get()), has(ModItems.SALTED_CARROT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.PINK_SALTED_CARROT_ON_A_STICK.get())
                .pattern("# ")
                .pattern(" X")
                .define('#', Items.FISHING_ROD)
                .define('X', ModItems.PINK_SALTED_CARROT.get())
                .unlockedBy(getHasName(Items.FISHING_ROD), has(Items.FISHING_ROD))
                .unlockedBy(getHasName(ModItems.PINK_SALTED_CARROT.get()), has(ModItems.PINK_SALTED_CARROT.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    private void addRecipePair(ItemLike baseItem, Item saltedResult, Item pinkSaltedResult,Consumer<FinishedRecipe> pWriter) {
        // Regular salt recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, saltedResult)
                .requires(ModItems.SALT.get())
                .requires(baseItem)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(pWriter);

        // Pink salt recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, pinkSaltedResult)
                .requires(ModItems.PINK_SALT.get())
                .requires(baseItem)
                .unlockedBy(getHasName(ModItems.PINK_SALT.get()), has(ModItems.PINK_SALT.get()))
                .unlockedBy(getHasName(baseItem), has(baseItem))
                .save(pWriter);
    }

    // Helper methods
    private String name(Item item) {
        return ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private String getItemName(Item item) {
        return "has_" + ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    private void addSmeltingPair(Item saltedRaw, Item saltedCooked, Item pinkSaltedRaw, Item pinkSaltedCooked,Consumer<FinishedRecipe> pWriter) {
        // Regular salt smelting
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(saltedRaw), RecipeCategory.FOOD, saltedCooked, 0.35F, 200)
                .unlockedBy("has_" + getItemName(saltedRaw), has(saltedRaw))
                .save(pWriter, name(saltedCooked) + "_from_smelting_" + name(saltedRaw));

        // Pink salt smelting
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(pinkSaltedRaw), RecipeCategory.FOOD, pinkSaltedCooked, 0.35F, 200)
                .unlockedBy("has_" + getItemName(pinkSaltedRaw), has(pinkSaltedRaw))
                .save(pWriter, name(pinkSaltedCooked) + "_from_smelting_" + name(pinkSaltedRaw));
    }
}
