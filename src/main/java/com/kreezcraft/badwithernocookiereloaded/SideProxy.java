package com.kreezcraft.badwithernocookiereloaded;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

/**
 * SideProxy allows client and server code to be separated, while executing common code on both
 * sides. You could use this just for the sided code, but I initialize everything in proxy classes.
 * There are two nested classes, {@link Client} and {@link Server}.
 */
interface SideProxy {

    public void clientSetup(FMLClientSetupEvent event);

    public void serverStarting(FMLServerStartingEvent event);
    
    /**
     * In addition to everything handled by SideProxy, this will handle client-side resources. This
     * is where you would register things like models and color handlers.
     */
    static class Client implements SideProxy {

		@Override
		public void clientSetup(FMLClientSetupEvent event) {
        	MinecraftForge.EVENT_BUS.register(new SoundEventHandler());			
		}

		@Override
		public void serverStarting(FMLServerStartingEvent event) {			
		}
    }

    /**
     * Only created on dedicated servers.
     */
   /* static class Server implements SideProxy {

		@Override
		public void clientSetup(FMLClientSetupEvent event) {}

*//*
		@Override
		public void serverStarting(FMLServerStartingEvent event) {
			ListenCommand.register(event.getCommandDispatcher());
		}
*//*
    }*/
}
