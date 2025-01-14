package com.cursee.ender_pack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class EnderPackClient {

    public static final ResourceLocation ENDER_PACK_TEXTURE = new ResourceLocation(Constants.MOD_ID, "textures/entity/wearable/ender_pack.png");
    public static ModelLayerLocation ENDER_PACK_LAYER = new ModelLayerLocation(new ResourceLocation("player"), "ender_pack");
}
