package net.proctoredgames.saltcraft.entity.ai.goal;

import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.custom.Crystid;

public class CrystidAttackGoal extends MeleeAttackGoal {
    private final Crystid crystid;
    private int openMouthTicks;

    public CrystidAttackGoal(Crystid crystid, double speed, boolean pauseWhenMobIdle) {
        super(crystid, speed, pauseWhenMobIdle);
        this.crystid = crystid;
    }

    @Override
    public void start() {
        super.start();
        this.openMouthTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        crystid.setAttackWindup(false);
        this.crystid.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.openMouthTicks;
        if (this.openMouthTicks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.crystid.setAttacking(true);
        } else if (this.openMouthTicks >= 0 && this.getCooldown() > this.getMaxCooldown() / 2) {
            crystid.setAttackWindup(true);
            // The target can die/unload between this goal's shouldContinue() check and this tick
            if (this.crystid.getTarget() != null && this.crystid.getRandom().nextDouble() <= 0.2) {
                this.crystid.getTarget().addStatusEffect(new StatusEffectInstance(ModEffects.THIRST, 150, 1));
            }
        } else {
            crystid.setAttackWindup(false);
            this.crystid.setAttacking(false);
        }
    }
}
