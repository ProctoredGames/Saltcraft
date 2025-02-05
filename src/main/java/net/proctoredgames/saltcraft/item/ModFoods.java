package net.proctoredgames.saltcraft.item;


import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.proctoredgames.saltcraft.effect.ModEffects;

public class ModFoods {
    public static final FoodProperties SALT = new FoodProperties.Builder().nutrition(0)
            .saturationMod(0f).effect(() -> new MobEffectInstance(ModEffects.THIRST.get(),600, 0),1.0f).build();
    public static final FoodProperties PINK_SALT = new FoodProperties.Builder().nutrition(0)
            .saturationMod(0f).effect(() -> new MobEffectInstance(ModEffects.THIRST.get(),600, 0),1.0f)
                .effect(() -> new MobEffectInstance(MobEffects.REGENERATION,600),1.0f).build();

    // Vegetables
    public static final FoodProperties SALTED_BAKED_POTATO = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_BAKED_POTATO = new FoodProperties.Builder().nutrition(8).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_BEETROOT = new FoodProperties.Builder().nutrition(2).saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_BEETROOT = new FoodProperties.Builder().nutrition(4).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_CARROT = new FoodProperties.Builder().nutrition(4).saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_CARROT = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_POTATO = new FoodProperties.Builder().nutrition(2).saturationMod(0.3F).build();
    public static final FoodProperties PINK_SALTED_POTATO = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    // Meats & Fish
    public static final FoodProperties SALTED_BEEF = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties PINK_SALTED_BEEF = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_CHICKEN = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600), 0.3F).build();
    public static final FoodProperties PINK_SALTED_CHICKEN = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 600), 0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0F).build();

    public static final FoodProperties SALTED_COD = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build();
    public static final FoodProperties PINK_SALTED_COD = new FoodProperties.Builder().nutrition(5).saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_BEEF = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F).build();
    public static final FoodProperties PINK_SALTED_COOKED_BEEF = new FoodProperties.Builder().nutrition(11).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_CHICKEN = new FoodProperties.Builder().nutrition(7).saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_COOKED_CHICKEN = new FoodProperties.Builder().nutrition(9).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_COD = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_COOKED_COD = new FoodProperties.Builder().nutrition(8).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_MUTTON = new FoodProperties.Builder().nutrition(7).saturationMod(0.8F).build();
    public static final FoodProperties PINK_SALTED_COOKED_MUTTON = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_PORKCHOP = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F).build();
    public static final FoodProperties PINK_SALTED_COOKED_PORKCHOP = new FoodProperties.Builder().nutrition(11).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_RABBIT = new FoodProperties.Builder().nutrition(6).saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_COOKED_RABBIT = new FoodProperties.Builder().nutrition(8).saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_COOKED_SALMON = new FoodProperties.Builder().nutrition(7).saturationMod(0.8F).build();
    public static final FoodProperties PINK_SALTED_COOKED_SALMON = new FoodProperties.Builder().nutrition(9).saturationMod(0.8F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_MUTTON = new FoodProperties.Builder().nutrition(3).saturationMod(0.3F).build();
    public static final FoodProperties PINK_SALTED_MUTTON = new FoodProperties.Builder().nutrition(5).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_PORKCHOP = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties PINK_SALTED_PORKCHOP = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_PUFFERFISH = new FoodProperties.Builder().nutrition(2).saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 1200), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300), 1.0F).build();
    public static final FoodProperties PINK_SALTED_PUFFERFISH = new FoodProperties.Builder().nutrition(4).saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 300), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 1200), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 300), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0F).build();

    public static final FoodProperties SALTED_RABBIT = new FoodProperties.Builder().nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties PINK_SALTED_RABBIT = new FoodProperties.Builder().nutrition(6).saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_SALMON = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build();
    public static final FoodProperties PINK_SALTED_SALMON = new FoodProperties.Builder().nutrition(5).saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();

    public static final FoodProperties SALTED_TROPICAL_FISH = new FoodProperties.Builder().nutrition(3).saturationMod(0.1F).build();
    public static final FoodProperties PINK_SALTED_TROPICAL_FISH = new FoodProperties.Builder().nutrition(5).saturationMod(0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 600), 1.0f).build();
}
