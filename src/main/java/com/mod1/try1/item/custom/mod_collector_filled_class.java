package com.mod1.try1.item.custom;

import com.mod1.try1.block.ModBlocks;
import com.mod1.try1.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import java.util.Objects;

public class mod_collector_filled_class extends Item {
    public mod_collector_filled_class(Properties p_41383_) {
        super(p_41383_);
    }
    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()){
            Player player_entity = Objects.requireNonNull(context.getPlayer());
            BlockState clicked_block = level.getBlockState(context.getClickedPos());
            rightClick1(clicked_block, context, player_entity);
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClick1(BlockState clicked_block, UseOnContext context, Player player_entity) {
        if(clicked_block.getBlock() == Blocks.GRASS_BLOCK){

                complex_item_one_class.applyEffect2(player_entity);
                player_entity.getInventory().removeItem(context.getItemInHand());
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
                player_entity.getInventory().add(new ItemStack(ModItems.MOD_COLLECTOR.get()));


            // player_entity.getInventory().add(new ItemStack())
        }
    }

}
