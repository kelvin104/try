package com.mod1.try1.client.entity;


import com.mod1.try1.Main;
import com.mod1.try1.client.entity.model.new_mob_1_model;
import com.mod1.try1.entity.custom.new_mob_1_class;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public class new_mob_1_renderer extends MobRenderer<new_mob_1_class, new_mob_1_model> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/entity/new_mob_1.png");

    public new_mob_1_renderer(EntityRendererProvider.Context context) {
        super(context, new new_mob_1_model(context.getModelSet().bakeLayer(new_mob_1_model.LAYER)), 0.5f);
    }



    @Nullable
    @Override
    public ResourceLocation getTextureLocation(new_mob_1_class entity) {
        return TEXTURE;
    }
}