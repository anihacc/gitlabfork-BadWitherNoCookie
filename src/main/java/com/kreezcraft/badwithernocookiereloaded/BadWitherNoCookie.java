package com.kreezcraft.badwithernocookiereloaded;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("bwncr")
public class BadWitherNoCookie {

	public static final Logger LOGGER = LogManager.getLogger();

	public static BadWitherNoCookie instance;

	public static PlayerEntity player = null;
	public static Boolean whatWasThat = false;

	public BadWitherNoCookie() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BWNCR_Config.spec);

		MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
		MinecraftForge.EVENT_BUS.addListener(this::onCommandRegister);

		instance = this;
	}

	public void onCommandRegister(RegisterCommandsEvent event) {
		ListenCommand.register(event.getDispatcher());
	}
}
