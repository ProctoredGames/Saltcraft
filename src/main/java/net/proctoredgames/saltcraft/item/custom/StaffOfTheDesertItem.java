package net.proctoredgames.saltcraft.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.AttackDamageMobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.util.SandTeleport;
import org.joml.RayAabIntersection;

import java.awt.*;


public class StaffOfTheDesertItem extends Item implements Vanishable {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    Entity targetEntity;

    public StaffOfTheDesertItem(Properties pProperties) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> $$1 = ImmutableMultimap.builder();
        $$1.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Tool modifier", 2.0, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = $$1.build();
    }

    public boolean canAttackBlock(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer) {
        return !pPlayer.isCreative();
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BOW;
    }

    public int getUseDuration(ItemStack pStack) {
        return 60;
    }

    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pEntityLiving instanceof Player $$4 && targetEntity != null) {
            int $$5 = this.getUseDuration(pStack) - pTimeLeft;
            if ($$5 >=20) {
                if(targetEntity instanceof LivingEntity){
                    ((LivingEntity) targetEntity).removeEffect(MobEffects.GLOWING);
                }
                SandTeleport.teleportTo(targetEntity, pEntityLiving);
                targetEntity = null;
            }
        }
        if (pStack.hurt(1, pEntityLiving.getRandom(), pEntityLiving instanceof ServerPlayer ? (ServerPlayer) pEntityLiving : null)) {
            replaceWithUncharged(pEntityLiving, pStack);
        }
    }

    public Entity runThroughFindMob(Player pPlayer){

        double range = 50.0; // Adjust this value for your desired range

        // Get all entities within the range of the player
        java.util.List<Entity> entitiesInRange = pPlayer.level().getEntitiesOfClass(Entity.class, pPlayer.getBoundingBox().inflate(range), entity -> {
            return entity != null && entity.isAlive() && entity != pPlayer;
        });

        // Loop through all entities within the range
        for (Entity entity : entitiesInRange) {
            if (SandTeleport.isEntityValidTarget(pPlayer, entity)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack $$3 = pPlayer.getItemInHand(pHand);
        targetEntity = runThroughFindMob(pPlayer);
        if(targetEntity != null){
            pPlayer.startUsingItem(pHand);
        }
        return InteractionResultHolder.sidedSuccess($$3, pLevel.isClientSide());
    }

    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        if(targetEntity instanceof LivingEntity){
            ((LivingEntity) targetEntity).addEffect(new MobEffectInstance(MobEffects.GLOWING, 5, 0, false, false));
        }
        if(pLivingEntity instanceof Player){
            if(runThroughFindMob((Player) pLivingEntity) == null){
                targetEntity = null;
                pLivingEntity.stopUsingItem();
            };
        }
    }

    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        if (pStack.hurt(1, pAttacker.getRandom(), pAttacker instanceof ServerPlayer ? (ServerPlayer) pAttacker : null)) {
            replaceWithUncharged(pAttacker, pStack);
        }
        return true;
    }

    private void replaceWithUncharged(LivingEntity entity, ItemStack stack) {
        if (entity instanceof Player player) {
            ItemStack newItem = new ItemStack(ModItems.UNCHARGED_STAFF_OF_THE_DESERT.get());
            newItem.setTag(stack.getTag()); // Preserve NBT data if needed
            player.setItemInHand(InteractionHand.MAIN_HAND, newItem);
        }
    }

    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return pEquipmentSlot == EquipmentSlot.MAINHAND ? this.defaultModifiers : super.getDefaultAttributeModifiers(pEquipmentSlot);
    }

    public int getEnchantmentValue() {
        return 1;
    }
}
