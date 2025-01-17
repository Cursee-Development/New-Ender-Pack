package com.cursee.ender_pack.core.network.packet;

import com.cursee.ender_pack.core.network.ModMessagesFabric;
import com.cursee.ender_pack.core.network.input.OpenEnderPackKeyFabric;
import com.cursee.ender_pack.core.registry.ModItemsFabric;
import com.cursee.ender_pack.platform.Services;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.atomic.AtomicBoolean;

public class FabricOpenEnderPackC2SPacket implements CustomPacketPayload {

	@Override
	public Type<? extends CustomPacketPayload> type() {
		return ModMessagesFabric.PACKET_ID;
	}

	public static FabricOpenEnderPackC2SPacket read(RegistryFriendlyByteBuf buf) {
		return new FabricOpenEnderPackC2SPacket();
	}

	public void write(RegistryFriendlyByteBuf buf) {}

	public void handle(ServerPlayNetworking.Context packetContext) {

		Player player = packetContext.player();

		final AtomicBoolean SHOULD_OPEN_ENDER_PACK = new AtomicBoolean(false);

		// CHESTPLATE SLOT
		player.getArmorSlots().forEach(itemStack -> {
			if (itemStack.is(Services.PLATFORM.getRegisteredEnderPackItem())) SHOULD_OPEN_ENDER_PACK.set(true);
		});

		// TRINKET SLOT
		if (Services.PLATFORM.isModLoaded("trinkets")) SHOULD_OPEN_ENDER_PACK.set(Services.PLATFORM.checkSlotsFromMods(player));
		if (!SHOULD_OPEN_ENDER_PACK.get()) return;
		player.openMenu(new SimpleMenuProvider(
				(containerID, playerInventory, player1) -> ChestMenu.threeRows(containerID, playerInventory, player.getEnderChestInventory()),
				Component.translatable("container.enderPack"))
		);
	}
	
//	public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
//
//		final AtomicBoolean SHOULD_OPEN_ENDER_PACK = new AtomicBoolean(false);
//
//		// CHESTPLATE SLOT
//		player.getArmorSlots().forEach(itemStack -> {
//			if (itemStack.is(Services.PLATFORM.getRegisteredEnderPackItem())) SHOULD_OPEN_ENDER_PACK.set(true);
//		});
//
//		// TRINKET SLOT
//		if (Services.PLATFORM.isModLoaded("trinkets")) SHOULD_OPEN_ENDER_PACK.set(Services.PLATFORM.checkSlotsFromMods(player));
//
//		if (!SHOULD_OPEN_ENDER_PACK.get()) return;
//
//		player.openMenu(new SimpleMenuProvider(
//				(containerID, playerInventory, player1) -> ChestMenu.threeRows(containerID, playerInventory, player.getEnderChestInventory()),
//				Component.translatable("container.enderPack"))
//		);
//	}
}
