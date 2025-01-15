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

    public static final FoodProperties SALTED_POTATO = new FoodProperties.Builder().nutrition(2)
            .saturationMod(0.3F).build();
    public static final FoodProperties SALTED_BAKED_POTATO = new FoodProperties.Builder().nutrition(6)
            .saturationMod(0.6F).build();
    public static final FoodProperties PINK_SALTED_POTATO = new FoodProperties.Builder().nutrition(4)
            .saturationMod(0.3F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION,600),1.0f).build();
    public static final FoodProperties PINK_SALTED_BAKED_POTATO = new FoodProperties.Builder().nutrition(8)
            .saturationMod(0.6F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION,600),1.0f).build();
}
