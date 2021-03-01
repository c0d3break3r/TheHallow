package pugz.hallows.core.registry;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.block.*;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryUtil;

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

    public static RegistryObject<Block> SPECTER_KELP;
    public static RegistryObject<Block> SPECTER_KELP_PLANT;

    public static void registerBlocks() {
        HALLSTONE = RegistryUtil.createBlock("hallstone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_SLAB = RegistryUtil.createBlock("hallstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_STAIRS = RegistryUtil.createBlock("hallstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.HALLSTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_WALL = RegistryUtil.createBlock("hallstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        POLISHED_HALLSTONE = RegistryUtil.createBlock("polished_hallstone", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE_SLAB = RegistryUtil.createBlock("polished_hallstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE_STAIRS = RegistryUtil.createBlock("polished_hallstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.POLISHED_HALLSTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        POLISHED_HALLSTONE_WALL = RegistryUtil.createBlock("polished_hallstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        HALLOWED_DIRT = RegistryUtil.createBlock("hallowed_dirt", () -> new Block(AbstractBlock.Properties.from(Blocks.DIRT)), ItemGroup.BUILDING_BLOCKS);
        DEADROOT = RegistryUtil.createBlock("deadroot", () -> new DeadrootBlock(AbstractBlock.Properties.from(Blocks.GRASS)), ItemGroup.DECORATIONS);

        OPAL_ORE = RegistryUtil.createBlock("opal_ore", () -> new HallowsOreBlock(AbstractBlock.Properties.from(Blocks.STONE).setRequiresTool().harvestLevel(2), 3, 7), ItemGroup.BUILDING_BLOCKS);
        HALLSTONE_EMERALD_ORE = RegistryUtil.createBlock("hallstone_emerald_ore", () -> new HallowsOreBlock(AbstractBlock.Properties.from(Blocks.STONE).setRequiresTool().harvestLevel(2), 3, 7), ItemGroup.BUILDING_BLOCKS);
        STYGIAN_RUIN = RegistryUtil.createBlock("stygian_ruin", () -> new Block(AbstractBlock.Properties.from(Blocks.ANCIENT_DEBRIS).setRequiresTool().harvestLevel(3)), ItemGroup.BUILDING_BLOCKS);

        DAWN_MORTIS = RegistryUtil.createBlock("dawn_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_SLAB = RegistryUtil.createBlock("dawn_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_STAIRS = RegistryUtil.createBlock("dawn_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.DAWN_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_WALL = RegistryUtil.createBlock("dawn_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        DAWN_MORTIS_BRICKS = RegistryUtil.createBlock("dawn_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_SLAB = RegistryUtil.createBlock("dawn_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_STAIRS = RegistryUtil.createBlock("dawn_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.DAWN_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DAWN_MORTIS_BRICK_WALL = RegistryUtil.createBlock("dawn_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        NOON_MORTIS = RegistryUtil.createBlock("noon_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_SLAB = RegistryUtil.createBlock("noon_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_STAIRS = RegistryUtil.createBlock("noon_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.NOON_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_WALL = RegistryUtil.createBlock("noon_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        NOON_MORTIS_BRICKS = RegistryUtil.createBlock("noon_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_SLAB = RegistryUtil.createBlock("noon_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_STAIRS = RegistryUtil.createBlock("noon_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.NOON_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        NOON_MORTIS_BRICK_WALL = RegistryUtil.createBlock("noon_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        DUSK_MORTIS = RegistryUtil.createBlock("dusk_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_SLAB = RegistryUtil.createBlock("dusk_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_STAIRS = RegistryUtil.createBlock("dusk_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.DUSK_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_WALL = RegistryUtil.createBlock("dusk_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        DUSK_MORTIS_BRICKS = RegistryUtil.createBlock("dusk_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_SLAB = RegistryUtil.createBlock("dusk_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_STAIRS = RegistryUtil.createBlock("dusk_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.DUSK_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        DUSK_MORTIS_BRICK_WALL = RegistryUtil.createBlock("dusk_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        MIDNIGHT_MORTIS = RegistryUtil.createBlock("midnight_mortis", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_SLAB = RegistryUtil.createBlock("midnight_mortis_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_STAIRS = RegistryUtil.createBlock("midnight_mortis_stairs", () -> new StairsBlock(() -> HallowsBlocks.MIDNIGHT_MORTIS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_WALL = RegistryUtil.createBlock("midnight_mortis_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);
        MIDNIGHT_MORTIS_BRICKS = RegistryUtil.createBlock("midnight_mortis_bricks", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE_BRICKS)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_SLAB = RegistryUtil.createBlock("midnight_mortis_brick_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_STAIRS = RegistryUtil.createBlock("midnight_mortis_brick_stairs", () -> new StairsBlock(() -> HallowsBlocks.MIDNIGHT_MORTIS_BRICKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        MIDNIGHT_MORTIS_BRICK_WALL = RegistryUtil.createBlock("midnight_mortis_brick_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.DECORATIONS);

        NECROFIRE = RegistryUtil.createBlock("necrofire", () -> new NecrofireBlock(AbstractBlock.Properties.from(Blocks.FIRE).setLightLevel((s) -> {
            return 12;
        })), null);
        NECROFIRE_CAMPFIRE = RegistryUtil.createBlock("necrofire_campfire", () -> new NecrofireCampfireBlock(Block.Properties.from(Blocks.CAMPFIRE)), ItemGroup.DECORATIONS);
        NECROFIRE_LANTERN = RegistryUtil.createBlock("necrofire_lantern", () -> new LanternBlock(AbstractBlock.Properties.from(Blocks.LANTERN).setLightLevel((s) -> {
            return 15;
        })), ItemGroup.DECORATIONS);
        NECROFIRE_TORCH = RegistryUtil.createBlock("necrofire_torch", () -> new NecrofireTorchBlock(AbstractBlock.Properties.from(Blocks.TORCH).setLightLevel((s) -> {
            return 14;
        })), null);
        NECROFIRE_WALL_TORCH = RegistryUtil.createBlock("necrofire_wall_torch", () -> new NecrofireWallTorchBlock(AbstractBlock.Properties.from(Blocks.WALL_TORCH).setLightLevel((s) -> {
            return 14;
        })), null);

        STRIPPED_ASPHODEL_LOG = RegistryUtil.createBlock("stripped_asphodel_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_LOG = RegistryUtil.createBlock("asphodel_log", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_ASPHODEL_LOG.get()), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_ASPHODEL_WOOD = RegistryUtil.createBlock("stripped_asphodel_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_WOOD = RegistryUtil.createBlock("asphodel_wood", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_ASPHODEL_WOOD.get()), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_PLANKS = RegistryUtil.createBlock("asphodel_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_SLAB = RegistryUtil.createBlock("asphodel_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        ASPHODEL_STAIRS = RegistryUtil.createBlock("asphodel_stairs", () -> new StairsBlock(() -> HallowsBlocks.ASPHODEL_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);

        STRIPPED_EBONY_LOG = RegistryUtil.createBlock("stripped_ebony_log", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LOG = RegistryUtil.createBlock("ebony_log", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_LOG.get()), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_WOOD = RegistryUtil.createBlock("stripped_ebony_wood", () -> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)), ItemGroup.BUILDING_BLOCKS);
        EBONY_WOOD = RegistryUtil.createBlock("ebony_wood", () -> new HallowsLogBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_WOOD.get()), ItemGroup.BUILDING_BLOCKS);
        STRIPPED_EBONY_BRANCH = RegistryUtil.createBlock("stripped_ebony_branch", () -> new BranchBlock(null), ItemGroup.DECORATIONS);
        EBONY_BRANCH = RegistryUtil.createBlock("ebony_branch", () -> new BranchBlock((RotatedPillarBlock) HallowsBlocks.STRIPPED_EBONY_BRANCH.get()), ItemGroup.DECORATIONS);
        EBONY_PLANKS = RegistryUtil.createBlock("ebony_planks", () -> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)), ItemGroup.BUILDING_BLOCKS);
        EBONY_SLAB = RegistryUtil.createBlock("ebony_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        EBONY_STAIRS = RegistryUtil.createBlock("ebony_stairs", () -> new StairsBlock(() -> HallowsBlocks.EBONY_PLANKS.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.STONE)), ItemGroup.BUILDING_BLOCKS);
        EBONY_LEAVES = RegistryUtil.createBlock("ebony_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_LEAVES = RegistryUtil.createBlock("blood_ebony_leaves", () -> new LeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        HANGING_EBONY_LEAVES = RegistryUtil.createBlock("hanging_ebony_leaves", () -> new HangingLeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        HANGING_BLOOD_EBONY_LEAVES = RegistryUtil.createBlock("hanging_blood_ebony_leaves", () -> new HangingLeavesBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        EBONY_LEAF_CARPET = RegistryUtil.createBlock("ebony_leaf_carpet", () -> new LeafCarpetBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);
        BLOOD_EBONY_LEAF_CARPET = RegistryUtil.createBlock("blood_ebony_leaf_carpet", () -> new LeafCarpetBlock(AbstractBlock.Properties.from(Blocks.OAK_LEAVES)), ItemGroup.DECORATIONS);

        PETRIFIED_SAND = RegistryUtil.createBlock("petrified_sand", () -> new SandBlock(6377538, AbstractBlock.Properties.from(Blocks.SAND)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE = RegistryUtil.createBlock("petrified_sandstone", () -> new Block(AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_SLAB = RegistryUtil.createBlock("petrified_sandstone_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_STAIRS = RegistryUtil.createBlock("petrified_sandstone_stairs", () -> new StairsBlock(() -> HallowsBlocks.PETRIFIED_SANDSTONE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.BUILDING_BLOCKS);
        PETRIFIED_SANDSTONE_WALL = RegistryUtil.createBlock("petrified_sandstone_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.SANDSTONE)), ItemGroup.DECORATIONS);

        IGNIS_CRYSTAL_FLOWER = RegistryUtil.createBlock("ignis_crystal_flower", () -> new IgnisCrystalFlowerBlock(AbstractBlock.Properties.from(Blocks.STONE).setLightLevel((s) -> {
            return 8;
        })), ItemGroup.DECORATIONS);
        IGNIS_CRYSTAL_STEM = RegistryUtil.createBlock("ignis_crystal_stem", () -> new IgnisCrystalBlock(AbstractBlock.Properties.from(Blocks.STONE).setLightLevel((s) -> {
            return 9;
        })), ItemGroup.DECORATIONS);
        IGNIS_CRYSTAL_BLOCK = RegistryUtil.createBlock("ignis_crystal_block", () -> new Block(AbstractBlock.Properties.from(Blocks.STONE).setLightLevel((s) -> {
            return 10;
        })), ItemGroup.BUILDING_BLOCKS);

        HEMLOCK = RegistryUtil.createBlock("hemlock", () -> new HemlockBlock(AbstractBlock.Properties.from(Blocks.GRASS)), ItemGroup.DECORATIONS);

        TENEBRITE = RegistryUtil.createBlock("tenebrite", () -> new Block(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_SLAB = RegistryUtil.createBlock("tenebrite_slab", () -> new SlabBlock(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_STAIRS = RegistryUtil.createBlock("tenebrite_stairs", () -> new StairsBlock(() -> HallowsBlocks.TENEBRITE.get().getDefaultState(), AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        TENEBRITE_WALL = RegistryUtil.createBlock("tenebrite_wall", () -> new WallBlock(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.DECORATIONS);
        GILDED_TENEBRITE = RegistryUtil.createBlock("gilded_tenebrite", () -> new Block(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);
        SKULLISH_TENEBRITE = RegistryUtil.createBlock("skullish_tenebrite", () -> new Block(AbstractBlock.Properties.from(Blocks.END_STONE)), ItemGroup.BUILDING_BLOCKS);

        SPECTER_KELP = RegistryUtil.createBlock("specter_kelp", () -> new KelpTopBlock(AbstractBlock.Properties.from(Blocks.KELP_PLANT)), ItemGroup.DECORATIONS);
        SPECTER_KELP_PLANT = RegistryUtil.createBlock("specter_kelp_plant", () -> new KelpBlock(AbstractBlock.Properties.from(Blocks.KELP_PLANT)), null);
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
        RenderTypeLookup.setRenderLayer(SPECTER_KELP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(SPECTER_KELP_PLANT.get(), RenderType.getCutout());
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
        ComposterBlock.CHANCES.put(SPECTER_KELP.get(), 0.3F);
    }
}