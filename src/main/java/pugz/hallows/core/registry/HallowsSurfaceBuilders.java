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
import pugz.hallows.core.util.RegistryHelper;

public class HallowsSurfaceBuilders {
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, Hallows.MOD_ID);

    public static RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> HALLOWED;
    public static RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> GLACIERS;

    public static void registerSurfaceBuilders() {
        HALLOWED = RegistryHelper.createSurfaceBuilder("hallowed", () -> new HallowedSurfaceBuilder(SurfaceBuilderConfig.CODEC));
        GLACIERS = RegistryHelper.createSurfaceBuilder("glaciers", () -> new GlaciersSurfaceBuilder(SurfaceBuilderConfig.CODEC));
    }

    public static class Configured {
        public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> HALLOWED = RegistryHelper.createConfiguredSurfaceBuilder("hallowed", HallowsSurfaceBuilders.HALLOWED.get().configured(SurfaceBuilder.CONFIG_OCEAN_SAND));
        public static final ConfiguredSurfaceBuilder<SurfaceBuilderConfig> GLACIERS = RegistryHelper.createConfiguredSurfaceBuilder("glaciers", HallowsSurfaceBuilders.GLACIERS.get().configured(SurfaceBuilder.CONFIG_OCEAN_SAND));
    }
}