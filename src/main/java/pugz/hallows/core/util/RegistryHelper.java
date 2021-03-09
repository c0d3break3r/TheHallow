package pugz.hallows.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraftforge.fml.RegistryObject;
import pugz.hallows.common.world.biome.AbstractBiome;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.registry.*;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;

import java.util.Set;
import java.util.function.Supplier;

public class RegistryHelper {
    public static <T extends Container> RegistryObject<ContainerType<T>> createContainer(final String key, ContainerType.IFactory<T> factory) {
        return HallowsContainers.CONTAINERS.register(key, () -> new ContainerType<>(factory));
    }

    public static <T extends IRecipe<?>> IRecipeType<T> createRecipe(final String name) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Hallows.MOD_ID, name), new IRecipeType<T>() {
            public String toString() {
                return name;
            }
        });
    }

    public static <S extends IRecipeSerializer<T>, T extends IRecipe<?>> RegistryObject<S> createRecipeSerializer(final String name, S recipeSerializer) {
        return HallowsRecipes.RECIPE_SERIALIZERS.register(name, () -> recipeSerializer);
    }

    public static RegistryKey<Biome> createBiome(AbstractBiome biome) {
        HallowsBiomes.BIOMES.register(biome.getName(), biome::getBiome);
        return biome.getKey();
    }

    public static <F extends Feature<?>> RegistryObject<F> createFeature(String name, Supplier<? extends F> supplier) {
        return HallowsFeatures.FEATURES.register(name, supplier);
    }

    public static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> createConfiguredFeature(String name, ConfiguredFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(Hallows.MOD_ID, name), feature);
    }

    public static <T extends Structure<?>> RegistryObject<T> createStructure(String name, Supplier<T> structure) {
        return HallowsStructures.STRUCTURE_FEATURES.register(name, structure);
    }

    public static <FC extends IFeatureConfig> StructureFeature<FC, ?> createConfiguredStructure(String name, StructureFeature<FC, ?> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, new ResourceLocation(Hallows.MOD_ID, name), feature);
    }

    public static <S extends SurfaceBuilder<?>> RegistryObject<S> createSurfaceBuilder(String name, Supplier<? extends S> surfaceBuilder) {
        return HallowsSurfaceBuilders.SURFACE_BUILDERS.register(name, surfaceBuilder);
    }

    public static ConfiguredSurfaceBuilder<SurfaceBuilderConfig> createConfiguredSurfaceBuilder(String name, ConfiguredSurfaceBuilder<SurfaceBuilderConfig> surfaceBuilder) {
        return Registry.register(WorldGenRegistries.CONFIGURED_SURFACE_BUILDER, new ResourceLocation(Hallows.MOD_ID, name), surfaceBuilder);
    }

    public static <C extends WorldCarver<?>> RegistryObject<C> createCarver(String name, Supplier<? extends C> supplier) {
        return HallowsCarvers.CARVERS.register(name, supplier);
    }

    public static <WC extends ICarverConfig> ConfiguredCarver<WC> createConfiguredCarver(String name, ConfiguredCarver<WC> feature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_CARVER, new ResourceLocation(Hallows.MOD_ID, name), feature);
    }

    public static <P extends TreeDecorator> RegistryObject<TreeDecoratorType<P>> createTreeDecorator(String name, Supplier<? extends TreeDecoratorType<P>> supplier) {
        return HallowsFeatures.TREE_DECORATORS.register(name, supplier);
    }

    public static RegistryObject<PointOfInterestType> createPOI(String name, Set<BlockState> states, int freeTickets, int range) {
        return HallowsDimensions.POINTS_OF_INTEREST.register(name, () -> new PointOfInterestType(name, states, freeTickets, range));
    }

    public static <P extends BasicParticleType> RegistryObject<BasicParticleType> createParticle(String name, Supplier<? extends BasicParticleType> supplier) {
        return HallowsParticles.PARTICLES.register(name, supplier);
    }
}