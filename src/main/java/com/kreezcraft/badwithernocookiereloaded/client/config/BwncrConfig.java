package com.kreezcraft.badwithernocookiereloaded.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

import java.util.ArrayList;
import java.util.List;

@Config(name = "badwithernocookiereloaded")
public class BwncrConfig implements ConfigData {
    @Comment("Modwide debug mode")
    public boolean debug = false;

    @Comment("a comma separated and quoted list of sound event ids")
    public List<String> soundsToSilence = new ArrayList<>(
            List.of("entity.wither.spawn", "entity.wither.death", "entity.enderdragon.death", "entity.lightning.thunder")
    );
}
