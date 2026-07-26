package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModModelLayers {
    public static final EntityModelLayer JELLYFISH_LAYER = new EntityModelLayer(
            Identifier.of(Saltcraft.MOD_ID, "jellyfish_layer"), "main");
    public static final EntityModelLayer CRYSTID_LAYER = new EntityModelLayer(
            Identifier.of(Saltcraft.MOD_ID, "crystid_layer"), "main");
    public static final EntityModelLayer SALT_MAGE_LAYER = new EntityModelLayer(
            Identifier.of(Saltcraft.MOD_ID, "salt_mage_layer"), "main");
    public static final EntityModelLayer FLAMINGO_LAYER = new EntityModelLayer(
            Identifier.of(Saltcraft.MOD_ID, "flamingo_layer"), "main");
    public static final EntityModelLayer MIRAGE_LAYER = new EntityModelLayer(
            Identifier.of(Saltcraft.MOD_ID, "mirage_layer"), "main");
}
