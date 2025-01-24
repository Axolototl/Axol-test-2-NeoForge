package net.axolototl.axoltest2.component;

import net.axolototl.axoltest2.AxolTest2;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public class ModDataComponentTypes {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES =
            DeferredRegister.createDataComponents(AxolTest2.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<BlockPos>> COORDINATES = register("coordinates",
            builder -> builder.persistent(BlockPos.CODEC));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<FoundBlockData>> FOUND_BLOCK =
            register("found_block", foundBlockDataBuilder -> foundBlockDataBuilder.persistent(FoundBlockData.CODEC));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builderOperator) {
        return DATA_COMPONENT_TYPES.register(name, () -> builderOperator.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }


}
