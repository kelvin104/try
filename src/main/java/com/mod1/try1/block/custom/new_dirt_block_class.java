package com.mod1.try1.block.custom;

import com.mod1.try1.item.custom.complex_item_one_class;
import net.minecraft.FieldsAreNonnullByDefault;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.event.ColorHandlerEvent;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class new_dirt_block_class extends Block {
    public new_dirt_block_class(Properties properties) {
        super(properties);
    }



    @Override
    @ParametersAreNonnullByDefault
    public void stepOn(Level level, BlockPos block_pos, BlockState block_state, Entity entity) {
        complex_item_one_class.applyEffect(entity,5);
        if (entity instanceof Player){
            complex_item_one_class.applyEffect2((Player)entity);
        }
        super.stepOn(level, block_pos, block_state, entity);
    }

    @SuppressWarnings("deprecation")
    @Override
    @Nonnull
    @ParametersAreNonnullByDefault
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if(level.isClientSide()){
            if((hand == InteractionHand.MAIN_HAND) && (player.getMainHandItem().getItem() == Items.GLASS_BOTTLE) ){
                player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 200));

            }
        }


        return super.use(blockState, level, blockPos, player, hand, hitResult);
    }
}
