//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.proctoredgames.saltcraft.item.custom;

import java.util.List;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult.Type;
import net.minecraft.world.phys.Vec3;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;
import net.proctoredgames.saltcraft.item.ModItems;

public class FilledOilCanItem extends Item {
    public FilledOilCanItem(Item.Properties pProperties) {
        super(pProperties);
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack whatHandItem = pPlayer.getItemInHand(pHand);

        List<IronGolem> ironGolemExists = pLevel.getEntitiesOfClass(IronGolem.class, pPlayer.getBoundingBox().inflate(2.0), (p_289499_) -> {
            return p_289499_ != null && p_289499_.isAlive();
        });
        List<AbstractMinecart> minecartExists = pLevel.getEntitiesOfClass(AbstractMinecart.class, pPlayer.getBoundingBox().inflate(2.0), (p_289499_) -> {
            return p_289499_ != null && p_289499_.isAlive();
        });

        if (!ironGolemExists.isEmpty()) {
            IronGolem whatIronGolem = (IronGolem) ironGolemExists.get(0);
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.NEUTRAL, 1.0F, 1.0F);
            pLevel.gameEvent(pPlayer, GameEvent.ENTITY_INTERACT, pPlayer.position());
            if (pPlayer instanceof ServerPlayer) {
                ServerPlayer $$6 = (ServerPlayer) pPlayer;
                CriteriaTriggers.PLAYER_INTERACTED_WITH_ENTITY.trigger($$6, whatHandItem, whatIronGolem);
                whatIronGolem.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 120, 2));
//                triggerAdvancement((ServerPlayer) pPlayer, new ResourceLocation("saltcraft", "oil_iron_golem"));
            }

            return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(whatHandItem, pPlayer, new ItemStack(ModItems.OIL_CAN.get())), pLevel.isClientSide());

        } else if (!minecartExists.isEmpty()) {
            AbstractMinecart whatMinecart = (AbstractMinecart) minecartExists.get(0);
            pLevel.playSound((Player) null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.BOTTLE_EMPTY, SoundSource.NEUTRAL, 1.0F, 1.0F);
            pLevel.gameEvent(pPlayer, GameEvent.ENTITY_INTERACT, pPlayer.position());
            if (pPlayer instanceof ServerPlayer) {
                ServerPlayer $$6 = (ServerPlayer) pPlayer;
                CriteriaTriggers.PLAYER_INTERACTED_WITH_ENTITY.trigger($$6, whatHandItem, whatMinecart);
                //won't actually work because the function is capped at unchangeable max speed... (1.2F)
//                whatMinecart.setCurrentCartSpeedCapOnRail(500F);
                // Increase the speed of the minecart
                double speedMultiplier = 1.5;  // Adjust this multiplier for desired speed increase
                Vec3 currentVelocity = whatMinecart.getDeltaMovement();
                Vec3 newVelocity = currentVelocity.multiply(speedMultiplier, 1, speedMultiplier);
                whatMinecart.setDeltaMovement(newVelocity);
            }

            return InteractionResultHolder.sidedSuccess(this.turnBottleIntoItem(whatHandItem, pPlayer, new ItemStack(ModItems.OIL_CAN.get())), pLevel.isClientSide());

        } else{
            return InteractionResultHolder.pass(whatHandItem);
        }
    }

    protected ItemStack turnBottleIntoItem(ItemStack pBottleStack, Player pPlayer, ItemStack pFilledBottleStack) {
        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        return ItemUtils.createFilledResult(pBottleStack, pPlayer, pFilledBottleStack);
    }

    public static void triggerAdvancement(ServerPlayer player, ResourceLocation advancement) {
        Advancement adv = player.getServer().getAdvancements().getAdvancement(advancement);
        if (adv != null) {
            AdvancementProgress progress = player.getAdvancements().getOrStartProgress(adv);
            if (!progress.isDone()) {
                progress.grantProgress("oil");
            }
        }
    }
}
