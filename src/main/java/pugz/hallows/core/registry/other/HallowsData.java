package pugz.hallows.core.registry.other;

import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.DataProcessors;
import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.TrackedData;
import com.minecraftabnormals.abnormals_core.common.world.storage.tracking.TrackedDataManager;
import net.minecraft.util.ResourceLocation;
import pugz.hallows.core.Hallows;

public class HallowsData {
    public static final TrackedData<Integer> PLAYER_CHARGE = TrackedData.Builder.create(DataProcessors.INT, () -> 0).enableSaving().build();

    public static void registerData() {
        TrackedDataManager.INSTANCE.registerData(new ResourceLocation(Hallows.MOD_ID, "player_charge"), PLAYER_CHARGE);
    }
}