package com.kreezcraft.badwithernocookiereloaded;

import com.mojang.brigadier.CommandDispatcher;

import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public final class ListenCommand {

	private ListenCommand() {}
	
	/*public static void register(CommandDispatcher<CommandSource> dispatcher) {
		dispatcher.register(Commands.literal("listen")
				.requires(source -> source.hasPermissionLevel(2) || source.getServer().isSinglePlayer())
				.executes((command) -> {
					
					BadWitherNoCookie.whatWasThat = !BadWitherNoCookie.whatWasThat;
					BadWitherNoCookie.player = command.getSource().asPlayer();
					command.getSource().sendFeedback(new StringTextComponent("Event Listening is now " + (BadWitherNoCookie.whatWasThat ? "on":"off")),true);
					return 0;
				}));
	}*/
}
