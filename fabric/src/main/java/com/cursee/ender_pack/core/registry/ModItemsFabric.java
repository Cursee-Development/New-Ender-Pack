package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.item.custom.EnderPackItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;

public class ModItemsFabric {

    public static void register() {}

    public static final Item ENDER_PACK = RegistryFabric.registerItem("ender_pack", () -> new EnderPackItem(ArmorMaterials.LEATHER.value(), ArmorItem.Type.CHESTPLATE, new Item.Properties()));
}
