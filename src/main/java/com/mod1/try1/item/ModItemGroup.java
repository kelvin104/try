package com.mod1.try1.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItemGroup {
    public static final CreativeModeTab MOD1_GROUP = new CreativeModeTab("mod1tab") {
        @Override
        public ItemStack makeIcon() {

        return new ItemStack(ModItems.ITEM_ONE.get());

        }
    };
}
