package com.mod1.try1.item.custom;

import com.mod1.try1.block.ModBlocks;
import com.mod1.try1.item.ModItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;
import java.util.Random;

public class mod_collector_class extends Item {
    public mod_collector_class(Properties p_41383_) {
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
        if(clicked_block.getBlock() == ModBlocks.NEW_DIRT.get()){
            if(Math.random()<0.7){
                player_entity.getInventory().removeItem(context.getItemInHand());
            }
            else{
                player_entity.getInventory().removeItem(context.getItemInHand());
                player_entity.getInventory().add(new ItemStack(ModItems.MOD_COLLECTOR_FILLED.get()));
            }

           // player_entity.getInventory().add(new ItemStack())
        }
    }


}
