package com.mod1.try1.entity;

import com.mod1.try1.Main;
import com.mod1.try1.entity.custom.mod_villager_class;
import com.mod1.try1.entity.custom.new_mob_1_class;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

    public static final RegistryObject<EntityType<new_mob_1_class>> NEW_MOB_1 = ENTITY_TYPES.register("new_mob_1",
            () -> EntityType.Builder.<new_mob_1_class>of(new_mob_1_class::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(4)
                    .build(new ResourceLocation(Main.MOD_ID,"new_mob_1").toString())
    );
    public static final RegistryObject<EntityType<mod_villager_class>> MOD_VILLAGER = ENTITY_TYPES.register("mod_villager",
            () -> EntityType.Builder.<mod_villager_class>of(mod_villager_class::new, MobCategory.MONSTER).sized(0.6F,1.95F).clientTrackingRange(8)
                    .build(new ResourceLocation(Main.MOD_ID,"mod_villager").toString())
    );

}
