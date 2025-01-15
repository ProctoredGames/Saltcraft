package net.proctoredgames.saltcraft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Saltcraft.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ModTags.Blocks.ALL_ORES)
                .add(ModBlocks.SALT_ORE.get(),
                        ModBlocks.PINK_SALT_ORE.get())
                .addTag(Tags.Blocks.ORES);

        this.tag(ModTags.Blocks.SALT_MATERIALS)
                .add(ModBlocks.SALT_BLOCK.get(),
                        ModBlocks.CLUMPED_SALT_BLOCK.get(),
                        ModBlocks.ROCK_SALT_BLOCK.get(),
                        ModBlocks.ROCK_SALT_BRICKS.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICKS.get(),
                        ModBlocks.CLUMPED_SALT_STAIRS.get(),
                        ModBlocks.ROCK_SALT_STAIRS.get(),
                        ModBlocks.ROCK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.CLUMPED_SALT_SLAB.get(),
                        ModBlocks.ROCK_SALT_SLAB.get(),
                        ModBlocks.ROCK_SALT_BRICK_SLAB.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB.get(),
                        ModBlocks.CLUMPED_SALT_WALL.get(),
                        ModBlocks.ROCK_SALT_WALL.get(),
                        ModBlocks.ROCK_SALT_BRICK_WALL.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get(),
                        ModBlocks.CHISELED_ROCK_SALT_BRICKS.get(),
                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get());

        this.tag(ModTags.Blocks.PINK_SALT_MATERIALS)
                .add(ModBlocks.PINK_SALT_BLOCK.get(),
                        ModBlocks.CLUMPED_PINK_SALT_BLOCK.get(),
                        ModBlocks.ROCK_PINK_SALT_BLOCK.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICKS.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get(),
                        ModBlocks.CLUMPED_PINK_SALT_STAIRS.get(),
                        ModBlocks.ROCK_PINK_SALT_STAIRS.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.CLUMPED_PINK_SALT_SLAB.get(),
                        ModBlocks.ROCK_PINK_SALT_SLAB.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB.get(),
                        ModBlocks.CLUMPED_PINK_SALT_WALL.get(),
                        ModBlocks.ROCK_PINK_SALT_WALL.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get(),
                        ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get());

        this.tag(ModTags.Blocks.ALL_SALT_MATERIALS)
                .addTag(ModTags.Blocks.SALT_MATERIALS)
                        .addTag(ModTags.Blocks.PINK_SALT_MATERIALS);

        this.tag(ModTags.Blocks.SUMMONING_PLINTH_TAG)
                .add(ModBlocks.SUMMONING_PLINTH.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.SALT_ORE.get(),
                        ModBlocks.PINK_SALT_ORE.get(),
                        ModBlocks.DEEPSLATE_SALT_ORE.get(),
                        ModBlocks.DEEPSLATE_PINK_SALT_ORE.get(),
                        ModBlocks.SALT_BLOCK.get(),
                        ModBlocks.PINK_SALT_BLOCK.get(),

                        ModBlocks.CLUMPED_SALT_BLOCK.get(),
                        ModBlocks.CLUMPED_PINK_SALT_BLOCK.get(),
                        ModBlocks.ROCK_SALT_BLOCK.get(),
                        ModBlocks.ROCK_SALT_BRICKS.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICKS.get(),
                        ModBlocks.ROCK_PINK_SALT_BLOCK.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICKS.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get(),

                        ModBlocks.CLUMPED_SALT_STAIRS.get(),
                        ModBlocks.CLUMPED_PINK_SALT_STAIRS.get(),
                        ModBlocks.ROCK_SALT_STAIRS.get(),
                        ModBlocks.ROCK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.ROCK_PINK_SALT_STAIRS.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS.get(),

                        ModBlocks.CLUMPED_SALT_SLAB.get(),
                        ModBlocks.CLUMPED_PINK_SALT_SLAB.get(),
                        ModBlocks.ROCK_SALT_SLAB.get(),
                        ModBlocks.ROCK_SALT_BRICK_SLAB.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB.get(),
                        ModBlocks.ROCK_PINK_SALT_SLAB.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB.get(),

                        ModBlocks.CLUMPED_SALT_WALL.get(),
                        ModBlocks.CLUMPED_PINK_SALT_WALL.get(),
                        ModBlocks.ROCK_SALT_WALL.get(),
                        ModBlocks.ROCK_SALT_BRICK_WALL.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get(),
                        ModBlocks.ROCK_PINK_SALT_WALL.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get(),

                        ModBlocks.CHISELED_ROCK_SALT_BRICKS.get(),
                        ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get(),

                        ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get());

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.SALT_BLOCK.get(),
                        ModBlocks.PINK_SALT_BLOCK.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SALT_ORE.get(),
                        ModBlocks.PINK_SALT_ORE.get(),
                        ModBlocks.DEEPSLATE_SALT_ORE.get(),
                        ModBlocks.DEEPSLATE_PINK_SALT_ORE.get());

        this.tag(BlockTags.WALLS)
                .add(ModBlocks.CLUMPED_SALT_WALL.get(),
                        ModBlocks.CLUMPED_PINK_SALT_WALL.get(),
                        ModBlocks.ROCK_SALT_WALL.get(),
                        ModBlocks.ROCK_SALT_BRICK_WALL.get(),
                        ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get(),
                        ModBlocks.ROCK_PINK_SALT_WALL.get(),
                        ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get(),
                        ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get());
    }
}
