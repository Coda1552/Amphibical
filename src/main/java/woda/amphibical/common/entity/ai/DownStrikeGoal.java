package woda.amphibical.common.entity.ai;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;

public class DownStrikeGoal extends Goal {
    private PinocchioSwordmasterEntity entity;
    private final int COOLDOWN = 20;
    private int timer = 0;
    private int ctimer = 0;
    public DownStrikeGoal(PinocchioSwordmasterEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return this.entity.getTarget() != null;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entity.getAttackState() == 0 || this.entity.getAttackState() == 1){
            if(ctimer < COOLDOWN){
                ctimer++;
                timer = 0;
            }
            else{
                if(timer < 40){
                    this.timer++;
                    this.entity.setAttackState(1);
                    if(timer == 14){
                        tryAttack(entity, this.entity.getTarget());
                    }
                }
                else{
                    this.stop();
                }
            }
        }
    }

    @Override
    public void stop() {
        super.stop();
        timer = 0;
        ctimer = 0;
        this.entity.setAttackState(0);
    }

    public void tryAttack(PinocchioSwordmasterEntity entity, LivingEntity target){
        float attackReach = 4f;
        if(entity.distanceTo(target) < attackReach){
            target.hurt(DamageSource.mobAttack(entity), (float) this.entity.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * (this.entity.getRandom().nextFloat() + 1f));
        }
    }
}
