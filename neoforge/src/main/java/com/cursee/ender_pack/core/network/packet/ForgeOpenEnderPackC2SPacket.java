package com.cursee.ender_pack.core.network.packet;

import com.cursee.ender_pack.Constants;
import com.cursee.ender_pack.platform.Services;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

public class ForgeOpenEnderPackC2SPacket implements CustomPacketPayload {

    public static final Type<ForgeOpenEnderPackC2SPacket> TYPE = new Type<ForgeOpenEnderPackC2SPacket>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "ender_pack"));
    public static final StreamCodec<FriendlyByteBuf, ForgeOpenEnderPackC2SPacket> CODEC = StreamCodec.ofMember(ForgeOpenEnderPackC2SPacket::write, ForgeOpenEnderPackC2SPacket::read);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static ForgeOpenEnderPackC2SPacket read(FriendlyByteBuf buf) {
        return new ForgeOpenEnderPackC2SPacket();
    }

    public void write(FriendlyByteBuf buf) {
    }

    public static boolean handle(ForgeOpenEnderPackC2SPacket packet, IPayloadContext context) {

        context.enqueueWork(() -> {

            if (!(context.player() instanceof ServerPlayer)) return;
            ServerPlayer player = (ServerPlayer) context.player();

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

//    public ForgeOpenEnderPackC2SPacket() {}
//    public ForgeOpenEnderPackC2SPacket(FriendlyByteBuf buf) {}
//    public void toBytes(FriendlyByteBuf buf) {}
}
