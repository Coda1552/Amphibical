package woda.amphibical.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.amphibical.client.model.PinocchioSwordmasterModel;
import woda.amphibical.client.model.RainFrogModel;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;
import woda.amphibical.common.entity.RainFrogEntity;

public class RainFrogRenderer extends GeoEntityRenderer<RainFrogEntity> {

    public RainFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RainFrogModel());
        this.shadowRadius = 0.3F;
    }
}