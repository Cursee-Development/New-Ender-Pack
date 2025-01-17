package com.cursee.ender_pack;

import com.cursee.ender_pack.client.block.entity.renderer.EnderPackBlockEntityRenderer;
import com.cursee.ender_pack.client.player.EnderPackBagModel;
import com.cursee.ender_pack.client.player.EnderPackBagModelRendererLayer;
import com.cursee.ender_pack.core.network.input.OpenEnderPackKeyFabric;
import com.cursee.ender_pack.core.registry.ModBlockEntityTypesFabric;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;

public class EnderPackClientFabric implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        OpenEnderPackKeyFabric.register();

        BlockEntityRenderers.register(ModBlockEntityTypesFabric.ENDER_PACK, EnderPackBlockEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(EnderPackClient.ENDER_PACK_LAYER, EnderPackBagModel::createBodyLayer);

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, livingEntityRenderer, livingEntityFeatureRendererRegistrationHelper, context) -> {
            if(livingEntityRenderer instanceof PlayerRenderer playerRenderer) livingEntityFeatureRendererRegistrationHelper.register(new EnderPackBagModelRendererLayer<>(playerRenderer));
        });
    }
}
