package com.cursee.ender_pack.platform;

import com.cursee.ender_pack.core.block.custom.entity.EnderPackBlockEntity;
import com.cursee.ender_pack.core.registry.ModBlockEntityTypesForge;
import com.cursee.ender_pack.core.registry.ModBlocksForge;
import com.cursee.ender_pack.core.registry.ModItemsForge;
import com.cursee.ender_pack.platform.services.IPlatformHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicBoolean;

public class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "NeoForge";
    }

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.isProduction();
    }

    @Override
    public BlockEntityType<EnderPackBlockEntity> getRegisteredEnderPackBlockEntity() {
        return ModBlockEntityTypesForge.ENDER_PACK.get();
    }

    @Override
    public Block getRegisteredEnderPackBlock() {
        return ModBlocksForge.ENDER_PACK.get();
    }

    @Override
    public Item getRegisteredEnderPackItem() {
        return ModItemsForge.ENDER_PACK.get();
    }

    @Override
    public boolean checkSlotsFromMods(LivingEntity entity) {

        final AtomicBoolean CURIOS_FOUND = new AtomicBoolean(false);

        CuriosApi.getCuriosInventory(entity).ifPresent(iCuriosItemHandler -> {
            if (iCuriosItemHandler.isEquipped(ModItemsForge.ENDER_PACK.get())) CURIOS_FOUND.set(true);
        });

        return CURIOS_FOUND.get();
    }
}