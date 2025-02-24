package net.axolototl.axoltest2;

import net.axolototl.axoltest2.block.ModBlocks;
import net.axolototl.axoltest2.component.ModDataComponentTypes;
import net.axolototl.axoltest2.item.ModArmorMaterials;
import net.axolototl.axoltest2.item.ModCreativeModeTabs;
import net.axolototl.axoltest2.item.ModItems;
import net.axolototl.axoltest2.util.ModItemProperties;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AxolTest2.MOD_ID)
public class AxolTest2 {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "axoltest2";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public AxolTest2(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        ModItems.register(modEventBus);

        ModBlocks.register(modEventBus);

        ModCreativeModeTabs.register(modEventBus);

        ModArmorMaterials.register(modEventBus);

        ModDataComponentTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->{
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.TREBOL.getId(), ModBlocks.POTTED_TREBOL);
        });
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS){
            event.accept(ModItems.BLACK_OPAL);
            event.accept(ModItems.RAW_BLACK_OPAL);
            }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ModItemProperties.customItemProperties();
        }

        @SubscribeEvent
        public static void registerColoredBlocks(RegisterColorHandlersEvent.Block event) {
            event.register((blockState, blockAndTintGetter, blockPos, i) -> blockAndTintGetter != null && blockPos != null ?
                    BiomeColors.getAverageFoliageColor(blockAndTintGetter, blockPos) : FoliageColor.getDefaultColor(),
                     ModBlocks.COLORED_LEAVES.get());
        }

        @SubscribeEvent
        public static void registerColoreditems(RegisterColorHandlersEvent.Item event) {
            event.register((itemStack, i) -> FoliageColor.getDefaultColor(), ModBlocks.COLORED_LEAVES);
        }
    }
}
