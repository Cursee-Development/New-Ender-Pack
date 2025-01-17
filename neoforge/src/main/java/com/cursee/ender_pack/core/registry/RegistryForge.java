package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class RegistryForge {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, Constants.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, Constants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Constants.MOD_ID);

    public static void register(IEventBus modEventBus) {

        ModBlocksForge.register();
        ModBlockEntityTypesForge.register();
        ModItemsForge.register();
        ModTabsForge.register();

        BLOCKS.register(modEventBus);
        BLOCK_ENTITY_TYPES.register(modEventBus);
        ITEMS.register(modEventBus);
        TABS.register(modEventBus);
    }

    public static <T extends Block> DeferredHolder<Block, T> registerBlock(String objectID, Supplier<T> objectSupplier) {
        return BLOCKS.register(objectID, objectSupplier);
    }

    public static <T extends BlockEntityType<?>> DeferredHolder<BlockEntityType<?>, T> registerBlockEntityType(String objectID, Supplier<T> objectSupplier) {
        return BLOCK_ENTITY_TYPES.register(objectID, objectSupplier);
    }

    public static <T extends Item> DeferredHolder<Item, T> registerItem(String objectID, Supplier<T> objectSupplier) {
        return ITEMS.register(objectID, objectSupplier);
    }

    public static <T extends CreativeModeTab> DeferredHolder<CreativeModeTab, T> registerTab(String objectID, Supplier<T> objectSupplier) {
        return TABS.register(objectID, objectSupplier);
    }
}
