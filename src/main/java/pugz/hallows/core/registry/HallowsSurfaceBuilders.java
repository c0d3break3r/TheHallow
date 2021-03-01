package pugz.hallows.core.registry;

import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.world.surface.GlaciersSurfaceBuilder;
import pugz.hallows.common.world.surface.HallowedSurfaceBuilder;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryUtil;

public class HallowsSurfaceBuilders {
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Hallows.MOD_ID);

    public static RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> HALLOWED;
    public static RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> GLACIERS;

    public static void registerSurfaceBuilders() {
        HALLOWED = RegistryUtil.createSurfaceBuilder("hallowed", () -> new HallowedSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
        GLACIERS = RegistryUtil.createSurfaceBuilder("glaciers", () -> new GlaciersSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
    }

    public static class Configured {
        public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> HALLOWED = RegistryUtil.createConfiguredSurfaceBuilder("hallowed", HallowsSurfaceBuilders.HALLOWED.get().func_242929_a(SurfaceBuilder.GRASS_DIRT_SAND_CONFIG));
        public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> GLACIERS = RegistryUtil.createConfiguredSurfaceBuilder("glaciers", HallowsSurfaceBuilders.GLACIERS.get().func_242929_a(SurfaceBuilder.GRASS_DIRT_SAND_CONFIG));
    }
}