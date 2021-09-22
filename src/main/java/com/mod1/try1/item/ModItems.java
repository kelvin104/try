package com.mod1.try1.item;

import com.mod1.try1.Main;
import com.mod1.try1.item.custom.*;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);

    public static final RegistryObject<Item> ITEM_ONE = ITEMS.register("item_one",
            () -> new Item(new Item.Properties().tab(ModItemGroup.MOD1_GROUP)));

    public static final RegistryObject<Item> WEAPON_ONE = ITEMS.register("weapon_one",
            () -> new mod_weapon_class(ModItemTier.ITEM_ONE, 0,5f,new Item.Properties().tab(ModItemGroup.MOD1_GROUP)));

    public static final RegistryObject<Item> COMPLEX_ITEM_ONE = ITEMS.register("complex_item_one",
            () -> new complex_item_one_class(new Item.Properties().tab(ModItemGroup.MOD1_GROUP)));

    public static final RegistryObject<Item> MOD_COLLECTOR = ITEMS.register("mod_collector",
            () -> new mod_collector_class(new Item.Properties().tab(ModItemGroup.MOD1_GROUP).stacksTo(1)));

    public static final RegistryObject<Item> MOD_COLLECTOR_FILLED = ITEMS.register("mod_collector_filled",
            () -> new mod_collector_filled_class(new Item.Properties().tab(ModItemGroup.MOD1_GROUP).stacksTo(1)));

  /*  public static final RegistryObject<Item> MOD_BOTTLE_ITEM = ITEMS.register("mod_bottle_item",
            () -> new mod_bottle_class
                    (
                            (new Item.Properties())
                                    .craftRemainder(Items.GLASS_BOTTLE)
                                    .food((new FoodProperties.Builder()).nutrition(0).saturationMod(0F).build())
                                    .tab(ModItemGroup.MOD1_GROUP)
                                    .stacksTo(1)
                    )
    );
    */

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
