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
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

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
        // todo: implement curios functionality
        return false;
    }
}