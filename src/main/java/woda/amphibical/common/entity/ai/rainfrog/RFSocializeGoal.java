package woda.amphibical.common.entity.ai.rainfrog;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import woda.amphibical.common.entity.RainFrogEntity;

import java.util.List;

public class RFSocializeGoal extends Goal {
    private static final TargetingConditions SOCIALIZE_TARGETTING = TargetingConditions.forNonCombat().range(8.0D).ignoreLineOfSight();
    private RainFrogEntity entity;
    final int PRIORITY = 2;
    final int COOLDOWN = 150;
    private int cTimer;
    private int timer;

    public RFSocializeGoal(RainFrogEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return PRIORITY >= this.entity.getActionPriority() && this.entity.getTarget() == null;
    }

    @Override
    public void tick() {
        super.tick();
        System.out.println(this.entity.getAnimState());
        if(cTimer < COOLDOWN){
            cTimer++;
        }
        else {
            if(timer < 80) {
                if (getClosestFrog() != null) {
                    this.entity.setActionPriority(PRIORITY);
                    if(this.entity.distanceToSqr(getClosestFrog()) > 4f) {
                        this.entity.getNavigation().moveTo(getClosestFrog(), 1.0d);
                    }
                    this.entity.getLookControl().setLookAt(getClosestFrog(), 30f, 30f);

                    this.entity.setAnimState(1);
                    timer++;
                }
                else{
                    this.entity.setActionPriority(0);
                    this.entity.setAnimState(0);
                    cTimer = 0;
                    timer = 0;
                }
            }
            else{
                this.entity.setActionPriority(0);
                this.entity.setAnimState(0);
               cTimer = this.entity.getRandom().nextInt(40);
                timer = 0;
            }
        }
    }

    private RainFrogEntity getClosestFrog(){
        List<? extends RainFrogEntity> list = this.entity.level.getNearbyEntities(this.entity.getClass(), SOCIALIZE_TARGETTING, this.entity, this.entity.getBoundingBox().inflate(8.0D));
        if(!list.isEmpty()) {
            RainFrogEntity frog = list.get(0);
            return frog;
        }

        return null;
    }

}

