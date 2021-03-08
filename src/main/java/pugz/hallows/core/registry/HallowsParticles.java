package pugz.hallows.core.registry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Hallows.MOD_ID);

    public static RegistryObject<BasicParticleType> NECROFLAME;

    public static void registerParticles() {
        NECROFLAME = RegistryHelper.createParticle("necroflame", () -> new BasicParticleType(false));
    }

    public static void onParticleFactoryRegister(ParticleFactoryRegisterEvent event) {
        if (NECROFLAME.isPresent()) {
            Minecraft.getInstance().particles.registerFactory(NECROFLAME.get(), FlameParticle.Factory::new);
        }
    }
}