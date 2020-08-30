package com.kreezcraft.badwithernocookiereloaded;

import net.minecraft.command.ICommand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import org.apache.logging.log4j.Logger;

@Mod(
		modid = BadWitherNoCookie.MODID, 
		name = BadWitherNoCookie.NAME, 
		version = BadWitherNoCookie.VERSION,
		acceptedMinecraftVersions = "1.12.2",
		acceptableRemoteVersions = "*"
	)
public class BadWitherNoCookie {
    public static final String MODID = "badwithernocookiereloaded";
    public static final String NAME = "Bad Wither No Cookie! Reloaded";
    public static final String VERSION = "1.12.2-3.4.18";
    
    public static Logger logger;
    
    public static boolean whatWasThat = false;
    public static EntityPlayer player;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
    	logger = e.getModLog();
    }

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void init(FMLInitializationEvent event) {
        boolean god = false;
        if(god) {
            System.out.println("God has shown up to code!");
        }
        // Register event handler to do the real work
        MinecraftForge.EVENT_BUS.register(new SoundEventHandler());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
    	event.registerServerCommand((ICommand) new ListenCommand());
    }
}
