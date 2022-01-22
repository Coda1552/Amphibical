package woda.amphibical.common.entity.ai.rainfrog;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import woda.amphibical.common.entity.RainFrogEntity;
import woda.amphibical.registry.AmphibicalItems;

public class RFEatGoal extends Goal {
    private final int PRIORITY = 4;
    private RainFrogEntity entity;
    private int timer;

    public RFEatGoal(RainFrogEntity entity){
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return PRIORITY >= this.entity.getActionPriority();
    }

    @Override
    public void tick() {
        super.tick();
        if(this.entity.getItemBySlot(EquipmentSlot.MAINHAND).is(AmphibicalItems.FRUG.get()) && this.entity.getHealth() < this.entity.getMaxHealth()){
            startEating();
        }
    }

    public void startEating(){
        if(timer < 10){
            timer++;
            this.entity.setActionPriority(PRIORITY);
            this.entity.setAnimState(4);
            if(timer > 6){
                this.entity.playSound(SoundEvents.GENERIC_EAT, 3.0f, 1.5f);
            }
        }
        else{
            this.entity.playSound(SoundEvents.PLAYER_BURP, 3.0f, 1.5f);
            this.entity.setDeltaMovement(0d, this.entity.getDeltaMovement().y(), 0d);
            this.entity.heal(4.0f);
            this.entity.setActionPriority(0);
            this.entity.setAnimState(0);
            this.entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(Items.AIR, 1));
            this.timer = 0;
        }
    }
}
