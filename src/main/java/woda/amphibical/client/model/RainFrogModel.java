package woda.amphibical.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;
import woda.amphibical.common.entity.RainFrogEntity;

public class RainFrogModel extends AnimatedTickingGeoModel<RainFrogEntity> {

    @Override
    public ResourceLocation getModelLocation(RainFrogEntity object) {
        return new ResourceLocation(Amphibical.MOD_ID, "geo/entity/rain_frog.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RainFrogEntity object) {
        return new ResourceLocation(Amphibical.MOD_ID, "textures/entity/rain_frog.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RainFrogEntity animatable) {
        return new ResourceLocation(Amphibical.MOD_ID, "animations/entity/rain_frog.animations.json");
    }
}
