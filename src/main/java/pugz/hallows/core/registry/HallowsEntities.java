package pugz.hallows.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.EntitySubRegistryHelper;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import pugz.hallows.client.render.GhostRenderer;
import pugz.hallows.client.render.HauntRenderer;
import pugz.hallows.common.entity.GhostEntity;
import pugz.hallows.common.entity.HauntEntity;
import pugz.hallows.core.Hallows;

public class HallowsEntities {
    public static final EntitySubRegistryHelper HELPER = Hallows.REGISTRY_HELPER.getEntitySubHelper();

    public static RegistryObject<EntityType<GhostEntity>> GHOST;
    public static RegistryObject<EntityType<HauntEntity>> HAUNT;

    public static void registerEntities() {
        GHOST = HELPER.createLivingEntity("ghost", GhostEntity::new, EntityClassification.MONSTER, 0.5F, 1.3F);
        HAUNT = HELPER.createLivingEntity("haunt", HauntEntity::new, EntityClassification.MONSTER, 1.1F, 2.2F);
    }

    public static void registerAttributes() {
        GlobalEntityTypeAttributes.put(GHOST.get(), GhostEntity.registerAttributes().create());
        GlobalEntityTypeAttributes.put(HAUNT.get(), HauntEntity.registerAttributes().create());
    }

    public static void registerSpawns() {
        EntitySpawnPlacementRegistry.register(GHOST.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GhostEntity::canGhostSpawn);
        EntitySpawnPlacementRegistry.register(HAUNT.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, MonsterEntity::canMonsterSpawn);
    }


    @OnlyIn(Dist.CLIENT)
    public static void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(GHOST.get(), GhostRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(HAUNT.get(), HauntRenderer::new);
    }
}