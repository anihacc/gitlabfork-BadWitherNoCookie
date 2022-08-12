package com.kreezcraft.badwithernocookiereloaded;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Player;

public final class ListenCommand {

	private ListenCommand() {}
	
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		final LiteralArgumentBuilder<CommandSourceStack> root = Commands.literal("bwncr");
		root.requires(source -> source.hasPermission(2) /*|| source.getServer().isSingleplayer()*/)
				.then(Commands.literal("listen").executes((command) -> {
						if(CommonClass.whatWasThat) {
							CommonClass.whatWasThat = false;
							CommonClass.player = null;
						} else {
							CommonClass.whatWasThat = true;
							if(command.getSource().getEntity() instanceof Player) {
								CommonClass.player = command.getSource().getPlayerOrException();
							}
						}

						command.getSource().sendSuccess(new TextComponent("Event Listening is now " + (CommonClass.whatWasThat ? "on":"off")),true);
						return 0;
					}));
		dispatcher.register(root);
	}
}