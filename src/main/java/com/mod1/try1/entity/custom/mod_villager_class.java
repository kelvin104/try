package com.mod1.try1.entity.custom;

import com.mod1.try1.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.UUID;

public class mod_villager_class extends AbstractIllager implements NeutralMob {
    private static final float MOB_HEALTH = 8.0F;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(Wolf.class, EntityDataSerializers.INT);


    public mod_villager_class(EntityType<mod_villager_class> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);

    }

    public static final AttributeSupplier MAP = AttributeSupplier.builder()
            .add(Attributes.MOVEMENT_SPEED, 4.0)
            .add(Attributes.FOLLOW_RANGE, 20.0)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.ATTACK_DAMAGE, 0.1D)
            .build();




    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, (double)4.0F).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 0.1D);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Zombie.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }




    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    public void applyRaidBuffs(int p_37844_, boolean p_37845_) {

    }


    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_30404_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
    }

    protected void populateDefaultEquipmentSlots(DifficultyInstance p_34084_) {
            this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.WEAPON_ONE.get()));
    }

    @Nullable
    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }


    @Override
    public void setPersistentAngerTarget(@Nullable UUID p_30400_) {
        this.persistentAngerTarget = p_30400_;
    }


    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    public AbstractIllager.IllagerArmPose getArmPose(){
        if (this.isAggressive()) {
            return AbstractIllager.IllagerArmPose.ATTACKING;
        }
           return AbstractIllager.IllagerArmPose.NEUTRAL;
    }

    public boolean doHurtTarget(Entity p_30372_) {
        boolean flag = p_30372_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, p_30372_);
        }

        return flag;
    }
    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        if (this.isInvulnerableTo(p_30386_)) {
            return false;
        } else {


            return super.hurt(p_30386_, 1.0F);
        }
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return null;
    }

    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }

    }
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()||this.isAggressive()) {
            return SoundEvents.WITHER_SHOOT;

        } else {
            return SoundEvents.CAT_AMBIENT;
        }
    }

    protected SoundEvent getHurtSound(DamageSource p_30424_) {
        return SoundEvents.PIGLIN_ANGRY;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.RAID_HORN;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }
    public void addAdditionalSaveData(CompoundTag p_30418_) {
        super.addAdditionalSaveData(p_30418_);
        this.addPersistentAngerSaveData(p_30418_);
    }
    public void readAdditionalSaveData(CompoundTag p_30402_) {
        super.readAdditionalSaveData(p_30402_);
        this.readPersistentAngerSaveData(this.level, p_30402_);
    }
    protected void playStepSound(BlockPos p_30415_, BlockState p_30416_) {
        this.playSound(SoundEvents.FIRE_AMBIENT, 0.15F, 1.0F);
    }

}
