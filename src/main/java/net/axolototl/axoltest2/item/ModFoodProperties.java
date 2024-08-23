package net.axolototl.axoltest2.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties TOMATO = new FoodProperties.Builder().nutrition(3).saturationModifier(2)
            .effect(() -> new MobEffectInstance(MobEffects.LUCK, 2400), 1f).build();
}
// Para añadir bebidas se debe de hacer una custom clase