package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.item.custom.EnderPackItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModItemsForge {

    public static void register() {}

    public static final DeferredHolder<Item, Item> ENDER_PACK = RegistryForge.registerItem("ender_pack", () -> new EnderPackItem((ArmorMaterial) ArmorMaterials.LEATHER.value(), ArmorItem.Type.CHESTPLATE, new Item.Properties()));
}
