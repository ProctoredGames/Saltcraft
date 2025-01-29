package net.proctoredgames.saltcraft.datagen;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemLike[]{ModItems.SALTED_POTATO.get()}), RecipeCategory.FOOD, ModItems.SALTED_BAKED_POTATO.get(), 0.35F, 200).unlockedBy("has_salted_potato", has(ModItems.SALTED_POTATO.get())).save(pWriter, Saltcraft.MOD_ID + ":" + "salted_baked_potato_from_smelting_salted_potato");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemLike[]{ModItems.PINK_SALTED_POTATO.get()}), RecipeCategory.FOOD, ModItems.PINK_SALTED_BAKED_POTATO.get(), 0.35F, 200).unlockedBy("has_pink_salted_potato", has(ModItems.PINK_SALTED_POTATO.get())).save(pWriter, Saltcraft.MOD_ID + ":" + "pink_salted_baked_potato_from_smelting_baked_salted_potato");

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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALTED_POTATO.get())
                .requires(ModItems.SALT.get())
                .requires(Items.POTATO)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SALTED_BAKED_POTATO.get())
                .requires(ModItems.SALT.get())
                .requires(Items.BAKED_POTATO)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .unlockedBy(getHasName(Items.BAKED_POTATO), has(Items.BAKED_POTATO))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_SALTED_POTATO.get())
                .requires(ModItems.PINK_SALT.get())
                .requires(Items.POTATO)
                .unlockedBy(getHasName(ModItems.PINK_SALT.get()), has(ModItems.PINK_SALT.get()))
                .unlockedBy(getHasName(Items.POTATO), has(Items.POTATO))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PINK_SALTED_BAKED_POTATO.get())
                .requires(ModItems.PINK_SALT.get())
                .requires(Items.BAKED_POTATO)
                .unlockedBy(getHasName(ModItems.PINK_SALT.get()), has(ModItems.PINK_SALT.get()))
                .unlockedBy(getHasName(Items.BAKED_POTATO), has(Items.BAKED_POTATO))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.POTATO)
                .requires(ModTags.Items.SALT_OR_PINK_SALT)
                .requires(Items.POISONOUS_POTATO)
                .unlockedBy(getHasName(ModItems.SALT.get()), has(ModItems.SALT.get()))
                .unlockedBy(getHasName(Items.POISONOUS_POTATO), has(Items.POISONOUS_POTATO))
                .save(pWriter);

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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STAFF_OF_THE_DESERT.get(),1)
                .requires(ModItems.UNCHARGED_STAFF_OF_THE_DESERT.get())
                .requires(ModItems.EYE_OF_THE_DESERT.get())
                .unlockedBy(getHasName(ModItems.UNCHARGED_STAFF_OF_THE_DESERT.get()), has(ModItems.UNCHARGED_STAFF_OF_THE_DESERT.get()))
                .unlockedBy(getHasName(ModItems.EYE_OF_THE_DESERT.get()), has(ModItems.EYE_OF_THE_DESERT.get()))
                .save(pWriter);

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }
}
