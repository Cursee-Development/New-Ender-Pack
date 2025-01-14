package com.cursee.ender_pack.core.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModTabsFabric {

    public static void register() {}

    public static final CreativeModeTab ENDER_PACK = RegistryFabric.registerTab("ender_pack", () -> FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItemsFabric.ENDER_PACK))
            .title(Component.translatable("itemGroup.enderPack"))
            .displayItems(((itemDisplayParameters, output) -> output.accept(ModItemsFabric.ENDER_PACK))).build());
}
