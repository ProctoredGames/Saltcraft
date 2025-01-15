package net.proctoredgames.saltcraft.datagen.loot;

import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.SALT_BLOCK.get());
        this.dropSelf(ModBlocks.PINK_SALT_BLOCK.get());
        this.add(ModBlocks.CLUMPED_SALT_BLOCK.get(),
                block -> createSingleItemTableWithSilkTouch(ModBlocks.CLUMPED_SALT_BLOCK.get(), Item.byBlock(ModBlocks.SALT_BLOCK.get())));
        this.add(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get(),
                block -> createSingleItemTableWithSilkTouch(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get(), Item.byBlock(ModBlocks.PINK_SALT_BLOCK.get())));
        this.add(ModBlocks.ROCK_SALT_BLOCK.get(),
                block -> createSingleItemTableWithSilkTouch(ModBlocks.ROCK_SALT_BLOCK.get(), Item.byBlock(ModBlocks.CLUMPED_SALT_BLOCK.get())));
        this.add(ModBlocks.ROCK_PINK_SALT_BLOCK.get(),
                block -> createSingleItemTableWithSilkTouch(ModBlocks.ROCK_PINK_SALT_BLOCK.get(), Item.byBlock(ModBlocks.CLUMPED_PINK_SALT_BLOCK.get())));

        this.add(ModBlocks.SALT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.SALT_ORE.get(), ModItems.SALT.get()));
        this.add(ModBlocks.PINK_SALT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.PINK_SALT_ORE.get(), ModItems.PINK_SALT.get()));
        this.add(ModBlocks.DEEPSLATE_SALT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_SALT_ORE.get(), ModItems.SALT.get()));
        this.add(ModBlocks.DEEPSLATE_PINK_SALT_ORE.get(),
                block -> createCopperLikeOreDrops(ModBlocks.DEEPSLATE_PINK_SALT_ORE.get(), ModItems.PINK_SALT.get()));

        this.dropSelf(ModBlocks.ROCK_SALT_BRICKS.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_SALT_BRICKS.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS.get());

        this.dropSelf(ModBlocks.CLUMPED_SALT_STAIRS.get());
        this.dropSelf(ModBlocks.CLUMPED_PINK_SALT_STAIRS.get());
        this.dropSelf(ModBlocks.ROCK_SALT_STAIRS.get());
        this.dropSelf(ModBlocks.ROCK_SALT_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_STAIRS.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS.get());
        this.dropSelf(ModBlocks.CLUMPED_SALT_WALL.get());
        this.dropSelf(ModBlocks.CLUMPED_PINK_SALT_WALL.get());
        this.dropSelf(ModBlocks.ROCK_SALT_WALL.get());
        this.dropSelf(ModBlocks.ROCK_SALT_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_WALL.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_BRICK_WALL.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL.get());

        this.dropSelf(ModBlocks.CLUMPED_SALT_SLAB.get());
        this.dropSelf(ModBlocks.CLUMPED_PINK_SALT_SLAB.get());
        this.dropSelf(ModBlocks.ROCK_SALT_SLAB.get());
        this.dropSelf(ModBlocks.ROCK_SALT_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_SLAB.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB.get());
        this.dropSelf(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB.get());

        this.dropSelf(ModBlocks.CHISELED_ROCK_SALT_BRICKS.get());
        this.dropSelf(ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS.get());

        this.dropSelf(ModBlocks.ROCK_SALT_PILLAR.get());
        this.dropSelf(ModBlocks.ROCK_PINK_SALT_PILLAR.get());

        this.add(ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get(),
                block -> createSingleItemTableWithSilkTouch(ModBlocks.ROCK_SALT_FOSSIL_BLOCK.get(), ModItems.ROCK_SALT_FOSSIL.get()));

    }

    protected LootTable.Builder createCopperLikeOreDrops(Block pBlock, Item item) {
        return createSilkTouchDispatchTable(pBlock,
                this.applyExplosionDecay(pBlock,
                        LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                                .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}