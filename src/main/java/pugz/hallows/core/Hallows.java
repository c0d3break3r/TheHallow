package pugz.hallows.core;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.village.PointOfInterestType;
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
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.client.gui.AnointingTableScreen;
import pugz.hallows.common.block.NecrofireBlock;
import pugz.hallows.core.registry.*;
import pugz.hallows.core.registry.other.HallowsContainers;
import pugz.hallows.core.registry.other.HallowsRecipes;
import pugz.hallows.core.util.Events;

import java.util.HashMap;
import java.util.Map;

@Mod(Hallows.MOD_ID)
public class Hallows {
    public static final String MOD_ID = "hallows";

    public Hallows() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        HallowsBlocks.BLOCKS.register(eventBus);
        HallowsItems.ITEMS.register(eventBus);
        HallowsTileEntities.TILE_ENTITIES.register(eventBus);
        HallowsContainers.CONTAINERS.register(eventBus);
        HallowsRecipes.RECIPE_SERIALIZERS.register(eventBus);
        HallowsEntities.ENTITIES.register(eventBus);
        HallowsBiomes.BIOMES.register(eventBus);
        HallowsSurfaceBuilders.SURFACE_BUILDERS.register(eventBus);
        HallowsCarvers.CARVERS.register(eventBus);
        HallowsFeatures.FEATURES.register(eventBus);
        HallowsStructures.STRUCTURE_FEATURES.register(eventBus);
        HallowsFeatures.TREE_DECORATORS.register(eventBus);
        HallowsDimensions.POINTS_OF_INTEREST.register(eventBus);
        HallowsParticles.PARTICLES.register(eventBus);

        HallowsBlocks.registerBlocks();
        HallowsItems.registerItems();
        HallowsTileEntities.registerTileEntities();
        HallowsContainers.registerContainers();
        HallowsRecipes.registerRecipeSerializers();
        HallowsEntities.registerEntities();
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
        MinecraftForge.EVENT_BUS.addListener(NecrofireBlock::onRightClickBlock);
        MinecraftForge.EVENT_BUS.addListener(Events.Teleport::onProjectileImpact);
        MinecraftForge.EVENT_BUS.addListener(Events.Charge::onLivingHurt);
        //MinecraftForge.EVENT_BUS.addListener(HallowsEntities::onEntityAttributeCreation);

        eventBus.addListener(EventPriority.NORMAL, this::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(EventPriority.LOWEST, this::clientSetup);
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
            HallowsEntities.registerEntityAttributes();

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
        MinecraftForge.EVENT_BUS.addListener(HallowsParticles::onParticleFactoryRegister);

        ScreenManager.registerFactory(HallowsContainers.ANOINTING.get(), AnointingTableScreen::new);
    }
}