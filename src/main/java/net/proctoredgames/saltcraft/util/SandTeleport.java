package net.proctoredgames.saltcraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class SandTeleport {

    public static boolean isEntityLookingAtMe(LivingEntity pEntity, LivingEntity pCastingEntity) {
        Vec3 vec3 = pEntity.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(pCastingEntity.getX() - pEntity.getX(), pCastingEntity.getEyeY() - pEntity.getEyeY(), pCastingEntity.getZ() - pEntity.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? pEntity.hasLineOfSight(this) : false;
    }

    public static boolean isEntityValidTarget(Entity pEntity, LivingEntity pCastingEntity) {
        Vec3 vec3 = pCastingEntity.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(pEntity.getX()-pCastingEntity.getX(), pEntity.getEyeY()-pCastingEntity.getEyeY(), pEntity.getZ()-pCastingEntity.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? pCastingEntity.hasLineOfSight(pEntity) : false;
    }

    public static void teleportTo(Entity pEntity, Entity pCastingEntity) {
        // Get the current position of the mob
        double selfX = pCastingEntity.getX();
        double selfY = pCastingEntity.getY();
        double selfZ = pCastingEntity.getZ();

        // Get the mob's yaw (horizontal rotation) and pitch (vertical rotation)
        float yaw = pCastingEntity.getYRot(); // This is the mob's horizontal rotation
        float pitch = pCastingEntity.getXRot(); // This is the mob's vertical rotation

        // Calculate the direction vector based on the mob's yaw and pitch
        double radYaw = Math.toRadians(yaw); // Convert yaw to radians
        double radPitch = Math.toRadians(pitch); // Convert pitch to radians

        // Use trigonometry to calculate the direction
        double dirX = -Math.sin(radYaw) * Math.cos(radPitch); // X direction
        double dirY = -Math.sin(radPitch); // Y direction (vertical)
        double dirZ = Math.cos(radYaw) * Math.cos(radPitch); // Z direction

        // Scale the direction to 5 blocks (this can be adjusted)
        double moveDistance = 5;
        double targetX = selfX + dirX * moveDistance;
        double targetY = selfY;
        double targetZ = selfZ + dirZ * moveDistance;

        // Start with the target position (adjust Y to ground level)
        BlockPos spawnPos = new BlockPos((int) targetX, (int) targetY, (int) targetZ);

        // Move the position down until we find a solid block (or hit the minimum build height)
        while (pEntity.level().getBlockState(spawnPos.below()).isAir() && spawnPos.below().getY() > pEntity.level().getMinBuildHeight()) {
            spawnPos = spawnPos.below();
        }

        // Move the position up until we find an air block or reach the maximum build height
        while (!pEntity.level().getBlockState(spawnPos).isAir() && spawnPos.getY() < pEntity.level().getMaxBuildHeight()) {
            spawnPos = spawnPos.above();
        }

        if (pEntity.level().isClientSide) {
            spawnTeleportParticles(pEntity);
        }
        pEntity.setInvisible(true);

        // Teleport the player to the new position
        pEntity.teleportTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
        if (pEntity.level().isClientSide) {
            spawnTeleportParticles(pEntity);
        }
        pEntity.setInvisible(false);
    }

    private static void spawnTeleportParticles(Entity pEntity) {
        RandomSource random = RandomSource.create();
        Vec3 position = pEntity.position();
        for(int i = 0; i<pEntity.getBbWidth()*pEntity.getBbHeight()*10; i++){
            double x = position.x+random.nextDouble()*pEntity.getBbWidth()-(pEntity.getBbWidth())/2;
            double y = position.y + pEntity.getBbHeight()*random.nextDouble();
            double z = position.z+random.nextDouble()*pEntity.getBbWidth()-(pEntity.getBbWidth())/2;

            pEntity.level().addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), x, y, z, 0, 0, 0); // x, y, z, velocity (dx, dy, dz)
        }

    }
}
