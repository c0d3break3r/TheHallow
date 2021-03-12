package pugz.hallows.core.registry;

import com.google.common.collect.ImmutableSet;
import net.minecraft.client.world.DimensionRenderInfo;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.PointOfInterestType;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.world.HallowsBiomeProvider;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

import javax.annotation.Nonnull;

public class HallowsDimensions {
    public static final DeferredRegister<PointOfInterestType> POINTS_OF_INTEREST = DeferredRegister.create(ForgeRegistries.POI_TYPES, Hallows.MOD_ID);

    //public static RegistryObject<PointOfInterestType> PORTAL;
    public static final RegistryKey<World> DIMENSION = RegistryKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Hallows.MOD_ID, "the_hallows"));
    public static final RegistryKey<DimensionType> DIMENSION_TYPE = RegistryKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(Hallows.MOD_ID, "the_hallows"));

    public static void registerPOIs() {
        //PORTAL = RegistryHelper.createPOI("hallow_portal", ImmutableSet.copyOf(HallowsBlocks.GIANT_CAULDRON.get().getStateContainer().getValidStates()), 1, 1);
    }

    public static void registerDimensions() {
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(Hallows.MOD_ID, "biome_source"), HallowsBiomeProvider.CODEC);
    }

    public static void registerEffects() {
        DimensionRenderInfo.EFFECTS.put(DIMENSION.getRegistryName(), new DimensionRenderInfo(Float.NaN, false, DimensionRenderInfo.FogType.NORMAL, false, true) {
            @Nonnull
            @Override
            public Vector3d getBrightnessDependentFogColor(Vector3d vector3d, float p_230494_2_) {
                return vector3d;
            }

            @Override
            public boolean isFoggyAt(int x, int y) {
                return false;
            }
        });
    }
}