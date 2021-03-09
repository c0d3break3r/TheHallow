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
    public static final RegistryKey<World> DIMENSION = RegistryKey.getOrCreateKey(Registry.WORLD_KEY, new ResourceLocation(Hallows.MOD_ID, "the_hallows"));
    public static final RegistryKey<DimensionType> DIMENSION_TYPE = RegistryKey.getOrCreateKey(Registry.DIMENSION_TYPE_KEY, new ResourceLocation(Hallows.MOD_ID, "the_hallows"));

    public static void registerPOIs() {
        //PORTAL = RegistryHelper.createPOI("hallow_portal", ImmutableSet.copyOf(HallowsBlocks.GIANT_CAULDRON.get().getStateContainer().getValidStates()), 1, 1);
    }

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