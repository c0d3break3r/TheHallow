package pugz.hallows.core;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import pugz.hallows.client.gui.AnointingTableScreen;
import pugz.hallows.common.block.NecrofireBlock;
import pugz.hallows.core.registry.*;
import pugz.hallows.core.registry.other.HallowsContainers;
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
        HallowsBiomes.BIOMES.register(eventBus);
        HallowsSurfaceBuilders.SURFACE_BUILDERS.register(eventBus);
        HallowsCarvers.CARVERS.register(eventBus);
        HallowsFeatures.FEATURES.register(eventBus);
        HallowsStructures.STRUCTURE_FEATURES.register(eventBus);
        HallowsFeatures.TREE_DECORATORS.register(eventBus);

        HallowsBlocks.registerBlocks();
        HallowsItems.registerItems();
        HallowsTileEntities.registerTileEntities();
        HallowsContainers.registerContainers();
        HallowsBiomes.registerBiomes();
        HallowsSurfaceBuilders.registerSurfaceBuilders();
        HallowsCarvers.registerCarvers();
        HallowsFeatures.registerFeatures();
        HallowsStructures.registerStructures();
        HallowsFeatures.registerDecorators();

        MinecraftForge.EVENT_BUS.addListener(HallowsBiomes::onBiomeLoading);
        MinecraftForge.EVENT_BUS.addListener(HallowsStructures::onWorldLoad);
        MinecraftForge.EVENT_BUS.addListener(NecrofireBlock::onRightClickBlock);
        MinecraftForge.EVENT_BUS.addListener(Events.Charge::onItemTooltip);
        MinecraftForge.EVENT_BUS.addListener(Events.Charge::onLivingHurt);

        eventBus.addListener(EventPriority.NORMAL, this::commonSetup);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            eventBus.addListener(EventPriority.LOWEST, this::clientSetup);
        });
    }

    public void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            HallowsDimensions.registerDimensions();
            HallowsFeatures.registerConfiguredFeatures();
            HallowsStructures.setupStructures();
            HallowsStructures.registerConfiguredStructures();
            HallowsBlocks.registerFlammability();
            HallowsBlocks.registerCompostables();

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

    public void clientSetup(FMLClientSetupEvent event) {
        HallowsBlocks.registerRenderLayers();
        HallowsDimensions.registerEffects();

        ScreenManager.registerFactory(HallowsContainers.ANOINTING.get(), AnointingTableScreen::new);
    }
}