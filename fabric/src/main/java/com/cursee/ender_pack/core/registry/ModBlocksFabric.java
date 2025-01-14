package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.block.custom.EnderPackBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlocksFabric {

    public static void register() {}

    public static final Block ENDER_PACK = RegistryFabric.registerBlock("ender_pack", () -> new EnderPackBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(0.8f)
            .lightLevel(blockState -> 7))
    );
}
