package com.kreezcraft.badwithernocookiereloaded;

import java.util.Arrays;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Originally created by droidicus.
 * Now heavily modifed by Kreezxil
 */
public class SoundEventHandler {
    // LOWEST Priority so that everything else can do their thing if they need to first
    @SubscribeEvent(priority= EventPriority.LOWEST, receiveCanceled=false)
    public void onEvent(PlaySoundEvent event) {
    	
    	final boolean theSilence = BWNCR_Config.general.silenceSuccess;
    	
        // Disable the Wither spawn broadcast sound if it is configed to do so
        if ((event.getName().equals("entity.wither.spawn") || event.getName().equals("entity.wither.death")) &&
        		BWNCR_Config.general.silenceWither) {
            if(!theSilence) {
            	System.out.println("WITHER SOUND SILENCED!!!");
            }
            event.setResultSound(null);
        }

        // Disable the Ender Dragon death broadcast sound if it is configed to do so
		if ((event.getName().equals("entity.enderdragon.death")) &&
        		BWNCR_Config.general.silenceDragon) {
        	if(!theSilence) {
        		System.out.println("ENDER DRAGON SOUND SILENCED!!!");
        	}
            event.setResultSound(null);
        }
        
        // Disable the Thunderous Lightning broadcast sound if it is configed to do so
        if ((event.getName().equals("entity.lightning.thunder")) &&
        		BWNCR_Config.general.silenceLightning) {
            if(!theSilence) {
            	System.out.println("THUNDER SILENCED!!!");
            }
            event.setResultSound(null);
        }
        
        if (!Arrays.asList(BWNCR_Config.xtra.silenceUs).isEmpty()) {
        	for (String soundName: BWNCR_Config.xtra.silenceUs) {
        		if((event.getName().equals(soundName))) {
        			if(!theSilence) {
        				System.out.println(soundName + " Silenced!!!");
        			}
        			event.setResultSound(null);
        		}
        	}
        	
        }
        
        if (BadWitherNoCookie.whatWasThat) {
        	if(BadWitherNoCookie.player == null) {
        		System.out.println("Sound is "+event.getName());
        	} else {
        	    BadWitherNoCookie.player.sendMessage(new TextComponentString(TextFormatting.AQUA + "Sound is "+TextFormatting.RED + event.getName()));
        	}
        	event.getListenerList();
        }
    }
}
