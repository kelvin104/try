package com.mod1.try1.effect.custom;

import com.google.common.collect.Maps;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IGenericEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.UUID;

public class sickness_effect_class extends MobEffect {
    private final Map<Attribute, AttributeModifier> attributeModifiers = Maps.newHashMap();
    public sickness_effect_class() {


        super(MobEffectCategory.HARMFUL, 7033103);
        addAttributeModifier(Attributes.MOVEMENT_SPEED,"7107DE1E-7CE2-4040-930E-578C1F160123", -2D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        addAttributeModifier(Attributes.MAX_HEALTH,"7107DE1E-7CE2-4040-930E-578C1F160121", 1D, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }

  //  @Override
   // public void applyEffectTick(LivingEntity entity, int p_19468_) {
    //    if(entity instanceof Player){
     //       entity.add;
      //  }
    //}


    @ParametersAreNonnullByDefault
    @MethodsReturnNonnullByDefault
    public MobEffect addAttributeModifier(Attribute p_19473_, String p_19474_, double p_19475_, AttributeModifier.Operation p_19476_) {
        AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(p_19474_), this::getDescriptionId, p_19475_, p_19476_);
        this.attributeModifiers.put(p_19473_, attributemodifier);
        return this;
    }
}
