package com.cursee.ender_pack.platform;

import com.cursee.ender_pack.core.block.custom.entity.EnderPackBlockEntity;
import com.cursee.ender_pack.core.registry.ModBlockEntityTypesFabric;
import com.cursee.ender_pack.core.registry.ModBlocksFabric;
import com.cursee.ender_pack.core.registry.ModItemsFabric;
import com.cursee.ender_pack.platform.services.IPlatformHelper;
import dev.emi.trinkets.api.TrinketsApi;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.concurrent.atomic.AtomicBoolean;

public class FabricPlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Fabric";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public BlockEntityType<EnderPackBlockEntity> getRegisteredEnderPackBlockEntity() {
        return ModBlockEntityTypesFabric.ENDER_PACK;
    }

    @Override
    public Block getRegisteredEnderPackBlock() {
        return ModBlocksFabric.ENDER_PACK;
    }

    @Override
    public Item getRegisteredEnderPackItem() {
        return ModItemsFabric.ENDER_PACK;
    }

    @Override
    public boolean checkSlotsFromMods(LivingEntity entity) {

        final AtomicBoolean TRINKET_FOUND = new AtomicBoolean(false);

        TrinketsApi.getTrinketComponent(entity).ifPresent(component -> {
            if (component.isEquipped(Services.PLATFORM.getRegisteredEnderPackItem())) TRINKET_FOUND.set(true);
        });

        return TRINKET_FOUND.get();
    }
}
