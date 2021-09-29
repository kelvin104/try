package com.mod1.try1.block;

import com.mod1.try1.Main;
import com.mod1.try1.block.custom.new_dirt_block_class;
import com.mod1.try1.item.ModItemGroup;
import com.mod1.try1.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS,Main.MOD_ID);



    public static final RegistryObject<Block> ITEM_ONE_ORE = registerBlock("item_one_ore",
            ()-> new OreBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .sound(SoundType.METAL)
                    .requiresCorrectToolForDrops()
                    .strength(2f)
                   ));


    public static final RegistryObject<Block> NEW_DIRT = registerBlock("new_dirt",
            ()-> new new_dirt_block_class(BlockBehaviour.Properties.of(Material.DIRT)
                    .sound(SoundType.GRASS)
                    .strength(0.5f)
            ));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block>void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () ->  new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.MOD1_GROUP)));
    }


    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }

}
