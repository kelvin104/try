package com.mod1.try1.effect;

import com.mod1.try1.Main;
import com.mod1.try1.effect.custom.sickness_effect_class;
import com.mod1.try1.item.ModItemGroup;
import com.mod1.try1.item.ModItems;
import com.mod1.try1.item.custom.mod_bottle_class;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MOD_ID);

    public static final RegistryObject<MobEffect> SICKNESS_EFFECT = MOB_EFFECTS.register("sickness_effect", sickness_effect_class::new);
            //   (new sickness_effect_class()).addAttributeModifier(Attributes.MOVEMENT_SPEED,"7107DE1E-7CE2-4040-930E-578C1F160123", -2D, AttributeModifier.Operation.MULTIPLY_TOTAL));


   public static final RegistryObject<Potion> MOD_BOTTLE = POTIONS.register("mod_bottle",
           () -> new Potion(new MobEffectInstance(SICKNESS_EFFECT.get(),1200,1)));




    /*
    private static <T extends Potion>RegistryObject<T> registerPotion(String name, Supplier<T> potion){
        RegistryObject<T> toReturn = POTIONS.register(name, potion);
        registerPotionItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Potion>void registerPotionItem(String name, RegistryObject<T> potion){
        ModItems.ITEMS.register(name, () ->  new PotionItem(new Item.Properties().tab(ModItemGroup.MOD1_GROUP)));
    }


    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }*/
}
