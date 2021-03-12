package pugz.hallows.core.registry;

import com.minecraftabnormals.abnormals_core.common.blocks.*;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.chest.AbnormalsTrappedChestBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsStandingSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.sign.AbnormalsWallSignBlock;
import com.minecraftabnormals.abnormals_core.common.blocks.wood.*;
import com.minecraftabnormals.abnormals_core.core.util.registry.BlockSubRegistryHelper;
import com.mojang.datafixers.util.Pair;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import pugz.hallows.common.block.*;
import pugz.hallows.common.block.LeafCarpetBlock;
import pugz.hallows.common.world.tree.AsphodelTree;
import pugz.hallows.common.world.tree.BloodEbonyTree;
import pugz.hallows.common.world.tree.EbonyTree;
import pugz.hallows.core.Hallows;

public class HallowsBlocks {
    public static final BlockSubRegistryHelper HELPER = Hallows.REGISTRY_HELPER.getBlockSubHelper();

    public static RegistryObject<Block> HALLSTONE;
    public static RegistryObject<Block> HALLSTONE_SLAB;
    public static RegistryObject<Block> HALLSTONE_VERTICAL_SLAB;
    public static RegistryObject<Block> HALLSTONE_STAIRS;
    public static RegistryObject<Block> HALLSTONE_WALL;
    public static RegistryObject<Block> POLISHED_HALLSTONE;
    public static RegistryObject<Block> POLISHED_HALLSTONE_SLAB;
    public static RegistryObject<Block> POLISHED_HALLSTONE_VERTICAL_SLAB;
    public static RegistryObject<Block> POLISHED_HALLSTONE_STAIRS;
    public static RegistryObject<Block> POLISHED_HALLSTONE_WALL;
    public static RegistryObject<Block> HALLOWED_DIRT;
    public static RegistryObject<Block> DEADROOT;

    public static RegistryObject<Block> OPAL_ORE;
    public static RegistryObject<Block> HALLSTONE_EMERALD_ORE;
    public static RegistryObject<Block> STYGIAN_RUIN;

    public static RegistryObject<Block> DAWN_MORTIS;
    public static RegistryObject<Block> DAWN_MORTIS_SLAB;
    public static RegistryObject<Block> DAWN_MORTIS_VERTICAL_SLAB;
    public static RegistryObject<Block> DAWN_MORTIS_STAIRS;
    public static RegistryObject<Block> DAWN_MORTIS_WALL;
    public static RegistryObject<Block> DAWN_MORTIS_BRICKS;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_VERTICAL_SLAB;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_WALL;
    public static RegistryObject<Block> NOON_MORTIS;
    public static RegistryObject<Block> NOON_MORTIS_SLAB;
    public static RegistryObject<Block> NOON_MORTIS_VERTICAL_SLAB;
    public static RegistryObject<Block> NOON_MORTIS_STAIRS;
    public static RegistryObject<Block> NOON_MORTIS_WALL;
    public static RegistryObject<Block> NOON_MORTIS_BRICKS;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_VERTICAL_SLAB;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_WALL;
    public static RegistryObject<Block> DUSK_MORTIS;
    public static RegistryObject<Block> DUSK_MORTIS_SLAB;
    public static RegistryObject<Block> DUSK_MORTIS_VERTICAL_SLAB;
    public static RegistryObject<Block> DUSK_MORTIS_STAIRS;
    public static RegistryObject<Block> DUSK_MORTIS_WALL;
    public static RegistryObject<Block> DUSK_MORTIS_BRICKS;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_VERTICAL_SLAB;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_WALL;
    public static RegistryObject<Block> MIDNIGHT_MORTIS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_SLAB;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_VERTICAL_SLAB;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_STAIRS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_WALL;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICKS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_VERTICAL_SLAB;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_WALL;
    //pavements

    public static RegistryObject<Block> NECROFIRE;
    public static RegistryObject<Block> NECROFIRE_CAMPFIRE;
    public static RegistryObject<Block> NECROFIRE_LANTERN;
    public static RegistryObject<Block> NECROFIRE_TORCH;
    public static RegistryObject<Block> NECROFIRE_WALL_TORCH;

