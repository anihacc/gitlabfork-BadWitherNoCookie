package com.kreezcraft.badwithernocookiereloaded;

import com.kreezcraft.badwithernocookiereloaded.command.ForgeListenCommand;
import com.kreezcraft.badwithernocookiereloaded.handler.SoundEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(Constants.MOD_ID)
public class BadwitherNoCookieForge {

	public BadwitherNoCookieForge() {
		CommonClass.init();

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, BWNCR_Config.spec);

		MinecraftForge.EVENT_BUS.addListener(this::onCommandRegister);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
			MinecraftForge.EVENT_BUS.addListener(EventPriority.LOWEST, SoundEventHandler::onSoundEvent);
		});

		ModLoadingContext.get().registerExtensionPoint(DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() ->
				DisplayTest.IGNORESERVERONLY, (remoteVersionString, networkBool) -> networkBool));
	}

	public void onCommandRegister(RegisterClientCommandsEvent event) {
		ForgeListenCommand.register(event.getDispatcher());
	}
}