package net.proctoredgames.saltcraft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Saltcraft.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SALT_BLOCK);
        blockWithItem(ModBlocks.PINK_SALT_BLOCK);
        blockWithItem(ModBlocks.CLUMPED_SALT_BLOCK);
        blockWithItem(ModBlocks.CLUMPED_PINK_SALT_BLOCK);
        blockWithItem(ModBlocks.ROCK_SALT_BLOCK);
        blockWithItem(ModBlocks.ROCK_SALT_BRICKS);
        blockWithItem(ModBlocks.CRACKED_ROCK_SALT_BRICKS);
        blockWithItem(ModBlocks.ROCK_PINK_SALT_BLOCK);
        blockWithItem(ModBlocks.ROCK_PINK_SALT_BRICKS);
        blockWithItem(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS);
        blockWithItem(ModBlocks.SALT_ORE);
        blockWithItem(ModBlocks.PINK_SALT_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SALT_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_PINK_SALT_ORE);

        stairsBlock(((StairBlock) ModBlocks.CLUMPED_SALT_STAIRS.get()), blockTexture(ModBlocks.CLUMPED_SALT_BLOCK.get()));
        stairsBlock(((StairBlock) ModBlocks.CLUMPED_PINK_SALT_STAIRS.get()), blockTexture(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()));
        stairsBlock(((StairBlock) ModBlocks.ROCK_SALT_STAIRS.get()), blockTexture(ModBlocks.ROCK_SALT_BLOCK.get()));
        stairsBlock(((StairBlock) ModBlocks.ROCK_SALT_BRICK_STAIRS.get()), blockTexture(ModBlocks.ROCK_SALT_BRICKS.get()));
        stairsBlock(((StairBlock) ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS.get()), blockTexture(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()));
        stairsBlock(((StairBlock) ModBlocks.ROCK_PINK_SALT_STAIRS.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BLOCK.get()));
        stairsBlock(((StairBlock) ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BRICKS.get()));
        stairsBlock(((StairBlock) ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS.get()), blockTexture(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.CLUMPED_SALT_SLAB.get()), blockTexture(ModBlocks.CLUMPED_SALT_BLOCK.get()), blockTexture(ModBlocks.CLUMPED_SALT_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.CLUMPED_PINK_SALT_SLAB.get()), blockTexture(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()), blockTexture(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.ROCK_SALT_SLAB.get()), blockTexture(ModBlocks.ROCK_SALT_BLOCK.get()), blockTexture(ModBlocks.ROCK_SALT_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.ROCK_SALT_BRICK_SLAB.get()), blockTexture(ModBlocks.ROCK_SALT_BRICKS.get()), blockTexture(ModBlocks.ROCK_SALT_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB.get()), blockTexture(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()), blockTexture(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.ROCK_PINK_SALT_SLAB.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BLOCK.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BRICKS.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BRICKS.get()));
        slabBlock(((SlabBlock) ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB.get()), blockTexture(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()), blockTexture(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.CLUMPED_SALT_WALL.get()), blockTexture(ModBlocks.CLUMPED_SALT_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.CLUMPED_PINK_SALT_WALL.get()), blockTexture(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.ROCK_SALT_WALL.get()), blockTexture(ModBlocks.ROCK_SALT_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.ROCK_SALT_BRICK_WALL.get()), blockTexture(ModBlocks.ROCK_SALT_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get()), blockTexture(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.ROCK_PINK_SALT_WALL.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get()), blockTexture(ModBlocks.ROCK_PINK_SALT_BRICKS.get()));
        wallBlock(((WallBlock) ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get()), blockTexture(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get()));


    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
