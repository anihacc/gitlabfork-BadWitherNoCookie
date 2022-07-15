package com.kreezxil.bwncr.fabric.mixin;

import com.kreezxil.bwncr.BWNCR;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.resources.sounds.TickableSoundInstance;
import net.minecraft.client.sounds.SoundEngine;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(SoundEngine.class)
public class SoundMixin {
    @Inject(method = "play", at = @At("HEAD"), cancellable = true)
    public void play(SoundInstance instance, CallbackInfo info) {
        if(BWNCR.shouldCancel(instance.getSound().getLocation().toString())) info.cancel();
    }

    @Inject(method = "playDelayed", at = @At("HEAD"), cancellable = true)
    public void play(SoundInstance instance, int delay, CallbackInfo info) {
        if(BWNCR.shouldCancel(instance.getSound().getLocation().toString())) info.cancel();
    }

    @Inject(method = "queueTickingSound", at = @At("HEAD"), cancellable = true)
    public void playNextTick(TickableSoundInstance instance, CallbackInfo info) {
        if(BWNCR.shouldCancel(instance.getSound().getLocation().toString())) info.cancel();
    }
}
