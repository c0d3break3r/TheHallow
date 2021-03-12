package pugz.hallows.core.registry.other;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import pugz.hallows.core.Hallows;

public class HallowsTags {
    public static class Blocks {
        public static final ITag.INamedTag<Block> ASPHODEL_LOGS = BlockTags.bind(Hallows.MOD_ID + ":asphodel_logs");
        public static final ITag.INamedTag<Block> EBONY_LOGS = BlockTags.bind(Hallows.MOD_ID + ":ebony_logs");
        public static final ITag.INamedTag<Block> INFINIBURN_HALLOWS = BlockTags.bind(Hallows.MOD_ID + ":infiniburn_hallows");
        public static final ITag.INamedTag<Block> NECROFIRE_BASE_BLOCKS = BlockTags.bind(Hallows.MOD_ID + ":necrofire_base_blocks");
        public static final ITag.INamedTag<Block> BASE_STONE_HALLOWS = BlockTags.bind(Hallows.MOD_ID + ":base_stone_hallows");
    }

    public static class Items {
        public static final ITag.INamedTag<Item> ASPHODEL_LOGS = ItemTags.bind(Hallows.MOD_ID + ":asphodel_logs");
        public static final ITag.INamedTag<Item> EBONY_LOGS = ItemTags.bind(Hallows.MOD_ID + ":ebony_logs");
        public static final ITag.INamedTag<Item> NECROFIRE_BASE_BLOCKS = ItemTags.bind(Hallows.MOD_ID + ":necrofire_base_blocks");
    }
}