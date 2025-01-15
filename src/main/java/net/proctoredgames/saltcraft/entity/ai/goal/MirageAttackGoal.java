package net.proctoredgames.saltcraft.entity.ai.goal;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.Mirage;

public class MirageAttackGoal extends MeleeAttackGoal {
    private final Mirage mirage;
    private int punchTicks;
    private final double attackRange;

    public MirageAttackGoal(Mirage pMirage, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen, double pAttackRange) {
        super(pMirage, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.attackRange = pAttackRange;
        this.mirage = pMirage;
    }

    public void start() {
        super.start();
        this.punchTicks = 0;
    }

    public void stop() {
        super.stop();
        this.mirage.setAttacking(false);
        this.mirage.setAggressive(false);
    }

    public void tick() {
        super.tick();
        ++this.punchTicks;
        if (this.punchTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
            this.mirage.setAggressive(true);
        }else if (this.punchTicks >= 0 && this.getTicksUntilNextAttack() > this.getAttackInterval() / 2) {
            mirage.setAttacking(true);
        } else {
            this.mirage.setAttacking(false);
            this.mirage.setAggressive(false);
        }

    }

    @Override
    protected void checkAndPerformAttack(LivingEntity enemy, double squaredDistance) {
        double attackReachSq = this.getAttackReachSqr(enemy);
        if (squaredDistance <= attackReachSq && this.isTimeToAttack()) {
            this.resetAttackCooldown();
            this.mob.doHurtTarget(enemy);
        }
    }

    @Override
    protected double getAttackReachSqr(LivingEntity target) {
        return this.attackRange * this.attackRange;
    }
}
