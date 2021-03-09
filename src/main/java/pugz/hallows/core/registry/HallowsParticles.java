package pugz.hallows.core.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.client.particle.WillOWispParticle;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Hallows.MOD_ID);

    public static RegistryObject<BasicParticleType> NECROFLAME;
    public static RegistryObject<BasicParticleType> ORANGE_WILL_O_WISP;
    public static RegistryObject<BasicParticleType> YELLOW_WILL_O_WISP;
    public static RegistryObject<BasicParticleType> BLUE_WILL_O_WISP;
    public static RegistryObject<BasicParticleType> PURPLE_WILL_O_WISP;

    public static void registerParticles() {
        NECROFLAME = RegistryHelper.createParticle("necroflame", () -> new BasicParticleType(false));
        ORANGE_WILL_O_WISP = RegistryHelper.createParticle("orange_will_o_wisp", () -> new BasicParticleType(false));
        YELLOW_WILL_O_WISP = RegistryHelper.createParticle("yellow_will_o_wisp", () -> new BasicParticleType(false));
        BLUE_WILL_O_WISP = RegistryHelper.createParticle("blue_will_o_wisp", () -> new BasicParticleType(false));
        PURPLE_WILL_O_WISP = RegistryHelper.createParticle("purple_will_o_wisp", () -> new BasicParticleType(false));
    }

    public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
        if (NECROFLAME.isPresent()) Minecraft.getInstance().particles.registerFactory(NECROFLAME.get(), FlameParticle.Factory::new);
        if (ORANGE_WILL_O_WISP.isPresent()) Minecraft.getInstance().particles.registerFactory(ORANGE_WILL_O_WISP.get(), WillOWispParticle.Factory::new);
        if (YELLOW_WILL_O_WISP.isPresent()) Minecraft.getInstance().particles.registerFactory(YELLOW_WILL_O_WISP.get(), WillOWispParticle.Factory::new);
        if (BLUE_WILL_O_WISP.isPresent()) Minecraft.getInstance().particles.registerFactory(BLUE_WILL_O_WISP.get(), WillOWispParticle.Factory::new);
        if (PURPLE_WILL_O_WISP.isPresent()) Minecraft.getInstance().particles.registerFactory(PURPLE_WILL_O_WISP.get(), WillOWispParticle.Factory::new);
    }
}