package com.cursee.ender_pack.core.block.custom.entity;

import com.cursee.ender_pack.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;

public class EnderPackBlockEntity extends BlockEntity implements BlockEntityTicker<EnderPackBlockEntity> {

    public EnderPackBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(Services.PLATFORM.getRegisteredEnderPackBlockEntity(), blockPos, blockState);
    }

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, EnderPackBlockEntity enderPackBlockEntity) {

        final ItemEntity ENDER_PEARL_ITEM_ENTITY = new ItemEntity(level, blockPos.getX(), blockPos.getY(), blockPos.getZ(), new ItemStack(Items.ENDER_PEARL));

        if (level.random.nextFloat() <= 0.00001f) {
            level.addFreshEntity(ENDER_PEARL_ITEM_ENTITY);
        }
    }
}
