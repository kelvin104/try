package com.mod1.try1.world.gen;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
/*
public class ModOreGeneration {
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
    }
}

 */