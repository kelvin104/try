package com.mod1.try1.entity.custom;

import com.mod1.try1.Main;
import com.mod1.try1.entity.ModEntities;
import com.mod1.try1.item.ModItemGroup;
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
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Predicate;

public class mod_villager_neutral extends AbstractIllager implements NeutralMob {

    private static final float MOB_HEALTH = 8.0F;
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;
    private static final EntityDataAccessor<Integer> DATA_REMAINING_ANGER_TIME = SynchedEntityData.defineId(ModEntities.MOD_VILLAGER_NEUTRAL.get().getBaseClass(), EntityDataSerializers.INT);
    static final Predicate<ItemEntity> ALLOWED_ITEMS = (p_37872_) -> {
        return !p_37872_.hasPickUpDelay() && p_37872_.isAlive() && ItemStack.matches(p_37872_.getItem(), ItemStack.EMPTY);
    };

    public mod_villager_neutral(EntityType<mod_villager_neutral> p_35267_, Level p_35268_) {
        super(p_35267_, p_35268_);

    }

    //public static final AttributeSupplier MAP = AttributeSupplier.builder()
    //      .add(Attributes.MOVEMENT_SPEED, 4.0)
    //    .add(Attributes.FOLLOW_RANGE, 20.0)
    //  .add(Attributes.MAX_HEALTH, 20.0D)
    //.add(Attributes.ATTACK_DAMAGE, 0.1D)
    //.build();


    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor p_34088_, DifficultyInstance p_34089_, MobSpawnType p_34090_, @Nullable SpawnGroupData p_34091_, @Nullable CompoundTag p_34092_) {
        SpawnGroupData spawngroupdata = super.finalizeSpawn(p_34088_, p_34089_, p_34090_, p_34091_, p_34092_);
        ((GroundPathNavigation)this.getNavigation()).setCanOpenDoors(true);
        this.populateDefaultEquipmentSlots(p_34089_);
        // this.populateDefaultEquipmentEnchantments(p_34089_);
        return spawngroupdata;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.23D).add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ATTACK_DAMAGE, 1D);
    }


    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(3, new MoveTowardsTargetGoal(this, 0.9D, 32.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, this::isAngryAt));
        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Zombie.class, false));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void die(DamageSource p_37847_) {
        super.die(p_37847_);
    }

    @Override
    public void applyRaidBuffs(int p_37844_, boolean p_37845_) {

    }


    protected void populateDefaultEquipmentSlots(DifficultyInstance p_34084_) {
        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
    }


    public AbstractIllager.IllagerArmPose getArmPose(){
      //  if (this.isAggressive()) {
        //    return AbstractIllager.IllagerArmPose.ATTACKING;
        //}
        return AbstractIllager.IllagerArmPose.NEUTRAL;
    }


    //@Override
    public boolean doHurtTarget(Entity p_30372_) {
        boolean flag = p_30372_.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            //p_30372_.setDeltaMovement(p_30372_.getDeltaMovement().add(0.0D, (double)0.4F, 0.0D));
            this.doEnchantDamageEffects(this, p_30372_);
        }

        return flag;
    }

    //@Override
    public boolean hurt(DamageSource p_30386_, float p_30387_) {
        if (this.isInvulnerableTo(p_30386_)) {
            return false;
        } else {


            return super.hurt(p_30386_, 1.0F);
        }
    }

    //@Override
    public SoundEvent getCelebrateSound() {
        return null;
    }

    //@Override
    public void aiStep() {
        super.aiStep();
        if (!this.level.isClientSide) {
            this.updatePersistentAnger((ServerLevel)this.level, true);
        }

    }

    //@Override
    protected SoundEvent getAmbientSound() {
        if (this.isAngry()||this.isAggressive()) {
            return SoundEvents.CREEPER_PRIMED;

        } else {
            return SoundEvents.WOLF_DEATH;
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

    protected void playStepSound(BlockPos p_30415_, BlockState p_30416_) {
        this.playSound(SoundEvents.FIRE_AMBIENT, 0.15F, 1.0F);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(DATA_REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int p_30404_) {
        this.entityData.set(DATA_REMAINING_ANGER_TIME, p_30404_);
    }

    @Override
    @Nullable
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

    @Override
    public boolean isAngryAt(LivingEntity p_21675_) {
        return NeutralMob.super.isAngryAt(p_21675_);
    }
}
