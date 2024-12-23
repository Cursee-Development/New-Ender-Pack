package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.item.custom.EnderPackItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

public class ModItemsForge {

    public static void register() {}

    public static final RegistryObject<Item> ENDER_PACK = RegistryForge.registerItem("ender_pack", () -> new EnderPackItem(ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
}
