package woda.amphibical.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.amphibical.client.layer.RainFrogArmourLayer;
import woda.amphibical.client.model.RainFroggleModel;
import woda.amphibical.common.entity.RainFroggleEntity;

public class RainFroggleRenderer extends GeoEntityRenderer<RainFroggleEntity> {
    public RainFroggleRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RainFroggleModel());
        this.addLayer(new RainFrogArmourLayer(this));
        this.shadowRadius = 0.3F;
    }

    @Override
    public void renderEarly(RainFroggleEntity entity, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(entity, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
    }

}