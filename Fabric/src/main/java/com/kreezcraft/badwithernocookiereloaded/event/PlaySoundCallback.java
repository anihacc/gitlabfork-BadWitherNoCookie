package com.kreezcraft.badwithernocookiereloaded.event;

import com.kreezcraft.badwithernocookiereloaded.ProxyPlaySoundEvent;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.resources.sounds.SoundInstance;

public interface PlaySoundCallback {
	Event<PlaySoundCallback> EVENT = EventFactory.createArrayBacked(PlaySoundCallback.class,
			(listeners) -> (event) -> {
				for (PlaySoundCallback listener : listeners) {
					SoundInstance resultSound = listener.playSound(event);

					if(resultSound != event.getSound()) {
						return resultSound;
					}
				}

				return event.getSound();
			});

	SoundInstance playSound(ProxyPlaySoundEvent event);
}
