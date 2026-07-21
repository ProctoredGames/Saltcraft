package net.proctoredgames.saltcraft.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

public class SandTeleport {

    //with the Mirage for example the player triggers the waiting mirage to attack by looking at it
    //for the Staff Of The Desert the player triggers the waiting mob to be able to be teleported by looking at it
    public static boolean isEntityValidTarget(Player pTriggeringEntity, Entity pWaitingEntity) {
        Vec3 vec3 = pTriggeringEntity.getViewVector(1.0F).normalize();
        Vec3  vec31 = new Vec3(pWaitingEntity.getX() - pTriggeringEntity.getX(), pWaitingEntity.getEyeY() - pTriggeringEntity.getEyeY(), pWaitingEntity.getZ() - pTriggeringEntity.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? pTriggeringEntity.hasLineOfSight(pWaitingEntity) : false;
    }

    public static void teleportTo(Entity pEntity, Entity pCastingEntity) {
        double selfX = pCastingEntity.getX();
        double selfY = pCastingEntity.getY();
        double selfZ = pCastingEntity.getZ();

        float yaw = pCastingEntity.getYRot();
        float pitch = pCastingEntity.getXRot();

        double radYaw = Math.toRadians(yaw);
        double radPitch = Math.toRadians(pitch);

        double dirX = -Math.sin(radYaw) * Math.cos(radPitch);
        double dirY = -Math.sin(radPitch);
        double dirZ = Math.cos(radYaw) * Math.cos(radPitch);

        double moveDistance = 3;

        BlockPos spawnPos = determineGroundAdjustedPosition(new BlockPos((int)Math.round(selfX + dirX * moveDistance),
                (int)Math.round(selfY), (int)Math.round(selfZ + dirZ * moveDistance)), pEntity.level());

        if (pEntity.level().isClientSide) {
            spawnTeleportParticles(pEntity);
        }
        pEntity.setInvisible(true);

        pEntity.teleportTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());

        if (pEntity.level().isClientSide) {
            spawnTeleportParticles(pEntity);
        }
        pEntity.setInvisible(false);
    }

    public static BlockPos determineGroundAdjustedPosition(BlockPos pSpawnPos, Level pLevel){

        while (pLevel.getBlockState(pSpawnPos.below()).isAir() && pSpawnPos.below().getY() > pLevel.getMinBuildHeight()) {
            pSpawnPos = pSpawnPos.below();
        }

        while (!pLevel.getBlockState(pSpawnPos).isAir() && pSpawnPos.getY() < pLevel.getMaxBuildHeight()) {
            pSpawnPos = pSpawnPos.above();
        }
        return pSpawnPos;
    }

    private static void spawnTeleportParticles(Entity pEntity) {
        RandomSource random = pEntity.level().random;
        Vec3 position = pEntity.position();
        for(int i = 0; i<pEntity.getBbWidth()*pEntity.getBbHeight()*10; i++){
            double x = position.x+random.nextDouble()*pEntity.getBbWidth()-(pEntity.getBbWidth())/2;
            double y = position.y + pEntity.getBbHeight()*random.nextDouble();
            double z = position.z+random.nextDouble()*pEntity.getBbWidth()-(pEntity.getBbWidth())/2;

            pEntity.level().addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), x, y, z, 0, 0, 0); // x, y, z, velocity (dx, dy, dz)
        }

    }
}
