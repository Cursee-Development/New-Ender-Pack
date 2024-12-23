package com.cursee.ender_pack.core.network.packet;

import com.cursee.ender_pack.platform.Services;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;

import java.util.concurrent.atomic.AtomicBoolean;

public class FabricOpenEnderPackC2SPacket {
	
	public static void receive(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {

		final AtomicBoolean SHOULD_OPEN_ENDER_PACK = new AtomicBoolean(false);

		// CHESTPLATE SLOT
		player.getArmorSlots().forEach(itemStack -> {
			if (itemStack.is(Services.PLATFORM.getRegisteredEnderPackItem())) SHOULD_OPEN_ENDER_PACK.set(true);
		});

		// TRINKET SLOT
		TrinketsApi.getTrinketComponent(player).ifPresent(component -> {
			if (component.isEquipped(Services.PLATFORM.getRegisteredEnderPackItem())) SHOULD_OPEN_ENDER_PACK.set(true);
		});

		if (!SHOULD_OPEN_ENDER_PACK.get()) return;

		player.openMenu(new SimpleMenuProvider(
				(containerID, playerInventory, player1) -> ChestMenu.threeRows(containerID, playerInventory, player.getEnderChestInventory()),
				Component.translatable("container.enderPack"))
		);
	}

}
