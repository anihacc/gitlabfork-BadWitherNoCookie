package com.kreezcraft.badwithernocookiereloaded.handler;

import com.kreezcraft.badwithernocookiereloaded.BWNCR_Config;
import com.kreezcraft.badwithernocookiereloaded.ProxyPlaySoundEvent;
import com.kreezcraft.badwithernocookiereloaded.SoundHandler;
import me.shedaniel.autoconfig.AutoConfig;


public class SoundEventHandler {

    public static ProxyPlaySoundEvent onSoundEvent(ProxyPlaySoundEvent event) {
        BWNCR_Config config = AutoConfig.getConfigHolder(BWNCR_Config.class).getConfig();
        SoundHandler.silenceWither = config.general.silenceWither;
        SoundHandler.silenceTrader = config.general.silenceTrader;
        SoundHandler.silenceDragon = config.general.silenceDragon;
        SoundHandler.silenceLightning = config.general.silenceLightning;
        SoundHandler.silenceUs = config.general.silenceUs;

        ProxyPlaySoundEvent proxy = SoundHandler.onPlaySound(event);
        return proxy;
    }
}
