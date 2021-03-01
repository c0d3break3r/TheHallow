package pugz.hallows.core.util;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.ParticleEffectAmbience;
import pugz.hallows.common.world.biome.AbstractBiome;

public interface IBaseBiome {
    default Biome.Category getCategory() {
        return Biome.Category.PLAINS;
    }

    default Biome.RainType getRainType() {
        return Biome.RainType.RAIN;
    }

    default float getTemperature() {
        return 0.7F;
    }

    default float getDownfall() {
        return 0.8F;
    }

    default float getDepth() {
        return 0.125F;
    }

    default float getScale() {
        return 0.05F;
    }

    default int getWaterColor() {
        return 4159204;
    }

    default int getWaterFogColor() {
        return 329011;
    }

    default int getFogColor() {
        return 12638463;
    }

    ParticleEffectAmbience getParticleEffect();

    int getGrassColor();

    int getFoliageColor();

    default int getSkyColor() {
        return AbstractBiome.getSkyColorWithTemperatureModifier(this.getTemperature());
    }
}