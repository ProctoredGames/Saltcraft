package net.proctoredgames.saltcraft.particle;

import com.mojang.serialization.Codec;
import net.minecraft.client.particle.Particle;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.item.ModFoods;
import net.proctoredgames.saltcraft.item.custom.SaltItem;

import java.util.function.Function;

public class ModParticles {
    public static final DeferredRegister<Particle> PARTICLES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES.getRegistryName(), Saltcraft.MOD_ID);
    public static final SimpleParticleType SALT_PARTICLE = register("salt_particle", false);

    private static SimpleParticleType register(String pKey, boolean pOverrideLimiter) {
        return (SimpleParticleType)Registry.register(BuiltInRegistries.PARTICLE_TYPE, pKey, new SimpleParticleType(pOverrideLimiter));
    }

    private static <T extends ParticleOptions> ParticleType<T> register(String pKey, boolean pOverrideLimiter, ParticleOptions.Deserializer<T> pDeserializer, final Function<ParticleType<T>, Codec<T>> pCodecFactory) {
        return (ParticleType)Registry.register(BuiltInRegistries.PARTICLE_TYPE, pKey, new ParticleType<T>(pOverrideLimiter, pDeserializer) {
            public Codec<T> codec() {
                return (Codec)pCodecFactory.apply(this);
            }
        });
    }
}
