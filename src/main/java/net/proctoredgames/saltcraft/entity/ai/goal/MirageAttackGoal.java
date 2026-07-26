package net.proctoredgames.saltcraft.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.proctoredgames.saltcraft.entity.custom.Mirage;

public class MirageAttackGoal extends MeleeAttackGoal {
    private final Mirage mirage;
    private int punchTicks;
    private final double attackRange;

    public MirageAttackGoal(Mirage mirage, double speed, boolean pauseWhenMobIdle, double attackRange) {
        super(mirage, speed, pauseWhenMobIdle);
        this.attackRange = attackRange;
        this.mirage = mirage;
    }

    @Override
    public void start() {
        super.start();
        this.punchTicks = 0;
    }

    @Override
    public void stop() {
        super.stop();
        this.mirage.setAttackWindup(false);
        this.mirage.setAttacking(false);
    }

    @Override
    public void tick() {
        super.tick();
        ++this.punchTicks;
        if (this.punchTicks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2) {
            this.mirage.setAttacking(true);
        } else if (this.punchTicks >= 0 && this.getCooldown() > this.getMaxCooldown() / 2) {
            mirage.setAttackWindup(true);
        } else {
            this.mirage.setAttackWindup(false);
            this.mirage.setAttacking(false);
        }
    }

    @Override
    protected void attack(LivingEntity target) {
        if (this.mirage.squaredDistanceTo(target) <= this.attackRange * this.attackRange && this.isCooledDown()) {
            this.resetCooldown();
            this.mob.tryAttack(target);
        }
    }
}
