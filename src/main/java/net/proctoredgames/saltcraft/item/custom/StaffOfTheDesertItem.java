package net.proctoredgames.saltcraft.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import org.joml.RayAabIntersection;

import java.awt.*;

public class StaffOfTheDesertItem extends Item {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public StaffOfTheDesertItem(Properties pProperties) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> $$1 = ImmutableMultimap.builder();
        $$1.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 2.0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = $$1.build();
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        Player player = pPlayer;
        Level level = pLevel;
        ItemStack whatHandItem = pPlayer.getItemInHand(pHand);
        if (player == null || pLevel.isClientSide()) {
            return InteractionResultHolder.pass(whatHandItem);
        }

        double range = 50.0; // Adjust this value for your desired range

        // Get all entities within the range of the player
        java.util.List<Entity> entitiesInRange = player.level().getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(range), entity -> {
            return entity != null && entity.isAlive() && entity != player;
        });

        // Loop through all entities within the range
        for (Entity entity : entitiesInRange) {
            System.out.println(entity);
            if (isEntityValidTarget(entity, player)) {
                spawnTeleportParticles(entity);
                teleportTo(entity, player);
                System.out.println("teleported" + entity);
                spawnTeleportParticles(entity);
                break;
            }
        }
        return InteractionResultHolder.sidedSuccess(whatHandItem, pLevel.isClientSide());
    }

    boolean isEntityValidTarget(Entity pEntity, LivingEntity pCastingEntity) {
        Vec3 vec3 = pCastingEntity.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(pEntity.getX()-pCastingEntity.getX(), pEntity.getEyeY()-pCastingEntity.getEyeY(), pEntity.getZ()-pCastingEntity.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0 - 0.025 / d0 ? pCastingEntity.hasLineOfSight(pEntity) : false;
    }

    public void teleportTo(Entity pEntity, Entity pCastingEntity) {
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
        double moveDistance = 3;
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

        spawnTeleportParticles(pEntity);

        // Teleport the player to the new position
        pEntity.teleportTo(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
        spawnTeleportParticles(pEntity);
    }

    private void spawnTeleportParticles(Entity pEntity) {
        RandomSource random = RandomSource.create();
        Vec3 position = pEntity.position();
        for(int i = 0; i<20; i++){
            double x = position.x+random.nextDouble()-0.5;
            double y = position.y + pEntity.getBbHeight()*random.nextDouble();
            double z = position.z+random.nextDouble()-0.5;

            pEntity.level().addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, Blocks.SAND.defaultBlockState()), x, y, z, 0, 0, 0); // x, y, z, velocity (dx, dy, dz)
        }

    }
}
