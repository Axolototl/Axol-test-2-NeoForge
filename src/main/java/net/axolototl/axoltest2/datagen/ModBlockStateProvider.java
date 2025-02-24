package net.axolototl.axoltest2.datagen;

import net.axolototl.axoltest2.AxolTest2;
import net.axolototl.axoltest2.block.ModBlocks;
import net.axolototl.axoltest2.block.custom.BlackOpalLampBlock;
import net.axolototl.axoltest2.block.custom.TomatoCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.awt.*;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AxolTest2.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.BLACK_OPAL_BLOCK);
        blockWithItem(ModBlocks.RAW_BLACK_OPAL_BLOCK);

        blockWithItem(ModBlocks.BLACK_OPAL_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_DEEPSLATE_ORE );
        blockWithItem(ModBlocks.BLACK_OPAL_END_ORE);
        blockWithItem(ModBlocks.BLACK_OPAL_NETHER_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        stairsBlock(((StairBlock) ModBlocks.BLACK_OPAL_STAIRS.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));
        slabBlock(((SlabBlock) ModBlocks.BLACK_OPAL_SLAB.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.BLACK_OPAL_PRESSURE_PLATE.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));
        buttonBlock(((ButtonBlock) ModBlocks.BLACK_OPAL_BUTTON.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));

        fenceBlock(((FenceBlock) ModBlocks.BLACK_OPAL_FENCE.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));
        fenceGateBlock(((FenceGateBlock) ModBlocks.BLACK_OPAL_FENCE_GATE.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));
        wallBlock(((WallBlock) ModBlocks.BLACK_OPAL_WALL.get()), blockTexture(ModBlocks.BLACK_OPAL_BLOCK.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.BLACK_OPAL_DOOR.get()), modLoc("block/black_opal_door_bottom"), modLoc("block/black_opal_door_top"), "cutout");
        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.BLACK_OPAL_TRAPDOOR.get()), modLoc("block/black_opal_trapdoor"), true, "cutout");

        blockItem(ModBlocks.BLACK_OPAL_STAIRS);
        blockItem(ModBlocks.BLACK_OPAL_SLAB);
        blockItem(ModBlocks.BLACK_OPAL_PRESSURE_PLATE);
        blockItem(ModBlocks.BLACK_OPAL_FENCE_GATE);

        blockItem(ModBlocks.BLACK_OPAL_TRAPDOOR, "_bottom");

        customLamp();

        makeCrop(((TomatoCropBlock) ModBlocks.TOMATO_CROP.get()), "tomato_crop_stage","tomato_crop_stage");

        simpleBlock(ModBlocks.TREBOL.get(),
                models().cross(blockTexture(ModBlocks.TREBOL.get()).getPath(), blockTexture(ModBlocks.TREBOL.get())).renderType("cutout"));

        simpleBlock(ModBlocks.POTTED_TREBOL.get(), models().singleTexture("potted_trebol",
                ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.TREBOL.get())).renderType("cutout"));

        leavesBlock(ModBlocks.COLORED_LEAVES);
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private void leavesBlock(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(),
                models().singleTexture(deferredBlock.getId().getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(deferredBlock.get())).renderType("cutout"));
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((TomatoCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, "block/" + textureName +
                        state.getValue(((TomatoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.BLACK_OPAL_LAMP.get()).forAllStates(state -> {
            if(state.getValue(BlackOpalLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("black_opal_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, "block/" + "black_opal_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("black_opal_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, "block/" + "black_opal_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.BLACK_OPAL_LAMP.get(), models().cubeAll("black_opal_lamp_on",
                ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, "block/" + "black_opal_lamp_on")));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("axoltest2:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<Block> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("axoltest2:block/" + deferredBlock.getId().getPath() + appendix));
    }
}