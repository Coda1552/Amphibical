package woda.amphibical.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.amphibical.client.model.FrugModel;
import woda.amphibical.common.entity.FrugEntity;

public class FrugRenderer extends GeoEntityRenderer<FrugEntity> {

    public FrugRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new FrugModel());
        this.shadowRadius = 0.3F;
    }
}