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
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Main.MOD_ID);
    public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS, Main.MOD_ID);

    public static final RegistryObject<MobEffect> SICKNESS= MOB_EFFECTS.register("sickness", sickness_effect_class::new);

   public static final RegistryObject<Potion> SICKNESS_POTION = POTIONS.register("sickness",
           () -> new Potion(new MobEffectInstance(SICKNESS.get(),1200,0)));
    public static final RegistryObject<Potion> LONG_SICKNESS_POTION = POTIONS.register("long_sickness",
            () -> new Potion(new MobEffectInstance(SICKNESS.get(),2400,0)));
    public static final RegistryObject<Potion> STRONG_SICKNESS_POTION = POTIONS.register("strong_sickness",
            () -> new Potion(new MobEffectInstance(SICKNESS.get(),1200,2)));

    public static void addPotionRecipes(){
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipes(Potions.AWKWARD,ModItems.MOD_COLLECTOR_FILLED.get(),SICKNESS_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipes(SICKNESS_POTION.get(),Items.GLOWSTONE_DUST,STRONG_SICKNESS_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new NewBrewingRecipes(SICKNESS_POTION.get(),Items.REDSTONE,LONG_SICKNESS_POTION.get()));


    }

    private static class NewBrewingRecipes implements IBrewingRecipe{
        private final Potion bottleInput;
        private final Item itemInput;
        private final ItemStack output;
        public NewBrewingRecipes(Potion bottleInput, Item itemInput, Potion outputIn){
            this.bottleInput=bottleInput;
            this.itemInput=itemInput;
            this.output = PotionUtils.setPotion(new ItemStack(Items.POTION),outputIn);
        }

        @Override
        public boolean isInput(ItemStack input) {
            return PotionUtils.getPotion(input).equals(this.bottleInput);
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem().equals(this.itemInput);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if(isInput(input)&&isIngredient(ingredient)){
                return this.output.copy();
            }else{
                return ItemStack.EMPTY;
            }
        }
    }



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
