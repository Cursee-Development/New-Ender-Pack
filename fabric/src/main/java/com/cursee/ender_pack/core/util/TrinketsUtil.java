package com.cursee.ender_pack.core.util;

import com.cursee.ender_pack.platform.Services;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.player.Player;

import java.util.concurrent.atomic.AtomicBoolean;

public class TrinketsUtil {

    public static boolean checkForTrinket(Player player) {

        final AtomicBoolean TRINKET_FOUND = new AtomicBoolean(false);

        TrinketsApi.getTrinketComponent(player).ifPresent(component -> {
            if (component.isEquipped(Services.PLATFORM.getRegisteredEnderPackItem())) TRINKET_FOUND.set(true);
        });

        return TRINKET_FOUND.get();
    }

}
