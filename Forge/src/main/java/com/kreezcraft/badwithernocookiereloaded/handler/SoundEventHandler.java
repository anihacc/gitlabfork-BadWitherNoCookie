package com.kreezcraft.badwithernocookiereloaded.handler;

import com.kreezcraft.badwithernocookiereloaded.BWNCR_Config;
import com.kreezcraft.badwithernocookiereloaded.CommonClass;
import com.kreezcraft.badwithernocookiereloaded.ProxyPlaySoundEvent;
import com.kreezcraft.badwithernocookiereloaded.SoundHandler;
import net.minecraftforge.client.event.sound.PlaySoundEvent;


public class SoundEventHandler {

	public static void onSoundEvent(PlaySoundEvent event) {
		SoundHandler.silenceWither = BWNCR_Config.GENERAL.silenceWither.get();
		SoundHandler.silenceTrader = BWNCR_Config.GENERAL.silenceTrader.get();
		SoundHandler.silenceDragon = BWNCR_Config.GENERAL.silenceDragon.get();
		SoundHandler.silenceLightning = BWNCR_Config.GENERAL.silenceLightning.get();
		SoundHandler.silenceUs = BWNCR_Config.GENERAL.silenceUs.get();
		CommonClass.debugMode = BWNCR_Config.GENERAL.debugMode.get();

		ProxyPlaySoundEvent proxy = SoundHandler.onPlaySound(new ProxyPlaySoundEvent(event.getName(), event.getOriginalSound()));
		event.setSound(proxy.getSound());
	}
}
