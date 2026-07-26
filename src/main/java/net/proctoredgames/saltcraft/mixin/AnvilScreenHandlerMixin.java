package net.proctoredgames.saltcraft.mixin;

import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.item.ModItems;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

    @Shadow
    @Final
    private Property levelCost;

    @Unique
    private boolean saltcraft$maxedEnchantedBook;

    public AnvilScreenHandlerMixin(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
    }

    // Combining an enchanted book with a Salt Tome maxes out every enchantment on the book
    @Inject(method = "updateResult", at = @At("TAIL"))
    private void saltcraft$saltTomeMaxEnchants(CallbackInfo ci) {
        this.saltcraft$maxedEnchantedBook = false;

        ItemStack leftInput = this.input.getStack(0);
        ItemStack rightInput = this.input.getStack(1);

        if (leftInput.isOf(Items.ENCHANTED_BOOK) && rightInput.isOf(ModItems.SALT_TOME)) {
            ItemEnchantmentsComponent stored = EnchantmentHelper.getEnchantments(leftInput);
            if (stored.isEmpty()) {
                return;
            }

            ItemStack result = leftInput.copy();
            ItemEnchantmentsComponent.Builder builder = new ItemEnchantmentsComponent.Builder(ItemEnchantmentsComponent.DEFAULT);
            for (RegistryEntry<Enchantment> enchantment : stored.getEnchantments()) {
                builder.set(enchantment, enchantment.value().getMaxLevel());
            }
            EnchantmentHelper.set(result, builder.build());

            this.levelCost.set(5);
            this.output.setStack(0, result);
            this.saltcraft$maxedEnchantedBook = true;
            this.sendContentUpdates();
        }
    }

    @Inject(method = "onTakeOutput", at = @At("HEAD"))
    private void saltcraft$grantMaxEnchantAdvancement(PlayerEntity player, ItemStack stack, CallbackInfo ci) {
        if (this.saltcraft$maxedEnchantedBook && player instanceof ServerPlayerEntity serverPlayer) {
            AdvancementEntry advancement = serverPlayer.getServer().getAdvancementLoader()
                    .get(Identifier.of(Saltcraft.MOD_ID, "max_out_enchanted_book"));
            if (advancement != null) {
                serverPlayer.getAdvancementTracker().grantCriterion(advancement, "max_out_enchant");
            }
        }
    }
}
