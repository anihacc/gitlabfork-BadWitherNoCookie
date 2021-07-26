package com.kreezcraft.badwithernocookiereloaded;

import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.util.Util;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

/**
 * Originally created by droidicus.
 * Now heavily modifed by Kreezxil
 */
public class SoundEventHandler {
    final boolean debugMode = BWNCR_Config.GENERAL.debugMode.get();

    private void debugMsg(String msg) {
        if (debugMode)
            BadWitherNoCookie.LOGGER.info(msg);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = false)
    public void onEvent(PlaySoundEvent event) {
        debugMsg("Intercepted " + event.getName());

        // Disable the Wither spawn broadcast sound if it is configed to do so
        if ((event.getName().equals("entity.wither.spawn") || event.getName().equalsIgnoreCase("entity.wither.death")) &&
                BWNCR_Config.GENERAL.silenceWither.get()) {
            debugMsg("Silencing the wither's death.");
            event.setResultSound(null);
        }

        if ((event.getName().equalsIgnoreCase("entity.wandering_trader.ambient") ||
                event.getName().equalsIgnoreCase("entity.llama.ambient")) &&
                BWNCR_Config.GENERAL.silenceTrader.get()) {
            debugMsg("Silencing wandering trader and llama ambient sounds.");
            event.setResultSound(null);
        }

        if (event.getName().equals("entity.enderdragon.death") && BWNCR_Config.GENERAL.silenceDragon.get()) {
            debugMsg("Silencing the ender dragon death");
            event.setResultSound(null);
        }

        // Disable the Thunderous Lightning broadcast sound if it is configed to do so
        if (event.getName().equals("entity.lightning.thunder") && BWNCR_Config.GENERAL.silenceLightning.get()) {
            debugMsg("Silencing thunder");
            event.setResultSound(null);
        }

        if (!BWNCR_Config.GENERAL.silenceUs.get().isEmpty()) {
            for (String soundName : BWNCR_Config.GENERAL.silenceUs.get()) {
                if ((event.getName().equals(soundName))) {
                    debugMsg("Silencing " + soundName);
                    event.setResultSound(null);
                }
            }
        }

        if (BadWitherNoCookie.whatWasThat) {
            if(BadWitherNoCookie.player == null) {
                BadWitherNoCookie.LOGGER.info(new StringTextComponent(TextFormatting.AQUA + "Sound is " + TextFormatting.RED + event.getName()));
            } else {
                BadWitherNoCookie.player.sendMessage(new StringTextComponent(TextFormatting.AQUA + "Sound is " + TextFormatting.RED + event.getName()), Util.DUMMY_UUID);
            }
        }
    }
}
