package net.proctoredgames.saltcraft.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ModFoods {
    public static final FoodComponent SALT = new FoodComponent.Builder().nutrition(0)
            .saturationModifier(0f).statusEffect(new StatusEffectInstance(ModEffects.THIRST, 100, 0), 1.0f).build();
    public static final FoodComponent PINK_SALT = new FoodComponent.Builder().nutrition(0)
            .saturationModifier(0f).statusEffect(new StatusEffectInstance(ModEffects.THIRST, 100, 0), 1.0f)
                .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    // Vegetables
    public static final FoodComponent SALTED_BAKED_POTATO = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build();
    public static final FoodComponent PINK_SALTED_BAKED_POTATO = new FoodComponent.Builder().nutrition(8).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_BEETROOT = new FoodComponent.Builder().nutrition(2).saturationModifier(0.6F).build();
    public static final FoodComponent PINK_SALTED_BEETROOT = new FoodComponent.Builder().nutrition(4).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_CARROT = new FoodComponent.Builder().nutrition(4).saturationModifier(0.6F).build();
    public static final FoodComponent PINK_SALTED_CARROT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_POTATO = new FoodComponent.Builder().nutrition(2).saturationModifier(0.3F).build();
    public static final FoodComponent PINK_SALTED_POTATO = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    // Meats & Fish
    public static final FoodComponent SALTED_BEEF = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent PINK_SALTED_BEEF = new FoodComponent.Builder().nutrition(6).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_CHICKEN = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100), 0.3F).build();
    public static final FoodComponent PINK_SALTED_CHICKEN = new FoodComponent.Builder().nutrition(5).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100), 0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0F).build();

    public static final FoodComponent SALTED_COD = new FoodComponent.Builder().nutrition(3).saturationModifier(0.1F).build();
    public static final FoodComponent PINK_SALTED_COD = new FoodComponent.Builder().nutrition(5).saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_BEEF = new FoodComponent.Builder().nutrition(9).saturationModifier(0.8F).build();
    public static final FoodComponent PINK_SALTED_COOKED_BEEF = new FoodComponent.Builder().nutrition(11).saturationModifier(0.8F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_CHICKEN = new FoodComponent.Builder().nutrition(7).saturationModifier(0.6F).build();
    public static final FoodComponent PINK_SALTED_COOKED_CHICKEN = new FoodComponent.Builder().nutrition(9).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_COD = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build();
    public static final FoodComponent PINK_SALTED_COOKED_COD = new FoodComponent.Builder().nutrition(8).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_MUTTON = new FoodComponent.Builder().nutrition(7).saturationModifier(0.8F).build();
    public static final FoodComponent PINK_SALTED_COOKED_MUTTON = new FoodComponent.Builder().nutrition(9).saturationModifier(0.8F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_PORKCHOP = new FoodComponent.Builder().nutrition(9).saturationModifier(0.8F).build();
    public static final FoodComponent PINK_SALTED_COOKED_PORKCHOP = new FoodComponent.Builder().nutrition(11).saturationModifier(0.8F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_RABBIT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.6F).build();
    public static final FoodComponent PINK_SALTED_COOKED_RABBIT = new FoodComponent.Builder().nutrition(8).saturationModifier(0.6F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_COOKED_SALMON = new FoodComponent.Builder().nutrition(7).saturationModifier(0.8F).build();
    public static final FoodComponent PINK_SALTED_COOKED_SALMON = new FoodComponent.Builder().nutrition(9).saturationModifier(0.8F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_MUTTON = new FoodComponent.Builder().nutrition(3).saturationModifier(0.3F).build();
    public static final FoodComponent PINK_SALTED_MUTTON = new FoodComponent.Builder().nutrition(5).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_PORKCHOP = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent PINK_SALTED_PORKCHOP = new FoodComponent.Builder().nutrition(6).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_PUFFERFISH = new FoodComponent.Builder().nutrition(2).saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300), 1.0F).build();
    public static final FoodComponent PINK_SALTED_PUFFERFISH = new FoodComponent.Builder().nutrition(4).saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 1200), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300), 1.0F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0F).build();

    public static final FoodComponent SALTED_RABBIT = new FoodComponent.Builder().nutrition(4).saturationModifier(0.3F).build();
    public static final FoodComponent PINK_SALTED_RABBIT = new FoodComponent.Builder().nutrition(6).saturationModifier(0.3F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_SALMON = new FoodComponent.Builder().nutrition(3).saturationModifier(0.1F).build();
    public static final FoodComponent PINK_SALTED_SALMON = new FoodComponent.Builder().nutrition(5).saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_TROPICAL_FISH = new FoodComponent.Builder().nutrition(3).saturationModifier(0.1F).build();
    public static final FoodComponent PINK_SALTED_TROPICAL_FISH = new FoodComponent.Builder().nutrition(5).saturationModifier(0.1F)
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f).build();

    public static final FoodComponent SALTED_DRIED_KELP = new FoodComponent.Builder()
            .nutrition(2)  // Vanilla (1) +1 as with other salted foods
            .saturationModifier(0.3F)  // Matches vanilla dried kelp's saturation
            .build();

    public static final FoodComponent PINK_SALTED_DRIED_KELP = new FoodComponent.Builder()
            .nutrition(4)  // Vanilla (1) +3 as with other pink salted foods
            .saturationModifier(0.3F)  // Same saturation as base
            .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100), 1.0f)
            .build();
}
