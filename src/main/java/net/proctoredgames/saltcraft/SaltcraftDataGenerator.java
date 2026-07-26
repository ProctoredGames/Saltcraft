package net.proctoredgames.saltcraft;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.proctoredgames.saltcraft.datagen.ModBlockTagProvider;
import net.proctoredgames.saltcraft.datagen.ModItemTagProvider;
import net.proctoredgames.saltcraft.datagen.ModLootTableProvider;
import net.proctoredgames.saltcraft.datagen.ModModelProvider;
import net.proctoredgames.saltcraft.datagen.ModRecipeProvider;

public class SaltcraftDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
