package com.cursee.ender_pack.core.registry;

import com.cursee.ender_pack.core.block.custom.EnderPackBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ModBlocksForge {

    public static void register() {}

    public static final DeferredHolder<Block, Block> ENDER_PACK = RegistryForge.registerBlock("ender_pack", () -> new EnderPackBlock(BlockBehaviour.Properties.of()
            .mapColor(MapColor.COLOR_PURPLE)
            .instrument(NoteBlockInstrument.BASEDRUM)
            .strength(0.8f)
            .lightLevel(blockState -> 7))
    );
}