    public static RegistryObject<Block> ASPHODEL_LOG;
    public static RegistryObject<Block> STRIPPED_ASPHODEL_LOG;
    public static RegistryObject<Block> ASPHODEL_WOOD;
    public static RegistryObject<Block> STRIPPED_ASPHODEL_WOOD;
    public static RegistryObject<Block> ASPHODEL_PLANKS;
    public static RegistryObject<Block> ASPHODEL_SLAB;
    public static RegistryObject<Block> ASPHODEL_STAIRS;
    public static RegistryObject<Block> ASPHODEL_FENCE;
    public static RegistryObject<Block> ASPHODEL_FENCE_GATE;
    public static RegistryObject<Block> ASPHODEL_BUTTON;
    public static RegistryObject<Block> ASPHODEL_PRESSURE_PLATE;
    public static RegistryObject<Block> ASPHODEL_SAPLING;
    public static RegistryObject<Block> POTTED_ASPHODEL_SAPLING;
    public static RegistryObject<Block> ASPHODEL_DOOR;
    public static RegistryObject<Block> ASPHODEL_TRAPDOOR;
    public static Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> ASPHODEL_SIGNS;
    public static RegistryObject<Block> VERTICAL_ASPHODEL_PLANKS;
    public static RegistryObject<Block> ASPHODEL_VERTICAL_SLAB;
    public static RegistryObject<Block> ASPHODEL_LADDER;
    public static RegistryObject<Block> ASPHODEL_BOOKSHELF;
    public static RegistryObject<Block> STRIPPED_ASPHODEL_POST;
    public static RegistryObject<Block> ASPHODEL_POST;
    public static RegistryObject<Block> ASPHODEL_BEEHIVE;
    public static Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> ASPHODEL_CHESTS;

    public static RegistryObject<Block> EBONY_LOG;
    public static RegistryObject<Block> STRIPPED_EBONY_LOG;
    public static RegistryObject<Block> EBONY_WOOD;
    public static RegistryObject<Block> STRIPPED_EBONY_WOOD;
    public static RegistryObject<Block> EBONY_PLANKS;
    public static RegistryObject<Block> EBONY_SLAB;
    public static RegistryObject<Block> EBONY_STAIRS;
    public static RegistryObject<Block> EBONY_FENCE;
    public static RegistryObject<Block> EBONY_FENCE_GATE;
    public static RegistryObject<Block> EBONY_BUTTON;
    public static RegistryObject<Block> EBONY_PRESSURE_PLATE;
    public static RegistryObject<Block> EBONY_LEAVES;
    public static RegistryObject<Block> EBONY_SAPLING;
    public static RegistryObject<Block> POTTED_EBONY_SAPLING;
    public static RegistryObject<Block> BLOOD_EBONY_LEAVES;
    public static RegistryObject<Block> BLOOD_EBONY_SAPLING;
    public static RegistryObject<Block> POTTED_BLOOD_EBONY_SAPLING;
    public static RegistryObject<Block> HANGING_EBONY_LEAVES;
    public static RegistryObject<Block> HANGING_BLOOD_EBONY_LEAVES;
    public static RegistryObject<Block> EBONY_DOOR;
    public static RegistryObject<Block> EBONY_TRAPDOOR;
    public static Pair<RegistryObject<AbnormalsStandingSignBlock>, RegistryObject<AbnormalsWallSignBlock>> EBONY_SIGNS;
    public static RegistryObject<Block> VERTICAL_EBONY_PLANKS;
    public static RegistryObject<Block> EBONY_VERTICAL_SLAB;
    public static RegistryObject<Block> EBONY_LADDER;
    public static RegistryObject<Block> EBONY_BOOKSHELF;
    public static RegistryObject<Block> EBONY_LEAF_CARPET;
    public static RegistryObject<Block> BLOOD_EBONY_LEAF_CARPET;
    public static RegistryObject<Block> STRIPPED_EBONY_POST;
    public static RegistryObject<Block> EBONY_POST;
    public static RegistryObject<Block> EBONY_HEDGE;
    public static RegistryObject<Block> BLOOD_EBONY_HEDGE;
    public static RegistryObject<Block> EBONY_BEEHIVE;
    public static Pair<RegistryObject<AbnormalsChestBlock>, RegistryObject<AbnormalsTrappedChestBlock>> EBONY_CHESTS;

    public static RegistryObject<Block> PETRIFIED_SAND;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_SLAB;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_VERTICAL_SLAB;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_STAIRS;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_WALL;

    public static RegistryObject<Block> IGNIS_CRYSTAL_FLOWER;
    public static RegistryObject<Block> IGNIS_CRYSTAL_BLOCK;

    public static RegistryObject<Block> HEMLOCK;

