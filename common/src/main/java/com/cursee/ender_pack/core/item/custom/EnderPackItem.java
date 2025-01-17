package com.cursee.ender_pack.core.item.custom;

import com.cursee.ender_pack.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.gameevent.GameEvent;

public class EnderPackItem extends Item implements Equipable {

    protected final ArmorItem.Type type;

    public EnderPackItem(ArmorMaterial armorMaterial, ArmorItem.Type armorItemType, Properties itemProperties) {
        super(itemProperties);
        this.type = armorItemType;
    }

    @Override
    public EquipmentSlot getEquipmentSlot() {
        return this.type.getSlot();
    }

//    @Override
//    public SoundEvent getEquipSound() {
//        return SoundEvents.ARMOR_EQUIP_LEATHER;
//    }


    @Override
    public Holder<SoundEvent> getEquipSound() {
        return SoundEvents.ARMOR_EQUIP_LEATHER;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {

        player.openMenu(new SimpleMenuProvider(
                (containerID, playerInventory, player1) -> ChestMenu.threeRows(containerID, playerInventory, player.getEnderChestInventory()),
                Component.translatable("container.enderPack"))
        );

        return InteractionResultHolder.pass(player.getItemInHand(interactionHand));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {

        final Level level = context.getLevel();
        final Player player = context.getPlayer();
        final BlockPos clickedPos = context.getClickedPos();
        final BlockPos relativePos = clickedPos.relative(context.getClickedFace());

        final InteractionResult RETURNED_RESULT = InteractionResult.sidedSuccess(level.isClientSide());
        final BlockState blockState = Services.PLATFORM.getRegisteredEnderPackBlock().defaultBlockState();

        if (player == null) return RETURNED_RESULT;

        if (player.isShiftKeyDown() && level.isEmptyBlock(relativePos)) {

            if (level.setBlockAndUpdate(relativePos, blockState.setValue(BlockStateProperties.HORIZONTAL_FACING, player.getDirection().getOpposite()))) {
                player.playSound(SoundEvents.ENDER_CHEST_CLOSE, 0.5f, 0.5f);
                level.gameEvent(player, GameEvent.BLOCK_PLACE, relativePos);
                context.getItemInHand().shrink(1);
            }

            return RETURNED_RESULT;
        }

        player.openMenu(new SimpleMenuProvider(
                (containerID, playerInventory, player1) -> ChestMenu.threeRows(containerID, playerInventory, player.getEnderChestInventory()),
                Component.translatable("container.enderPack"))
        );

        return RETURNED_RESULT;
    }
}
