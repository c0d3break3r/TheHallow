package pugz.hallows.common.world.biome;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.*;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.IBaseBiome;

public abstract class AbstractBiome implements IBaseBiome {
    private final String name;
    private final Biome biome;
    private final RegistryKey<Biome> key;

    public AbstractBiome(String name) {
        this.name = name;
        this.biome = new Biome.Builder()
                .biomeCategory(this.getCategory())
                .depth(this.getDepth())
                .downfall(this.getDownfall())
                .precipitation(this.getRainType())
                .scale(this.getScale())
                .temperature(this.getTemperature())
                .specialEffects(new BiomeAmbience.Builder()
                        .fogColor(this.getFogColor())
                        .waterColor(this.getWaterColor())
                        .waterFogColor(this.getWaterFogColor())
                        .ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS)
                        .skyColor(this.getSkyColor())
                        .grassColorOverride(this.getGrassColor())
                        .foliageColorOverride(this.getFoliageColor())
                        .ambientParticle(this.getParticleEffect())
                        .build())
                .generationSettings(this.getGenerationSettings())
                .mobSpawnSettings(this.getMobSpawns())
                .build();

        this.key = RegistryKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(Hallows.MOD_ID, this.name));

        //BiomeManager.addBiome(this.getBiomeType(), new BiomeManager.BiomeEntry(this.getKey(), this.getWeight()));
        BiomeDictionary.addTypes(this.getKey(), this.getBiomeDictionaryTypes());
    }

    public String getName() {
        return name;
    }

    public Biome getBiome() {
        return biome;
    }

    public RegistryKey<Biome> getKey() {
        return key;
    }

    abstract BiomeManager.BiomeType getBiomeType();

    abstract int getWeight();

    abstract BiomeDictionary.Type[] getBiomeDictionaryTypes();

    abstract BiomeGenerationSettings getGenerationSettings();

    abstract MobSpawnInfo getMobSpawns();

    public static int getSkyColorWithTemperatureModifier(float temperature) {
        float lvt_1_1_ = temperature / 3.0F;
        lvt_1_1_ = MathHelper.clamp(lvt_1_1_, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - lvt_1_1_ * 0.05F, 0.5F + lvt_1_1_ * 0.1F, 1.0F);
    }
}