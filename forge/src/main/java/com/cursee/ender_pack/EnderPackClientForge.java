package com.cursee.ender_pack;

import com.cursee.ender_pack.client.block.entity.renderer.EnderPackBlockEntityRenderer;
import com.cursee.ender_pack.client.player.EnderPackBagModel;
import com.cursee.ender_pack.client.player.EnderPackBagModelRendererLayer;
import com.cursee.ender_pack.core.network.ModMessagesForge;
import com.cursee.ender_pack.core.network.input.OpenEnderPackKeyForge;
import com.cursee.ender_pack.core.network.packet.ForgeOpenEnderPackC2SPacket;
import com.cursee.ender_pack.platform.Services;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.function.Function;

public class EnderPackClientForge {

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ForgeBusEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (OpenEnderPackKeyForge.ENDER_PACK_KEY_MAPPING.consumeClick()) {
                ModMessagesForge.sendToServer(new ForgeOpenEnderPackC2SPacket());
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(OpenEnderPackKeyForge.ENDER_PACK_KEY_MAPPING);
        }

        @SubscribeEvent
        public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(Services.PLATFORM.getRegisteredEnderPackBlockEntity(), EnderPackBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void onRegisterLayerDefinitionsForEntityRenderers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(EnderPackClient.ENDER_PACK_LAYER, EnderPackBagModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void onAddLayersForEntityRenderers(EntityRenderersEvent.AddLayers event) {
            EnderPackClientForge.addLayerToPlayerSkin(event, "default", EnderPackBagModelRendererLayer::new);
            EnderPackClientForge.addLayerToPlayerSkin(event, "slim", EnderPackBagModelRendererLayer::new);
        }
    }
    // ignore <E, M> if issues occur??
    private static <E extends Player, M extends HumanoidModel<E>> void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, String skinName, Function<LivingEntityRenderer<E, M>, ? extends RenderLayer<E, M>> factory) {
        LivingEntityRenderer renderer = event.getSkin(skinName);
        if (renderer != null) renderer.addLayer(factory.apply(renderer));
    }
}
