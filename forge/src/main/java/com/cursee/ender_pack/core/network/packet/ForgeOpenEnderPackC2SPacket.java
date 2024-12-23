package com.cursee.ender_pack.core.network.packet;

import com.cursee.ender_pack.platform.Services;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ForgeOpenEnderPackC2SPacket {

    public ForgeOpenEnderPackC2SPacket() {}
    public ForgeOpenEnderPackC2SPacket(FriendlyByteBuf buf) {}
    public void toBytes(FriendlyByteBuf buf) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

        NetworkEvent.Context context = supplier.get();

        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            final AtomicBoolean SHOULD_OPEN_ENDER_PACK = new AtomicBoolean(false);

            // CHESTPLATE SLOT
            player.getArmorSlots().forEach(itemStack -> {
                if (itemStack.is(Services.PLATFORM.getRegisteredEnderPackItem())) SHOULD_OPEN_ENDER_PACK.set(true);
            });

            // CURIOS SLOT
            if (Services.PLATFORM.isModLoaded("curios")) SHOULD_OPEN_ENDER_PACK.set(Services.PLATFORM.checkSlotsFromMods(player));

            if (!SHOULD_OPEN_ENDER_PACK.get()) return;

            player.openMenu(new SimpleMenuProvider(
                    (containerID, playerInventory, player1) -> ChestMenu.threeRows(containerID, playerInventory, player.getEnderChestInventory()),
                    Component.translatable("container.enderPack"))
            );
        });

        return true;
    }
}
