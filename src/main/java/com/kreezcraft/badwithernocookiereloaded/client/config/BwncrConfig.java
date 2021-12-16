package com.kreezcraft.badwithernocookiereloaded.client.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import org.lwjgl.system.CallbackI;

import java.util.ArrayList;
import java.util.List;

@Config(name = "badwithernocookiereloaded")
public class BwncrConfig implements ConfigData {
    public boolean debug = false;
    public List<String> soundsToSilence = new ArrayList<>(
            List.of("entity.wither.spawn", "entity.wither.death", "entity.enderdragon.death", "entity.lightning.thunder")
    );
}
