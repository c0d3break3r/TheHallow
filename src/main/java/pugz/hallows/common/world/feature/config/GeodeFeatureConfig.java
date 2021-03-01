package pugz.hallows.common.world.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class GeodeFeatureConfig implements IFeatureConfig {
    public static final Codec<Double> a = Codec.doubleRange(0.0D, 1.0D);
    public static final Codec<GeodeFeatureConfig> CODEC = RecordCodecBuilder.create((var0) -> {
        return var0.group(a.fieldOf("use_potential_placements_chance").orElse(0.35D).forGetter((var0x) -> {
            return var0x.potentialPlacementsChance;
        }), a.fieldOf("use_alternate_layer0_chance").orElse(0.0D).forGetter((var0x) -> {
            return var0x.alternateLayer0Chance;
        }), Codec.BOOL.fieldOf("placements_require_layer0_alternate").orElse(true).forGetter((var0x) -> {
            return var0x.requireLayer0Alternate;
        }), Codec.intRange(1, 10).fieldOf("min_outer_wall_distance").orElse(4).forGetter((var0x) -> {
            return var0x.minOuterDistance;
        }), Codec.intRange(1, 20).fieldOf("max_outer_wall_distance").orElse(6).forGetter((var0x) -> {
            return var0x.maxOuterDistance;
        }), Codec.intRange(1, 10).fieldOf("min_distribution_points").orElse(3).forGetter((var0x) -> {
            return var0x.minDistribution;
        }), Codec.intRange(1, 20).fieldOf("max_distribution_points").orElse(5).forGetter((var0x) -> {
            return var0x.maxDistribution;
        }), Codec.intRange(0, 10).fieldOf("min_point_offset").orElse(1).forGetter((var0x) -> {
            return var0x.minOffset;
        }), Codec.intRange(0, 10).fieldOf("max_point_offset").orElse(3).forGetter((var0x) -> {
            return var0x.maxOffset;
        }), Codec.INT.fieldOf("min_gen_offset").orElse(-16).forGetter((var0x) -> {
            return var0x.minGenOffset;
        }), Codec.INT.fieldOf("max_gen_offset").orElse(16).forGetter((var0x) -> {
            return var0x.maxGenOffset;
        }), a.fieldOf("noise_multiplier").orElse(0.05D).forGetter((var0x) -> {
            return var0x.noiseMultiplier;
        })).apply(var0, GeodeFeatureConfig::new);
    });
    public final double potentialPlacementsChance;
    public final double alternateLayer0Chance;
    public final boolean requireLayer0Alternate;
    public final int minOuterDistance;
    public final int maxOuterDistance;
    public final int minDistribution;
    public final int maxDistribution;
    public final int minOffset;
    public final int maxOffset;
    public final int minGenOffset;
    public final int maxGenOffset;
    public final double noiseMultiplier;

    public GeodeFeatureConfig(double potentialPlacementsChance, double alternateLayer0Chance, boolean requireLayer0Alternate, int minOuterDistance, int maxOuterDistance, int minDistribution, int maxDistribution, int minOffset, int maxOffset, int minGenOffset, int maxGenOffset, double noiseMultiplier) {
        this.potentialPlacementsChance = potentialPlacementsChance;
        this.alternateLayer0Chance = alternateLayer0Chance;
        this.requireLayer0Alternate = requireLayer0Alternate;
        this.minOuterDistance = minOuterDistance;
        this.maxOuterDistance = maxOuterDistance;
        this.minDistribution = minDistribution;
        this.maxDistribution = maxDistribution;
        this.minOffset = minOffset;
        this.maxOffset = maxOffset;
        this.minGenOffset = minGenOffset;
        this.maxGenOffset = maxGenOffset;
        this.noiseMultiplier = noiseMultiplier;
    }
}