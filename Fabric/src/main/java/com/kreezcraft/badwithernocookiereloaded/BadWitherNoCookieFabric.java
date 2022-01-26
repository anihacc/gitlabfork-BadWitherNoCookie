package com.kreezcraft.badwithernocookiereloaded;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;

public class BadWitherNoCookieFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();

        AutoConfig.register(BWNCR_Config.class, Toml4jConfigSerializer::new);

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            ListenCommand.register(dispatcher);
        });
    }
}
