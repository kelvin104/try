package com.mod1.try1.item;

import com.mod1.try1.Main;
import com.mod1.try1.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.extensions.IForgeItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> ITEM_ONE = ITEMS.register("item_one",
            () -> new Item(new Item.Properties().tab(ModItemGroup.MOD1_GROUP)));


    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
