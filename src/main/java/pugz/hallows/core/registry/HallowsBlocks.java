package pugz.hallows.core.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.block.*;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Hallows.MOD_ID);

    public static RegistryObject<Block> HALLSTONE;
    public static RegistryObject<Block> HALLSTONE_SLAB;
    public static RegistryObject<Block> HALLSTONE_STAIRS;
    public static RegistryObject<Block> HALLSTONE_WALL;
    public static RegistryObject<Block> POLISHED_HALLSTONE;
    public static RegistryObject<Block> POLISHED_HALLSTONE_SLAB;
    public static RegistryObject<Block> POLISHED_HALLSTONE_STAIRS;
    public static RegistryObject<Block> POLISHED_HALLSTONE_WALL;
    public static RegistryObject<Block> HALLOWED_DIRT;
    public static RegistryObject<Block> DEADROOT;

    public static RegistryObject<Block> OPAL_ORE;
    public static RegistryObject<Block> HALLSTONE_EMERALD_ORE;
    public static RegistryObject<Block> STYGIAN_RUIN;

    public static RegistryObject<Block> DAWN_MORTIS;
    public static RegistryObject<Block> DAWN_MORTIS_SLAB;
    public static RegistryObject<Block> DAWN_MORTIS_STAIRS;
    public static RegistryObject<Block> DAWN_MORTIS_WALL;
    public static RegistryObject<Block> DAWN_MORTIS_BRICKS;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> DAWN_MORTIS_BRICK_WALL;
    public static RegistryObject<Block> NOON_MORTIS;
    public static RegistryObject<Block> NOON_MORTIS_SLAB;
    public static RegistryObject<Block> NOON_MORTIS_STAIRS;
    public static RegistryObject<Block> NOON_MORTIS_WALL;
    public static RegistryObject<Block> NOON_MORTIS_BRICKS;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> NOON_MORTIS_BRICK_WALL;
    public static RegistryObject<Block> DUSK_MORTIS;
    public static RegistryObject<Block> DUSK_MORTIS_SLAB;
    public static RegistryObject<Block> DUSK_MORTIS_STAIRS;
    public static RegistryObject<Block> DUSK_MORTIS_WALL;
    public static RegistryObject<Block> DUSK_MORTIS_BRICKS;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> DUSK_MORTIS_BRICK_WALL;
    public static RegistryObject<Block> MIDNIGHT_MORTIS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_SLAB;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_STAIRS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_WALL;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICKS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_SLAB;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_STAIRS;
    public static RegistryObject<Block> MIDNIGHT_MORTIS_BRICK_WALL;

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

    public static RegistryObject<Block> EBONY_LOG;
    public static RegistryObject<Block> STRIPPED_EBONY_LOG;
    public static RegistryObject<Block> EBONY_WOOD;
    public static RegistryObject<Block> STRIPPED_EBONY_WOOD;
    public static RegistryObject<Block> EBONY_BRANCH;
    public static RegistryObject<Block> STRIPPED_EBONY_BRANCH;
    public static RegistryObject<Block> EBONY_PLANKS;
    public static RegistryObject<Block> EBONY_SLAB;
    public static RegistryObject<Block> EBONY_STAIRS;
    public static RegistryObject<Block> EBONY_FENCE;
    public static RegistryObject<Block> EBONY_FENCE_GATE;
    public static RegistryObject<Block> EBONY_BUTTON;
    public static RegistryObject<Block> EBONY_PRESSURE_PLATE;
    public static RegistryObject<Block> EBONY_LEAVES;
    public static RegistryObject<Block> BLOOD_EBONY_LEAVES;
    public static RegistryObject<Block> HANGING_EBONY_LEAVES;
    public static RegistryObject<Block> HANGING_BLOOD_EBONY_LEAVES;
    public static RegistryObject<Block> EBONY_LEAF_CARPET;
    public static RegistryObject<Block> BLOOD_EBONY_LEAF_CARPET;

    public static RegistryObject<Block> PETRIFIED_SAND;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_SLAB;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_STAIRS;
    public static RegistryObject<Block> PETRIFIED_SANDSTONE_WALL;

    public static RegistryObject<Block> IGNIS_CRYSTAL_FLOWER;
    public static RegistryObject<Block> IGNIS_CRYSTAL_STEM;
    public static RegistryObject<Block> IGNIS_CRYSTAL_BLOCK;

    public static RegistryObject<Block> HEMLOCK;

    public static RegistryObject<Block> TENEBRITE;
    public static RegistryObject<Block> TENEBRITE_SLAB;
    public static RegistryObject<Block> TENEBRITE_STAIRS;
    public static RegistryObject<Block> TENEBRITE_WALL;
    public static RegistryObject<Block> GILDED_TENEBRITE;
    public static RegistryObject<Block> SKULLISH_TENEBRITE;

    public static RegistryObject<Block> ANOINTMENT_TABLE;
    public static RegistryObject<Block> GIANT_CAULDRON;

    public static RegistryObject<Block> WILL_O_WISP_VINES;
    public static RegistryObject<Block> ORANGE_WILL_O_WISP_FRUIT;
    public static RegistryObject<Block> YELLOW_WILL_O_WISP_FRUIT;
    public static RegistryObject<Block> BLUE_WILL_O_WISP_FRUIT;
    public static RegistryObject<Block> PURPLE_WILL_O_WISP_FRUIT;

    public static void registerBlocks() {
        HALLSTONE = RegistryHelper.createBlock("hallstone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_SLAB = RegistryHelper.createBlock("hallstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_STAIRS = RegistryHelper.createBlock("hallstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.HALLSTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_WALL = RegistryHelper.createBlock("hallstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        POLISHED_HALLSTONE = RegistryHelper.createBlock("polished_hallstone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE_SLAB = RegistryHelper.createBlock("polished_hallstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE_STAIRS = RegistryHelper.createBlock("polished_hallstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.POLISHED_HALLSTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE_WALL = RegistryHelper.createBlock("polished_hallstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        HALLOWED_DIRT = RegistryHelper.createBlock("hallowed_dirt", () -> new Block(AbstractBlock.Properties.from(Blocks.DIRT)), ItemGroup.BUILDING_BLOCKS);
        DEADROOT = RegistryHelper.createBlock("deadroot", () -> new DeadrootBlock(AbstractBlock.Properties.from(Blocks.GRASS)), ItemGroup.DECORATIONS);

        OPAL_ORE = RegistryHelper.createBlock("opal_ore", () -> new HallowsOreBlock(AbstractBlock.Properties.from(Blocks.STONE).setRequiresTool().harvestLevel(2), 3, 7), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_EMERALD_ORE = RegistryHelper.createBlock("hallstone_emerald_ore", () -> new HallowsOreBlock(AbstractBlock.Properties.from(Blocks.STONE).setRequiresTool().harvestLevel(2), 3, 7), ItemGroup.BUILDING_BLOCKS);
        STYGIAN_RUIN = RegistryHelper.createBlock("stygian_ruin", () -> new Block(AbstractBlock.Properties.from(Blocks.ANCIENT_DEBRIS).setRequiresTool().harvestLevel(3)), ItemGroup.BUILDING_BLOCKS);

        DAWN_MORTIS = RegistryHelper.createBlock("dawn_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_SLAB = RegistryHelper.createBlock("dawn_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_STAIRS = RegistryHelper.createBlock("dawn_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.DAWN_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_WALL = RegistryHelper.createBlock("dawn_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        DAWN_MORTIS_BRICKS = RegistryHelper.createBlock("dawn_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_SLAB = RegistryHelper.createBlock("dawn_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_STAIRS = RegistryHelper.createBlock("dawn_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.DAWN_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_WALL = RegistryHelper.createBlock("dawn_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        NOON_MORTIS = RegistryHelper.createBlock("noon_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_SLAB = RegistryHelper.createBlock("noon_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_STAIRS = RegistryHelper.createBlock("noon_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.NOON_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_WALL = RegistryHelper.createBlock("noon_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        NOON_MORTIS_BRICKS = RegistryHelper.createBlock("noon_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_SLAB = RegistryHelper.createBlock("noon_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_STAIRS = RegistryHelper.createBlock("noon_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.NOON_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_WALL = RegistryHelper.createBlock("noon_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        DUSK_MORTIS = RegistryHelper.createBlock("dusk_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_SLAB = RegistryHelper.createBlock("dusk_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_STAIRS = RegistryHelper.createBlock("dusk_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.DUSK_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_WALL = RegistryHelper.createBlock("dusk_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        DUSK_MORTIS_BRICKS = RegistryHelper.createBlock("dusk_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_SLAB = RegistryHelper.createBlock("dusk_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_STAIRS = RegistryHelper.createBlock("dusk_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.DUSK_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_WALL = RegistryHelper.createBlock("dusk_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        MIDNIGHT_MORTIS = RegistryHelper.createBlock("midnight_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_SLAB = RegistryHelper.createBlock("midnight_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_STAIRS = RegistryHelper.createBlock("midnight_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.MIDNIGHT_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_WALL = RegistryHelper.createBlock("midnight_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        MIDNIGHT_MORTIS_BRICKS = RegistryHelper.createBlock("midnight_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_SLAB = RegistryHelper.createBlock("midnight_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_STAIRS = RegistryHelper.createBlock("midnight_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.MIDNIGHT_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_WALL = RegistryHelper.createBlock("midnight_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);

        NECROFIRE = RegistryHelper.createBlock("necrofire", () -> new NecrofireBlock(AbstractBlock.Properties.from(Blocks.FIRE).setLightLevel((s) -> {
            return 12;
        })), null);
        NECROFIRE_CAMPFIRE = RegistryHelper.createBlock("necrofire_campfire", () -> new NecrofireCampfireBlock(Block.Properties.from(Blocks.CAMPFIRE)), ItemGroup.DECORATIONS);
        NECROFIRE_LANTERN = RegistryHelper.createBlock("necrofire_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN).setLightLevel((s) -> {
            return 15;
        })), ItemGroup.DECORATIONS);
        NECROFIRE_TORCH = RegistryHelper.createBlock("necrofire_torch", () -> new NecrofireTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH).setLightLevel((s) -> {
            return 14;
        })), null);
        NECROFIRE_WALL_TORCH = RegistryHelper.createBlock("necrofire_wall_torch", () -> new NecrofireWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH).setLightLevel((s) -> {
            return 14;
        })), null);

        STRIPPED_ASPHODEL_LOG = RegistryHelper.createBlock("stripped_asphodel_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_LOG = RegistryHelper.createBlock("asphodel_log", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_ASPHODEL_LOG.get()), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_ASPHODEL_WOOD = RegistryHelper.createBlock("stripped_asphodel_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_WOOD = RegistryHelper.createBlock("asphodel_wood", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_ASPHODEL_WOOD.get()), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_PLANKS = RegistryHelper.createBlock("asphodel_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_SLAB = RegistryHelper.createBlock("asphodel_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_STAIRS = RegistryHelper.createBlock("asphodel_stairs", () -> new StairsBlock(() -> HallowsBlocks.ASPHODEL_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);

        STRIPPED_EBONY_LOG = RegistryHelper.createBlock("stripped_ebony_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LOG = RegistryHelper.createBlock("ebony_log", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_LOG.get()), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_WOOD = RegistryHelper.createBlock("stripped_ebony_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
        EBONY_WOOD = RegistryHelper.createBlock("ebony_wood", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_WOOD.get()), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_BRANCH = RegistryHelper.createBlock("stripped_ebony_branch", () -> new BranchBlock(null), ItemGroup.DECORATIONS);
        EBONY_BRANCH = RegistryHelper.createBlock("ebony_branch", () -> new BranchBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_BRANCH.get()), ItemGroup.DECORATIONS);
        EBONY_PLANKS = RegistryHelper.createBlock("ebony_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
        EBONY_SLAB = RegistryHelper.createBlock("ebony_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        EBONY_STAIRS = RegistryHelper.createBlock("ebony_stairs", () -> new StairsBlock(() -> HallowsBlocks.EBONY_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LEAVES = RegistryHelper.createBlock("ebony_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_LEAVES = RegistryHelper.createBlock("blood_ebony_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        HANGING_EBONY_LEAVES = RegistryHelper.createBlock("hanging_ebony_leaves", () -> new HangingLeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        HANGING_BLOOD_EBONY_LEAVES = RegistryHelper.createBlock("hanging_blood_ebony_leaves", () -> new HangingLeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        EBONY_LEAF_CARPET = RegistryHelper.createBlock("ebony_leaf_carpet", () -> new LeafCarpetBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_LEAF_CARPET = RegistryHelper.createBlock("blood_ebony_leaf_carpet", () -> new LeafCarpetBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);

        PETRIFIED_SAND = RegistryHelper.createBlock("petrified_sand", () -> new SandBlock(6377538, AbstractBlock.Properties.from(Blocks.SAND)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE = RegistryHelper.createBlock("petrified_sandstone", () -> new Block(AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_SLAB = RegistryHelper.createBlock("petrified_sandstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_STAIRS = RegistryHelper.createBlock("petrified_sandstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.PETRIFIED_SANDSTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_WALL = RegistryHelper.createBlock("petrified_sandstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.DECORATIONS);

        IGNIS_CRYSTAL_FLOWER = RegistryHelper.createBlock("ignis_crystal_flower", () -> new IgnisCrystalFlowerBlock(AbstractBlock.Properties.from(Blocks.STONE).setLightLevel((s) -> {
            return 8;
        })), ItemGroup.DECORATIONS);
        IGNIS_CRYSTAL_STEM = RegistryHelper.createBlock("ignis_crystal_stem", () -> new IgnisCrystalBlock(AbstractBlock.Properties.from(Blocks.STONE).setLightLevel((s) -> {
            return 9;
        })), ItemGroup.DECORATIONS);
        IGNIS_CRYSTAL_BLOCK = RegistryHelper.createBlock("ignis_crystal_block", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE).setLightLevel((s) -> {
            return 10;
        })), ItemGroup.BUILDING_BLOCKS);

        HEMLOCK = RegistryHelper.createBlock("hemlock", () -> new HemlockBlock(AbstractBlock.Properties.from(Blocks.GRASS)), ItemGroup.DECORATIONS);

        TENEBRITE = RegistryHelper.createBlock("tenebrite", () -> new Block(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_SLAB = RegistryHelper.createBlock("tenebrite_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_STAIRS = RegistryHelper.createBlock("tenebrite_stairs", () -> new StairsBlock(() -> HallowsBlocks.TENEBRITE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_WALL = RegistryHelper.createBlock("tenebrite_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.DECORATIONS);
        GILDED_TENEBRITE = RegistryHelper.createBlock("gilded_tenebrite", () -> new Block(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        SKULLISH_TENEBRITE = RegistryHelper.createBlock("skullish_tenebrite", () -> new Block(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);

        ANOINTMENT_TABLE = RegistryHelper.createBlock("anointment_table", () -> new AnointmentTableBlock(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.DECORATIONS);
        GIANT_CAULDRON = RegistryHelper.createBlock("giant_cauldron", () -> new GiantCauldronBlock(AbstractBlock.Properties.from(Blocks.CAULDRON)), ItemGroup.MISC);

        WILL_O_WISP_VINES = RegistryHelper.createBlock("will_o_wisp_vines", () -> new WillOWispVineStemBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT)), null);
        ORANGE_WILL_O_WISP_FRUIT = RegistryHelper.createBlock("orange_will_o_wisp_fruit", () -> new WillOWispFruitBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).setLightLevel((s) -> {
            return s.get(WillOWispFruitBlock.FRUIT) ? 8 : 0;
        }).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setEmmisiveRendering(HallowsBlocks::needsPostProcessing).setNeedsPostProcessing(HallowsBlocks::needsPostProcessing), () -> HallowsItems.ORANGE_WILL_O_WISP_BERRY.get()), ItemGroup.DECORATIONS);
        YELLOW_WILL_O_WISP_FRUIT = RegistryHelper.createBlock("yellow_will_o_wisp_fruit", () -> new WillOWispFruitBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).setLightLevel((s) -> {
            return s.get(WillOWispFruitBlock.FRUIT) ? 8 : 0;
        }).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setEmmisiveRendering(HallowsBlocks::needsPostProcessing).setNeedsPostProcessing(HallowsBlocks::needsPostProcessing), () -> HallowsItems.YELLOW_WILL_O_WISP_BERRY.get()), ItemGroup.DECORATIONS);
        BLUE_WILL_O_WISP_FRUIT = RegistryHelper.createBlock("blue_will_o_wisp_fruit", () -> new WillOWispFruitBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).setLightLevel((s) -> {
            return s.get(WillOWispFruitBlock.FRUIT) ? 8 : 0;
        }).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setEmmisiveRendering(HallowsBlocks::needsPostProcessing).setNeedsPostProcessing(HallowsBlocks::needsPostProcessing), () -> HallowsItems.BLUE_WILL_O_WISP_BERRY.get()), ItemGroup.DECORATIONS);
        PURPLE_WILL_O_WISP_FRUIT = RegistryHelper.createBlock("purple_will_o_wisp_fruit", () -> new WillOWispFruitBlock(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.NETHERRACK).setLightLevel((s) -> {
            return s.get(WillOWispFruitBlock.FRUIT) ? 8 : 0;
        }).tickRandomly().doesNotBlockMovement().zeroHardnessAndResistance().sound(SoundType.PLANT).setEmmisiveRendering(HallowsBlocks::needsPostProcessing).setNeedsPostProcessing(HallowsBlocks::needsPostProcessing), () -> HallowsItems.PURPLE_WILL_O_WISP_BERRY.get()), ItemGroup.DECORATIONS);
    }

    private static boolean needsPostProcessing(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }

    public static void registerRenderLayers() {
        RenderTypeLookup.setRenderLayer(NECROFIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(EBONY_LEAVES.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BLOOD_EBONY_LEAVES.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HANGING_EBONY_LEAVES.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(HANGING_BLOOD_EBONY_LEAVES.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(EBONY_LEAF_CARPET.get(), RenderType.getCutoutMipped());
        RenderTypeLookup.setRenderLayer(BLOOD_EBONY_LEAF_CARPET.get(), RenderType.getCutoutMipped());

        RenderTypeLookup.setRenderLayer(DEADROOT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(IGNIS_CRYSTAL_FLOWER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(HEMLOCK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(NECROFIRE_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(NECROFIRE_WALL_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(NECROFIRE_CAMPFIRE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(WILL_O_WISP_VINES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ORANGE_WILL_O_WISP_FRUIT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(YELLOW_WILL_O_WISP_FRUIT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(BLUE_WILL_O_WISP_FRUIT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(PURPLE_WILL_O_WISP_FRUIT.get(), RenderType.getCutout());
    }

    public static void registerFlammability() {
        final FireBlock fire = (FireBlock) Blocks.FIRE;

        fire.setFireInfo(ASPHODEL_LOG.get(), 5, 5);
        fire.setFireInfo(STRIPPED_ASPHODEL_LOG.get(), 5, 5);
        fire.setFireInfo(ASPHODEL_WOOD.get(), 5, 5);
        fire.setFireInfo(STRIPPED_ASPHODEL_WOOD.get(), 5, 5);
        fire.setFireInfo(ASPHODEL_PLANKS.get(), 5, 20);
        fire.setFireInfo(EBONY_LOG.get(), 5, 5);
        fire.setFireInfo(STRIPPED_EBONY_LOG.get(), 5, 5);
        fire.setFireInfo(EBONY_WOOD.get(), 5, 5);
        fire.setFireInfo(STRIPPED_EBONY_WOOD.get(), 5, 5);
        fire.setFireInfo(EBONY_PLANKS.get(), 5, 20);
        fire.setFireInfo(EBONY_LEAVES.get(), 30, 60);
        fire.setFireInfo(BLOOD_EBONY_LEAVES.get(), 30, 60);
        fire.setFireInfo(HANGING_EBONY_LEAVES.get(), 30, 60);
        fire.setFireInfo(HANGING_BLOOD_EBONY_LEAVES.get(), 30, 60);
        fire.setFireInfo(EBONY_LEAF_CARPET.get(), 30, 60);
        fire.setFireInfo(BLOOD_EBONY_LEAF_CARPET.get(), 30, 60);
        fire.setFireInfo(DEADROOT.get(), 60, 100);
        fire.setFireInfo(HEMLOCK.get(), 60, 100);
    }

    public static void registerCompostables() {
        ComposterBlock.CHANCES.put(EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.CHANCES.put(BLOOD_EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.CHANCES.put(HANGING_EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.CHANCES.put(HANGING_BLOOD_EBONY_LEAVES.get(), 0.3F);
        ComposterBlock.CHANCES.put(EBONY_LEAF_CARPET.get(), 0.3F);
        ComposterBlock.CHANCES.put(BLOOD_EBONY_LEAF_CARPET.get(), 0.3F);
        ComposterBlock.CHANCES.put(DEADROOT.get(), 0.1F);
        ComposterBlock.CHANCES.put(HEMLOCK.get(), 0.25F);
        ComposterBlock.CHANCES.put(ORANGE_WILL_O_WISP_FRUIT.get(), 0.6F);
        ComposterBlock.CHANCES.put(YELLOW_WILL_O_WISP_FRUIT.get(), 0.6F);
        ComposterBlock.CHANCES.put(BLUE_WILL_O_WISP_FRUIT.get(), 0.6F);
        ComposterBlock.CHANCES.put(PURPLE_WILL_O_WISP_FRUIT.get(), 0.6F);
    }
}