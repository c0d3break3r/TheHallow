package pugz.hallows.common.world.biome;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import pugz.hallows.core.registry.HallowsSurfaceBuilders;

import javax.annotation.Nonnull;

public class HemlockSwampBiome extends AbstractBiome {
    public HemlockSwampBiome() {
        super("hemlock_swamp");
    }

    @Override
    public Biome.Category getCategory() {
        return Biome.Category.NONE;
    }

    @Override
    public int getWaterColor() {
        return 5001581;
    }

    @Override
    public int getWaterFogColor() {
        return 8620438;
    }

    @Override
    public int getGrassColor() {
        return 9470298;
    }

    @Override
    public int getFoliageColor() {
        return 10387802;
    }

    @Override
    public int getSkyColor() {
        return 4210816;
    }

    @Override
    public ParticleEffectAmbience getParticleEffect() {
        return new ParticleEffectAmbience(ParticleTypes.WHITE_ASH, 0.01F);
    }

    @Override
    public float getDepth() {
        return -0.2F;
    }

    @Override
    public float getScale() {
        return 0.1F;
    }

    @Override
    public float getTemperature() {
        return 0.5F;
    }

    @Override
    public float getDownfall() {
        return 0.6F;
    }

    @Nonnull
    @Override
    BiomeManager.BiomeType getBiomeType() {
        return BiomeManager.BiomeType.COOL;
    }

    @Override
    int getWeight() {
        return 2;
    }

    @Nonnull
    @Override
    BiomeDictionary.Type[] getBiomeDictionaryTypes() {
        return new BiomeDictionary.Type[]{
                BiomeDictionary.Type.COLD,
                BiomeDictionary.Type.DENSE,
                BiomeDictionary.Type.SWAMP,
                BiomeDictionary.Type.WET,
                BiomeDictionary.Type.SPOOKY,
                BiomeDictionary.Type.FOREST
        };
    }

    @Nonnull
    @Override
    BiomeGenerationSettings getGenerationSettings() {
        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder();
        builder.surfaceBuilder(() -> HallowsSurfaceBuilders.Configured.HALLOWED);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.PATCH_GRASS_PLAIN);
        return builder.build();
    }

    @Nonnull
    @Override
    MobSpawnInfo getMobSpawns() {
        MobSpawnInfo.Builder builder = new MobSpawnInfo.Builder();
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 2, 3));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 2, 3));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 2, 3));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 2, 3));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 2));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 2));
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        return builder.build();
    }
}