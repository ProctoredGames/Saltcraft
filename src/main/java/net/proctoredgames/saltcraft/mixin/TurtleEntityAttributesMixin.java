package net.proctoredgames.saltcraft.mixin;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.TurtleEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// Vanilla turtles have no ATTACK_DAMAGE attribute since they're normally passive; the
// mod adds a MeleeAttackGoal to fight jellyfish (see ModEvents), which needs one to exist.
@Mixin(TurtleEntity.class)
public abstract class TurtleEntityAttributesMixin {

    @Inject(method = "createTurtleAttributes", at = @At("RETURN"))
    private static void saltcraft$addAttackDamage(CallbackInfoReturnable<DefaultAttributeContainer.Builder> cir) {
        cir.getReturnValue().add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0);
    }
}
