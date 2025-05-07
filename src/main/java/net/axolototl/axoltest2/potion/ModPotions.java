package net.axolototl.axoltest2.potion;

import com.mojang.blaze3d.shaders.Effect;
import net.axolototl.axoltest2.AxolTest2;
import net.axolototl.axoltest2.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(BuiltInRegistries.POTION, AxolTest2.MOD_ID);

    public static final Holder<Potion> SLIMEY_POTION = POTIONS.register("slimey_potion",
            () -> new Potion(new MobEffectInstance(ModEffects.SLIMEY_EFFECT, 200,0)));

    public static final Holder<Potion> WITHER_POTION = POTIONS.register("wither_potion",
            ()-> new Potion(new MobEffectInstance(MobEffects.WITHER, 400, 0)));
    public static final Holder<Potion> DOLPHIN_POTION = POTIONS.register("dolphin_potion",
            ()-> new Potion(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 600, 0)));
    public static final Holder<Potion> HEALTH_POTION = POTIONS.register("health_potion",
            ()-> new Potion(new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0)));
    public static final Holder<Potion> FATIGUE_POTION = POTIONS.register("fatigue_potion",
            ()-> new Potion(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 0)));
    public static final Holder<Potion> MINING_POTION = POTIONS.register("mining_potion",
            ()-> new Potion(new MobEffectInstance(MobEffects.DIG_SPEED, 600, 0)));

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }
}
