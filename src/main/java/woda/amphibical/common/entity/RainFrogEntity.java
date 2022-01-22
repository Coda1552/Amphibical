package woda.amphibical.common.entity;


import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.level.Level;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import woda.amphibical.common.entity.ai.rainfrog.RFCheerGoal;
import woda.amphibical.common.entity.ai.rainfrog.RFSocializeGoal;

public class RainFrogEntity extends AbstractFrogEntity implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> ACTION_PRIORITY = SynchedEntityData.defineId(RainFrogEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIM_STATE = SynchedEntityData.defineId(RainFrogEntity.class, EntityDataSerializers.INT);


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ACTION_PRIORITY, 0);
        this.entityData.define(ANIM_STATE, 0);
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
       if(this.isAggressive()){
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.attack", true));
            return PlayState.CONTINUE;
        }
        switch (getAnimState()){
            case 0:
                if(!event.isMoving()) {
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.idle", true));
                    return PlayState.CONTINUE;
                }
                else{
                    event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.walk", true));
                    return PlayState.CONTINUE;
                }
            case 1:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.talk", true));
                return PlayState.CONTINUE;
            case 2:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.attack", true));
                return PlayState.CONTINUE;
            case 3:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.dance", false));
                return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.idle", true));
        return PlayState.CONTINUE;

    }

    public RainFrogEntity(EntityType<? extends AbstractFrogEntity> frog, Level level) {
        super(frog, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.25D).add(Attributes.ATTACK_DAMAGE, 4.5D).add(Attributes.FOLLOW_RANGE, 30f).add(Attributes.MAX_HEALTH, 6.0D);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(2, new RFSocializeGoal(this));
        this.goalSelector.addGoal(2, new RFCheerGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.3f, false));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FrugEntity.class, true));
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 3, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public int tickTimer() {
        return tickCount;
    }

    public void setActionPriority(int actionPriority){
        this.entityData.set(ACTION_PRIORITY, actionPriority);
    }

    public int getActionPriority(){
       return this.entityData.get(ACTION_PRIORITY);
    }

    public void setAnimState(int animState){
        this.entityData.set(ANIM_STATE, animState);
    }

    public int getAnimState() {
        return this.entityData.get(ANIM_STATE);
    }
}
