package net.proctoredgames.saltcraft.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.block.custom.RockSaltFossilBlock;
import net.proctoredgames.saltcraft.block.custom.SaltFallingBlock;
import net.proctoredgames.saltcraft.block.custom.SpawningPlinthBlock;
import net.proctoredgames.saltcraft.block.custom.SummoningPlinthBlock;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Saltcraft.MOD_ID);

    public static final RegistryObject<Block> SALT_BLOCK = registerBlock("salt_block",
            () -> new SaltFallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND).randomTicks()));
    public static final RegistryObject<Block> PINK_SALT_BLOCK = registerBlock("pink_salt_block",
            () -> new SaltFallingBlock(BlockBehaviour.Properties.copy(Blocks.SAND).randomTicks()));

    public static final RegistryObject<Block> CLUMPED_SALT_BLOCK = registerBlock("clumped_salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> CLUMPED_PINK_SALT_BLOCK = registerBlock("clumped_pink_salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));

    public static final RegistryObject<Block> ROCK_SALT_BLOCK = registerBlock("rock_salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_SALT_BRICKS = registerBlock("rock_salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_SALT_BRICKS = registerBlock("cracked_rock_salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_BLOCK = registerBlock("rock_pink_salt_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_BRICKS = registerBlock("rock_pink_salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_PINK_SALT_BRICKS = registerBlock("cracked_rock_pink_salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<Block> ROCK_SALT_FOSSIL_BLOCK = registerBlock("rock_salt_fossil_block",
            () -> new RockSaltFossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<Block> SALT_ORE = registerBlock("salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    public static final RegistryObject<Block> PINK_SALT_ORE = registerBlock("pink_salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2f).requiresCorrectToolForDrops(), UniformInt.of(6,9)));
    public static final RegistryObject<Block> DEEPSLATE_SALT_ORE = registerBlock("deepslate_salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(3,6)));
    public static final RegistryObject<Block> DEEPSLATE_PINK_SALT_ORE = registerBlock("deepslate_pink_salt_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .strength(3f).requiresCorrectToolForDrops(), UniformInt.of(6,9)));

    public static final RegistryObject<Block> CLUMPED_SALT_STAIRS = registerBlock("clumped_salt_stairs",
            () -> new StairBlock(() -> ModBlocks.CLUMPED_SALT_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> CLUMPED_PINK_SALT_STAIRS = registerBlock("clumped_pink_salt_stairs",
            () -> new StairBlock(() -> ModBlocks.CLUMPED_PINK_SALT_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> ROCK_SALT_STAIRS = registerBlock("rock_salt_stairs",
            () -> new StairBlock(() -> ModBlocks.ROCK_SALT_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_SALT_BRICK_STAIRS = registerBlock("rock_salt_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.ROCK_SALT_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_SALT_BRICK_STAIRS = registerBlock("cracked_rock_salt_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.CRACKED_ROCK_SALT_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_STAIRS = registerBlock("rock_pink_salt_stairs",
            () -> new StairBlock(() -> ModBlocks.ROCK_PINK_SALT_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_BRICK_STAIRS = registerBlock("rock_pink_salt_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.ROCK_PINK_SALT_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_PINK_SALT_BRICK_STAIRS = registerBlock("cracked_rock_pink_salt_brick_stairs",
            () -> new StairBlock(() -> ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<Block> CLUMPED_SALT_SLAB = registerBlock("clumped_salt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> CLUMPED_PINK_SALT_SLAB = registerBlock("clumped_pink_salt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> ROCK_SALT_SLAB = registerBlock("rock_salt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_SALT_BRICK_SLAB = registerBlock("rock_salt_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_SALT_BRICK_SLAB = registerBlock("cracked_rock_salt_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_SLAB = registerBlock("rock_pink_salt_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_BRICK_SLAB = registerBlock("rock_pink_salt_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_PINK_SALT_BRICK_SLAB = registerBlock("cracked_rock_pink_salt_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<Block> CLUMPED_SALT_WALL = registerBlock("clumped_salt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> CLUMPED_PINK_SALT_WALL = registerBlock("clumped_pink_salt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.0f)));
    public static final RegistryObject<Block> ROCK_SALT_WALL = registerBlock("rock_salt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_SALT_BRICK_WALL = registerBlock("rock_salt_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_SALT_BRICK_WALL = registerBlock("cracked_rock_salt_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_WALL = registerBlock("rock_pink_salt_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_BRICK_WALL = registerBlock("rock_pink_salt_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CRACKED_ROCK_PINK_SALT_BRICK_WALL = registerBlock("cracked_rock_pink_salt_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<Block> CHISELED_ROCK_SALT_BRICKS = registerBlock("chiseled_rock_salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> CHISELED_ROCK_PINK_SALT_BRICKS = registerBlock("chiseled_rock_pink_salt_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<Block> ROCK_SALT_PILLAR = registerBlock("rock_salt_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));
    public static final RegistryObject<Block> ROCK_PINK_SALT_PILLAR  = registerBlock("rock_pink_salt_pillar",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(1.5f)));

    public static final RegistryObject<LiquidBlock> OIL_BLOCK = BLOCKS.register("oil_block",
            () -> new LiquidBlock(ModFluids.OIL, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<Block> SUMMONING_PLINTH = registerBlock("summoning_plinth",
            () -> new SummoningPlinthBlock(5, BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).lightLevel(litBlockEmission(7)).noOcclusion().noLootTable()));
    public static final RegistryObject<Block> SPAWNING_PLINTH = registerBlock("spawning_plinth",
            () -> new SpawningPlinthBlock(BlockBehaviour.Properties.copy(Blocks.REINFORCED_DEEPSLATE).lightLevel(litBlockEmission(5)).noOcclusion().noLootTable()));

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return (Boolean)p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 0;
        };
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}