    public static RegistryObject<Block> TENEBRITE;
    public static RegistryObject<Block> TENEBRITE_SLAB;
    public static RegistryObject<Block> TENEBRITE_VERTICAL_SLAB;
    public static RegistryObject<Block> TENEBRITE_STAIRS;
    public static RegistryObject<Block> TENEBRITE_WALL;
    public static RegistryObject<Block> GILDED_TENEBRITE;
    public static RegistryObject<Block> SKULLISH_TENEBRITE;
    //tenebrite bricks
    //pavement

    public static RegistryObject<Block> ANOINTMENT_TABLE;
    public static RegistryObject<Block> GIANT_CAULDRON;

    public static RegistryObject<Block> ORANGE_WILL_O_WISP_VINES;
    public static RegistryObject<Block> YELLOW_WILL_O_WISP_VINES;
    public static RegistryObject<Block> BLUE_WILL_O_WISP_VINES;
    public static RegistryObject<Block> PURPLE_WILL_O_WISP_VINES;
    public static RegistryObject<Block> ORANGE_WILL_O_WISP_VINES_END;
    public static RegistryObject<Block> YELLOW_WILL_O_WISP_VINES_END;
    public static RegistryObject<Block> BLUE_WILL_O_WISP_VINES_END;
    public static RegistryObject<Block> PURPLE_WILL_O_WISP_VINES_END;

