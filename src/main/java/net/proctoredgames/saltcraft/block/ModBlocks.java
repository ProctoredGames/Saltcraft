package net.proctoredgames.saltcraft.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.custom.RockSaltFossilBlock;
import net.proctoredgames.saltcraft.block.custom.SaltFallingBlock;
import net.proctoredgames.saltcraft.block.custom.SpawningPlinthBlock;
import net.proctoredgames.saltcraft.block.custom.SummoningPlinthBlock;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.item.ModItems;

import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final Block SALT_BLOCK = registerBlock("salt_block",
            new SaltFallingBlock(AbstractBlock.Settings.copy(Blocks.SAND).ticksRandomly()));
    public static final Block PINK_SALT_BLOCK = registerBlock("pink_salt_block",
            new SaltFallingBlock(AbstractBlock.Settings.copy(Blocks.SAND).ticksRandomly()));

    public static final Block CLUMPED_SALT_BLOCK = registerBlock("clumped_salt_block",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block CLUMPED_PINK_SALT_BLOCK = registerBlock("clumped_pink_salt_block",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));

    public static final Block ROCK_SALT_BLOCK = registerBlock("rock_salt_block",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_SALT_BRICKS = registerBlock("rock_salt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_SALT_BRICKS = registerBlock("cracked_rock_salt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_BLOCK = registerBlock("rock_pink_salt_block",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_BRICKS = registerBlock("rock_pink_salt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_PINK_SALT_BRICKS = registerBlock("cracked_rock_pink_salt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final Block ROCK_SALT_FOSSIL_BLOCK = registerBlock("rock_salt_fossil_block",
            new RockSaltFossilBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final Block SALT_ORE = registerBlock("salt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.copy(Blocks.STONE)
                    .strength(2f).requiresTool()));
    public static final Block PINK_SALT_ORE = registerBlock("pink_salt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(6, 9), AbstractBlock.Settings.copy(Blocks.STONE)
                    .strength(2f).requiresTool()));
    public static final Block DEEPSLATE_SALT_ORE = registerBlock("deepslate_salt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 6), AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresTool()));
    public static final Block DEEPSLATE_PINK_SALT_ORE = registerBlock("deepslate_pink_salt_ore",
            new ExperienceDroppingBlock(UniformIntProvider.create(6, 9), AbstractBlock.Settings.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresTool()));

    public static final Block CLUMPED_SALT_STAIRS = registerBlock("clumped_salt_stairs",
            new StairsBlock(ModBlocks.CLUMPED_SALT_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block CLUMPED_PINK_SALT_STAIRS = registerBlock("clumped_pink_salt_stairs",
            new StairsBlock(ModBlocks.CLUMPED_PINK_SALT_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block ROCK_SALT_STAIRS = registerBlock("rock_salt_stairs",
            new StairsBlock(ModBlocks.ROCK_SALT_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_SALT_BRICK_STAIRS = registerBlock("rock_salt_brick_stairs",
            new StairsBlock(ModBlocks.ROCK_SALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_SALT_BRICK_STAIRS = registerBlock("cracked_rock_salt_brick_stairs",
            new StairsBlock(ModBlocks.CRACKED_ROCK_SALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_STAIRS = registerBlock("rock_pink_salt_stairs",
            new StairsBlock(ModBlocks.ROCK_PINK_SALT_BLOCK.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_BRICK_STAIRS = registerBlock("rock_pink_salt_brick_stairs",
            new StairsBlock(ModBlocks.ROCK_PINK_SALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_PINK_SALT_BRICK_STAIRS = registerBlock("cracked_rock_pink_salt_brick_stairs",
            new StairsBlock(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final Block CLUMPED_SALT_SLAB = registerBlock("clumped_salt_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block CLUMPED_PINK_SALT_SLAB = registerBlock("clumped_pink_salt_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block ROCK_SALT_SLAB = registerBlock("rock_salt_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_SALT_BRICK_SLAB = registerBlock("rock_salt_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_SALT_BRICK_SLAB = registerBlock("cracked_rock_salt_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_SLAB = registerBlock("rock_pink_salt_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_BRICK_SLAB = registerBlock("rock_pink_salt_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_PINK_SALT_BRICK_SLAB = registerBlock("cracked_rock_pink_salt_brick_slab",
            new SlabBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final Block CLUMPED_SALT_WALL = registerBlock("clumped_salt_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block CLUMPED_PINK_SALT_WALL = registerBlock("clumped_pink_salt_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.0f)));
    public static final Block ROCK_SALT_WALL = registerBlock("rock_salt_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_SALT_BRICK_WALL = registerBlock("rock_salt_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_SALT_BRICK_WALL = registerBlock("cracked_rock_salt_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_WALL = registerBlock("rock_pink_salt_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_BRICK_WALL = registerBlock("rock_pink_salt_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CRACKED_ROCK_PINK_SALT_BRICK_WALL = registerBlock("cracked_rock_pink_salt_brick_wall",
            new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final Block CHISELED_ROCK_SALT_BRICKS = registerBlock("chiseled_rock_salt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block CHISELED_ROCK_PINK_SALT_BRICKS = registerBlock("chiseled_rock_pink_salt_bricks",
            new Block(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final Block ROCK_SALT_PILLAR = registerBlock("rock_salt_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));
    public static final Block ROCK_PINK_SALT_PILLAR = registerBlock("rock_pink_salt_pillar",
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STONE).strength(1.5f)));

    public static final FluidBlock OIL_BLOCK = Registry.register(Registries.BLOCK, Identifier.of(Saltcraft.MOD_ID, "oil_block"),
            new FluidBlock(ModFluids.OIL, AbstractBlock.Settings.copy(Blocks.WATER).dropsNothing()));

    public static final Block SUMMONING_PLINTH = registerBlock("summoning_plinth",
            new SummoningPlinthBlock(5, AbstractBlock.Settings.copy(Blocks.REINFORCED_DEEPSLATE).luminance(litBlockEmission(7)).nonOpaque().dropsNothing()));
    public static final Block SPAWNING_PLINTH = registerBlock("spawning_plinth",
            new SpawningPlinthBlock(AbstractBlock.Settings.copy(Blocks.REINFORCED_DEEPSLATE).luminance(litBlockEmission(5)).nonOpaque().dropsNothing()));

    private static ToIntFunction<net.minecraft.block.BlockState> litBlockEmission(int lightValue) {
        return state -> state.get(Properties.LIT) ? lightValue : 0;
    }

    private static <T extends Block> T registerBlock(String name, T block) {
        T registered = Registry.register(Registries.BLOCK, Identifier.of(Saltcraft.MOD_ID, name), block);
        Registry.register(Registries.ITEM, Identifier.of(Saltcraft.MOD_ID, name), new BlockItem(registered, new Item.Settings()));
        return registered;
    }

    public static void register() {
    }
}
