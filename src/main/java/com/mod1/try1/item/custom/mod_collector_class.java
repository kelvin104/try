package com.mod1.try1.item.custom;

import com.mod1.try1.block.ModBlocks;
import com.mod1.try1.entity.custom.mod_villager_jaw;
import com.mod1.try1.entity.custom.mod_villager_none;
import com.mod1.try1.item.ModItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
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

    @Override
    public InteractionResult interactLivingEntity(ItemStack p_41398_, Player p_41399_, LivingEntity p_41400_, InteractionHand p_41401_) {
        if(p_41400_ instanceof mod_villager_jaw){
            if(Math.random()<0.8){
                //player_entity.playSound(SoundEvents.BOTTLE_EMPTY,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_AMBIENT,2.0F,1.0F);
                p_41399_.getInventory().removeItem(p_41398_);

            }
            else{
                p_41399_.getInventory().removeItem(p_41398_);
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_DEATH,2.0F,1.0F);
                p_41399_.getInventory().add(new ItemStack(ModItems.MOD_COLLECTOR_FILLED.get()));
            }
        }
        if(p_41400_ instanceof mod_villager_none){
            if(Math.random()<0.5){
                //player_entity.playSound(SoundEvents.BOTTLE_EMPTY,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_AMBIENT,2.0F,1.0F);
                p_41399_.getInventory().removeItem(p_41398_);

            }
            else{
                p_41399_.getInventory().removeItem(p_41398_);
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
                // player_entity.playSound(SoundEvents.PILLAGER_DEATH,2.0F,1.0F);
                p_41399_.getInventory().add(new ItemStack(ModItems.MOD_COLLECTOR_FILLED.get()));
            }
        }
        return super.interactLivingEntity(p_41398_, p_41399_, p_41400_, p_41401_);
    }

    private void rightClick1(BlockState clicked_block, UseOnContext context, Player player_entity) {
        if(clicked_block.getBlock() == ModBlocks.NEW_DIRT.get()){
            if(Math.random()<0.7){
                //player_entity.playSound(SoundEvents.BOTTLE_EMPTY,2.0F,1.0F);
               // player_entity.playSound(SoundEvents.PILLAGER_AMBIENT,2.0F,1.0F);
                player_entity.getInventory().removeItem(context.getItemInHand());

            }
            else{
                player_entity.getInventory().removeItem(context.getItemInHand());
                //player_entity.playSound(SoundEvents.BOTTLE_FILL,2.0F,1.0F);
               // player_entity.playSound(SoundEvents.PILLAGER_DEATH,2.0F,1.0F);
                player_entity.getInventory().add(new ItemStack(ModItems.MOD_COLLECTOR_FILLED.get()));
            }

           // player_entity.getInventory().add(new ItemStack())
        }
    }


}
