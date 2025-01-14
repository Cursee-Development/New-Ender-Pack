package com.cursee.ender_pack.client.block.entity.renderer;

import com.cursee.ender_pack.EnderPackClient;
import com.cursee.ender_pack.core.block.custom.entity.EnderPackBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.joml.Vector3f;

public class EnderPackBlockEntityRenderer implements BlockEntityRenderer<EnderPackBlockEntity> {

    private final ModelPart ENDER_PACK;

    public EnderPackBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart rootModelPart = context.bakeLayer(EnderPackClient.ENDER_PACK_LAYER);
        this.ENDER_PACK = rootModelPart.getChild("ender_pack");
    }

    @Override
    public void render(EnderPackBlockEntity blockEntity, float partialTick, PoseStack pose, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        if (blockEntity.getLevel() == null) return;

        BlockState state = blockEntity.getBlockState();

        if (!(blockEntity.getBlockState().hasProperty(BlockStateProperties.HORIZONTAL_FACING))) return;

        pose.pushPose();
        final Direction FACING_DIRECTION = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        Vector3f offset = new Vector3f();
        float rotationDegrees = 0;

        switch (FACING_DIRECTION) {
            case NORTH -> {
                offset.set(1f, 0.125f, 1f);
                rotationDegrees = 180;
                break;
            }
            case EAST -> {
                offset.set(0f, 0.125f, 1f);
                rotationDegrees = 90;
                break;
            }
            case SOUTH -> {
                offset.set(0.0f, 0.125f, 0f);
                break;
            }
            case WEST -> {
                offset.set(1f, 0.125f, 0f);
                rotationDegrees = 270;
                break;
            }
        }

        pose.translate(offset.x, offset.y, offset.z);
        pose.mulPose(com.mojang.math.Axis.YP.rotationDegrees(rotationDegrees));

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(EnderPackClient.ENDER_PACK_TEXTURE));

        pose.translate(0.5D, 0.3125D, 0.5625D);

        pose.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(180));
        ENDER_PACK.render(pose, vertexConsumer, packedLight, packedOverlay);

        pose.popPose();
    }
}
