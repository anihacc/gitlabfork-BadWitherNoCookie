package com.kreezxil.bwncr.forge;

import com.kreezxil.bwncr.BWNCR;
import com.kreezxil.bwncr.ListenCommand;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;

@Mod(BWNCR.MOD_ID)
public class BWNCRForge {

    public static final BWNCRConfigImpl CONFIG;
    public static final ForgeConfigSpec CONFIG_SPEC;

    static {
        Pair<BWNCRConfigImpl, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(BWNCRConfigImpl::new);
        CONFIG = commonSpecPair.getLeft();
        CONFIG_SPEC = commonSpecPair.getRight();
    }

    public BWNCRForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CONFIG_SPEC);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onPlaySound(PlaySoundEvent event) {
        if(event.getSound() == null) return;
        if(BWNCR.shouldCancel(event.getSound().getSound().getLocation().toString())) {
            event.setSound(null);
        }
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        ListenCommand.register(event.getDispatcher());
    }
}