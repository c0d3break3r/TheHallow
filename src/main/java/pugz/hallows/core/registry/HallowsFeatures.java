package pugz.hallows.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.template.BlockMatchRuleTest;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import net.minecraft.world.gen.foliageplacer.AcaciaFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.ForkyTrunkPlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.world.decorator.BranchTreeDecorator;
import pugz.hallows.common.world.decorator.HangingLeavesTreeDecorator;
import pugz.hallows.common.world.decorator.JackOLanternTreeDecorator;
import pugz.hallows.common.world.feature.*;
import pugz.hallows.common.world.feature.config.CaveBiomeFeatureConfig;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.registry.other.HallowsTags;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Hallows.MOD_ID);
    public static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATORS = DeferredRegister.create(ForgeRegistries.TREE_DECORATOR_TYPES, Hallows.MOD_ID);

    public static final BlockClusterFeatureConfig POPPY_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.POPPY.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(32).build();

    public static RegistryObject<Feature<NoFeatureConfig>> VERTICAL_PILLAR;
    public static RegistryObject<Feature<OreFeatureConfig>> SPREAD_ORE;
    public static RegistryObject<Feature<CaveBiomeFeatureConfig>> CAVE_BIOME;
    public static RegistryObject<Feature<NoFeatureConfig>> SPREAD_PLANT;
    public static RegistryObject<Feature<NoFeatureConfig>> WILL_O_WISP_VINES;

    public static class Configured {
        public static ConfiguredFeature<?, ?> PATCH_NECROFIRE;

        public static ConfiguredFeature<?, ?> OPAL_ORE;
        public static ConfiguredFeature<?, ?> HALLSTONE_EMERALD_ORE;
        public static ConfiguredFeature<?, ?> ORE_STYGIAN_RUIN;
        public static ConfiguredFeature<?, ?> ORE_BLACKSTONE;
        public static ConfiguredFeature<?, ?> ORE_TENEBRITE;
        public static ConfiguredFeature<?, ?> ORE_GILDED_TENEBRITE;

        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> ASPHODEL;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> BIG_ASPHODEL;
        public static ConfiguredFeature<?, ?> TREES_ASPHODEL;

        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> EBONY;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> BLOOD_EBONY;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> EBONY_HANGING_LEAVES;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> BLOOD_EBONY_HANGING_LEAVES;
        public static ConfiguredFeature<?, ?> TREES_EBONY;
        public static ConfiguredFeature<?, ?> TREES_BLOOD_EBONY;

        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> SMALL_EBONY;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> SMALL_BLOOD_EBONY;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> SMALL_EBONY_HANGING_LEAVES;
        public static ConfiguredFeature<BaseTreeFeatureConfig, ?> SMALL_BLOOD_EBONY_HANGING_LEAVES;
        public static ConfiguredFeature<?, ?> TREES_SMALL_EBONY;
        public static ConfiguredFeature<?, ?> TREES_SMALL_BLOOD_EBONY;

        public static ConfiguredFeature<?, ?> FLOWER_POPPIES;

        public static ConfiguredFeature<?, ?> PATCH_DEADROOT;
        public static ConfiguredFeature<?, ?> PATCH_DEADROOT_DENSE;
        public static ConfiguredFeature<?, ?> PATCH_DEADROOT_WATER;
        public static ConfiguredFeature<?, ?> PATCH_HEMLOCK;
        public static ConfiguredFeature<?, ?> PATCH_PUMPKIN_COMMON;

        public static ConfiguredFeature<?, ?> VERTICAL_PILLAR;
        public static ConfiguredFeature<?, ?> WATER_DELTA;

        public static ConfiguredFeature<?, ?> PETRIFIED_SANDSTONE_ROCK;
        public static ConfiguredFeature<?, ?> DISK_PETRIFIED_SAND;

        public static ConfiguredFeature<?, ?> IGNIS_CAVE_BIOME;

        public static ConfiguredFeature<?, ?> WILL_O_WISP_VINES;
    }

    public static class Decorators {
        public static RegistryObject<TreeDecoratorType<HangingLeavesTreeDecorator>> HANGING_LEAVES;
        public static RegistryObject<TreeDecoratorType<BranchTreeDecorator>> BRANCH;
        public static RegistryObject<TreeDecoratorType<JackOLanternTreeDecorator>> JACK_O_LANTERN;
    }

    public static class Placements {
        public static HangingLeavesTreeDecorator HANGING_LEAVES_EBONY_PLACEMENT = new HangingLeavesTreeDecorator(HallowsBlocks.HANGING_EBONY_LEAVES.get().defaultBlockState(), 1.0F);
        public static HangingLeavesTreeDecorator HANGING_LEAVES_BLOOD_EBONY_PLACEMENT = new HangingLeavesTreeDecorator(HallowsBlocks.HANGING_BLOOD_EBONY_LEAVES.get().defaultBlockState(), 1.0F);
        public static BranchTreeDecorator BRANCH_EBONY_PLACEMENT = new BranchTreeDecorator(HallowsBlocks.EBONY_POST.get().defaultBlockState(), 0.8F, 2, 0.4F);
        public static JackOLanternTreeDecorator JACK_O_LANTERN_PLACEMENT = new JackOLanternTreeDecorator(0.2F);

        public static class Configured {
            public static ConfiguredPlacement<?> CAVE_BIOME_PLACEMENT = new ConfiguredPlacement<>(Placement.CARVING_MASK, new CaveEdgeConfig(GenerationStage.Carving.AIR, 0.4F)).chance(18);
        }
    }

    public static void registerFeatures() {
        VERTICAL_PILLAR = RegistryHelper.createFeature("vertical_pillar", VerticalPillarFeature::new);
        SPREAD_ORE = RegistryHelper.createFeature("spread_ore", SpreadOreFeature::new);
        CAVE_BIOME = RegistryHelper.createFeature("cave_biome", IgnisCaveBiomeFeature::new);
        //SPREAD_PLANT = RegistryHelper.createFeature("spread_plant", SpreadPlantFeature::new);
        WILL_O_WISP_VINES = RegistryHelper.createFeature("will_o_wisp_vines", WillOWispVinesFeature::new);
    }

    public static void registerDecorators() {
        Decorators.HANGING_LEAVES = RegistryHelper.createTreeDecorator("hanging_leaves", () -> new TreeDecoratorType<>(HangingLeavesTreeDecorator.CODEC));
        Decorators.BRANCH = RegistryHelper.createTreeDecorator("branch", () -> new TreeDecoratorType<>(BranchTreeDecorator.CODEC));
        Decorators.JACK_O_LANTERN = RegistryHelper.createTreeDecorator("jack_o_lantern", () -> new TreeDecoratorType<>(JackOLanternTreeDecorator.CODEC));
    }

    public static void registerConfiguredFeatures() {
        Configured.PATCH_NECROFIRE = RegistryHelper.createConfiguredFeature("patch_necrofire", Feature.RANDOM_PATCH.configured(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.NECROFIRE.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)
                        .tries(64).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLSTONE.get(), HallowsBlocks.HALLOWED_DIRT.get()))
                        .noProjection().build()
        ).decorated(Placement.FIRE.configured(new FeatureSpreadConfig(8))));

        Configured.OPAL_ORE = RegistryHelper.createConfiguredFeature("opal_ore", Feature.ORE.configured(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.OPAL_ORE.get().defaultBlockState(), 7)
        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(8, 16, 128)))
                .squared()
                .range(1)
                .count(FeatureSpread.fixed(3)));

        Configured.HALLSTONE_EMERALD_ORE = RegistryHelper.createConfiguredFeature("hallstone_emerald_ore", Feature.ORE.configured(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.HALLSTONE_EMERALD_ORE.get().defaultBlockState(), 7)
        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(8, 16, 128)))
                .squared()
                .range(1)
                .count(FeatureSpread.fixed(3)));

        Configured.ASPHODEL = RegistryHelper.createConfiguredFeature("asphodel", Feature.TREE.configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.ASPHODEL_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()),
                        new AcaciaFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0)),
                        new ForkyTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(4, 3, 3))
                        .build())));

        Configured.BIG_ASPHODEL = RegistryHelper.createConfiguredFeature("big_asphodel", Feature.TREE.configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.ASPHODEL_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(Blocks.AIR.defaultBlockState()),
                        new AcaciaFoliagePlacer(FeatureSpread.fixed(0), FeatureSpread.fixed(0)),
                        new FancyTrunkPlacer(11, 11, 5),
                        new TwoLayerFeature(4, 3, 3))
                        .build())));

        Configured.TREES_ASPHODEL = RegistryHelper.createConfiguredFeature("trees_asphodel", Feature.RANDOM_SELECTOR.configured(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.BIG_ASPHODEL.weighted(0.1F)), Configured.ASPHODEL))
                .decorated(Features.Placements.HEIGHTMAP).chance(12)
                .decorated(Placement.COUNT_MULTILAYER.configured(
                        new FeatureSpreadConfig(1))));

        Configured.EBONY = RegistryHelper.createConfiguredFeature("ebony", Feature.TREE.configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LEAVES.get().defaultBlockState()),
                        new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1), 1),
                        new FancyTrunkPlacer(11, 11, 5),
                        new TwoLayerFeature(4, 3, 3))
                        .heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
                        .build())));

        Configured.BLOOD_EBONY = RegistryHelper.createConfiguredFeature("blood_ebony", Feature.TREE.configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(HallowsBlocks.BLOOD_EBONY_LEAVES.get().defaultBlockState()),
                        new BlobFoliagePlacer(FeatureSpread.fixed(2), FeatureSpread.fixed(1), 1),
                        new FancyTrunkPlacer(11, 11, 5),
                        new TwoLayerFeature(4, 3, 3))
                        .heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
                        .build())));

        Configured.EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("ebony_hanging_leaves", Feature.TREE.configured(
                Configured.EBONY.config().withDecorators(
                        ImmutableList.of(Placements.HANGING_LEAVES_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.BLOOD_EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("blood_ebony_hanging_leaves", Feature.TREE.configured(
                Configured.BLOOD_EBONY.config().withDecorators(
                        ImmutableList.of(Placements.HANGING_LEAVES_BLOOD_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.TREES_EBONY = RegistryHelper.createConfiguredFeature("trees_ebony", Feature.RANDOM_SELECTOR.configured(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.EBONY_HANGING_LEAVES.weighted(1.0F)), Configured.EBONY_HANGING_LEAVES))
                .decorated(Features.Placements.HEIGHTMAP)
                .decorated(Placement.COUNT_MULTILAYER.configured(
                        new FeatureSpreadConfig(3))));

        Configured.TREES_BLOOD_EBONY = RegistryHelper.createConfiguredFeature("trees_blood_ebony", Feature.RANDOM_SELECTOR.configured(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.BLOOD_EBONY_HANGING_LEAVES.weighted(0.5F)), Configured.BLOOD_EBONY_HANGING_LEAVES))
                .decorated(Features.Placements.HEIGHTMAP)
                .decorated(Placement.COUNT_MULTILAYER.configured(
                        new FeatureSpreadConfig(2))));

        Configured.SMALL_EBONY = RegistryHelper.createConfiguredFeature("ebony", Feature.TREE.configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LEAVES.get().defaultBlockState()),
                        new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(2), 2),
                        new StraightTrunkPlacer(6, 4, 2),
                        new TwoLayerFeature(2, 1, 1))
                        .heightmap(Heightmap.Type.MOTION_BLOCKING)
                        .build())));

        Configured.SMALL_BLOOD_EBONY = RegistryHelper.createConfiguredFeature("blood_ebony", Feature.TREE.configured(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().defaultBlockState()),
                        new SimpleBlockStateProvider(HallowsBlocks.BLOOD_EBONY_LEAVES.get().defaultBlockState()),
                        new BlobFoliagePlacer(FeatureSpread.fixed(3), FeatureSpread.fixed(2), 2),
                        new StraightTrunkPlacer(6, 4, 2),
                        new TwoLayerFeature(2, 1, 1))
                        .heightmap(Heightmap.Type.MOTION_BLOCKING)
                        .build())));

        Configured.SMALL_EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("ebony_hanging_leaves", Feature.TREE.configured(
                Configured.SMALL_EBONY.config().withDecorators(
                        ImmutableList.of(Placements.HANGING_LEAVES_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.SMALL_BLOOD_EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("blood_ebony_hanging_leaves", Feature.TREE.configured(
                Configured.SMALL_BLOOD_EBONY.config().withDecorators(
                        ImmutableList.of(Placements.HANGING_LEAVES_BLOOD_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.TREES_SMALL_EBONY = RegistryHelper.createConfiguredFeature("trees_ebony", Feature.RANDOM_SELECTOR.configured(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.SMALL_EBONY_HANGING_LEAVES.weighted(1.0F)), Configured.SMALL_EBONY_HANGING_LEAVES))
                .decorated(Features.Placements.HEIGHTMAP)
                .decorated(Placement.COUNT_MULTILAYER.configured(
                        new FeatureSpreadConfig(2))));

        Configured.TREES_SMALL_BLOOD_EBONY = RegistryHelper.createConfiguredFeature("trees_blood_ebony", Feature.RANDOM_SELECTOR.configured(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.SMALL_BLOOD_EBONY_HANGING_LEAVES.weighted(0.5F)), Configured.SMALL_BLOOD_EBONY_HANGING_LEAVES))
                .decorated(Features.Placements.HEIGHTMAP)
                .decorated(Placement.COUNT_MULTILAYER.configured(
                        new FeatureSpreadConfig(1))));

        Configured.FLOWER_POPPIES = RegistryHelper.createConfiguredFeature("flower_poppies", Feature.FLOWER
                .configured(POPPY_FLOWER_CONFIG)
                .decorated(Features.Placements.ADD_32)
                .decorated(Features.Placements.HEIGHTMAP)
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))
                .range(1)));

        Configured.PATCH_DEADROOT = RegistryHelper.createConfiguredFeature("patch_deadroot", Feature.RANDOM_PATCH.configured(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.DEADROOT.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)
                        .tries(64).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLOWED_DIRT.get(), Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.DIRT))
                        .noProjection().build()
        ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.range(8)));

        Configured.PATCH_DEADROOT_DENSE = RegistryHelper.createConfiguredFeature("patch_deadroot_dense", Feature.RANDOM_PATCH.configured(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.DEADROOT.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)
                        .tries(48).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLOWED_DIRT.get(), Blocks.COARSE_DIRT))
                        .noProjection().build()
        ).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.range(12)));

        Configured.PATCH_DEADROOT_WATER = RegistryHelper.createConfiguredFeature("patch_deadroot_water", Feature.RANDOM_PATCH.configured(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.DEADROOT.get().defaultBlockState()), SimpleBlockPlacer.INSTANCE)
                        .tries(64).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLOWED_DIRT.get(), Blocks.COARSE_DIRT))
                        .noProjection().build()
        ).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE.range(16))
                .decorated(Placement.COUNT_NOISE.configured(new NoiseDependant(-0.8D, 5, 10))));

        Configured.VERTICAL_PILLAR = RegistryHelper.createConfiguredFeature("vertical_pillar", VERTICAL_PILLAR.get().configured(
                IFeatureConfig.NONE)
                .range(128)
                .squared()
                .range(10));

        Configured.WATER_DELTA = RegistryHelper.createConfiguredFeature("water_delta", Feature.DELTA_FEATURE.configured(
                new BasaltDeltasFeature(
                        Blocks.WATER.defaultBlockState(), HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState(),
                        FeatureSpread.of(3, 4),
                        FeatureSpread.of(0, 2)))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(40))));

        Configured.ORE_STYGIAN_RUIN = RegistryHelper.createConfiguredFeature("ore_stygian_ruin", SPREAD_ORE.get().configured(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.STYGIAN_RUIN.get().defaultBlockState(), 5)
        )).decorated(Placement.DEPTH_AVERAGE.configured(new DepthAverageConfig(16, 16))).squared();

        Configured.ORE_BLACKSTONE = RegistryHelper.createConfiguredFeature("ore_blackstone", Feature.ORE.configured(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), Blocks.BLACKSTONE.defaultBlockState(), 33)
        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(5, 10, 37))).squared().range(2));

        Configured.ORE_TENEBRITE = RegistryHelper.createConfiguredFeature("ore_tenebrite", Feature.ORE.configured(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.TENEBRITE.get().defaultBlockState(), 64)
        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 10, 37))).range(4));

        Configured.ORE_GILDED_TENEBRITE = RegistryHelper.createConfiguredFeature("ore_gilded_tenebrite", Feature.ORE.configured(
                new OreFeatureConfig(new BlockMatchRuleTest(HallowsBlocks.TENEBRITE.get()), HallowsBlocks.GILDED_TENEBRITE.get().defaultBlockState(), 8)
        ).decorated(Placement.RANGE.configured(new TopSolidRangeConfig(0, 10, 37))).range(4));

        Configured.PETRIFIED_SANDSTONE_ROCK = RegistryHelper.createConfiguredFeature("petrified_sandstone_rock", Feature.FOREST_ROCK.configured(
                new BlockStateFeatureConfig(HallowsBlocks.PETRIFIED_SANDSTONE.get().defaultBlockState()))
                .decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(10)))
                .decorated(Placement.HEIGHTMAP_WORLD_SURFACE.configured(IPlacementConfig.NONE))
                .decorated(Placement.RANGE.configured(new TopSolidRangeConfig(63, 0, 256)))
                .range(4));

        Configured.DISK_PETRIFIED_SAND = RegistryHelper.createConfiguredFeature("petrified_sandstone_rock", Feature.DISK.configured(
                new SphereReplaceConfig(HallowsBlocks.PETRIFIED_SAND.get().defaultBlockState(),
                        FeatureSpread.of(4, 1), 1,
                        ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState())))
                .decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE));

        //Configured.PATCH_HEMLOCK = RegistryHelper.createConfiguredFeature("patch_hemlock", SPREAD_PLANT.get().withConfiguration(
        //        IFeatureConfig.NO_FEATURE_CONFIG
        //));

        Configured.PATCH_PUMPKIN_COMMON = RegistryHelper.createConfiguredFeature("patch_pumpkin_common", Feature.RANDOM_PATCH.configured(
                new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.PUMPKIN.defaultBlockState()), SimpleBlockPlacer.INSTANCE)
                        .tries(64)
                        .whitelist(ImmutableSet.of(Blocks.DIRT, Blocks.GRASS_BLOCK, HallowsBlocks.HALLOWED_DIRT.get())).noProjection().build()).decorated(
                                Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(6));

        Configured.IGNIS_CAVE_BIOME = RegistryHelper.createConfiguredFeature("ignis_cave_biome", CAVE_BIOME.get().configured(
                new CaveBiomeFeatureConfig(
                        HallowsBlocks.TENEBRITE.get().defaultBlockState(), HallowsBlocks.TENEBRITE.get().defaultBlockState(), HallowsBlocks.TENEBRITE.get().defaultBlockState(), HallowsBlocks.TENEBRITE.get().defaultBlockState(),
                        128, 0.0005F, new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), false)
        ).decorated(Placements.Configured.CAVE_BIOME_PLACEMENT));

        Configured.WILL_O_WISP_VINES = RegistryHelper.createConfiguredFeature("will_o_wisp_vines", WILL_O_WISP_VINES.get().configured(IFeatureConfig.NONE)
                .range(128)
                .squared()
                .range(10));
    }
}