package com.kreezcraft.badwithernocookiereloaded;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;

public final class ListenCommand {

	private ListenCommand() {}
	
	public static void register(CommandDispatcher<CommandSource> dispatcher) {
		final LiteralArgumentBuilder<CommandSource> root = Commands.literal("bwncr");
		root.requires(source -> source.hasPermissionLevel(2) || source.getServer().isSinglePlayer())
				.then(Commands.literal("listen").executes((command) -> {
						if(BadWitherNoCookie.whatWasThat) {
							BadWitherNoCookie.whatWasThat = false;
							BadWitherNoCookie.player = null;
						} else {
							BadWitherNoCookie.whatWasThat = true;
							if(command.getSource().getEntity() instanceof PlayerEntity) {
								BadWitherNoCookie.player = command.getSource().asPlayer();
							}
						}

						command.getSource().sendFeedback(new StringTextComponent("Event Listening is now " + (BadWitherNoCookie.whatWasThat ? "on":"off")),true);
						return 0;
					}));
		dispatcher.register(root);
	}
}
