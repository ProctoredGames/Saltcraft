package net.proctoredgames.saltcraft.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.proctoredgames.saltcraft.Saltcraft;
//import net.proctoredgames.saltcraft.entity.custom.CustomTurtle;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Saltcraft.MOD_ID);

    static int templateClientTrackingRange = EntityType.COW.clientTrackingRange();

    public static final RegistryObject<EntityType<Jellyfish>> JELLYFISH =
            ENTITY_TYPES.register("jellyfish", () -> EntityType.Builder.of(Jellyfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.25f).clientTrackingRange(8).build("jellyfish"));

    public static final RegistryObject<EntityType<Crystid>> CRYSTID =
            ENTITY_TYPES.register("crystid", () -> EntityType.Builder.of(Crystid::new, MobCategory.MONSTER)
                    .sized(0.8f, 1.0f).clientTrackingRange(8).build("crystid"));

    public static final RegistryObject<EntityType<SaltMage>> SALT_MAGE =
            ENTITY_TYPES.register("salt_mage", () -> EntityType.Builder.of(SaltMage::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).clientTrackingRange(8).build("salt_mage"));

    public static final RegistryObject<EntityType<Flamingo>> FLAMINGO =
            ENTITY_TYPES.register("flamingo", () -> EntityType.Builder.of(Flamingo::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.95F).clientTrackingRange(10).canSpawnFarFromPlayer().build("flamingo"));

    public static final RegistryObject<EntityType<Mirage>> MIRAGE =
            ENTITY_TYPES.register("mirage", () -> EntityType.Builder.of(Mirage::new, MobCategory.MONSTER)
                    .sized(0.6F, 1.95F).clientTrackingRange(10).build("mirage"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}