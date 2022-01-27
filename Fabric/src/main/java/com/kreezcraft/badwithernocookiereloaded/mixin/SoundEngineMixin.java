package com.kreezcraft.badwithernocookiereloaded.mixin;

import com.kreezcraft.badwithernocookiereloaded.ProxyPlaySoundEvent;
import com.kreezcraft.badwithernocookiereloaded.event.PlaySoundCallback;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SoundEngine.class)
public class SoundEngineMixin {

	@Inject(method = "play(Lnet/minecraft/client/resources/sounds/SoundInstance;)V", at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/resources/sounds/SoundInstance;canPlaySound()Z",
			shift = Shift.BEFORE,
			ordinal = 0), cancellable = true)
	public void play(SoundInstance soundInstance, CallbackInfo ci) {
		soundInstance = PlaySoundCallback.EVENT.invoker().playSound(new ProxyPlaySoundEvent(soundInstance.getLocation().getPath(), soundInstance));
		if(soundInstance == null) {
			ci.cancel();
		}
	}
}
