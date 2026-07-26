package net.proctoredgames.saltcraft.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class ModEntities {
    public static final EntityType<Jellyfish> JELLYFISH = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Saltcraft.MOD_ID, "jellyfish"),
            EntityType.Builder.create(Jellyfish::new, SpawnGroup.WATER_AMBIENT)
                    .dimensions(0.5f, 0.25f).maxTrackingRange(10).build());

    public static final EntityType<Crystid> CRYSTID = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Saltcraft.MOD_ID, "crystid"),
            EntityType.Builder.create(Crystid::new, SpawnGroup.MONSTER)
                    .dimensions(0.8f, 1.0f).maxTrackingRange(8).build());

    public static final EntityType<SaltMage> SALT_MAGE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Saltcraft.MOD_ID, "salt_mage"),
            EntityType.Builder.create(SaltMage::new, SpawnGroup.MONSTER)
                    .dimensions(0.6f, 1.95f).maxTrackingRange(8).build());

    public static final EntityType<Flamingo> FLAMINGO = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Saltcraft.MOD_ID, "flamingo"),
            EntityType.Builder.create(Flamingo::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 1.95f).maxTrackingRange(10).spawnableFarFromPlayer().build());

    public static final EntityType<Mirage> MIRAGE = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Saltcraft.MOD_ID, "mirage"),
            EntityType.Builder.create(Mirage::new, SpawnGroup.MONSTER)
                    .dimensions(0.6f, 1.95f).maxTrackingRange(10).build());

    public static void register() {
        Saltcraft.LOGGER.info("Registering Mod Entities for " + Saltcraft.MOD_ID);
    }
}
