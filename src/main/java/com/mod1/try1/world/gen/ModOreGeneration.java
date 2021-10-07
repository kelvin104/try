package com.mod1.try1.world.gen;

import com.google.common.collect.ImmutableList;
import com.mod1.try1.Main;
import com.mod1.try1.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModOreGeneration {
    /*
    public static void generateOres(final BiomeLoadingEvent event) {
        for (OreType ore : OreType.values()) {

            OreConfiguration oreFeatureConfig = new OreConfiguration(
                    OreConfiguration.Predicates.STONE_ORE_REPLACEABLES,
                    ore.getBlock().get().defaultBlockState(), ore.getMaxVeinSize());

            // bottomOffset -> minimum height for the ore
            // maximum -> minHeight + maximum = top level (the vertical expansion of the ore, it grows x levels from bottomOffset)
            // topOffset -> subtracted from the maximum to give actual top level
            // ore effectively exists from bottomOffset to (bottomOffset + maximum - topOffset)
            ConfiguredPlacement<TopSolidRangeConfig> configuredPlacement = Placement.RANGE.configure(
                    new TopSolidRangeConfig(ore.getMinHeight(), ore.getMinHeight(), ore.getMaxHeight()));

            ConfiguredFeature<?, ?> oreFeature = registerOreFeature(ore, oreFeatureConfig, configuredPlacement);

            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreFeature);
        }
    }

    private static ConfiguredFeature<?, ?> registerOreFeature(OreType ore, OreConfiguration oreFeatureConfig,
                                                              ConfiguredPlacement configuredPlacement) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, ore.getBlock().get().getRegistryName(),
                Feature.ORE.withConfiguration(oreFeatureConfig).withPlacement(configuredPlacement)
                        .square().count(ore.getMaxVeinSize()));
    }*/
    public static ImmutableList<OreConfiguration.TargetBlockState> MOD_ORE_TARGET_LIST;
    public static ConfiguredFeature<?, ?> MOD_ORE;

    public ModOreGeneration() {
    }

    public static void registerConfiguredFeatures() {
        MOD_ORE_TARGET_LIST = ImmutableList.of(OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ((Block) ModBlocks.ITEM_ONE_ORE.get()).defaultBlockState()), OreConfiguration.target(OreConfiguration.Predicates.STONE_ORE_REPLACEABLES, ((Block) ModBlocks.ITEM_ONE_ORE.get()).defaultBlockState()));
        MOD_ORE = (ConfiguredFeature)( (ConfiguredFeature)((ConfiguredFeature) Feature.ORE.configured(new OreConfiguration(MOD_ORE_TARGET_LIST, (Integer) ModOreConfig.MOD_ORE_SIZE.get())).rangeUniform(VerticalAnchor.absolute((Integer) ModOreConfig.MOD_ORE_MIN_HEIGHT.get()), VerticalAnchor.absolute((Integer) ModOreConfig.MOD_ORE_MAX_HEIGHT.get()))).squared()).count((Integer) ModOreConfig.MOD_ORE_CHANCE.get());
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(Main.MOD_ID, "mod_ore"), MOD_ORE);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerBiomeModification(BiomeLoadingEvent event) {
        //event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> {
        registerConfiguredFeatures();
        event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES).add(() -> {
            return MOD_ORE;
        });
    }
}

