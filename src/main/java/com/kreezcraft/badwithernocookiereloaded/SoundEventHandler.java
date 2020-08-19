package com.kreezcraft.badwithernocookiereloaded;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
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
	ClientPlayerEntity player = null;
	
    @SubscribeEvent(priority= EventPriority.LOWEST, receiveCanceled=false)
    public void onEvent(PlaySoundEvent event) {
    	final boolean theSilence = BWNCR_Config.GENERAL.silenceSuccess.get(); 
    	
        // Disable the Wither spawn broadcast sound if it is configed to do so
        if ((event.getName().equals("entity.wither.spawn")) &&
        		BWNCR_Config.GENERAL.silenceWither.get()) {
            if(!theSilence) {
            	System.out.println("WITHER SOUND SILENCED!!!");
            }
            event.setResultSound(null);
        }

        // Disable the Ender Dragon death broadcast sound if it is configed to do so
        if ((event.getName().equals("entity.enderdragon.death")) &&
        		BWNCR_Config.GENERAL.silenceDragon.get()) {
        	if(!theSilence) {
        		System.out.println("ENDER DRAGON SOUND SILENCED!!!");
        	}
        	event.setResultSound(null);
        }
        
        // Disable the Thunderous Lightning broadcast sound if it is configed to do so
        if ((event.getName().equals("entity.lightning.thunder")) &&
        		BWNCR_Config.GENERAL.silenceLightning.get()) {
            if(!theSilence) {
            	System.out.println("THUNDER SILENCED!!!");
            }
            event.setResultSound(null);
        }
        
        if (!Arrays.asList(BWNCR_Config.GENERAL.silenceUs).isEmpty()) {
        	for (String soundName: BWNCR_Config.GENERAL.silenceUs.get()) {
        		if((event.getName().equals(soundName))) {
        			if(!theSilence) {
        				System.out.println(soundName + " Silenced!!!");
        			}
        			event.setResultSound(null);
        		}
        	}
        	
        }
        
        if (BadWitherNoCookie.whatWasThat) {
        	player = Minecraft.getInstance().player;
        	if(player != null) {
        	    player.sendChatMessage(TextFormatting.AQUA + "Sound is "+TextFormatting.RED + event.getName());
        	} else {
        		BadWitherNoCookie.LOGGER.info(new StringTextComponent(TextFormatting.AQUA + "Sound is "+TextFormatting.RED + event.getName()));
        	}
        	event.getListenerList();
        }
    }
}
