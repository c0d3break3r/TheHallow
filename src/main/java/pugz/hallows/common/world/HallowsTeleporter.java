package pugz.hallows.common.world;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class HallowsTeleporter implements ITeleporter {
    private final BlockPos pos;

    public HallowsTeleporter(BlockPos pos) {
        this.pos = pos;
    }

    @Override
    public Entity placeEntity(Entity entity, ServerWorld currentWorld, ServerWorld destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
        Entity e = repositionEntity.apply(false);
        if (!(e instanceof ServerPlayerEntity)) return e;

        ServerPlayerEntity player = (ServerPlayerEntity) e;
        BlockPos teleporterPos = this.pos;

        if (teleporterPos == null) return e;
        player.giveExperienceLevels(0);
        player.setPos(teleporterPos.getX() + 0.5D, teleporterPos.getY() + 1D, teleporterPos.getZ() + 0.5D);
        return e;
    }
}