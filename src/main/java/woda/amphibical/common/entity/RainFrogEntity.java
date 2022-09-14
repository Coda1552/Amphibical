package woda.amphibical.common.entity;


import com.github.alexthe666.alexsmobs.entity.EntityGuster;
import com.github.alexthe666.alexsmobs.entity.EntityRattlesnake;
import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import com.github.alexthe666.alexsmobs.entity.EntityTarantulaHawk;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import woda.amphibical.common.entity.ai.rainfrog.RFCheerGoal;
import woda.amphibical.common.entity.ai.rainfrog.RFEatGoal;
import woda.amphibical.common.entity.ai.rainfrog.RFSocializeGoal;

public class RainFrogEntity extends AbstractFrogEntity implements IAnimatable, IAnimationTickable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private static final EntityDataAccessor<Integer> ACTION_PRIORITY = SynchedEntityData.defineId(RainFrogEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> ANIM_STATE = SynchedEntityData.defineId(RainFrogEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> VARIANT = SynchedEntityData.defineId(RainFrogEntity.class, EntityDataSerializers.BOOLEAN);
    private final SimpleContainer inventory = new SimpleContainer(8);


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ACTION_PRIORITY, 0);
        this.entityData.define(ANIM_STATE, 0);
        this.entityData.define(VARIANT, false);
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
            case 4:
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.eat", false));
                return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.frog.idle", true));
        return PlayState.CONTINUE;

    }

    public RainFrogEntity(EntityType<? extends AbstractFrogEntity> frog, Level level) {
        super(frog, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15D).add(Attributes.MOVEMENT_SPEED, 0.3D).add(Attributes.ATTACK_DAMAGE, 4.5D).add(Attributes.FOLLOW_RANGE, 25D);
    }

    @Override
    public void tick() {
        super.tick();
        //System.out.println(this.getItemBySlot(EquipmentSlot.HEAD));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        setVariant(tag.getBoolean("variant"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("variant", getVariant());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(2, new RFSocializeGoal(this));
        this.goalSelector.addGoal(2, new RFCheerGoal(this));
        this.goalSelector.addGoal(2, new RFEatGoal(this));
        this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.3f, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityRoadrunner.class, true));
        //this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, EntityRattlesnake.class, true));
        //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityGuster.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, FrugEntity.class, true));
        //this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, EntityTarantulaHawk.class, true));
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
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

    public void setVariant(boolean variant){
    this.entityData.set(VARIANT, variant);
    }

    public boolean getVariant(){
        return this.entityData.get(VARIANT);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        if(this.getRandom().nextFloat() < 0.18){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_HOE));
        }
        if(this.getRandom().nextFloat() < 0.2f){
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
        }
        if(this.getRandom().nextFloat() < 0.1f){
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_SWORD));
        }
        if(this.getRandom().nextFloat() < 0.45f){
            this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(Items.LEATHER_HELMET));
        }
        if(this.getRandom().nextFloat() < 0.24f){
            setVariant(true);
        }
    }


    public SimpleContainer getInventory(){
        return this.inventory;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        return super.mobInteract(player, hand);
    }
}
