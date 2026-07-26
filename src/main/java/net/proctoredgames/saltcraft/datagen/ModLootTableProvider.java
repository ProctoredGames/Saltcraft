package net.proctoredgames.saltcraft.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.block.Block;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.SALT_BLOCK);
        addDrop(ModBlocks.PINK_SALT_BLOCK);
        addDrop(ModBlocks.CLUMPED_SALT_BLOCK, block -> drops(block, ModBlocks.SALT_BLOCK));
        addDrop(ModBlocks.CLUMPED_PINK_SALT_BLOCK, block -> drops(block, ModBlocks.PINK_SALT_BLOCK));
        addDrop(ModBlocks.ROCK_SALT_BLOCK, block -> drops(block, ModBlocks.CLUMPED_SALT_BLOCK));
        addDrop(ModBlocks.ROCK_PINK_SALT_BLOCK, block -> drops(block, ModBlocks.CLUMPED_PINK_SALT_BLOCK));

        addDrop(ModBlocks.SALT_ORE, block -> createCopperOreDrops(block, ModItems.SALT));
        addDrop(ModBlocks.PINK_SALT_ORE, block -> createCopperOreDrops(block, ModItems.PINK_SALT));
        addDrop(ModBlocks.DEEPSLATE_SALT_ORE, block -> createCopperOreDrops(block, ModItems.SALT));
        addDrop(ModBlocks.DEEPSLATE_PINK_SALT_ORE, block -> createCopperOreDrops(block, ModItems.PINK_SALT));

        addDrop(ModBlocks.ROCK_SALT_BRICKS);
        addDrop(ModBlocks.ROCK_PINK_SALT_BRICKS);
        addDrop(ModBlocks.CRACKED_ROCK_SALT_BRICKS);
        addDrop(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICKS);

        addDrop(ModBlocks.CLUMPED_SALT_STAIRS);
        addDrop(ModBlocks.CLUMPED_PINK_SALT_STAIRS);
        addDrop(ModBlocks.ROCK_SALT_STAIRS);
        addDrop(ModBlocks.ROCK_SALT_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_ROCK_SALT_BRICK_STAIRS);
        addDrop(ModBlocks.ROCK_PINK_SALT_STAIRS);
        addDrop(ModBlocks.ROCK_PINK_SALT_BRICK_STAIRS);
        addDrop(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_STAIRS);
        addDrop(ModBlocks.CLUMPED_SALT_WALL);
        addDrop(ModBlocks.CLUMPED_PINK_SALT_WALL);
        addDrop(ModBlocks.ROCK_SALT_WALL);
        addDrop(ModBlocks.ROCK_SALT_BRICK_WALL);
        addDrop(ModBlocks.CRACKED_ROCK_SALT_BRICK_WALL);
        addDrop(ModBlocks.ROCK_PINK_SALT_WALL);
        addDrop(ModBlocks.ROCK_PINK_SALT_BRICK_WALL);
        addDrop(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_WALL);

        addDrop(ModBlocks.CLUMPED_SALT_SLAB, this::slabDrops);
        addDrop(ModBlocks.CLUMPED_PINK_SALT_SLAB, this::slabDrops);
        addDrop(ModBlocks.ROCK_SALT_SLAB, this::slabDrops);
        addDrop(ModBlocks.ROCK_SALT_BRICK_SLAB, this::slabDrops);
        addDrop(ModBlocks.CRACKED_ROCK_SALT_BRICK_SLAB, this::slabDrops);
        addDrop(ModBlocks.ROCK_PINK_SALT_SLAB, this::slabDrops);
        addDrop(ModBlocks.ROCK_PINK_SALT_BRICK_SLAB, this::slabDrops);
        addDrop(ModBlocks.CRACKED_ROCK_PINK_SALT_BRICK_SLAB, this::slabDrops);

        addDrop(ModBlocks.CHISELED_ROCK_SALT_BRICKS);
        addDrop(ModBlocks.CHISELED_ROCK_PINK_SALT_BRICKS);

        addDrop(ModBlocks.ROCK_SALT_PILLAR);
        addDrop(ModBlocks.ROCK_PINK_SALT_PILLAR);

        addDrop(ModBlocks.ROCK_SALT_FOSSIL_BLOCK, block -> drops(block, ModItems.ROCK_SALT_FOSSIL));
    }

    private LootTable.Builder createCopperOreDrops(Block block, Item item) {
        RegistryWrapper.Impl<Enchantment> enchantments = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return dropsWithSilkTouch(block,
                applyExplosionDecay(block,
                        ItemEntry.builder(item)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 5.0F)))
                                .apply(ApplyBonusLootFunction.oreDrops(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
