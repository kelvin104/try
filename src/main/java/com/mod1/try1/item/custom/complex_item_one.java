package com.mod1.try1.item.custom;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class complex_item_one extends Item {
    public complex_item_one(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide){
            Player player_entity = Objects.requireNonNull(context.getPlayer());
            BlockState clicked_block = level.getBlockState(context.getClickedPos());
            rightClick1(clicked_block, context, player_entity);
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClick1(BlockState clicked_block, UseOnContext context, Player player_entity) {
        applyEffect(player_entity,5);
        applyEffect2(player_entity);

    }

    ////for potion effect
    private void applyEffect2(Player player_entity) {
        player_entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200));
    }

    ////for entity effect
    public static void applyEffect(Entity entity, int second){
        entity.setSecondsOnFire(second);
    }
}
