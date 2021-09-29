package com.mod1.try1.world.gen;

import net.minecraftforge.common.ForgeConfigSpec;

public class ModOreConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Boolean> MOD_ORE_GENERATION;
    public static final ForgeConfigSpec.ConfigValue<Integer> MOD_ORE_SIZE;
    public static final ForgeConfigSpec.ConfigValue<Integer> MOD_ORE_MIN_HEIGHT;
    public static final ForgeConfigSpec.ConfigValue<Integer> MOD_ORE_MAX_HEIGHT;
    public static final ForgeConfigSpec.ConfigValue<Integer> MOD_ORE_CHANCE;

    public ModOreConfig() {
    }

    static {
        BUILDER.push("Mod ore generation");
        MOD_ORE_GENERATION = BUILDER.define("Generate mod ore", true);
        MOD_ORE_SIZE = BUILDER.define("Mod ore vein size", 8);
        MOD_ORE_MIN_HEIGHT = BUILDER.define("Minimum mod ore generation height", 1);
        MOD_ORE_MAX_HEIGHT = BUILDER.define("Maximum mod ore generation height", 70);
        MOD_ORE_CHANCE = BUILDER.define("Chance of generate mod ore", 99);
        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
