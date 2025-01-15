package net.proctoredgames.saltcraft.entity.ai.goal;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.Mirage;

public class MirageNearestAttackableTargetGoal extends NearestAttackableTargetGoal {
    private final Mirage mirage;

    public MirageNearestAttackableTargetGoal(Mirage pMirage, Class pTargetType, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMirage, pTargetType, pFollowingTargetEvenIfNotSeen);
        this.mirage = pMirage;
    }

    @Override
    public boolean canUse() {
        if(mirage.isHunting()){
            if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
                return false;
            } else {
                this.findTarget();
                return this.target != null;
            }
        } else{
            return false;
        }
    }
}
