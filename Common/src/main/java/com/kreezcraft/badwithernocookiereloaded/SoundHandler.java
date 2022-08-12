package com.kreezcraft.badwithernocookiereloaded;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.TextComponent;

import java.util.List;

/**
 * Originally created by droidicus.
 * Now heavily modified by Kreezxil, MrBysco, and others.
 */
public class SoundHandler {
	public static boolean silenceWither;
	public static boolean silenceTrader;
	public static boolean silenceDragon;
	public static boolean silenceLightning;
	public static List<? extends String> silenceUs;

	private static void debugMsg(String msg) {
		if (CommonClass.debugMode)
			Constants.LOGGER.info(msg);
	}

	public static ProxyPlaySoundEvent onPlaySound(ProxyPlaySoundEvent event) {
		debugMsg("Intercepted " + event.getName());

		// Disable the Wither spawn broadcast sound if it is configed to do so
		if ((event.getName().equals("entity.wither.spawn") || event.getName().equalsIgnoreCase("entity.wither.death")) &&
				silenceWither) {
			debugMsg("Silencing the wither's death.");
			event.setSound(null);
		}

		if ((event.getName().equalsIgnoreCase("entity.wandering_trader.ambient") ||
				event.getName().equalsIgnoreCase("entity.llama.ambient")) &&
				silenceTrader) {
			debugMsg("Silencing wandering trader and llama ambient sounds.");
			event.setSound(null);
		}

		if (event.getName().equals("entity.enderdragon.death") && silenceDragon) {
			debugMsg("Silencing the ender dragon death");
			event.setSound(null);
		}

		// Disable the Thunderous Lightning broadcast sound if it is configed to do so
		if (event.getName().equals("entity.lightning.thunder") && silenceLightning) {
			debugMsg("Silencing thunder");
			event.setSound(null);
		}

		if (!silenceUs.isEmpty()) {
			for (String soundName : silenceUs) {
				if ((event.getName().equals(soundName))) {
					debugMsg("Silencing " + soundName);
					event.setSound(null);
				}
			}
		}

		if (CommonClass.whatWasThat) {
			if(CommonClass.player == null) {
				Constants.LOGGER.info(new TextComponent(ChatFormatting.AQUA + "Sound is " + ChatFormatting.RED + event.getName()));
			} else {
				CommonClass.player.sendMessage(new TextComponent(ChatFormatting.AQUA + "Sound is " + ChatFormatting.RED + event.getName()), Util.NIL_UUID);
			}
		}

		return event;
	}
}
