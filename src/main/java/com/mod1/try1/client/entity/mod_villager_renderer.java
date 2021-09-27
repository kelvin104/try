package com.mod1.try1.client.entity;

import com.google.common.collect.Sets;
import com.mod1.try1.Main;
import com.mod1.try1.client.entity.model.mod_villager_model;
import com.mod1.try1.entity.custom.mod_villager_class;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Vindicator;

import java.util.Set;


public class mod_villager_renderer extends MobRenderer<mod_villager_class, mod_villager_model<mod_villager_class>> {
    //private static final ResourceLocation MOD_VILLAGER = new ResourceLocation(Main.MOD_ID,"textures/entity/modvillager/modvillager.png");
    private static final ResourceLocation MOD_VILLAGER = new ResourceLocation(Main.MOD_ID,"textures/entity/modvillager/modvillagerv.png");
   // private static final ModelLayerLocation MOD_VILLAGER_MODEL = new ModelLayerLocation(MOD_VILLAGER,"mod_villager");
    public mod_villager_renderer(EntityRendererProvider.Context p_174439_) {
        //super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);
      //  super(p_174439_, new IllagerModel<>(p_174439_.bakeLayer(MOD_VILLAGER_MODEL)), 0.5F);
        super(p_174439_, new mod_villager_model<>(p_174439_.bakeLayer(ModelLayers.VINDICATOR)), 0.5F);

        this.addLayer(new ItemInHandLayer<>(this) {
        //this.addLayer(new ItemInHandLayer<mod_villager_class, IllagerModel<mod_villager_class>>(this) {
            public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, mod_villager_class p_116355_, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
                if (p_116355_.isAggressive()) {
                    super.render(p_116352_, p_116353_, p_116354_, p_116355_, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
                }

            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(mod_villager_class p_114482_) {
        return MOD_VILLAGER;
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

