package com.kreezxil.bwncr.fabric;

import com.kreezxil.bwncr.ListenCommand;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class BWNCRFabric implements ModInitializer {
    static BWNCRConfigImpl config;

    @Override
    public void onInitialize() {
        AutoConfig.register(BWNCRConfigImpl.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(BWNCRConfigImpl.class).get();

        CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> ListenCommand.register(dispatcher));
    }
}