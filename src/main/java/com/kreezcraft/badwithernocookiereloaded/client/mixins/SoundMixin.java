package com.kreezcraft.badwithernocookiereloaded.client.mixins;

import com.kreezcraft.badwithernocookiereloaded.client.BadwithernocookiereloadedClient;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.sound.SoundInstance;
import net.minecraft.client.sound.SoundSystem;
import net.minecraft.client.sound.TickableSoundInstance;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SoundSystem.class)
public class SoundMixin {
    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;)V", at = @At("HEAD"), cancellable = true)
    public void play(SoundInstance instance, CallbackInfo info) {
        if(testSound(instance)) info.cancel();
    }

    @Inject(method = "play(Lnet/minecraft/client/sound/SoundInstance;I)V", at = @At("HEAD"), cancellable = true)
    public void play(SoundInstance instance, int delay, CallbackInfo info) {
        if(testSound(instance)) info.cancel();
    }

    @Inject(method = "playNextTick", at = @At("HEAD"), cancellable = true)
    public void playNextTick(TickableSoundInstance instance, CallbackInfo info) {
        if(testSound(instance)) info.cancel();
    }

    private boolean testSound(SoundInstance instance) {
        if(BadwithernocookiereloadedClient.tellPlayerSounds) {
            BadwithernocookiereloadedClient.playerToTell.sendMessage(new TranslatableText("bwncr.sound", instance.getId().getPath()), false);
        }
        if(BadwithernocookiereloadedClient.config.debug) System.out.println("[BWNCR] Found sound " + instance.getId().getPath());
        if(BadwithernocookiereloadedClient.config.soundsToSilence.contains(instance.getId().getPath())) {
            if(BadwithernocookiereloadedClient.config.debug) System.out.println("[BWNCR] Silenced sound " + instance.getId().getPath());
            return true;
        }
        return false;
    }
}
