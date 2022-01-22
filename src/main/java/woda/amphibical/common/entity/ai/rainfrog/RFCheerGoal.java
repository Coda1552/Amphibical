package woda.amphibical.common.entity.ai.rainfrog;

import net.minecraft.world.entity.ai.goal.Goal;
import woda.amphibical.common.entity.RainFrogEntity;

public class RFCheerGoal extends Goal {
    final int PRIORITY = 5;
    private int timer;
    private boolean hasTarget = false;
    private RainFrogEntity entity;

    public RFCheerGoal(RainFrogEntity entity){
    this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return PRIORITY >= this.entity.getActionPriority();
    }


    @Override
    public void tick() {
        super.tick();

        if(this.entity.getAnimState() == 3 && this.entity.getTarget() != null){
            this.entity.setActionPriority(0);
            this.entity.setAnimState(0);
            this.hasTarget = false;
            this.timer = 0;
        }
        if(this.entity.getTarget() != null){
            this.hasTarget = true;
            if(this.entity.distanceToSqr(this.entity.getTarget()) > 10f){
                this.hasTarget = false;
            }

        }

        if(this.entity.getTarget() == null && this.hasTarget){
            this.entity.setActionPriority(PRIORITY);
            int randomness = this.entity.getRandom().nextInt(25);
            if(timer < 20 + randomness) {
                this.entity.setDeltaMovement(0d, this.entity.getDeltaMovement().y(), 0d);
                this.entity.getNavigation().stop();
                this.entity.setActionPriority(5);
                if(timer > randomness) {
                    this.entity.setAnimState(3);
                }
                timer++;
            }
            else{
                this.entity.setActionPriority(0);
                this.entity.setAnimState(0);
                this.hasTarget = false;
                this.timer = 0;
            }
        }

    }
}

