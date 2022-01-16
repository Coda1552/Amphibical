package woda.amphibical.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.amphibical.client.model.PinocchioSwordmasterModel;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;

public class PinocchioSwordmasterRenderer extends GeoEntityRenderer<PinocchioSwordmasterEntity> {

    public PinocchioSwordmasterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new PinocchioSwordmasterModel());
        this.shadowRadius = 0.5F;
    }
}