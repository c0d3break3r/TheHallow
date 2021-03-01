package pugz.hallows.core.registry;

import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import pugz.hallows.common.world.HallowsBiomeProvider;
import pugz.hallows.core.Hallows;

import javax.annotation.Nonnull;

public class HallowsDimensions {
    public static final RegistryKey<World> DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(Hallows.MOD_ID, "the_hallows"));
    public static final RegistryKey<DimensionType> DIMENSION_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(Hallows.MOD_ID, "the_hallows"));

    public static void registerDimensions() {
        Registry.register(Registry.BIOME_PROVIDER_CODEC, new ResourceLocation(Hallows.MOD_ID, "biome_source"), HallowsBiomeProvider.CODEC);
    }

    public static void registerEffects() {
        DimensionRenderInfo.field_239208_a_.put(DIMENSION.getLocation(), new DimensionRenderInfo(Float.NaN, false, DimensionRenderInfo.FogType.NORMAL, false, true) {
            @Nonnull
            @Override
            public Vector3d func_230494_a_(Vector3d vector3d, float sun) {
                return vector3d;
            }

            @Override
            public boolean func_230493_a_(int x, int y) {
                return true;
            }
        });
    }
}