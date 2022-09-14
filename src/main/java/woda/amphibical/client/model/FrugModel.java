package woda.amphibical.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedTickingGeoModel;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.FrugEntity;

public class FrugModel extends AnimatedTickingGeoModel<FrugEntity> {

    @Override
    public ResourceLocation getModelLocation(FrugEntity object) {
        return new ResourceLocation(Amphibical.MOD_ID, "geo/entity/frug.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(FrugEntity object) {
        return new ResourceLocation(Amphibical.MOD_ID, "textures/entity/frug.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(FrugEntity animatable) {
        return new ResourceLocation(Amphibical.MOD_ID, "animations/entity/frug.animations.json");
    }
}
