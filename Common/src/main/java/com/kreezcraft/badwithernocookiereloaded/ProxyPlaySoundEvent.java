package com.kreezcraft.badwithernocookiereloaded;

import net.minecraft.client.resources.sounds.SoundInstance;

public class ProxyPlaySoundEvent {
	private final String name;
	private final SoundInstance originalSound;
	private SoundInstance sound;

	public ProxyPlaySoundEvent(String name, SoundInstance originalSound) {
		this.name = name;
		this.originalSound = originalSound;
		this.setSound(originalSound);
	}

	public String getName() {
		return name;
	}

	public SoundInstance getOriginalSound()
	{
		return originalSound;
	}

	public SoundInstance getSound()
	{
		return sound;
	}

	public void setSound(SoundInstance result)
	{
		this.sound = result;
	}
}
