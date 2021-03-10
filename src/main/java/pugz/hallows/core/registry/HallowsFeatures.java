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

    public static final BlockClusterFeatureConfig POPPY_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).addWeightedBlockstate(Blocks.POPPY.getDefaultState(), 1), SimpleBlockPlacer.PLACER)).tries(32).build();

    public static RegistryObject<Feature<NoFeatureConfig>> VERTICAL_PILLAR;
    public static RegistryObject<Feature<OreFeatureConfig>> SPREAD_ORE;
    public static RegistryObject<Feature<CaveBiomeFeatureConfig>> CAVE_BIOME;
    public static RegistryObject<Feature<NoFeatureConfig>> SPREAD_PLANT;

    public static class Configured {
        public static ConfiguredFeature<?, ?> PATCH_NECROFIRE;
        public static ConfiguredFeature<?, ?> OPAL_ORE;
        public static ConfiguredFeature<?, ?> HALLSTONE_EMERALD_ORE;
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
        public static ConfiguredFeature<?, ?> VERTICAL_PILLAR;
        public static ConfiguredFeature<?, ?> WATER_DELTA;
        public static ConfiguredFeature<?, ?> ORE_STYGIAN_RUIN;
        public static ConfiguredFeature<?, ?> ORE_BLACKSTONE;
        public static ConfiguredFeature<?, ?> ORE_TENEBRITE;
        public static ConfiguredFeature<?, ?> ORE_GILDED_TENEBRITE;
        public static ConfiguredFeature<?, ?> PETRIFIED_SANDSTONE_ROCK;
        public static ConfiguredFeature<?, ?> DISK_PETRIFIED_SAND;
        public static ConfiguredFeature<?, ?> PATCH_HEMLOCK;
        public static ConfiguredFeature<?, ?> PATCH_PUMPKIN_COMMON;
        public static ConfiguredFeature<?, ?> IGNIS_CAVE_BIOME;
    }

    public static class Decorators {
        public static RegistryObject<TreeDecoratorType<HangingLeavesTreeDecorator>> HANGING_LEAVES;
        public static RegistryObject<TreeDecoratorType<BranchTreeDecorator>> BRANCH;
        public static RegistryObject<TreeDecoratorType<JackOLanternTreeDecorator>> JACK_O_LANTERN;
    }

    public static class Placements {
        public static HangingLeavesTreeDecorator HANGING_LEAVES_EBONY_PLACEMENT = new HangingLeavesTreeDecorator(HallowsBlocks.HANGING_EBONY_LEAVES.get().getDefaultState(), 0.8F);
        public static HangingLeavesTreeDecorator HANGING_LEAVES_BLOOD_EBONY_PLACEMENT = new HangingLeavesTreeDecorator(HallowsBlocks.HANGING_BLOOD_EBONY_LEAVES.get().getDefaultState(), 0.8F);
        public static BranchTreeDecorator BRANCH_EBONY_PLACEMENT = new BranchTreeDecorator(HallowsBlocks.EBONY_POST.get().getDefaultState(), 0.8F, 2, 0.4F);
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
    }

    public static void registerDecorators() {
        Decorators.HANGING_LEAVES = RegistryHelper.createTreeDecorator("hanging_leaves", () -> new TreeDecoratorType<>(HangingLeavesTreeDecorator.CODEC));
        Decorators.BRANCH = RegistryHelper.createTreeDecorator("branch", () -> new TreeDecoratorType<>(BranchTreeDecorator.CODEC));
        Decorators.JACK_O_LANTERN = RegistryHelper.createTreeDecorator("jack_o_lantern", () -> new TreeDecoratorType<>(JackOLanternTreeDecorator.CODEC));
    }

    public static void registerConfiguredFeatures() {
        Configured.PATCH_NECROFIRE = RegistryHelper.createConfiguredFeature("patch_necrofire", Feature.RANDOM_PATCH.withConfiguration(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.NECROFIRE.get().getDefaultState()), SimpleBlockPlacer.PLACER)
                        .tries(64).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLSTONE.get(), HallowsBlocks.HALLOWED_DIRT.get()))
                        .func_227317_b_().build()
        ).withPlacement(Placement.FIRE.configure(new FeatureSpreadConfig(8))));

        Configured.OPAL_ORE = RegistryHelper.createConfiguredFeature("opal_ore", Feature.ORE.withConfiguration(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.OPAL_ORE.get().getDefaultState(), 7)
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(8, 16, 128)))
                .square()
                .func_242731_b(1)
                .func_242730_a(FeatureSpread.func_242252_a(3)));

        Configured.HALLSTONE_EMERALD_ORE = RegistryHelper.createConfiguredFeature("hallstone_emerald_ore", Feature.ORE.withConfiguration(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.HALLSTONE_EMERALD_ORE.get().getDefaultState(), 7)
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(8, 16, 128)))
                .square()
                .func_242731_b(1)
                .func_242730_a(FeatureSpread.func_242252_a(3)));

        Configured.ASPHODEL = RegistryHelper.createConfiguredFeature("asphodel", Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.ASPHODEL_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()),
                        new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0)),
                        new ForkyTrunkPlacer(5, 2, 2),
                        new TwoLayerFeature(4, 3, 3))
                        .build())));

        Configured.BIG_ASPHODEL = RegistryHelper.createConfiguredFeature("big_asphodel", Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.ASPHODEL_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()),
                        new AcaciaFoliagePlacer(FeatureSpread.func_242252_a(0), FeatureSpread.func_242252_a(0)),
                        new FancyTrunkPlacer(11, 11, 5),
                        new TwoLayerFeature(4, 3, 3))
                        .build())));

        Configured.TREES_ASPHODEL = RegistryHelper.createConfiguredFeature("trees_asphodel", Feature.RANDOM_SELECTOR.withConfiguration(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.BIG_ASPHODEL.withChance(0.1F)), Configured.ASPHODEL))
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT).chance(12)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(
                        new FeatureSpreadConfig(1))));

        Configured.EBONY = RegistryHelper.createConfiguredFeature("ebony", Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LEAVES.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(1), 1),
                        new FancyTrunkPlacer(11, 11, 5),
                        new TwoLayerFeature(4, 3, 3))
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
                        .build())));

        Configured.BLOOD_EBONY = RegistryHelper.createConfiguredFeature("blood_ebony", Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(HallowsBlocks.BLOOD_EBONY_LEAVES.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(2), FeatureSpread.func_242252_a(1), 1),
                        new FancyTrunkPlacer(11, 11, 5),
                        new TwoLayerFeature(4, 3, 3))
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES)
                        .build())));

        Configured.EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("ebony_hanging_leaves", Feature.TREE.withConfiguration(
                Configured.EBONY.getConfig().func_236685_a_(
                        ImmutableList.of(Placements.HANGING_LEAVES_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.BLOOD_EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("blood_ebony_hanging_leaves", Feature.TREE.withConfiguration(
                Configured.BLOOD_EBONY.getConfig().func_236685_a_(
                        ImmutableList.of(Placements.HANGING_LEAVES_BLOOD_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.TREES_EBONY = RegistryHelper.createConfiguredFeature("trees_ebony", Feature.RANDOM_SELECTOR.withConfiguration(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.EBONY_HANGING_LEAVES.withChance(1.0F)), Configured.EBONY_HANGING_LEAVES))
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(
                        new FeatureSpreadConfig(3))));

        Configured.TREES_BLOOD_EBONY = RegistryHelper.createConfiguredFeature("trees_blood_ebony", Feature.RANDOM_SELECTOR.withConfiguration(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.BLOOD_EBONY_HANGING_LEAVES.withChance(0.5F)), Configured.BLOOD_EBONY_HANGING_LEAVES))
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(
                        new FeatureSpreadConfig(2))));

        Configured.SMALL_EBONY = RegistryHelper.createConfiguredFeature("ebony", Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LEAVES.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(1), 1),
                        new StraightTrunkPlacer(6, 4, 2),
                        new TwoLayerFeature(2, 1, 1))
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
                        .build())));

        Configured.SMALL_BLOOD_EBONY = RegistryHelper.createConfiguredFeature("blood_ebony", Feature.TREE.withConfiguration(
                (new BaseTreeFeatureConfig.Builder(
                        new SimpleBlockStateProvider(HallowsBlocks.EBONY_LOG.get().getDefaultState()),
                        new SimpleBlockStateProvider(HallowsBlocks.BLOOD_EBONY_LEAVES.get().getDefaultState()),
                        new BlobFoliagePlacer(FeatureSpread.func_242252_a(3), FeatureSpread.func_242252_a(1), 1),
                        new StraightTrunkPlacer(6, 4, 2),
                        new TwoLayerFeature(2, 1, 1))
                        .func_236702_a_(Heightmap.Type.MOTION_BLOCKING)
                        .build())));

        Configured.SMALL_EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("ebony_hanging_leaves", Feature.TREE.withConfiguration(
                Configured.SMALL_EBONY.getConfig().func_236685_a_(
                        ImmutableList.of(Placements.HANGING_LEAVES_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.SMALL_BLOOD_EBONY_HANGING_LEAVES = RegistryHelper.createConfiguredFeature("blood_ebony_hanging_leaves", Feature.TREE.withConfiguration(
                Configured.SMALL_BLOOD_EBONY.getConfig().func_236685_a_(
                        ImmutableList.of(Placements.HANGING_LEAVES_BLOOD_EBONY_PLACEMENT, Placements.BRANCH_EBONY_PLACEMENT, Placements.JACK_O_LANTERN_PLACEMENT))));

        Configured.TREES_SMALL_EBONY = RegistryHelper.createConfiguredFeature("trees_ebony", Feature.RANDOM_SELECTOR.withConfiguration(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.SMALL_EBONY_HANGING_LEAVES.withChance(1.0F)), Configured.SMALL_EBONY_HANGING_LEAVES))
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(
                        new FeatureSpreadConfig(2))));

        Configured.TREES_SMALL_BLOOD_EBONY = RegistryHelper.createConfiguredFeature("trees_blood_ebony", Feature.RANDOM_SELECTOR.withConfiguration(
                new MultipleRandomFeatureConfig(
                        ImmutableList.of(Configured.SMALL_BLOOD_EBONY_HANGING_LEAVES.withChance(0.5F)), Configured.SMALL_BLOOD_EBONY_HANGING_LEAVES))
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(
                        new FeatureSpreadConfig(1))));

        Configured.FLOWER_POPPIES = RegistryHelper.createConfiguredFeature("flower_poppies", Feature.FLOWER
                .withConfiguration(POPPY_FLOWER_CONFIG)
                .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
                .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT)
                .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(2))
                .func_242731_b(1)));

        Configured.PATCH_DEADROOT = RegistryHelper.createConfiguredFeature("patch_deadroot", Feature.RANDOM_PATCH.withConfiguration(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.DEADROOT.get().getDefaultState()), SimpleBlockPlacer.PLACER)
                        .tries(64).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLOWED_DIRT.get(), Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.DIRT))
                        .func_227317_b_().build()
        ).withPlacement(Features.Placements.PATCH_PLACEMENT.func_242731_b(8)));

        Configured.PATCH_DEADROOT_DENSE = RegistryHelper.createConfiguredFeature("patch_deadroot_dense", Feature.RANDOM_PATCH.withConfiguration(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.DEADROOT.get().getDefaultState()), SimpleBlockPlacer.PLACER)
                        .tries(48).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLOWED_DIRT.get(), Blocks.COARSE_DIRT))
                        .func_227317_b_().build()
        ).withPlacement(Features.Placements.PATCH_PLACEMENT.func_242731_b(12)));

        Configured.PATCH_DEADROOT_WATER = RegistryHelper.createConfiguredFeature("patch_deadroot_water", Feature.RANDOM_PATCH.withConfiguration(
                new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(HallowsBlocks.DEADROOT.get().getDefaultState()), SimpleBlockPlacer.PLACER)
                        .tries(64).whitelist(ImmutableSet.of(HallowsBlocks.PETRIFIED_SAND.get(), HallowsBlocks.HALLOWED_DIRT.get(), Blocks.COARSE_DIRT))
                        .func_227317_b_().build()
        ).withPlacement(Features.Placements.SEAGRASS_DISK_PLACEMENT.func_242731_b(16))
                .withPlacement(Placement.COUNT_NOISE.configure(new NoiseDependant(-0.8D, 5, 10))));

        Configured.VERTICAL_PILLAR = RegistryHelper.createConfiguredFeature("vertical_pillar", VERTICAL_PILLAR.get().withConfiguration(
                IFeatureConfig.NO_FEATURE_CONFIG)
                .range(128)
                .square()
                .func_242731_b(10));

        Configured.WATER_DELTA = RegistryHelper.createConfiguredFeature("water_delta", Feature.DELTA_FEATURE.withConfiguration(
                new BasaltDeltasFeature(
                        Blocks.WATER.getDefaultState(), HallowsBlocks.HALLOWED_DIRT.get().getDefaultState(),
                        FeatureSpread.func_242253_a(3, 4),
                        FeatureSpread.func_242253_a(0, 2)))
                .withPlacement(Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(40))));

        Configured.ORE_STYGIAN_RUIN = RegistryHelper.createConfiguredFeature("ore_stygian_ruin", SPREAD_ORE.get().withConfiguration(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.STYGIAN_RUIN.get().getDefaultState(), 5)
        )).withPlacement(Placement.DEPTH_AVERAGE.configure(new DepthAverageConfig(16, 16))).square();

        Configured.ORE_BLACKSTONE = RegistryHelper.createConfiguredFeature("ore_blackstone", Feature.ORE.withConfiguration(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), Blocks.BLACKSTONE.getDefaultState(), 33)
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(5, 10, 37))).square().func_242731_b(2));

        Configured.ORE_TENEBRITE = RegistryHelper.createConfiguredFeature("ore_tenebrite", Feature.ORE.withConfiguration(
                new OreFeatureConfig(new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), HallowsBlocks.TENEBRITE.get().getDefaultState(), 64)
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 10, 37))).func_242731_b(4));

        Configured.ORE_GILDED_TENEBRITE = RegistryHelper.createConfiguredFeature("ore_gilded_tenebrite", Feature.ORE.withConfiguration(
                new OreFeatureConfig(new BlockMatchRuleTest(HallowsBlocks.TENEBRITE.get()), HallowsBlocks.GILDED_TENEBRITE.get().getDefaultState(), 8)
        ).withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(0, 10, 37))).func_242731_b(4));

        Configured.PETRIFIED_SANDSTONE_ROCK = RegistryHelper.createConfiguredFeature("petrified_sandstone_rock", Feature.FOREST_ROCK.withConfiguration(
                new BlockStateFeatureConfig(HallowsBlocks.PETRIFIED_SANDSTONE.get().getDefaultState())).withPlacement(
                        Placement.COUNT_MULTILAYER.configure(new FeatureSpreadConfig(10)))
                .func_242732_c(4));

        Configured.DISK_PETRIFIED_SAND = RegistryHelper.createConfiguredFeature("petrified_sandstone_rock", Feature.DISK.withConfiguration(
                new SphereReplaceConfig(HallowsBlocks.PETRIFIED_SAND.get().getDefaultState(),
                        FeatureSpread.func_242253_a(4, 1), 1,
                        ImmutableList.of(Blocks.DIRT.getDefaultState(), Blocks.GRASS_BLOCK.getDefaultState(), HallowsBlocks.HALLOWED_DIRT.get().getDefaultState())))
                .withPlacement(Features.Placements.PATCH_PLACEMENT));

        //Configured.PATCH_HEMLOCK = RegistryHelper.createConfiguredFeature("patch_hemlock", SPREAD_PLANT.get().withConfiguration(
        //        IFeatureConfig.NO_FEATURE_CONFIG
        //));

        Configured.PATCH_PUMPKIN_COMMON = RegistryHelper.createConfiguredFeature("patch_pumpkin_common", Feature.RANDOM_PATCH.withConfiguration(
                new BlockClusterFeatureConfig.Builder(
                        new SimpleBlockStateProvider(Blocks.PUMPKIN.getDefaultState()), SimpleBlockPlacer.PLACER)
                        .tries(64)
                        .whitelist(ImmutableSet.of(Blocks.DIRT, Blocks.GRASS_BLOCK, HallowsBlocks.HALLOWED_DIRT.get())).func_227317_b_().build()).withPlacement(
                                Features.Placements.PATCH_PLACEMENT).chance(6));

        Configured.IGNIS_CAVE_BIOME = RegistryHelper.createConfiguredFeature("ignis_cave_biome", CAVE_BIOME.get().withConfiguration(
                new CaveBiomeFeatureConfig(
                        HallowsBlocks.TENEBRITE.get().getDefaultState(), HallowsBlocks.TENEBRITE.get().getDefaultState(), HallowsBlocks.TENEBRITE.get().getDefaultState(), HallowsBlocks.TENEBRITE.get().getDefaultState(),
                        128, 0.01F, new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), false)
        ).withPlacement(Placements.Configured.CAVE_BIOME_PLACEMENT));
    }
}