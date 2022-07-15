package com.kreezxil.bwncr.forge;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.List;

public class BWNCRConfigImpl {
    ForgeConfigSpec.ConfigValue<Boolean> debug;
    ForgeConfigSpec.ConfigValue<List<? extends String>> soundsToSilence;

    public BWNCRConfigImpl(ForgeConfigSpec.Builder builder) {
        debug = builder.comment("Modwide debug mode").define("debug", false);
        soundsToSilence = builder.comment("a comma separated and quoted list of sound event ids")
                .defineListAllowEmpty(List.of("soundsToSilence"), () -> List.of("entity.wither.spawn", "entity.wither.death", "entity.enderdragon.death", "entity.lightning.thunder"), o -> o instanceof String);
    }


}
