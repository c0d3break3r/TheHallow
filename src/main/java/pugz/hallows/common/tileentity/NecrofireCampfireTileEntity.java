package pugz.hallows.common.tileentity;

import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntityType;
import pugz.hallows.core.registry.HallowsTileEntities;

import javax.annotation.Nonnull;

public class NecrofireCampfireTileEntity extends CampfireTileEntity {
    public NecrofireCampfireTileEntity() {
        super();
    }

    @Nonnull
    public TileEntityType<?> getType() {
        return HallowsTileEntities.NECROFIRE_CAMPFIRE.get();
    }
}