    public static void registerBlocks() {
        HALLSTONE = HELPER.createBlock("hallstone", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        HALLSTONE_SLAB = HELPER.createBlock("hallstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        HALLSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "hallstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        HALLSTONE_STAIRS = HELPER.createBlock("hallstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.HALLSTONE.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        HALLSTONE_WALL = HELPER.createBlock("hallstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        POLISHED_HALLSTONE = HELPER.createBlock("polished_hallstone", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        POLISHED_HALLSTONE_SLAB = HELPER.createBlock("polished_hallstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        POLISHED_HALLSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "polished_hallstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        POLISHED_HALLSTONE_STAIRS = HELPER.createBlock("polished_hallstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.POLISHED_HALLSTONE.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        POLISHED_HALLSTONE_WALL = HELPER.createBlock("polished_hallstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        HALLOWED_DIRT = HELPER.createBlock("hallowed_dirt", () -> new Block(AbstractBlock.Properties.copy(Blocks.DIRT)), ItemGroup.TAB_BUILDING_BLOCKS);
        DEADROOT = HELPER.createBlock("deadroot", () -> new DeadrootBlock(AbstractBlock.Properties.copy(Blocks.GRASS)), ItemGroup.TAB_DECORATIONS);

        OPAL_ORE = HELPER.createBlock("opal_ore", () -> new HallowsOreBlock(AbstractBlock.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().harvestLevel(2), 3, 7), ItemGroup.TAB_BUILDING_BLOCKS);
        HALLSTONE_EMERALD_ORE = HELPER.createBlock("hallstone_emerald_ore", () -> new HallowsOreBlock(AbstractBlock.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().harvestLevel(2), 3, 7), ItemGroup.TAB_BUILDING_BLOCKS);
        STYGIAN_RUIN = HELPER.createBlock("stygian_ruin", () -> new Block(AbstractBlock.Properties.copy(Blocks.ANCIENT_DEBRIS).requiresCorrectToolForDrops().harvestLevel(3)), ItemGroup.TAB_BUILDING_BLOCKS);

        DAWN_MORTIS = HELPER.createBlock("dawn_mortis", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_SLAB = HELPER.createBlock("dawn_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "dawn_mortis_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_STAIRS = HELPER.createBlock("dawn_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.DAWN_MORTIS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_WALL = HELPER.createBlock("dawn_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        DAWN_MORTIS_BRICKS = HELPER.createBlock("dawn_mortis_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_SLAB = HELPER.createBlock("dawn_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "dawn_mortis_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_STAIRS = HELPER.createBlock("dawn_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.DAWN_MORTIS_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_WALL = HELPER.createBlock("dawn_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        NOON_MORTIS = HELPER.createBlock("noon_mortis", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_SLAB = HELPER.createBlock("noon_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "noon_mortis_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_STAIRS = HELPER.createBlock("noon_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.NOON_MORTIS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_WALL = HELPER.createBlock("noon_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        NOON_MORTIS_BRICKS = HELPER.createBlock("noon_mortis_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_SLAB = HELPER.createBlock("noon_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "noon_mortis_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_STAIRS = HELPER.createBlock("noon_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.NOON_MORTIS_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_WALL = HELPER.createBlock("noon_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        DUSK_MORTIS = HELPER.createBlock("dusk_mortis", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_SLAB = HELPER.createBlock("dusk_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "dusk_mortis_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_STAIRS = HELPER.createBlock("dusk_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.DUSK_MORTIS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_WALL = HELPER.createBlock("dusk_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        DUSK_MORTIS_BRICKS = HELPER.createBlock("dusk_mortis_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_SLAB = HELPER.createBlock("dusk_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "dusk_mortis_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_STAIRS = HELPER.createBlock("dusk_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.DUSK_MORTIS_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_WALL = HELPER.createBlock("dusk_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        MIDNIGHT_MORTIS = HELPER.createBlock("midnight_mortis", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_SLAB = HELPER.createBlock("midnight_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "midnight_mortis_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_STAIRS = HELPER.createBlock("midnight_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.MIDNIGHT_MORTIS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_WALL = HELPER.createBlock("midnight_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);
        MIDNIGHT_MORTIS_BRICKS = HELPER.createBlock("midnight_mortis_bricks", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE_BRICKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_SLAB = HELPER.createBlock("midnight_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "midnight_mortis_brick_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_STAIRS = HELPER.createBlock("midnight_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.MIDNIGHT_MORTIS_BRICKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_WALL = HELPER.createBlock("midnight_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_DECORATIONS);

        NECROFIRE = HELPER.createBlockNoItem("necrofire", () -> new NecrofireBlock(AbstractBlock.Properties.copy(Blocks.FIRE).lightLevel((s) -> {
            return 12;
        })));
        NECROFIRE_CAMPFIRE = HELPER.createBlock("necrofire_campfire", () -> new NecrofireCampfireBlock(Block.Properties.copy(Blocks.CAMPFIRE)), ItemGroup.TAB_DECORATIONS);
        NECROFIRE_LANTERN = HELPER.createBlock("necrofire_lantern", () -> new LanternBlock(AbstractBlock.Properties.copy(Blocks.LANTERN).lightLevel((s) -> {
            return 15;
        })), ItemGroup.TAB_DECORATIONS);
        NECROFIRE_WALL_TORCH = HELPER.createBlockNoItem("necrofire_wall_torch", () -> new NecrofireWallTorchBlock(AbstractBlock.Properties.copy(Blocks.WALL_TORCH).lightLevel((s) -> {
            return 14;
        })));
        NECROFIRE_TORCH = HELPER.createWallOrFloorBlock("necrofire_torch", () -> new NecrofireTorchBlock(AbstractBlock.Properties.copy(Blocks.TORCH).lightLevel((s) -> {
            return 14;
        })), NECROFIRE_WALL_TORCH, ItemGroup.TAB_DECORATIONS);

        STRIPPED_ASPHODEL_LOG = HELPER.createBlock("stripped_asphodel_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_LOG = HELPER.createBlock("asphodel_log", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_ASPHODEL_LOG.get()), ItemGroup.TAB_BUILDING_BLOCKS);
        STRIPPED_ASPHODEL_WOOD = HELPER.createBlock("stripped_asphodel_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_WOOD = HELPER.createBlock("asphodel_wood", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_ASPHODEL_WOOD.get()), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_PLANKS = HELPER.createBlock("asphodel_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_SLAB = HELPER.createBlock("asphodel_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_STAIRS = HELPER.createBlock("asphodel_stairs", () -> new StairsBlock(() -> HallowsBlocks.ASPHODEL_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_FENCE = HELPER.createFuelBlock("asphodel_fence", () -> new WoodFenceBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
        ASPHODEL_FENCE_GATE = HELPER.createFuelBlock("asphodel_fence_gate", () -> new WoodFenceGateBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_REDSTONE);
        ASPHODEL_BUTTON = HELPER.createBlock("asphodel_button", () -> new AbnormalsWoodButtonBlock(AbstractBlock.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
        ASPHODEL_PRESSURE_PLATE = HELPER.createBlock("asphodel_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(Blocks.OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
        ASPHODEL_SAPLING = HELPER.createBlock("asphodel_sapling", () -> new AbnormalsSaplingBlock(new AsphodelTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)), ItemGroup.TAB_DECORATIONS);
        POTTED_ASPHODEL_SAPLING = HELPER.createBlockNoItem("potted_asphodel_sapling", () -> new FlowerPotBlock(ASPHODEL_SAPLING.get(), AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
        ASPHODEL_DOOR = HELPER.createBlock("asphodel_door", () -> new WoodDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
        ASPHODEL_TRAPDOOR = HELPER.createBlock("asphodel_trapdoor", () -> new WoodTrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
        ASPHODEL_SIGNS = HELPER.createSignBlock("asphodel", MaterialColor.SNOW);
        VERTICAL_ASPHODEL_PLANKS = HELPER.createCompatBlock("quark", "vertical_asphodel_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "asphodel_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_LADDER = HELPER.createCompatFuelBlock("quark", "asphodel_ladder", () -> new AbnormalsLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)), 300, ItemGroup.TAB_DECORATIONS);
        ASPHODEL_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "asphodel_bookshelf", () -> new BookshelfBlock(AbstractBlock.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
        STRIPPED_ASPHODEL_POST = HELPER.createCompatFuelBlock("quark", "stripped_asphodel_post", () -> new WoodPostBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_POST = HELPER.createCompatFuelBlock("quark", "asphodel_post", () -> new WoodPostBlock(STRIPPED_ASPHODEL_POST, AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
        ASPHODEL_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "asphodel_beehive", () -> new AbnormalsBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
        ASPHODEL_CHESTS = HELPER.createCompatChestBlocks("quark", "asphodel", MaterialColor.SNOW);

        STRIPPED_EBONY_LOG = HELPER.createBlock("stripped_ebony_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_LOG)), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_LOG = HELPER.createBlock("ebony_log", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_LOG.get()), ItemGroup.TAB_BUILDING_BLOCKS);
        STRIPPED_EBONY_WOOD = HELPER.createBlock("stripped_ebony_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.copy(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_WOOD = HELPER.createBlock("ebony_wood", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_WOOD.get()), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_PLANKS = HELPER.createBlock("ebony_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_SLAB = HELPER.createBlock("ebony_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_STAIRS = HELPER.createBlock("ebony_stairs", () -> new StairsBlock(() -> HallowsBlocks.EBONY_PLANKS.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_FENCE = HELPER.createFuelBlock("ebony_fence", () -> new WoodFenceBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
        EBONY_FENCE_GATE = HELPER.createFuelBlock("ebony_fence_gate", () -> new WoodFenceGateBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_REDSTONE);
        EBONY_BUTTON = HELPER.createBlock("ebony_button", () -> new AbnormalsWoodButtonBlock(AbstractBlock.Properties.copy(Blocks.OAK_BUTTON)), ItemGroup.TAB_REDSTONE);
        EBONY_PRESSURE_PLATE = HELPER.createBlock("ebony_pressure_plate", () -> new WoodPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, AbstractBlock.Properties.copy(Blocks.OAK_PRESSURE_PLATE)), ItemGroup.TAB_REDSTONE);
        EBONY_DOOR = HELPER.createBlock("ebony_door", () -> new WoodDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_DOOR)), ItemGroup.TAB_REDSTONE);
        EBONY_TRAPDOOR = HELPER.createBlock("ebony_trapdoor", () -> new WoodTrapDoorBlock(AbstractBlock.Properties.copy(Blocks.OAK_TRAPDOOR)), ItemGroup.TAB_REDSTONE);
        EBONY_SIGNS = HELPER.createSignBlock("ebony", MaterialColor.SNOW);
        EBONY_LEAVES = HELPER.createBlock("ebony_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)), ItemGroup.TAB_DECORATIONS);
        EBONY_SAPLING = HELPER.createBlock("ebony_sapling", () -> new AbnormalsSaplingBlock(new EbonyTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)), ItemGroup.TAB_DECORATIONS);
        POTTED_EBONY_SAPLING = HELPER.createBlockNoItem("potted_ebony_sapling", () -> new FlowerPotBlock(EBONY_SAPLING.get(), AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
        BLOOD_EBONY_LEAVES = HELPER.createBlock("blood_ebony_leaves", () -> new LeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)), ItemGroup.TAB_DECORATIONS);
        BLOOD_EBONY_SAPLING = HELPER.createBlock("blood_ebony_sapling", () -> new AbnormalsSaplingBlock(new BloodEbonyTree(), AbstractBlock.Properties.copy(Blocks.OAK_SAPLING)), ItemGroup.TAB_DECORATIONS);
        POTTED_BLOOD_EBONY_SAPLING = HELPER.createBlockNoItem("potted_blood_ebony_sapling", () -> new FlowerPotBlock(BLOOD_EBONY_SAPLING.get(), AbstractBlock.Properties.copy(Blocks.FLOWER_POT)));
        HANGING_EBONY_LEAVES = HELPER.createBlock("hanging_ebony_leaves", () -> new HangingLeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)), ItemGroup.TAB_DECORATIONS);
        HANGING_BLOOD_EBONY_LEAVES = HELPER.createBlock("hanging_blood_ebony_leaves", () -> new HangingLeavesBlock(AbstractBlock.Properties.copy(Blocks.OAK_LEAVES)), ItemGroup.TAB_DECORATIONS);

        VERTICAL_EBONY_PLANKS = HELPER.createCompatBlock("quark", "vertical_ebony_planks", () -> new Block(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_VERTICAL_SLAB = HELPER.createCompatFuelBlock("quark", "ebony_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 150, ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_LADDER = HELPER.createCompatFuelBlock("quark", "ebony_ladder", () -> new AbnormalsLadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER)), 300, ItemGroup.TAB_DECORATIONS);
        EBONY_BOOKSHELF = HELPER.createCompatFuelBlock("quark", "ebony_bookshelf", () -> new BookshelfBlock(AbstractBlock.Properties.copy(Blocks.BOOKSHELF)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_LEAF_CARPET = HELPER.createCompatBlock("quark", "ebony_leaf_carpet", () -> new LeafCarpetBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.SNOW).strength(0.0F).sound(SoundType.HARD_CROP).harvestTool(ToolType.HOE).dynamicShape()), ItemGroup.TAB_DECORATIONS);
        BLOOD_EBONY_LEAF_CARPET = HELPER.createCompatBlock("quark", "blood_ebony_leaf_carpet", () -> new LeafCarpetBlock(AbstractBlock.Properties.of(Material.WOOL, MaterialColor.SNOW).strength(0.0F).sound(SoundType.HARD_CROP).harvestTool(ToolType.HOE).dynamicShape()), ItemGroup.TAB_DECORATIONS);
        STRIPPED_EBONY_POST = HELPER.createCompatFuelBlock("quark", "stripped_ebony_post", () -> new WoodPostBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_POST = HELPER.createCompatFuelBlock("quark", "ebony_post", () -> new WoodPostBlock(STRIPPED_EBONY_POST, AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_BUILDING_BLOCKS);
        EBONY_HEDGE = HELPER.createCompatFuelBlock("quark", "ebony_hedge", () -> new HedgeBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
        BLOOD_EBONY_HEDGE = HELPER.createCompatFuelBlock("quark", "blood_ebony_hedge", () -> new HedgeBlock(AbstractBlock.Properties.copy(Blocks.OAK_PLANKS)), 300, ItemGroup.TAB_DECORATIONS);
        EBONY_BEEHIVE = HELPER.createCompatBlock("buzzier_bees", "ebony_beehive", () -> new AbnormalsBeehiveBlock(AbstractBlock.Properties.copy(Blocks.BEEHIVE)), ItemGroup.TAB_DECORATIONS);
        EBONY_CHESTS = HELPER.createCompatChestBlocks("quark", "ebony", MaterialColor.COLOR_BLACK);

        PETRIFIED_SAND = HELPER.createBlock("petrified_sand", () -> new SandBlock(6377538, AbstractBlock.Properties.copy(Blocks.SAND)), ItemGroup.TAB_BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE = HELPER.createBlock("petrified_sandstone", () -> new Block(AbstractBlock.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_SLAB = HELPER.createBlock("petrified_sandstone_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "petrified_sandstone_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_STAIRS = HELPER.createBlock("petrified_sandstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.PETRIFIED_SANDSTONE.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_WALL = HELPER.createBlock("petrified_sandstone_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.SANDSTONE)), ItemGroup.TAB_DECORATIONS);

        IGNIS_CRYSTAL_FLOWER = HELPER.createBlock("ignis_crystal_flower", () -> new IgnisCrystalBlock(AbstractBlock.Properties.copy(Blocks.STONE).lightLevel((s) -> {
            return 8;
        })), ItemGroup.TAB_DECORATIONS);
        IGNIS_CRYSTAL_BLOCK = HELPER.createBlock("ignis_crystal_block", () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE).lightLevel((s) -> {
            return 10;
        })), ItemGroup.TAB_BUILDING_BLOCKS);

        HEMLOCK = HELPER.createBlock("hemlock", () -> new HemlockBlock(AbstractBlock.Properties.copy(Blocks.GRASS)), ItemGroup.TAB_DECORATIONS);

        TENEBRITE = HELPER.createBlock("tenebrite", () -> new Block(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        TENEBRITE_SLAB = HELPER.createBlock("tenebrite_slab", () -> new SlabBlock(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        TENEBRITE_VERTICAL_SLAB = HELPER.createCompatBlock("quark", "tenebrite_vertical_slab", () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        TENEBRITE_STAIRS = HELPER.createBlock("tenebrite_stairs", () -> new StairsBlock(() -> HallowsBlocks.TENEBRITE.get().defaultBlockState(), AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        TENEBRITE_WALL = HELPER.createBlock("tenebrite_wall", () -> new WallBlock(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_DECORATIONS);
        GILDED_TENEBRITE = HELPER.createBlock("gilded_tenebrite", () -> new Block(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_BUILDING_BLOCKS);
        SKULLISH_TENEBRITE = HELPER.createBlock("skullish_tenebrite", () -> new Block(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_BUILDING_BLOCKS);

        ANOINTMENT_TABLE = HELPER.createBlock("anointment_table", () -> new AnointmentTableBlock(AbstractBlock.Properties.copy(Blocks.END_STONE)), ItemGroup.TAB_DECORATIONS);
        GIANT_CAULDRON = HELPER.createBlock("giant_cauldron", () -> new GiantCauldronBlock(AbstractBlock.Properties.copy(Blocks.CAULDRON)), ItemGroup.TAB_MISC);

        ORANGE_WILL_O_WISP_VINES = HELPER.createBlockNoItem("orange_will_o_wisp_vines", () -> new WillOWispVineStemBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineStemBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing).sound(SoundType.HARD_CROP), () -> HallowsItems.ORANGE_WILL_O_WISP_BERRY.get(), () -> ORANGE_WILL_O_WISP_VINES_END.get()));
        YELLOW_WILL_O_WISP_VINES = HELPER.createBlockNoItem("yellow_will_o_wisp_vines", () -> new WillOWispVineStemBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineStemBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing).sound(SoundType.HARD_CROP), () -> HallowsItems.YELLOW_WILL_O_WISP_BERRY.get(), () -> YELLOW_WILL_O_WISP_VINES_END.get()));
        BLUE_WILL_O_WISP_VINES = HELPER.createBlockNoItem("blue_will_o_wisp_vines", () -> new WillOWispVineStemBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineStemBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing).sound(SoundType.HARD_CROP), () -> HallowsItems.BLUE_WILL_O_WISP_BERRY.get(), () -> BLUE_WILL_O_WISP_VINES_END.get()));
        PURPLE_WILL_O_WISP_VINES = HELPER.createBlockNoItem("purple_will_o_wisp_vines", () -> new WillOWispVineStemBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineStemBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing).sound(SoundType.HARD_CROP), () -> HallowsItems.PURPLE_WILL_O_WISP_BERRY.get(), () -> PURPLE_WILL_O_WISP_VINES_END.get()));
        ORANGE_WILL_O_WISP_VINES_END = HELPER.createBlock("orange_will_o_wisp_vines_end", () -> new WillOWispVineEndBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineEndBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).sound(SoundType.HARD_CROP).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing), () -> HallowsItems.ORANGE_WILL_O_WISP_BERRY.get(), () -> ORANGE_WILL_O_WISP_VINES.get()), ItemGroup.TAB_DECORATIONS);
        YELLOW_WILL_O_WISP_VINES_END = HELPER.createBlock("yellow_will_o_wisp_vines_end", () -> new WillOWispVineEndBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineEndBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).sound(SoundType.HARD_CROP).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing), () -> HallowsItems.YELLOW_WILL_O_WISP_BERRY.get(), () -> YELLOW_WILL_O_WISP_VINES.get()), ItemGroup.TAB_DECORATIONS);
        BLUE_WILL_O_WISP_VINES_END = HELPER.createBlock("blue_will_o_wisp_vines_end", () -> new WillOWispVineEndBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineEndBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).sound(SoundType.HARD_CROP).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing), () -> HallowsItems.BLUE_WILL_O_WISP_BERRY.get(), () -> BLUE_WILL_O_WISP_VINES.get()), ItemGroup.TAB_DECORATIONS);
        PURPLE_WILL_O_WISP_VINES_END = HELPER.createBlock("purple_will_o_wisp_vines_end", () -> new WillOWispVineEndBlock(AbstractBlock.Properties.of(Material.PLANT, MaterialColor.NETHER).lightLevel((s) -> {
            return s.getValue(WillOWispVineEndBlock.FRUIT) ? 10 : 0;
        }).randomTicks().noCollission().strength(0.0F).sound(SoundType.HARD_CROP).emissiveRendering(HallowsBlocks::needsPostProcessing).hasPostProcess(HallowsBlocks::needsPostProcessing), () -> HallowsItems.PURPLE_WILL_O_WISP_BERRY.get(), () -> PURPLE_WILL_O_WISP_VINES.get()), ItemGroup.TAB_DECORATIONS);
    }

    private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    public static void registerRenderLayers() {
        RenderTypeLookup.setRenderLayer(NECROFIRE.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(EBONY_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(BLOOD_EBONY_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(HANGING_EBONY_LEAVES.get(), RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(HANGING_BLOOD_EBONY_LEAVES.get(), RenderType.cutoutMipped());

        RenderTypeLookup.setRenderLayer(DEADROOT.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ASPHODEL_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(EBONY_DOOR.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(EBONY_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOOD_EBONY_SAPLING.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(EBONY_LEAF_CARPET.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOOD_EBONY_LEAF_CARPET.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(EBONY_HEDGE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLOOD_EBONY_HEDGE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(IGNIS_CRYSTAL_FLOWER.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(HEMLOCK.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(NECROFIRE_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(NECROFIRE_WALL_TORCH.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(NECROFIRE_CAMPFIRE.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ORANGE_WILL_O_WISP_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(YELLOW_WILL_O_WISP_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLUE_WILL_O_WISP_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PURPLE_WILL_O_WISP_VINES.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ORANGE_WILL_O_WISP_VINES_END.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(YELLOW_WILL_O_WISP_VINES_END.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(BLUE_WILL_O_WISP_VINES_END.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(PURPLE_WILL_O_WISP_VINES_END.get(), RenderType.cutout());
    }

    public static void registerFlammability() {
        final FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.setFlammable(ASPHODEL_LOG.get(), 5, 5);
        fire.setFlammable(STRIPPED_ASPHODEL_LOG.get(), 5, 5);
        fire.setFlammable(ASPHODEL_WOOD.get(), 5, 5);
        fire.setFlammable(STRIPPED_ASPHODEL_WOOD.get(), 5, 5);
        fire.setFlammable(ASPHODEL_PLANKS.get(), 5, 20);
        fire.setFlammable(EBONY_LOG.get(), 5, 5);
        fire.setFlammable(STRIPPED_EBONY_LOG.get(), 5, 5);
        fire.setFlammable(EBONY_WOOD.get(), 5, 5);
        fire.setFlammable(STRIPPED_EBONY_WOOD.get(), 5, 5);
        fire.setFlammable(EBONY_PLANKS.get(), 5, 20);
        fire.setFlammable(EBONY_LEAVES.get(), 30, 60);
        fire.setFlammable(BLOOD_EBONY_LEAVES.get(), 30, 60);
        fire.setFlammable(HANGING_EBONY_LEAVES.get(), 30, 60);
        fire.setFlammable(HANGING_BLOOD_EBONY_LEAVES.get(), 30, 60);
        fire.setFlammable(DEADROOT.get(), 60, 100);
        fire.setFlammable(HEMLOCK.get(), 60, 100);
    }

    public static void registerCompostables() {
        ComposterBlock.COMPOSTABLES.put(EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(BLOOD_EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(HANGING_EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(HANGING_BLOOD_EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.COMPOSTABLES.put(DEADROOT.get(), 0.1F);
        ComposterBlock.COMPOSTABLES.put(HEMLOCK.get(), 0.25F);
        ComposterBlock.COMPOSTABLES.put(ORANGE_WILL_O_WISP_VINES_END.get(), 0.6F);
        ComposterBlock.COMPOSTABLES.put(YELLOW_WILL_O_WISP_VINES_END.get(), 0.6F);
        ComposterBlock.COMPOSTABLES.put(BLUE_WILL_O_WISP_VINES_END.get(), 0.6F);
        ComposterBlock.COMPOSTABLES.put(PURPLE_WILL_O_WISP_VINES_END.get(), 0.6F);
    }
}