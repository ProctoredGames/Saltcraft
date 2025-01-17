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
import net.proctoredgames.saltcraft.util.SandTeleport;
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

        double range = 50.0; // Adjust this value for your desired range

        // Get all entities within the range of the player
        java.util.List<Entity> entitiesInRange = player.level().getEntitiesOfClass(Entity.class, player.getBoundingBox().inflate(range), entity -> {
            return entity != null && entity.isAlive() && entity != player;
        });

        // Loop through all entities within the range
        for (Entity entity : entitiesInRange) {
            System.out.println(entity);
            if (SandTeleport.isEntityValidTarget(entity, player)) {
                SandTeleport.teleportTo(entity, player);
                break;
            }
        }
        return InteractionResultHolder.sidedSuccess(whatHandItem, pLevel.isClientSide());
    }
}
