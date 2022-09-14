package woda.amphibical.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.RainFroggleEntity;

public class RainFroggleModel extends AnimatedTickingGeoModel<RainFroggleEntity> {

    @Override
    public ResourceLocation getModelLocation(RainFroggleEntity object) {
        if(object.getAttackState() == 1){
            return new ResourceLocation(Amphibical.MOD_ID, "geo/entity/rain_froggle_rolling.geo.json");
        }
        return new ResourceLocation(Amphibical.MOD_ID, "geo/entity/rain_froggle.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(RainFroggleEntity object) {
        if(object.getAttackState() == 1){
            return new ResourceLocation(Amphibical.MOD_ID, "textures/entity/rain_froggle_rolling.png");
        }
        return new ResourceLocation(Amphibical.MOD_ID, "textures/entity/rain_froggle.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(RainFroggleEntity animatable) {
        if(animatable.getAttackState() ==1){
            return new ResourceLocation(Amphibical.MOD_ID, "animations/entity/rain_froggle_rolling.animations.json");
        }
        return new ResourceLocation(Amphibical.MOD_ID, "animations/entity/rain_froggle.animations.json");
    }
}
