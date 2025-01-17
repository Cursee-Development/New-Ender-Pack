package com.cursee.ender_pack.core.network;

import com.cursee.ender_pack.Constants;
import com.cursee.ender_pack.core.network.packet.FabricOpenEnderPackC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public class ModMessagesFabric {

	public static final StreamCodec<RegistryFriendlyByteBuf, FabricOpenEnderPackC2SPacket> PACKET_CODEC = StreamCodec.ofMember(FabricOpenEnderPackC2SPacket::write, FabricOpenEnderPackC2SPacket::read);
	public static final CustomPacketPayload.Type<FabricOpenEnderPackC2SPacket> PACKET_ID = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "ender_pack"));
	
//	public static final ResourceLocation ENDER_PACK = new ResourceLocation(Constants.MOD_ID, "ender_pack");
//
//	public static void registerC2SPackets() {
//		ServerPlayNetworking.registerGlobalReceiver(ENDER_PACK, FabricOpenEnderPackC2SPacket::receive);
//	}
}
