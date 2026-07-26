package net.proctoredgames.saltcraft.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncPayload;
import net.proctoredgames.saltcraft.thirst.PlayerThirst;
import net.proctoredgames.saltcraft.util.ModTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow
    protected ItemStack activeItemStack;

    @Inject(method = "consumeItem", at = @At("HEAD"))
    private void saltcraft$onConsumeItem(CallbackInfo ci) {
        if ((Object) this instanceof PlayerEntity player) {
            ItemStack usedItem = this.activeItemStack;

            int thirstPointValue = usedItem.isIn(ModTags.Items.QUENCHES_THIRST_3_POINTS) ? 3
                    : (usedItem.isIn(ModTags.Items.QUENCHES_THIRST_5_POINTS) ? 5 : 0);

            if (usedItem.get(net.minecraft.component.DataComponentTypes.FOOD) != null && thirstPointValue > 0) {
                PlayerThirst thirst = player.getAttachedOrCreate(PlayerThirst.THIRST);
                thirst.addThirst(thirstPointValue);
                if (player instanceof ServerPlayerEntity serverPlayer) {
                    ModMessages.sendToPlayer(new ThirstDataSyncPayload(thirst.getThirst()), serverPlayer);
                }
            }
        }
    }
}
