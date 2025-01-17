package com.cursee.ender_pack;

import com.cursee.ender_pack.core.network.packet.ForgeOpenEnderPackC2SPacket;
import com.cursee.ender_pack.core.registry.ModItemsForge;
import com.cursee.ender_pack.core.registry.RegistryForge;
import com.cursee.monolib.core.sailing.Sailing;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(Constants.MOD_ID)
public class EnderPackNeoForge {
    
    public EnderPackNeoForge(IEventBus modEventBus) {
        EnderPack.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        RegistryForge.register(modEventBus);

        modEventBus.addListener(this::registerPayloadHandler);
        modEventBus.addListener(this::addCreative);
    }

    private void registerPayloadHandler(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Constants.MOD_ID);
        registrar.playToServer(ForgeOpenEnderPackC2SPacket.TYPE, ForgeOpenEnderPackC2SPacket.CODEC, ForgeOpenEnderPackC2SPacket::handle);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(ModItemsForge.ENDER_PACK.get());
    }
}