package com.cursee.ender_pack;

import com.cursee.ender_pack.core.network.ModMessagesFabric;
import com.cursee.ender_pack.core.network.packet.FabricOpenEnderPackC2SPacket;
import com.cursee.ender_pack.core.registry.RegistryFabric;
import com.cursee.monolib.core.sailing.Sailing;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class EnderPackFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        EnderPack.init();
        Sailing.register(Constants.MOD_NAME, Constants.MOD_ID, Constants.MOD_VERSION, Constants.MC_VERSION_RAW, Constants.PUBLISHER_AUTHOR, Constants.PRIMARY_CURSEFORGE_MODRINTH);
        RegistryFabric.register();
        PayloadTypeRegistry.playC2S().register(ModMessagesFabric.PACKET_ID, ModMessagesFabric.PACKET_CODEC);
        ServerPlayNetworking.registerGlobalReceiver(ModMessagesFabric.PACKET_ID, FabricOpenEnderPackC2SPacket::handle);
    }
}
