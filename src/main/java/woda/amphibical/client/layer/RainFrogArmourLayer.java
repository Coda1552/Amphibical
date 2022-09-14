package woda.amphibical.client.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Items;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.RainFrogEntity;

public class RainFrogArmourLayer extends GeoLayerRenderer {
    private static final ResourceLocation MODEL = new ResourceLocation(Amphibical.MOD_ID, "geo/entity/rain_frog.geo.json");
    private static final ResourceLocation MODEL_BACKPACK = new ResourceLocation(Amphibical.MOD_ID, "geo/entity/rain_frog_backpack.geo.json");

    private RenderType cameo;
    @SuppressWarnings("unchecked")
    public RainFrogArmourLayer(IGeoRenderer<?> entityRendererIn) {
        super(entityRendererIn);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, Entity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if(entityLivingBaseIn instanceof RainFrogEntity && ((RainFrogEntity) entityLivingBaseIn).getItemBySlot(EquipmentSlot.HEAD) != null && !((RainFrogEntity) entityLivingBaseIn).getItemBySlot(EquipmentSlot.HEAD).is(Items.AIR)) {
            RenderType cameo = RenderType.armorCutoutNoCull(getResourceLocation((ArmorItem) ((RainFrogEntity) entityLivingBaseIn).getItemBySlot(EquipmentSlot.HEAD).getItem()));
            matrixStackIn.pushPose();
            //Move or scale the model as you see fit
            matrixStackIn.scale(1.05f, 1.05f, 1.05f);
            matrixStackIn.translate(0.0d, 0.0d, 0.0d);
            this.getRenderer().render(this.getEntityModel().getModel(((RainFrogEntity) entityLivingBaseIn).getVariant() ? MODEL_BACKPACK : MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                    bufferIn.getBuffer(cameo), packedLightIn, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);
            matrixStackIn.popPose();
        }

    }



    private ResourceLocation getResourceLocation(ArmorItem item){
        return new ResourceLocation(Amphibical.MOD_ID, "textures/entity/rain_frog_" + item.getMaterial().getName() + ".png");
    }
}
