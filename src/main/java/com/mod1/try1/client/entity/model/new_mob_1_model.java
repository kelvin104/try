package com.mod1.try1.client.entity.model;

import com.mod1.try1.Main;
import com.mod1.try1.entity.custom.new_mob_1_class;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;

public class new_mob_1_model extends EntityModel<new_mob_1_class> {
    private final ModelPart root;
    private final ModelPart hitbox;
    private final ModelPart mob;
    private final ModelPart h_head;
    private final ModelPart h_jaw;
    private final ModelPart legRight;
    private final ModelPart legLeft;
    private final ModelPart body;
    private final ModelPart rightArm;
    private final ModelPart leftArm;
    public new_mob_1_model(ModelPart modelPart) {
        this.root = modelPart;
        this.mob= modelPart.getChild("mob");
        this.hitbox = modelPart.getChild("hitbox");
        this.h_head = this.mob.getChild("h_head");
        this.h_jaw = this.h_head.getChild("h_jaw");
        this.legRight = this.mob.getChild("legRight");
        this.legLeft = this.mob.getChild("legLeft");
        this.body = this.mob.getChild("body");
        this.rightArm = this.mob.getChild("rightArm");
        this.leftArm = this.mob.getChild("leftArm");
        this.setRotationAngle(this.leftArm,-1.5708F, 0.0F, 0.0F);
    }

    public ModelPart root() {
        return this.root;
    }
    public static final String BODY = "body";
    public static ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(Main.MOD_ID, "new_mod_1"), BODY);
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("hitbox", CubeListBuilder.create()
                .texOffs(0, 2)
                .addBox(-3.0F, -17.0F, -3.0F, 6.0F, 16.0F, 6.0F), PartPose.rotation(0.0F,25.0F,1.0F));
        PartDefinition partdefinition1 = partdefinition.addOrReplaceChild("mob", CubeListBuilder.create(), PartPose.rotation(0.0F,10.0F,0F));
        PartDefinition partdefinition2= partdefinition1.addOrReplaceChild("h_head", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 4.0F), PartPose.rotation(0.0F,2.0F,1.0F));
        partdefinition2.addOrReplaceChild("h_jaw", CubeListBuilder.create()
                .texOffs(18,7)
                .addBox(-2.0F, 1.0F, -2.0F, 4.0F, 1.0F, 2.0F), PartPose.rotation(0.0F,-1.0F,-1.0F));

        partdefinition1.addOrReplaceChild("legRight", CubeListBuilder.create()
                .texOffs(16, 0)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F)
                .texOffs(18, 16)
                .addBox(-1.0F, 5.0F, -1.0F, 2.0F, 1.0F, 2.0F), PartPose.rotation(1.0F,8.0F,1.0F));
        partdefinition1.addOrReplaceChild("legLeft", CubeListBuilder.create()
                .texOffs(0, 16)
                .addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F)
                .texOffs(18, 13)
                .addBox(-1.0F, 5.0F, -1.0F, 2.0F, 1.0F, 2.0F), PartPose.rotation(1.0F,8.0F,1.0F));

        partdefinition1.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 8)
                .addBox(-2.0F, -3.0F, -1.0F, 4.0F, 6.0F, 2.0F), PartPose.rotation(0.0F,5.0F,1.0F));
        partdefinition1.addOrReplaceChild("rightArm",CubeListBuilder.create()
                .texOffs(10,15)
                .addBox(0.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.rotation(2.0F,-2.0F,0.0F));
        partdefinition1.addOrReplaceChild("leftArm", CubeListBuilder.create()
                .texOffs(12,8)
                .addBox(-2.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F), PartPose.rotation(-2.0F,-2.0F,0.0F));


       return LayerDefinition.create(meshdefinition, 32, 32);

    }


    public void setRotationAngle(ModelPart modelPart, float x, float y, float z) {
        modelPart.xRot = x;
        modelPart.yRot = y;
        modelPart.zRot = z;
    }

    @Override
    public void setupAnim(new_mob_1_class p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

    }

    @Override
    public void renderToBuffer(PoseStack p_103111_, VertexConsumer p_103112_, int p_103113_, int p_103114_, float p_103115_, float p_103116_, float p_103117_, float p_103118_) {
        hitbox.render(p_103111_, p_103112_, p_103113_, p_103114_, p_103115_,p_103116_, p_103117_, p_103118_);
        mob.render(p_103111_, p_103112_, p_103113_, p_103114_, p_103115_,p_103116_, p_103117_, p_103118_);
    }

}
