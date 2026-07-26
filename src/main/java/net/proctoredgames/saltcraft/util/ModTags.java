package net.proctoredgames.saltcraft.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModTags {

    public static class Blocks {
        public static final TagKey<Block> ALL_ORES = tag("all_ores");
        public static final TagKey<Block> SALT_MATERIALS = tag("salt_materials");
        public static final TagKey<Block> PINK_SALT_MATERIALS = tag("pink_salt_materials");
        public static final TagKey<Block> ALL_SALT_MATERIALS = tag("all_salt_materials");
        public static final TagKey<Block> SUMMONING_PLINTH_TAG = tag("summoning_plinth_tag");

        private static TagKey<Block> tag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Saltcraft.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> SALT_OR_PINK_SALT = tag("salt_or_pink_salt");
        public static final TagKey<Item> QUENCHES_THIRST_3_POINTS = tag("quenches_thirst_3_points");
        public static final TagKey<Item> QUENCHES_THIRST_5_POINTS = tag("quenches_thirst_5_points");

        private static TagKey<Item> tag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Saltcraft.MOD_ID, name));
        }
    }
}
