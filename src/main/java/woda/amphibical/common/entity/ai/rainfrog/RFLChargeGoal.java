package woda.amphibical.common.entity.ai.rainfrog;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import woda.amphibical.common.entity.RainFroggleEntity;

public class RFLChargeGoal extends Goal {
    private RainFroggleEntity entity;
    private int cooldown;
    private int timer;

    public RFLChargeGoal(RainFroggleEntity entity){
       this.entity = entity;
    }

    @Override
    public boolean canUse() {
        if(this.entity.getTarget() != null ){
            return true;
        }
        else{
            this.entity.setAttackState(0);
            cooldown = 0;
            timer = 0;
            return false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if(cooldown < 20){
            cooldown++;
            timer = 0;
            this.entity.setAttackState(0);
        }
        else {
            if (this.entity.distanceToSqr(this.entity.getTarget()) < 35f) {

            Vec3 targetPos = this.entity.getTarget().getPosition(1f);
            if (timer <= 25) {
                timer++;
                this.entity.setAttackState(1);
                this.entity.getNavigation().stop();
                this.entity.setDeltaMovement(0.0d, this.entity.getDeltaMovement().y(), 0.0d);
                this.entity.getLookControl().setLookAt(this.entity.getTarget(), 30f, 30f);
            } else {
                cooldown = 0;
                timer = 0;
                this.entity.setAttackState(0);
            }
            if (timer > 30) {
                this.entity.setAttackState(0);
            }
            if(timer == 1){
                this.entity.setDeltaMovement(this.entity.getDeltaMovement().add(0d, 0.5d, 0d));
            }
            if (timer == 20) {
                Vec3 pos = this.entity.getPosition(1f);
                this.entity.setDeltaMovement(pos.vectorTo(targetPos).multiply(1.2d, 0.4d, 1.2d));
                }
            }
            else{
                this.entity.getNavigation().moveTo(this.entity.getTarget(), 1.2f);
                this.entity.getLookControl().setLookAt(this.entity.getTarget(), 30f, 30f);
            }
        }
    }
}
