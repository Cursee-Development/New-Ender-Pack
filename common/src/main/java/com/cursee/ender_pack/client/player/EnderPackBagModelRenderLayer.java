package com.cursee.ender_pack.client.player;

import com.cursee.ender_pack.EnderPackClient;
import com.cursee.ender_pack.platform.Services;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.concurrent.atomic.AtomicBoolean;

public class EnderPackBagModelRenderLayer <T extends LivingEntity, M extends HumanoidModel<T>> extends RenderLayer<T, M> {

    private final EnderPackBagModel<T> BAG_MODEL;

    public EnderPackBagModelRenderLayer(RenderLayerParent<T, M> renderLayerParent) {
        super(renderLayerParent);
        BAG_MODEL = new EnderPackBagModel<T>(Minecraft.getInstance().getEntityModels().bakeLayer(EnderPackClient.ENDER_PACK_LAYER));
    }

    @Override
    protected ResourceLocation getTextureLocation(T $$0) {
        return EnderPackClient.ENDER_PACK_TEXTURE;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int lightness, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        final AtomicBoolean SHOULD_RENDER_BAG_MODEL = new AtomicBoolean(false);
        entity.getArmorSlots().forEach(itemStack -> {
            if (itemStack.is(Services.PLATFORM.getRegisteredEnderPackItem())) SHOULD_RENDER_BAG_MODEL.set(true);
        });

        if (!SHOULD_RENDER_BAG_MODEL.get()) return;

        poseStack.pushPose();
        poseStack.translate(0.0d, 0.25d, 0.3125d);
        RenderLayer.renderColoredCutoutModel(BAG_MODEL, getTextureLocation(entity), poseStack, multiBufferSource, lightness, entity, 1.0f, 1.0f, 1.0f);
        poseStack.popPose();
    }
}
