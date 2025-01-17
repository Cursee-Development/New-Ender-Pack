package com.cursee.ender_pack.platform;

import com.cursee.ender_pack.core.block.custom.entity.EnderPackBlockEntity;
import com.cursee.ender_pack.platform.services.IPlatformHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.concurrent.atomic.AtomicBoolean;

public class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {

        return "Forge";
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
        return null;
    }

    @Override
    public Block getRegisteredEnderPackBlock() {
        return null;
    }

    @Override
    public Item getRegisteredEnderPackItem() {
        return null;
    }

    @Override
    public boolean checkSlotsFromMods(LivingEntity entity) {
        return false;
    }
}