package pugz.hallows.core.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.DimensionStructuresSettings;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.world.structure.PetrifiedPyramidPieces;
import pugz.hallows.common.world.structure.PetrifiedPyramidStructure;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

import java.util.*;

public class HallowsStructures {
    public static final DeferredRegister<Structure<?>> STRUCTURE_FEATURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, Hallows.MOD_ID);
    public static final Map<Structure<?>, StructureSeparationSettings> HALLOWS_STRUCTURES = new HashMap<>();
    public static final Set<ResourceLocation> HALLOWS_STRUCTURE_START_PIECES = new HashSet<>();

    public static RegistryObject<Structure<NoFeatureConfig>> PETRIFIED_PYRAMID;

    public static class Configured {
        public static StructureFeature<NoFeatureConfig, ? extends Structure<NoFeatureConfig>> PETRIFIED_PYRAMID;

        public static void registerConfiguredStructures() {
            Configured.PETRIFIED_PYRAMID = RegistryHelper.createConfiguredStructure("petrified_pyramid", HallowsStructures.PETRIFIED_PYRAMID.get().withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));
        }
    }

    public static final class Pieces {
        public static IStructurePieceType PETRIFIED_PYRAMID_PIECE;

        public static void registerPieces() {
            PETRIFIED_PYRAMID_PIECE = Registry.register(Registry.STRUCTURE_PIECE, new ResourceLocation(Hallows.MOD_ID, "petrified_pyramid_piece"), PetrifiedPyramidPieces.Piece::new);
        }
    }

    public static void registerStructures() {
        PETRIFIED_PYRAMID = RegistryHelper.createStructure("petrified_pyramid", PetrifiedPyramidStructure::new);
    }

    public static void setupStructures() {
        setup(PETRIFIED_PYRAMID.get(), new StructureSeparationSettings(27, 1, 122147056), true);
    }

    public static <F extends Structure<?>> void setup(F structure, StructureSeparationSettings structureSeparationSettings, boolean transformLand) {
        Structure.NAME_STRUCTURE_BIMAP.put(structure.getRegistryName().toString().toLowerCase(Locale.ROOT), structure);
        DimensionStructuresSettings.field_236191_b_ = ImmutableMap.<Structure<?>, StructureSeparationSettings>builder().putAll(DimensionStructuresSettings.field_236191_b_).put(structure, structureSeparationSettings).build();
        HALLOWS_STRUCTURES.put(structure, structureSeparationSettings);
        if (transformLand) Structure.field_236384_t_ = ImmutableList.<Structure<?>>builder().addAll(Structure.field_236384_t_).add(structure).build();
    }

    public static void onWorldLoad(final WorldEvent.Load event) {
        if (event.getWorld() instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator && serverWorld.getDimensionKey().equals(World.OVERWORLD)) return;

            Map<Structure<?>, StructureSeparationSettings> temp = new HashMap<>(serverWorld.getChunkProvider().generator.func_235957_b_().func_236195_a_());
            temp.put(PETRIFIED_PYRAMID.get(), DimensionStructuresSettings.field_236191_b_.get(PETRIFIED_PYRAMID.get()));
            serverWorld.getChunkProvider().generator.func_235957_b_().field_236193_d_ = temp;
        }
    }
}