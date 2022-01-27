package com.kreezcraft.badwithernocookiereloaded;

import com.kreezcraft.badwithernocookiereloaded.event.PlaySoundCallback;
import com.kreezcraft.badwithernocookiereloaded.handler.SoundEventHandler;
import net.fabricmc.api.ClientModInitializer;

public class BadWitherNoCookieClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		PlaySoundCallback.EVENT.register((event) -> {
			ProxyPlaySoundEvent proxyEvent = SoundEventHandler.onSoundEvent(event);
			return proxyEvent.getSound();
		});
	}

}
