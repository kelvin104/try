package com.mod1.try1.entity;

import com.mod1.try1.Main;
import com.mod1.try1.entity.custom.mod_villager_class;
import com.mod1.try1.entity.custom.mod_villager_jaw;
import com.mod1.try1.entity.custom.mod_villager_neutral;
import com.mod1.try1.entity.custom.mod_villager_none;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);


    public static final RegistryObject<EntityType<mod_villager_class>> MOD_VILLAGER = ENTITY_TYPES.register("mod_villager",
            () -> EntityType.Builder.<mod_villager_class>of(mod_villager_class::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(8)
                    .build(new ResourceLocation(Main.MOD_ID,"mod_villager").toString())
    );
    public static final RegistryObject<EntityType<mod_villager_neutral>> MOD_VILLAGER_NEUTRAL = ENTITY_TYPES.register("mod_villager_neutral",
            () -> EntityType.Builder.<mod_villager_neutral>of(mod_villager_neutral::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(8)
                    .build(new ResourceLocation(Main.MOD_ID,"mod_villager_neutral").toString())
    );
    public static final RegistryObject<EntityType<mod_villager_jaw>> MOD_VILLAGER_JAW = ENTITY_TYPES.register("mod_villager_jaw",
            () -> EntityType.Builder.<mod_villager_jaw>of(mod_villager_jaw::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(8)
                    .build(new ResourceLocation(Main.MOD_ID,"mod_villager_jaw").toString())
    );
    public static final RegistryObject<EntityType<mod_villager_none>> MOD_VILLAGER_NONE = ENTITY_TYPES.register("mod_villager_none",
            () -> EntityType.Builder.<mod_villager_none>of(mod_villager_none::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(8)
                    .build(new ResourceLocation(Main.MOD_ID,"mod_villager_none").toString())
    );


   // public static final RegistryObject<EntityType<mod_villager_neutral>> MOD_VILLAGER_NEUTRAL = ENTITY_TYPES.register("mod_villager_neutral",
     //       () -> EntityType.Builder.<~>of(mod_villager_neutral::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(8)
       //             .build(new ResourceLocation(Main.MOD_ID,"mod_villager_neutral").toString())
         //   );

}
