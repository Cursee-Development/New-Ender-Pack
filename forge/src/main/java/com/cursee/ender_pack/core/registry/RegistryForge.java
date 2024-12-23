package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class RegistryForge {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Constants.MOD_ID);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Constants.MOD_ID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Constants.MOD_ID);
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

    public static <T extends Block> RegistryObject<T> registerBlock(String objectID, Supplier<T> objectSupplier) {
        return BLOCKS.register(objectID, objectSupplier);
    }

    public static <T extends BlockEntityType<?>> RegistryObject<T> registerBlockEntityType(String objectID, Supplier<T> objectSupplier) {
        return BLOCK_ENTITY_TYPES.register(objectID, objectSupplier);
    }

    public static <T extends Item> RegistryObject<T> registerItem(String objectID, Supplier<T> objectSupplier) {
        return ITEMS.register(objectID, objectSupplier);
    }

    public static <T extends CreativeModeTab> RegistryObject<T> registerTab(String objectID, Supplier<T> objectSupplier) {
        return TABS.register(objectID, objectSupplier);
    }
}
