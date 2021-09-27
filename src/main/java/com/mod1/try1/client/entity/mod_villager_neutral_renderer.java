package com.mod1.try1.client.entity;


import com.mod1.try1.Main;
import com.mod1.try1.client.entity.model.mod_villager_model;
import com.mod1.try1.client.entity.model.mod_villager_neutral_model;
import com.mod1.try1.entity.custom.mod_villager_class;
import com.mod1.try1.entity.custom.mod_villager_neutral;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class mod_villager_neutral_renderer extends MobRenderer<mod_villager_neutral, mod_villager_neutral_model<mod_villager_neutral>> {
    //private static final ResourceLocation MOD_VILLAGER = new ResourceLocation(Main.MOD_ID,"textures/entity/modvillager/modvillager.png");
    private static final ResourceLocation MOD_VILLAGER_NEUTRAL = new ResourceLocation(Main.MOD_ID,"textures/entity/modvillager/modvillager_n.png");
    // private static final ModelLayerLocation MOD_VILLAGER_MODEL = new ModelLayerLocation(MOD_VILLAGER,"mod_villager");
    public mod_villager_neutral_renderer(EntityRendererProvider.Context p_174439_) {
        //super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);
        //  super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(MOD_VILLAGER_MODEL)), 0.5F);
        super(p_174439_, new mod_villager_neutral_model<>(p_174439_.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);


    }

    @Override
    public ResourceLocation getTextureLocation(mod_villager_neutral p_114482_) {
        return MOD_VILLAGER_NEUTRAL;
    }


//
    //   @Override
    //  public ResourceLocation getTextureLocation(Entity p_114482_) {
    //      return MOD_VILLAGER;
    //  }

    // public ResourceLocation getTextureLocation(mod_villager_class p_116324_) {
    //   return MOD_VILLAGER;
    //}
    /*
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();

    private static ModelLayerLocation register(String p_171294_) {
        return register(p_171294_, "main");
    }

    private static ModelLayerLocation register(String p_171296_, String p_171297_) {
        ModelLayerLocation modellayerlocation =
                new ModelLayerLocation(new ResourceLocation("minecraft", p_171296_), p_171297_);
        if (!ALL_MODELS.add(modellayerlocation)) {
            throw new IllegalStateException("Duplicate registration for " + modellayerlocation);
        } else {
            return modellayerlocation;
        }
    }
    */
}