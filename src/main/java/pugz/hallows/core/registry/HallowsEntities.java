package pugz.hallows.core.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.client.render.GhostRenderer;
import pugz.hallows.client.render.HauntRenderer;
import pugz.hallows.common.entity.GhostEntity;
import pugz.hallows.common.entity.HauntEntity;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Hallows.MOD_ID);

    public static RegistryObject<EntityType<GhostEntity>> GHOST;
    public static RegistryObject<EntityType<HauntEntity>> HAUNT;

    public static void registerEntities() {
        GHOST = RegistryHelper.createEntity("ghost", () -> EntityType.Builder.<GhostEntity>create(
                GhostEntity::new, EntityClassification.MONSTER).size(0.5F, 1.3F)
                .setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)
                .build("ghost"));

        HAUNT = RegistryHelper.createEntity("haunt", () -> EntityType.Builder.<HauntEntity>create(
                HauntEntity::new, EntityClassification.MONSTER).size(1.1F, 2.2F)
                .setTrackingRange(64).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3)
                .build("haunt"));
    }

    public static void registerEntityAttributes() {
        GlobalEntityTypeAttributes.put(GHOST.get(), GhostEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(HAUNT.get(), HauntEntity.registerAttributes().create());
    }

    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(GHOST.get(), GhostRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(HAUNT.get(), HauntRenderer::new);
    }
}