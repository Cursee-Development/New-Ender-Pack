package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.block.custom.entity.EnderPackBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ModBlockEntityTypesFabric {

    public static void register() {}

    public static final BlockEntityType<EnderPackBlockEntity> ENDER_PACK = RegistryFabric.registerBlockEntityType("ender_pack", () -> BlockEntityType.Builder.of(EnderPackBlockEntity::new, ModBlocksFabric.ENDER_PACK).build(null));
}
