package pugz.hallows.core.registry.other;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.tileentity.container.AnointingTableContainer;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryUtil;

public class HallowsContainers {
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, Hallows.MOD_ID);

    public static RegistryObject<ContainerType<AnointingTableContainer>> ANOINTING;

    public static void registerContainers() {
        ANOINTING = RegistryUtil.createContainer("anointing", AnointingTableContainer::new);
    }
}