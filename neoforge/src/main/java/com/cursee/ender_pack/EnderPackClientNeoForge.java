package com.cursee.ender_pack;

import com.cursee.ender_pack.client.block.entity.renderer.EnderPackBlockEntityRenderer;
import com.cursee.ender_pack.client.player.EnderPackBagModel;
import com.cursee.ender_pack.client.player.EnderPackBagModelRendererLayer;
import com.cursee.ender_pack.core.network.input.OpenEnderPackKeyForge;
import com.cursee.ender_pack.core.network.packet.ForgeOpenEnderPackC2SPacket;
import com.cursee.ender_pack.core.registry.ModBlockEntityTypesForge;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.function.Function;

public class EnderPackClientNeoForge {

    @EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void registerBlockEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModBlockEntityTypesForge.ENDER_PACK.get(), EnderPackBlockEntityRenderer::new);
        }

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(OpenEnderPackKeyForge.ENDER_PACK_KEY_MAPPING);
        }

        @SubscribeEvent
        public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
        {
            event.registerLayerDefinition(EnderPackClient.ENDER_PACK_LAYER, EnderPackBagModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void construct(EntityRenderersEvent.AddLayers event)
        {
            addLayerToPlayerSkin(event, "default", EnderPackBagModelRendererLayer::new);
            addLayerToPlayerSkin(event, "slim", EnderPackBagModelRendererLayer::new);
        }
    }

    @EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void handleEventInput(ClientTickEvent.Post event) {

            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) {
                return;
            }

            if (OpenEnderPackKeyForge.ENDER_PACK_KEY_MAPPING.consumeClick()) {
                PacketDistributor.sendToServer(new ForgeOpenEnderPackC2SPacket());
            }
        }
    }

    private static <E extends Player, M extends HumanoidModel<E>>
    void addLayerToPlayerSkin(EntityRenderersEvent.AddLayers event, String skinName, Function<LivingEntityRenderer<E, M>, ? extends RenderLayer<E, M>> factory)
    {
        LivingEntityRenderer renderer = event.getSkin(PlayerSkin.Model.byName(skinName));
        if (renderer != null) renderer.addLayer(factory.apply(renderer));
    }
}
