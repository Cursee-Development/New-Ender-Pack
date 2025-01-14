package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.function.Supplier;

public class RegistryFabric {

    public static void register() {
        ModBlocksFabric.register();
        ModBlockEntityTypesFabric.register();
        ModItemsFabric.register();
        ModTabsFabric.register();
    }

    public static <T extends Block> T registerBlock(String objectID, Supplier<T> objectSupplier) {
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, objectID), objectSupplier.get());
    }

    public static <T extends BlockEntityType<?>> T registerBlockEntityType(String objectID, Supplier<T> objectSupplier) {
        return Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, new ResourceLocation(Constants.MOD_ID, objectID), objectSupplier.get());
    }

    public static <T extends Item> T registerItem(String objectID, Supplier<T> objectSupplier) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, objectID), objectSupplier.get());
    }

    public static <T extends CreativeModeTab> T registerTab(String objectID, Supplier<T> objectSupplier) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, objectID), objectSupplier.get());
    }
}
