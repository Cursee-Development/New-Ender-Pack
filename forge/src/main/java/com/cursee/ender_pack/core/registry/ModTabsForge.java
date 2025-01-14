package com.cursee.ender_pack.core.registry;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class ModTabsForge {

    public static void register() {}

    public static final RegistryObject<CreativeModeTab> ENDER_PACK = RegistryForge.registerTab("ender_pack", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItemsForge.ENDER_PACK.get()))
            .title(Component.translatable("itemGroup.enderPack"))
            .displayItems(((itemDisplayParameters, output) -> output.accept(ModItemsForge.ENDER_PACK.get()))).build());
}
