package net.proctoredgames.saltcraft.potion;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ModPotions {
    public static final Potion SALT_WATER_BOTTLE = register("salt_water_bottle",
            new Potion(new StatusEffectInstance(ModEffects.THIRST, 600, 0)));
    public static final Potion PINK_SALT_WATER_BOTTLE = register("pink_salt_water_bottle",
            new Potion(new StatusEffectInstance(ModEffects.THIRST, 600, 0),
                    new StatusEffectInstance(StatusEffects.REGENERATION, 600, 0)));

    private static Potion register(String name, Potion potion) {
        return Registry.register(Registries.POTION, Identifier.of(Saltcraft.MOD_ID, name), potion);
    }

    public static void register() {
    }
}
