package pugz.hallows.core.registry;

import com.minecraftabnormals.abnormals_core.core.util.registry.TileEntitySubRegistryHelper;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import pugz.hallows.common.tileentity.NecrofireCampfireTileEntity;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsTileEntities {
    public static final TileEntitySubRegistryHelper HELPER = Hallows.REGISTRY_HELPER.getTileEntitySubHelper();

    public static RegistryObject<TileEntityType<NecrofireCampfireTileEntity>> NECROFIRE_CAMPFIRE;

    public static void registerTileEntities() {
        NECROFIRE_CAMPFIRE = HELPER.createTileEntity("necrofire_campfire", NecrofireCampfireTileEntity::new, () -> new Block[]{HallowsBlocks.NECROFIRE_CAMPFIRE.get()});
    }
}