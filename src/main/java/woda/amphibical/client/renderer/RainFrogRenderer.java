package woda.amphibical.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import software.bernie.geckolib3.geo.render.built.GeoBone;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import woda.amphibical.client.model.PinocchioSwordmasterModel;
import woda.amphibical.client.model.RainFrogModel;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;
import woda.amphibical.common.entity.RainFrogEntity;

public class RainFrogRenderer extends GeoEntityRenderer<RainFrogEntity> {
    private RainFrogEntity entity;
    public RainFrogRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RainFrogModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void renderEarly(RainFrogEntity entity, PoseStack stackIn, float ticks, MultiBufferSource renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float partialTicks) {
        super.renderEarly(entity, stackIn, ticks, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, partialTicks);
        this.entity = entity;
    }

    @Override
    public void renderRecursively(GeoBone bone, PoseStack stack, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        if (bone.getName().equals("item")) {
            stack.pushPose();
            stack.translate(0.43D, 0.15D, -0.02D);
            stack.mulPose(Vector3f.XP.rotationDegrees(-90.0F));
            stack.scale(0.8f, 0.8f, 0.8f);
            ItemStack itemstack = this.entity.getItemBySlot(EquipmentSlot.MAINHAND);
            Minecraft.getInstance().getItemRenderer().renderStatic(itemstack, ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, packedLightIn, packedOverlayIn, stack, this.rtb, 0);
            stack.popPose();
            bufferIn = rtb.getBuffer(RenderType.entityTranslucent(whTexture));
        }
        super.renderRecursively(bone, stack, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }
}