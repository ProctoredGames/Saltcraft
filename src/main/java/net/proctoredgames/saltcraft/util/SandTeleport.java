package net.proctoredgames.saltcraft.util;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SandTeleport {

    // With the Mirage for example the player triggers the waiting mirage to attack by looking at it;
    // for the Staff Of The Desert the player triggers the waiting mob to be able to be teleported by looking at it
    public static boolean isEntityValidTarget(PlayerEntity triggeringEntity, Entity waitingEntity) {
        Vec3d vec3 = triggeringEntity.getRotationVec(1.0F).normalize();
        Vec3d vec31 = new Vec3d(waitingEntity.getX() - triggeringEntity.getX(), waitingEntity.getEyeY() - triggeringEntity.getEyeY(), waitingEntity.getZ() - triggeringEntity.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dotProduct(vec31);
        return d1 > 1.0 - 0.025 / d0 && triggeringEntity.canSee(waitingEntity);
    }

    public static void teleportTo(Entity entity, Entity castingEntity) {
        double selfX = castingEntity.getX();
        double selfY = castingEntity.getY();
        double selfZ = castingEntity.getZ();

        float yaw = castingEntity.getYaw();
        float pitch = castingEntity.getPitch();

        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);

        double dirX = -Math.sin(radYaw) * Math.cos(radPitch);
        double dirZ = Math.cos(radYaw) * Math.cos(radPitch);

        double moveDistance = 3;

        BlockPos spawnPos = determineGroundAdjustedPosition(new BlockPos((int) Math.round(selfX + dirX * moveDistance),
                (int) Math.round(selfY), (int) Math.round(selfZ + dirZ * moveDistance)), entity.getWorld());

        if (entity.getWorld().isClient) {
            spawnTeleportParticles(entity);
        }
        entity.setInvisible(true);

        entity.requestTeleport(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

        if (entity.getWorld().isClient) {
            spawnTeleportParticles(entity);
        }
        entity.setInvisible(false);
    }

    public static BlockPos determineGroundAdjustedPosition(BlockPos spawnPos, World world) {
        while (world.getBlockState(spawnPos.down()).isAir() && spawnPos.down().getY() > world.getBottomY()) {
            spawnPos = spawnPos.down();
        }

        while (!world.getBlockState(spawnPos).isAir() && spawnPos.getY() < world.getTopY()) {
            spawnPos = spawnPos.up();
        }
        return spawnPos;
    }

    private static void spawnTeleportParticles(Entity entity) {
        Random random = entity.getWorld().random;
        Vec3d position = entity.getPos();
        for (int i = 0; i < entity.getWidth() * entity.getHeight() * 10; i++) {
            double x = position.x + random.nextDouble() * entity.getWidth() - (entity.getWidth()) / 2;
            double y = position.y + entity.getHeight() * random.nextDouble();
            double z = position.z + random.nextDouble() * entity.getWidth() - (entity.getWidth()) / 2;

            entity.getWorld().addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, Blocks.SAND.getDefaultState()), x, y, z, 0, 0, 0);
        }
    }
}
