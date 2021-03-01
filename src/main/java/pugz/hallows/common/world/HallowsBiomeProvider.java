package pugz.hallows.common.world;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Function3;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
import it.unimi.dsi.fastutil.doubles.DoubleList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryLookupCodec;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeRegistry;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.MaxMinNoiseMixer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.registry.HallowsBiomes;

import javax.annotation.Nonnull;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class HallowsBiomeProvider extends BiomeProvider {
    private static final HallowsBiomeProvider.Noise DEFAULT_NOISE = new HallowsBiomeProvider.Noise(-8, ImmutableList.of(1.0D, 1.5D, 1.0D));

    public static final MapCodec<HallowsBiomeProvider> PACKET_CODEC = RecordCodecBuilder.mapCodec((builder) -> {
        return builder.group(Codec.LONG.fieldOf("seed").forGetter((hallowsProvider) -> {
            return hallowsProvider.seed;
        }), RecordCodecBuilder.<Pair<Biome.Attributes, Supplier<Biome>>>create((biomeAttributes) -> {
            return biomeAttributes.group(Biome.Attributes.CODEC.fieldOf("parameters").forGetter(Pair::getFirst), Biome.BIOME_CODEC.fieldOf("biome").forGetter(Pair::getSecond)).apply(biomeAttributes, Pair::of);
        }).listOf().fieldOf("biomes").forGetter((hallowsProvider) -> {
            return hallowsProvider.biomeAttributes;
        }), HallowsBiomeProvider.Noise.CODEC.fieldOf("temperature_noise").forGetter((hallowsProvider) -> {
            return hallowsProvider.temperatureNoise;
        }), HallowsBiomeProvider.Noise.CODEC.fieldOf("humidity_noise").forGetter((hallowsProvider) -> {
            return hallowsProvider.humidityNoise;
        }), HallowsBiomeProvider.Noise.CODEC.fieldOf("altitude_noise").forGetter((hallowsProvider) -> {
            return hallowsProvider.altitudeNoise;
        }), HallowsBiomeProvider.Noise.CODEC.fieldOf("weirdness_noise").forGetter((hallowsProvider) -> {
            return hallowsProvider.weirdnessNoise;
        })).apply(builder, HallowsBiomeProvider::new);
    });
    public static final Codec<HallowsBiomeProvider> CODEC = Codec.mapEither(HallowsBiomeProvider.DefaultBuilder.CODEC, PACKET_CODEC).xmap((either) -> {
        return either.map(HallowsBiomeProvider.DefaultBuilder::build, Function.identity());
    }, (hallowsProvider) -> {
        return hallowsProvider.getDefaultBuilder().map(Either::<HallowsBiomeProvider.DefaultBuilder, HallowsBiomeProvider>left).orElseGet(() -> {
            return Either.right(hallowsProvider);
        });
    }).codec();

    private final HallowsBiomeProvider.Noise temperatureNoise;
    private final HallowsBiomeProvider.Noise humidityNoise;
    private final HallowsBiomeProvider.Noise altitudeNoise;
    private final HallowsBiomeProvider.Noise weirdnessNoise;
    private final MaxMinNoiseMixer temperatureNoiseMixer;
    private final MaxMinNoiseMixer humidityNoiseMixer;
    private final MaxMinNoiseMixer altitudeNoiseMixer;
    private final MaxMinNoiseMixer weirdnessNoiseMixer;
    private final List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes;
    private final boolean useHeightForNoise;
    private final long seed;
    private final Optional<Pair<Registry<Biome>, HallowsBiomeProvider.Preset>> hallowsProviderPreset;

    private HallowsBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, Optional<Pair<Registry<Biome>, HallowsBiomeProvider.Preset>> hallowsProviderPreset) {
        this(seed, biomeAttributes, DEFAULT_NOISE, DEFAULT_NOISE, DEFAULT_NOISE, DEFAULT_NOISE, hallowsProviderPreset);
    }

    private HallowsBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, HallowsBiomeProvider.Noise temperatureNoise, HallowsBiomeProvider.Noise humidityNoise, HallowsBiomeProvider.Noise altitudeNoise, HallowsBiomeProvider.Noise weirdnessNoise) {
        this(seed, biomeAttributes, temperatureNoise, humidityNoise, altitudeNoise, weirdnessNoise, Optional.empty());
    }

    private HallowsBiomeProvider(long seed, List<Pair<Biome.Attributes, Supplier<Biome>>> biomeAttributes, HallowsBiomeProvider.Noise temperatureNoise, HallowsBiomeProvider.Noise humidityNoise, HallowsBiomeProvider.Noise altitudeNoise, HallowsBiomeProvider.Noise weirdnessNoise, Optional<Pair<Registry<Biome>, HallowsBiomeProvider.Preset>> hallowsProviderPreset) {
        super(biomeAttributes.stream().map(Pair::getSecond));
        this.seed = seed;
        this.hallowsProviderPreset = hallowsProviderPreset;
        this.temperatureNoise = temperatureNoise;
        this.humidityNoise = humidityNoise;
        this.altitudeNoise = altitudeNoise;
        this.weirdnessNoise = weirdnessNoise;
        this.temperatureNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed), temperatureNoise.getNumberOfOctaves(), temperatureNoise.getAmplitudes());
        this.humidityNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed + 1L), humidityNoise.getNumberOfOctaves(), humidityNoise.getAmplitudes());
        this.altitudeNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed + 2L), altitudeNoise.getNumberOfOctaves(), altitudeNoise.getAmplitudes());
        this.weirdnessNoiseMixer = MaxMinNoiseMixer.func_242930_a(new SharedSeedRandom(seed + 3L), weirdnessNoise.getNumberOfOctaves(), weirdnessNoise.getAmplitudes());
        this.biomeAttributes = biomeAttributes;
        this.useHeightForNoise = false;
    }

    @Nonnull
    @Override
    @OnlyIn(Dist.CLIENT)
    public BiomeProvider getBiomeProvider(long seed) {
        return new HallowsBiomeProvider(seed, this.biomeAttributes, this.hallowsProviderPreset);
    }

    private Optional<HallowsBiomeProvider.DefaultBuilder> getDefaultBuilder() {
        return this.hallowsProviderPreset.map((registryPresetPair) -> {
            return new HallowsBiomeProvider.DefaultBuilder(registryPresetPair.getSecond(), registryPresetPair.getFirst(), this.seed);
        });
    }

    @Nonnull
    @Override
    protected Codec<? extends BiomeProvider> getBiomeProviderCodec() {
        return CODEC;
    }

    @Nonnull
    public Biome getNoiseBiome(int x, int y, int z) {
        int i = this.useHeightForNoise ? y : 0;
        Biome.Attributes biome$attributes = new Biome.Attributes((float)this.temperatureNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), (float)this.humidityNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), (float)this.altitudeNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), (float)this.weirdnessNoiseMixer.func_237211_a_((double)x, (double)i, (double)z), 0.0F);
        return this.biomeAttributes.stream().min(Comparator.comparing((attributeBiomePair) -> {
            return attributeBiomePair.getFirst().getAttributeDifference(biome$attributes);
        })).map(Pair::getSecond).map(Supplier::get).orElse(BiomeRegistry.THE_VOID);
    }

    static final class DefaultBuilder {
        public static final MapCodec<HallowsBiomeProvider.DefaultBuilder> CODEC = RecordCodecBuilder.mapCodec((builder) -> {
            return builder.group(ResourceLocation.CODEC.flatXmap((id) -> {
                return Optional.ofNullable(HallowsBiomeProvider.Preset.PRESETS.get(id)).map(DataResult::success).orElseGet(() -> {
                    return DataResult.error("Unknown preset: " + id);
                });
            }, (preset) -> {
                return DataResult.success(preset.id);
            }).fieldOf("preset").stable().forGetter(HallowsBiomeProvider.DefaultBuilder::getPreset), RegistryLookupCodec.getLookUpCodec(Registry.BIOME_KEY).forGetter(HallowsBiomeProvider.DefaultBuilder::getLookupRegistry), Codec.LONG.fieldOf("seed").stable().forGetter(HallowsBiomeProvider.DefaultBuilder::getSeed)).apply(builder, builder.stable(HallowsBiomeProvider.DefaultBuilder::new));
        });
        private final HallowsBiomeProvider.Preset preset;
        private final Registry<Biome> lookupRegistry;
        private final long seed;

        private DefaultBuilder(HallowsBiomeProvider.Preset preset, Registry<Biome> lookupRegistry, long seed) {
            this.preset = preset;
            this.lookupRegistry = lookupRegistry;
            this.seed = seed;
        }

        public HallowsBiomeProvider.Preset getPreset() {
            return this.preset;
        }

        public Registry<Biome> getLookupRegistry() {
            return this.lookupRegistry;
        }

        public long getSeed() {
            return this.seed;
        }

        public HallowsBiomeProvider build() {
            return this.preset.build(this.lookupRegistry, this.seed);
        }
    }

    static class Noise {
        private final int numOctaves;
        private final DoubleList amplitudes;
        public static final Codec<HallowsBiomeProvider.Noise> CODEC = RecordCodecBuilder.create((builder) -> {
            return builder.group(Codec.INT.fieldOf("firstOctave").forGetter(HallowsBiomeProvider.Noise::getNumberOfOctaves), Codec.DOUBLE.listOf().fieldOf("amplitudes").forGetter(HallowsBiomeProvider.Noise::getAmplitudes)).apply(builder, HallowsBiomeProvider.Noise::new);
        });

        public Noise(int numOctaves, List<Double> amplitudes) {
            this.numOctaves = numOctaves;
            this.amplitudes = new DoubleArrayList(amplitudes);
        }

        public int getNumberOfOctaves() {
            return this.numOctaves;
        }

        public DoubleList getAmplitudes() {
            return this.amplitudes;
        }
    }

    public static class Preset {
        private static final Map<ResourceLocation, HallowsBiomeProvider.Preset> PRESETS = Maps.newHashMap();
        private final ResourceLocation id;
        private final Function3<HallowsBiomeProvider.Preset, Registry<Biome>, Long, HallowsBiomeProvider> hallowsProviderFunction;

        public Preset(ResourceLocation id, Function3<HallowsBiomeProvider.Preset, Registry<Biome>, Long, HallowsBiomeProvider> hallowsProviderFunction) {
            this.id = id;
            this.hallowsProviderFunction = hallowsProviderFunction;
            PRESETS.put(id, this);
        }

        public HallowsBiomeProvider build(Registry<Biome> lookupRegistry, long seed) {
            return this.hallowsProviderFunction.apply(this, lookupRegistry, seed);
        }
    }
}