package pugz.hallows.core;

import com.google.common.collect.ImmutableMap;
import com.minecraftabnormals.abnormals_core.core.util.registry.RegistryHelper;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import pugz.hallows.client.gui.AnointingTableScreen;
import pugz.hallows.core.registry.*;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsData;
import pugz.hallows.core.registry.other.HallowsRecipes;
import pugz.hallows.core.util.Events;

import java.util.HashMap;
import java.util.Map;

@Mod(Hallows.MOD_ID)
public class Hallows {
    public static final String MOD_ID = "hallows";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public Hallows() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        REGISTRY_HELPER.register(eventBus);
        HallowsContainers.CONTAINERS.register(eventBus);
        HallowsRecipes.RECIPE_SERIALIZERS.register(eventBus);
        HallowsBiomes.BIOMES.register(eventBus);
        HallowsSurfaceBuilders.SURFACE_BUILDERS.register(eventBus);
        HallowsCarvers.CARVERS.register(eventBus);
        HallowsFeatures.FEATURES.register(eventBus);
        HallowsStructures.STRUCTURE_FEATURES.register(eventBus);
        HallowsFeatures.TREE_DECORATORS.register(eventBus);
        HallowsDimensions.POINTS_OF_INTEREST.register(eventBus);
        HallowsParticles.PARTICLES.register(eventBus);

        HallowsBlocks.registerBlocks();
        HallowsTileEntities.registerTileEntities();
        HallowsContainers.registerContainers();
        HallowsRecipes.registerRecipeSerializers();
        HallowsEntities.registerEntities();
        HallowsItems.registerItems();
        HallowsBiomes.registerBiomes();
        HallowsSurfaceBuilders.registerSurfaceBuilders();
        HallowsCarvers.registerCarvers();
        HallowsFeatures.registerFeatures();
        HallowsStructures.registerStructures();
        HallowsFeatures.registerDecorators();
        HallowsDimensions.registerPOIs();
        HallowsParticles.registerParticles();

        MinecraftForge.EVENT_BUS.addListener(HallowsBiomes::onBiomeLoading);
        MinecraftForge.EVENT_BUS.addListener(HallowsStructures::onWorldLoad);
        MinecraftForge.EVENT_BUS.addListener(Events.Teleport::onProjectileImpact);
        MinecraftForge.EVENT_BUS.addListener(Events.Charge::onLivingHurt);
        MinecraftForge.EVENT_BUS.addListener(Events.Charge::onBlockBreak);

        eventBus.addListener(EventPriority.NORMAL, this::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(EventPriority.LOWEST, this::clientSetup);
            eventBus.addListener(EventPriority.LOWEST, HallowsParticles::onParticleFactoryRegister);
        });
    }

    public void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            //PointOfInterestType.registerBlockStates(HallowsDimensions.PORTAL.get());
            //PointOfInterestType.BLOCKS_OF_INTEREST.addAll(HallowsDimensions.PORTAL.get().blockStates);

            HallowsDimensions.registerDimensions();
            HallowsFeatures.registerConfiguredFeatures();
            HallowsStructures.setupStructures();
            HallowsStructures.Configured.registerConfiguredStructures();
            HallowsStructures.Pieces.registerPieces();
            HallowsBlocks.registerFlammability();
            HallowsBlocks.registerCompostables();
            HallowsEntities.registerAttributes();
            HallowsEntities.registerSpawns();
            HallowsData.registerData();

            WorldGenRegistries.NOISE_SETTINGS.getEntries().forEach(settings -> {
                Map<Structure<?>, StructureSeparationSettings> structureMap = settings.getValue().getStructures().func_236195_a_();
                if (structureMap instanceof ImmutableMap) {
                    Map<Structure<?>, StructureSeparationSettings> temp = new HashMap<>(structureMap);
                    temp.putAll(HallowsStructures.HALLOWS_STRUCTURES);
                    settings.getValue().getStructures().field_236193_d_ = temp;
                }
                else structureMap.putAll(HallowsStructures.HALLOWS_STRUCTURES);
            });
        });
    }

    @OnlyIn(Dist.CLIENT)
    public void clientSetup(final FMLClientSetupEvent event) {
        HallowsBlocks.registerRenderLayers();
        HallowsEntities.registerRenderers();
        HallowsDimensions.registerEffects();
        MinecraftForge.EVENT_BUS.addListener(Events.Charge::onItemTooltip);
        ScreenManager.registerFactory(HallowsContainers.ANOINTING.get(), AnointingTableScreen::new);
    }
}