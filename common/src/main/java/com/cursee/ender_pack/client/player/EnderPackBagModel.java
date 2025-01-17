package com.cursee.ender_pack.client.player;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.Entity;

public class EnderPackBagModel <T extends Entity> extends EntityModel<T> {

    private final ModelPart ENDER_PACK;

    public EnderPackBagModel(ModelPart root) {
        super(RenderType::entityCutoutNoCull);
        this.ENDER_PACK = root.getChild("ender_pack");
    }

    @Override
    public void setupAnim(T object, float v, float v1, float v2, float v3, float v4) {
        // no-op
    }

//    @Override
//    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
//        ENDER_PACK.render(poseStack, vertexConsumer, packedLightIn, packedOverlayIn, 1.0F, 1.0F, 1.0F, 1.0F);
//    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int i1, int i2) {
        this.ENDER_PACK.render(poseStack, vertexConsumer, packedLight, i1, i2);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        partdefinition.addOrReplaceChild(
                "ender_pack",
                CubeListBuilder.create()
                        .texOffs(0,   0).addBox(-4.0F, -1.0F, -13.0F, 10.0F, 12.0F, 6.0F)
                        .texOffs(0,  18).addBox(-3.0F, -2.0F, -12.0F,  8.0F,  1.0F, 4.0F)
                        .texOffs(28, 22).addBox(2.0F,  -3.0F, -11.0F,  1.0F,  1.0F, 1.0F)
                        .texOffs(0,   4).addBox(-1.0F, -3.0F, -11.0F,  1.0F,  1.0F, 1.0F)
                        .texOffs(8,  28).addBox(-1.0F, -4.0F, -11.0F,  4.0F,  1.0F, 1.0F)
                        .texOffs(20, 22).addBox(6.0F,   6.0F, -12.0F,  2.0F,  5.0F, 4.0F)
                        .texOffs(0,  28).addBox(-6.0F,  6.0F, -12.0F,  2.0F,  5.0F, 4.0F)
                        .texOffs(26,  0).addBox(-2.0F,  6.0F,  -6.0F,  6.0F,  4.0F, 1.0F)
                        .texOffs(0,   0).addBox(4.0F,   6.0F,  -6.0F,  1.0F,  3.0F, 1.0F)
                        .texOffs(0,  18).addBox(-3.0F,  6.0F,  -6.0F,  1.0F,  3.0F, 1.0F)
                        .texOffs(0,  23).addBox(-3.0F,  6.0F,  -7.0F,  8.0F,  4.0F, 1.0F)
                        .texOffs(18, 23).addBox(3.0F,   3.0F,  -7.0F,  1.0F,  2.0F, 1.0F)
                        .texOffs(0,  28).addBox(-2.0F,  3.0F,  -7.0F,  1.0F,  2.0F, 1.0F)
                        .texOffs(20, 18).addBox(-3.0F,  0.0F,  -7.0F,  8.0F,  3.0F, 1.0F),
                PartPose.offsetAndRotation(-1.0F, -4.0F, 10.0F, 0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }
}
