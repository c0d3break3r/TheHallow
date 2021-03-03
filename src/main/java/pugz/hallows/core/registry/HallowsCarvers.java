package pugz.hallows.core.registry;

import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.world.carver.HallowsCaveCarver;
import pugz.hallows.common.world.carver.NecromantleCrackCarver;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsCarvers {
    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, Hallows.MOD_ID);

    public static RegistryObject<WorldCarver<ProbabilityConfig>> HALLOWS_CAVE;
    public static RegistryObject<WorldCarver<ProbabilityConfig>> NECROMANTLE_CRACK;

    public static void registerCarvers() {
        HALLOWS_CAVE = RegistryHelper.createCarver("hallows_cave", () -> new HallowsCaveCarver(ProbabilityConfig.CODEC, 128));
        NECROMANTLE_CRACK = RegistryHelper.createCarver("necromantle_crack", () -> new NecromantleCrackCarver(ProbabilityConfig.CODEC));
    }

    public static class Configured {
        public static ConfiguredCarver<ProbabilityConfig> HALLOWS_CAVE = RegistryHelper.createConfiguredCarver("hallows_cave", new ConfiguredCarver<ProbabilityConfig>(HallowsCarvers.HALLOWS_CAVE.get(), new ProbabilityConfig(0.05555554F)));
        public static ConfiguredCarver<ProbabilityConfig> NECROMANTLE_CRACK = RegistryHelper.createConfiguredCarver("necromantle_crack", new ConfiguredCarver<ProbabilityConfig>(HallowsCarvers.NECROMANTLE_CRACK.get(), new ProbabilityConfig(0.04444445F)));
    }
}