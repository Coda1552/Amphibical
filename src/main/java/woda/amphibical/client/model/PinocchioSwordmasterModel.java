package woda.amphibical.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;

public class PinocchioSwordmasterModel extends AnimatedTickingGeoModel<PinocchioSwordmasterEntity> {

    @Override
    public ResourceLocation getModelLocation(PinocchioSwordmasterEntity object) {
        return new ResourceLocation(Amphibical.MOD_ID, "geo/entity/pinocchio_swordmaster.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(PinocchioSwordmasterEntity object) {
        return new ResourceLocation(Amphibical.MOD_ID, "textures/entity/pinocchio_swordmaster.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(PinocchioSwordmasterEntity animatable) {
        return new ResourceLocation(Amphibical.MOD_ID, "animations/entity/pinocchio_swordmaster.animations.json");
    }
}
