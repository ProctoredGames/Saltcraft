package net.proctoredgames.saltcraft.potion;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, Saltcraft.MOD_ID);

    public static final RegistryObject<Potion> SALT_WATER_BOTTLE = POTIONS.register("salt_water_bottle",
            () -> new Potion(new MobEffectInstance(ModEffects.THIRST.get(), 600, 0)));
    public static final RegistryObject<Potion> PINK_SALT_WATER_BOTTLE = POTIONS.register("pink_salt_water_bottle",
            () -> new Potion(new MobEffectInstance(ModEffects.THIRST.get(), 600, 0),
                    new MobEffectInstance(MobEffects.REGENERATION, 600, 0)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
