package net.axolototl.axoltest2.item;

import net.axolototl.axoltest2.AxolTest2;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, AxolTest2.MOD_ID);

    public static final Holder<ArmorMaterial> BLACK_OPAL = ARMOR_MATERIALS.register("black_opal", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), typeIntegerEnumMap -> {
                typeIntegerEnumMap.put(ArmorItem.Type.BODY, 6);
                typeIntegerEnumMap.put(ArmorItem.Type.BOOTS, 3);
                typeIntegerEnumMap.put(ArmorItem.Type.HELMET, 3);
                typeIntegerEnumMap.put(ArmorItem.Type.CHESTPLATE, 6);
                typeIntegerEnumMap.put(ArmorItem.Type.LEGGINGS, 5);
            }), 20, SoundEvents.ARMOR_EQUIP_NETHERITE, () -> Ingredient.of(ModItems.BLACK_OPAL.get()),
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, "black_opal"))),
            4, 0.5f));

    public static void register(IEventBus eventBus) {
        ARMOR_MATERIALS.register(eventBus);
    }

}
