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

        if (name.getPath().equals("perished_valley") || name.getPath().equals("pumpkin_valley")) {
            gen.withCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CRYSTAL);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.GIANT_PUMPKIN);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_ASPHODEL);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.FLOWER_POPPIES);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_PUMPKIN_COMMON);
            spawns.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 30, 1, 2));

            if (name.getPath().equals("pumpkin_valley")) {
                gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.GIANT_PUMPKIN);
            }
        }

        if (name.getPath().equals("necrotic_glaciers")) {
            gen.withCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.withCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.NECROMANTLE_CRACK);
            gen.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CRYSTAL);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.HALLSTONE_EMERALD_ORE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
        }

        if (name.getPath().equals("hanging_woods")) {
            gen.withCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CRYSTAL);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_EBONY);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_BLOOD_EBONY);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            spawns.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 30, 1, 2));
        }

        if (name.getPath().equals("hemlock_swamp")) {
            gen.withCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.withFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, HallowsFeatures.Configured.WATER_DELTA);
            gen.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CRYSTAL);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.DISK_PETRIFIED_SAND);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_ASPHODEL);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.FLOWER_POPPIES);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_DENSE);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            //gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_HEMLOCK_WATER);
            //gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_HEMLOCK_LAND);
            spawns.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 25, 1, 2));
        }

        if (name.getPath().equals("petrified_sands") || name.getPath().equals("petrified_boneyard")) {
            gen.withCarver(GenerationStage.Carving.AIR, HallowsCarvers.Configured.HALLOWS_CAVE);
            gen.getStructures().add(() -> HallowsStructures.Configured.PETRIFIED_PYRAMID);
            gen.withFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, HallowsFeatures.Configured.VERTICAL_PILLAR);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.PATCH_NECROFIRE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, HallowsFeatures.Configured.IGNIS_CRYSTAL);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.OPAL_ORE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_STYGIAN_RUIN);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_BLACKSTONE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_TENEBRITE);
            gen.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, HallowsFeatures.Configured.ORE_GILDED_TENEBRITE);
            //gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PETRIFIED_SANDSTONE_ROCK);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.TREES_ASPHODEL);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT);
            gen.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, HallowsFeatures.Configured.PATCH_DEADROOT_WATER);
            spawns.withSpawner(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(HallowsEntities.GHOST.get(), 25, 1, 2));
        }
    }
}