package net.axolototl.axoltest2.util;

import net.axolototl.axoltest2.AxolTest2;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> INCORRECT_FOR_BLACK_OPAL_TOOL = createTag("incorrect_for_black_opal_tool");
        public static final TagKey<Block> NEEDS_FOR_BLACK_OPAL_TOOL = createTag("needs_for_black_opal_tool");

        public static final TagKey<Block> PAXEL_MINABLE = createTag("mineables/paxel");

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, name));
        }
    }
    public static class Items{
        public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable_items");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(AxolTest2.MOD_ID, name));
        }
    }
}