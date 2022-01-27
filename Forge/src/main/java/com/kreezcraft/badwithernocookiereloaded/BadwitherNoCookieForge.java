package com.kreezcraft.badwithernocookiereloaded;

import com.kreezcraft.badwithernocookiereloaded.handler.SoundEventHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.fml.DistExecutor;
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
    }

    public void onCommandRegister(RegisterCommandsEvent event) {
        ListenCommand.register(event.getDispatcher());
    }
}