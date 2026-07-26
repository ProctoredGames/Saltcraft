package net.proctoredgames.saltcraft.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModEffects {
    public static final RegistryEntry.Reference<StatusEffect> THIRST = register("thirst",
            new ThirstEffect(StatusEffectCategory.HARMFUL, 3124687));

    private static RegistryEntry.Reference<StatusEffect> register(String name, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Saltcraft.MOD_ID, name), effect);
    }

    public static void register() {
    }
}
