package net.proctoredgames.saltcraft.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.util.SandTeleport;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class StaffOfTheDesertItem extends Item {

    // Items are singletons shared by every player on both sides, so the current target
    // is stored per-stack (in custom data) rather than in a field
    private static final String TARGET_ID_TAG = "SaltcraftStaffTargetId";

    public StaffOfTheDesertItem(Settings settings) {
        super(settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 2.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .build();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return !miner.isCreative();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 60;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        Entity targetEntity = getTargetEntity(world, stack);
        removeTargetId(stack);
        if (user instanceof PlayerEntity && targetEntity != null) {
            int usedTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
            if (usedTicks >= 20) {
                if (targetEntity instanceof LivingEntity livingTarget) {
                    livingTarget.removeStatusEffect(StatusEffects.GLOWING);
                }
                SandTeleport.teleportTo(targetEntity, user);
            }
        }
        damageAndMaybeReplace(stack, user);
    }

    public Entity runThroughFindMob(PlayerEntity player) {
        double range = 50.0;

        List<Entity> entitiesInRange = player.getWorld().getEntitiesByClass(Entity.class, player.getBoundingBox().expand(range), entity ->
                entity != null && entity.isAlive() && entity != player);

        for (Entity entity : entitiesInRange) {
            if (SandTeleport.isEntityValidTarget(player, entity)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        Entity targetEntity = runThroughFindMob(player);
        if (targetEntity != null) {
            setTargetId(stack, targetEntity.getId());
            player.setCurrentHand(hand);
        }
        return TypedActionResult.success(stack, world.isClient);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        Entity targetEntity = getTargetEntity(world, stack);
        if (targetEntity instanceof LivingEntity livingTarget) {
            livingTarget.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 5, 0, false, false));
        }
        if (user instanceof PlayerEntity player) {
            if (runThroughFindMob(player) == null) {
                removeTargetId(stack);
                user.stopUsingItem();
            }
        }
    }

    @Nullable
    private Entity getTargetEntity(World world, ItemStack stack) {
        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent == null || !nbtComponent.getNbt().contains(TARGET_ID_TAG)) {
            return null;
        }
        Entity entity = world.getEntityById(nbtComponent.getNbt().getInt(TARGET_ID_TAG));
        return entity != null && entity.isAlive() ? entity : null;
    }

    private void setTargetId(ItemStack stack, int entityId) {
        NbtComponent nbtComponent = stack.getOrDefault(DataComponentTypes.CUSTOM_DATA, NbtComponent.DEFAULT);
        NbtCompound nbt = nbtComponent.copyNbt();
        nbt.putInt(TARGET_ID_TAG, entityId);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    private void removeTargetId(ItemStack stack) {
        NbtComponent nbtComponent = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (nbtComponent == null) {
            return;
        }
        NbtCompound nbt = nbtComponent.copyNbt();
        nbt.remove(TARGET_ID_TAG);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        damageAndMaybeReplace(stack, attacker);
    }

    private void damageAndMaybeReplace(ItemStack stack, LivingEntity entity) {
        int newDamage = stack.getDamage() + 1;
        if (newDamage >= stack.getMaxDamage()) {
            replaceWithUncharged(entity, stack);
        } else {
            stack.setDamage(newDamage);
        }
    }

    private void replaceWithUncharged(LivingEntity entity, ItemStack stack) {
        if (entity instanceof PlayerEntity player) {
            Hand hand = player.getOffHandStack() == stack ? Hand.OFF_HAND : Hand.MAIN_HAND;
            ItemStack newItem = new ItemStack(ModItems.UNCHARGED_STAFF_OF_THE_DESERT);
            newItem.applyComponentsFrom(stack.getComponents());
            player.setStackInHand(hand, newItem);
        }
    }

    @Override
    public int getEnchantability() {
        return 1;
    }
}
