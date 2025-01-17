package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.block.custom.entity.EnderPackBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlockEntityTypesForge {

    public static void register() {}

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<EnderPackBlockEntity>> ENDER_PACK = RegistryForge.registerBlockEntityType("ender_pack", () -> BlockEntityType.Builder.of(EnderPackBlockEntity::new, ModBlocksForge.ENDER_PACK.get()).build(null));
}
