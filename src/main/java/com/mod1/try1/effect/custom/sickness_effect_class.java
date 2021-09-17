package com.mod1.try1.effect.custom;

import com.google.common.collect.Maps;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IGenericEvent;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Map;
import java.util.UUID;

public class sickness_effect_class extends MobEffect {
    public sickness_effect_class() {
        super(MobEffectCategory.HARMFUL, 7033103);
        this.addAttributeModifier(Attributes.MOVEMENT_SPEED,"7107DE1E-7CE2-4040-930E-578C1F160123", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL);
        this.addAttributeModifier(Attributes.ATTACK_DAMAGE,"7907DE9E-7CE2-9040-930E-978C1F160123", -0.5D, AttributeModifier.Operation.MULTIPLY_TOTAL);

    }





}
