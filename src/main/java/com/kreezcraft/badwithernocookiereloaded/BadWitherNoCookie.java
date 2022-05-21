package com.kreezcraft.badwithernocookiereloaded;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("bwncr")
public class BadWitherNoCookie {

	public static final Logger LOGGER = LogManager.getLogger();

	public static BadWitherNoCookie instance;

	public static Player player = null;
	public static Boolean whatWasThat = false;

	public BadWitherNoCookie()
	{
		ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> "", (c, b) -> true));
		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BWNCR_Config.spec);

			MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
			MinecraftForge.EVENT_BUS.addListener(this::onCommandRegister);

			instance = this;
		});
	}

	public void onCommandRegister(RegisterClientCommandsEvent event) {
		ListenCommand.register(event.getDispatcher());
	}
}
