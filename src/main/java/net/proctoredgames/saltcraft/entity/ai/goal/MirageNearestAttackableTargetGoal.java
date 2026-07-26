package net.proctoredgames.saltcraft.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.proctoredgames.saltcraft.entity.custom.Mirage;

public class MirageNearestAttackableTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
    private final Mirage mirage;

    public MirageNearestAttackableTargetGoal(Mirage mirage, Class<T> targetClass, boolean checkVisibility) {
        super(mirage, targetClass, checkVisibility);
        this.mirage = mirage;
    }

    @Override
    public boolean canStart() {
        if (mirage.isHunting()) {
            if (this.reciprocalChance > 0 && this.mob.getRandom().nextInt(this.reciprocalChance) != 0) {
                return false;
            } else {
                this.findClosestTarget();
                return this.targetEntity != null;
            }
        } else {
            return false;
        }
    }
}
