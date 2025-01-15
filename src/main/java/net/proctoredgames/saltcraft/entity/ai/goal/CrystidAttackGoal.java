package net.proctoredgames.saltcraft.entity.ai.goal;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class CrystidAttackGoal extends MeleeAttackGoal {
    private final Crystid crystid;
    private int openMouthTicks;

    public CrystidAttackGoal(Crystid pCrystid, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pCrystid, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.crystid = pCrystid;
    }

    public void start() {
        super.start();
        this.openMouthTicks = 0;
    }

    public void stop() {
        super.stop();
        crystid.setAttacking(false);
        this.crystid.setAggressive(false);
    }

    public void tick() {
        super.tick();
        ++this.openMouthTicks;
        if (this.openMouthTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.crystid.setAggressive(true);
        }else if (this.openMouthTicks >= 0 && this.getTicksUntilNextAttack() > this.getAttackInterval() / 2) {
            crystid.setAttacking(true);
            if(this.crystid.getRandom().nextDouble()<=0.2){
                this.crystid.getTarget().addEffect(new MobEffectInstance(ModEffects.THIRST.get(), 150, 1));

            }
        } else {
            crystid.setAttacking(false);
            this.crystid.setAggressive(false);
        }

    }
}
