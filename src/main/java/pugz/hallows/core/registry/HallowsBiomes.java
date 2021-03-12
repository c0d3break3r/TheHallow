package pugz.hallows.core.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.world.biome.*;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsBiomes {
    public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, Hallows.MOD_ID);

    public static RegistryKey<Biome> PERISHED_VALLEY;
    public static RegistryKey<Biome> PUMPKIN_VALLEY;
    public static RegistryKey<Biome> NECROTIC_GLACIERS;
    public static RegistryKey<Biome> HANGING_WOODS;
    public static RegistryKey<Biome> HEMLOCK_SWAMP;
    public static RegistryKey<Biome> PETRIFIED_SANDS;
    public static RegistryKey<Biome> PETRIFIED_BONEYARD;

    public static void registerBiomes() {
        PERISHED_VALLEY = RegistryHelper.createBiome(new PerishedValleyBiome());
        PUMPKIN_VALLEY = RegistryHelper.createBiome(new PumpkinValleyBiome());
        NECROTIC_GLACIERS = RegistryHelper.createBiome(new NecroticGlaciersBiome());
        HANGING_WOODS = RegistryHelper.createBiome(new HangingWoodsBiome());
        HEMLOCK_SWAMP = RegistryHelper.createBiome(new HemlockSwampBiome());
        PETRIFIED_SANDS = RegistryHelper.createBiome(new PetrifiedSandsBiome());
        PETRIFIED_BONEYARD = RegistryHelper.createBiome(new PetrifiedBoneyardBiome());
    }

    public static void onBiomeLoading(final BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        MobSpawnInfoBuilder spawns = event.getSpawns();
        ResourceLocation name = event.getName();

        assert name != null;

        if (name.getPath().equals("perished_valley") || name.getPath().equals("pumpkin_valley")) {
            gen.addCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CAVE_BIOME);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_ASPHODEL);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.FLOWER_POPPIES);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_PUMPKIN_COMMON);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.WILL_O_WISP_VINES);
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 30, 1, 1));
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.HAUNT.get(), 3, 1, 1));

            if (name.getPath().equals("pumpkin_valley")) {
            }
        }

        if (name.getPath().equals("necrotic_glaciers")) {
            gen.addCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.addCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.NECROMANTLE_CRACK);
            gen.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CAVE_BIOME);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.HALLSTONE_EMERALD_ORE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.HAUNT.get(), 5, 1, 1));
        }

        if (name.getPath().equals("hanging_woods")) {
            gen.addCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CAVE_BIOME);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_EBONY);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_SMALL_EBONY);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_BLOOD_EBONY);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_SMALL_EBONY);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.WILL_O_WISP_VINES);
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 30, 1, 1));
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.HAUNT.get(), 4, 1, 1));
        }

        if (name.getPath().equals("hemlock_swamp")) {
            gen.addCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, HallowsFeatures.Configured.WATER_DELTA);
            gen.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CAVE_BIOME);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.DISK_PETRIFIED_SAND);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_ASPHODEL);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_SMALL_EBONY);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_SMALL_BLOOD_EBONY);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.FLOWER_POPPIES);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.WILL_O_WISP_VINES);
            //gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_HEMLOCK);
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 25, 1, 1));
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.HAUNT.get(), 4, 1, 1));
        }

        if (name.getPath().equals("petrified_sands") || name.getPath().equals("petrified_boneyard")) {
            gen.addCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.getStructures().add(() -> HallowsStructures.Configured.PETRIFIED_PYRAMID);
            gen.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.PETRIFIED_SANDSTONE_ROCK);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CAVE_BIOME);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            //gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PETRIFIED_SANDSTONE_ROCK);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_ASPHODEL);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            spawns.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 25, 1, 1));
        }
    }
}