package pugz.hallows.core.registry;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.tileentity.NecrofireCampfireTileEntity;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, Hallows.MOD_ID);

    public static RegistryObject<TileEntityType<NecrofireCampfireTileEntity>> NECROFIRE_CAMPFIRE;

    public static void registerTileEntities() {
        NECROFIRE_CAMPFIRE = RegistryHelper.createTileEntity("necrofire_campfire", NecrofireCampfireTileEntity::new, () -> new Block[]{HallowsBlocks.NECROFIRE_CAMPFIRE.get()});
    }
}