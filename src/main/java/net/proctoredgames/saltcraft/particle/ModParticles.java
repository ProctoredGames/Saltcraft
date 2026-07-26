package net.proctoredgames.saltcraft.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModParticles {
    public static final SimpleParticleType SALT_PARTICLE = Registry.register(Registries.PARTICLE_TYPE,
            Identifier.of(Saltcraft.MOD_ID, "salt_particle"), FabricParticleTypes.simple());

    public static void register() {
    }
}
