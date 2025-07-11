package vance.profit.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import vance.profit.Guaranteed_profit;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_CRAZY_SLOTS_TOOL =
                createTag("needs_crazy_slots_tool");

        public static final TagKey<Block> INCORRECT_FOR_CRAZY_SLOTS_TOOL =
                createTag("incorrect_for_crazy_slots_tool");

        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Guaranteed_profit.MOD_ID, name));
        }
    }
    public static class Items {
        public static final TagKey<Item> CRAZY_SLOTS_REPAIR = createTag("crazy_slots_repair");

        private static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, name));
        }
    }
}
