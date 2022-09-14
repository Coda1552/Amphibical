package woda.amphibical.common.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import woda.amphibical.common.entity.ai.rainfrog.RFLChargeGoal;

public class RainFroggleEntity extends Monster implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> ATTACK_STATE = SynchedEntityData.defineId(RainFroggleEntity.class, EntityDataSerializers.INT);
    public RainFroggleEntity(EntityType<? extends Monster> monster, Level level) {
        super(monster, level);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if(getAttackState() == 0) {
            if (event.isMoving()) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.froggle.walk", true));
                return PlayState.CONTINUE;
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.froggle.idle", true));
                return PlayState.CONTINUE;
            }
        }
        else{
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.froggle.spin", true));
            return PlayState.CONTINUE;
        }
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RFLChargeGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0d));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FrugEntity.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));

    }

    @Override
    public void tick() {
        super.tick();
        if(this.getAttackState() == 1 && this.getTarget() != null){
            for(LivingEntity livingentity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox())) {
                if(!(livingentity instanceof RainFroggleEntity)) {
                    sendFlying(livingentity);
                    livingentity.hurt(DamageSource.mobAttack(this), (float) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue());
                }
            }
        }
    }

    public void sendFlying(LivingEntity entity){
        if(entity.isOnGround()){
            entity.setDeltaMovement(0.0d, 1.2d, 0.0d);
        }
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 3, this::predicate));
    }


    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 6.5D).add(Attributes.FOLLOW_RANGE, 30f).add(Attributes.MAX_HEALTH, 15.0D);
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    public int tickTimer() {
        return this.tickCount;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ATTACK_STATE, 0);
    }


    public void setAttackState(int attackState){
        this.entityData.set(ATTACK_STATE, attackState);
    }
    public int getAttackState(){
        return this.entityData.get(ATTACK_STATE);
    }
}
