package net.proctoredgames.saltcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.util.ModTags;

import java.util.concurrent.CompletableFuture;


public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Blocks.ALL_ORES)
                .add(ModBlocks.SALT_ORE, ModBlocks.PINK_SALT_ORE);

        getOrCreateTagBuilder(ModTags.Blocks.SALT_MATERIALS)
                .add(ModBlocks.SALT_BLOCK,
                        ModBlocks.CLUMPED_SALT_BLOCK,
                        ModBlocks.ROCK_SALT_BLOCK,
                        ModBlocks.ROCK_SALT_BRICKS,
                        ModBlocks.CRACKED_ROCK_SALT_BRICKS,
                        ModBlocks.CLUMPED_SALT_STAIRS,
                        ModBlocks.ROCK_SALT_STAIRS,
                        ModBlocks.ROCK_SALT_BRICK_STAIRS,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS,
                        ModBlocks.CLUMPED_SALT_SLAB,
                        ModBlocks.ROCK_SALT_SLAB,
                        ModBlocks.ROCK_SALT_BRICK_SLAB,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB,
                        ModBlocks.CLUMPED_SALT_WALL,
                        ModBlocks.ROCK_SALT_WALL,
                        ModBlocks.ROCK_SALT_BRICK_WALL,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL,
                        ModBlocks.CHISELED_ROCK_SALT_BRICKS,
                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK);

        getOrCreateTagBuilder(ModTags.Blocks.PINK_SALT_MATERIALS)
                .add(ModBlocks.PINK_SALT_BLOCK,
                        ModBlocks.CLUMPED_PINK_SALT_BLOCK,
                        ModBlocks.ROCK_PINK_SALT_BLOCK,
                        ModBlocks.ROCK_PINK_SALT_BRICKS,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS,
                        ModBlocks.CLUMPED_PINK_SALT_STAIRS,
                        ModBlocks.ROCK_PINK_SALT_STAIRS,
                        ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS,
                        ModBlocks.CLUMPED_PINK_SALT_SLAB,
                        ModBlocks.ROCK_PINK_SALT_SLAB,
                        ModBlocks.ROCK_PINK_SALT_BRICK_SLAB,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB,
                        ModBlocks.CLUMPED_PINK_SALT_WALL,
                        ModBlocks.ROCK_PINK_SALT_WALL,
                        ModBlocks.ROCK_PINK_SALT_BRICK_WALL,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL,
                        ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS);

        getOrCreateTagBuilder(ModTags.Blocks.ALL_SALT_MATERIALS)
                .addTag(ModTags.Blocks.SALT_MATERIALS)
                .addTag(ModTags.Blocks.PINK_SALT_MATERIALS);

        getOrCreateTagBuilder(ModTags.Blocks.SUMMONING_PLINTH_TAG)
                .add(ModBlocks.SUMMONING_PLINTH);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.SALT_ORE,
                        ModBlocks.PINK_SALT_ORE,
                        ModBlocks.DEEPSLATE_SALT_ORE,
                        ModBlocks.DEEPSLATE_PINK_SALT_ORE,
                        ModBlocks.SALT_BLOCK,
                        ModBlocks.PINK_SALT_BLOCK,

                        ModBlocks.CLUMPED_SALT_BLOCK,
                        ModBlocks.CLUMPED_PINK_SALT_BLOCK,
                        ModBlocks.ROCK_SALT_BLOCK,
                        ModBlocks.ROCK_SALT_BRICKS,
                        ModBlocks.CRACKED_ROCK_SALT_BRICKS,
                        ModBlocks.ROCK_PINK_SALT_BLOCK,
                        ModBlocks.ROCK_PINK_SALT_BRICKS,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS,

                        ModBlocks.CLUMPED_SALT_STAIRS,
                        ModBlocks.CLUMPED_PINK_SALT_STAIRS,
                        ModBlocks.ROCK_SALT_STAIRS,
                        ModBlocks.ROCK_SALT_BRICK_STAIRS,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS,
                        ModBlocks.ROCK_PINK_SALT_STAIRS,
                        ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS,

                        ModBlocks.CLUMPED_SALT_SLAB,
                        ModBlocks.CLUMPED_PINK_SALT_SLAB,
                        ModBlocks.ROCK_SALT_SLAB,
                        ModBlocks.ROCK_SALT_BRICK_SLAB,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB,
                        ModBlocks.ROCK_PINK_SALT_SLAB,
                        ModBlocks.ROCK_PINK_SALT_BRICK_SLAB,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB,

                        ModBlocks.CLUMPED_SALT_WALL,
                        ModBlocks.CLUMPED_PINK_SALT_WALL,
                        ModBlocks.ROCK_SALT_WALL,
                        ModBlocks.ROCK_SALT_BRICK_WALL,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL,
                        ModBlocks.ROCK_PINK_SALT_WALL,
                        ModBlocks.ROCK_PINK_SALT_BRICK_WALL,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL,

                        ModBlocks.CHISELED_ROCK_SALT_BRICKS,
                        ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS,

                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.SALT_BLOCK, ModBlocks.PINK_SALT_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SALT_ORE,
                        ModBlocks.PINK_SALT_ORE,
                        ModBlocks.DEEPSLATE_SALT_ORE,
                        ModBlocks.DEEPSLATE_PINK_SALT_ORE);

        getOrCreateTagBuilder(BlockTags.WALLS)
                .add(ModBlocks.CLUMPED_SALT_WALL,
                        ModBlocks.CLUMPED_PINK_SALT_WALL,
                        ModBlocks.ROCK_SALT_WALL,
                        ModBlocks.ROCK_SALT_BRICK_WALL,
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL,
                        ModBlocks.ROCK_PINK_SALT_WALL,
                        ModBlocks.ROCK_PINK_SALT_BRICK_WALL,
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL);
    }
}
