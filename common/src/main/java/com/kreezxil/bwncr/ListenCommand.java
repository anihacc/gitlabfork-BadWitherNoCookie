package com.kreezxil.bwncr;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import java.lang.ref.WeakReference;

public final class ListenCommand {
	public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
		LiteralArgumentBuilder<CommandSourceStack> cmd = Commands.literal("listen")
				.requires(source -> source.hasPermission(2) || source.getServer().isSingleplayer())
				.executes((command) -> {
					if (BWNCR.listeningPlayer != null && BWNCR.listeningPlayer.get() != null) {
						BWNCR.listeningPlayer = null;
						command.getSource().sendSuccess(Component.translatable("bwncr.listening.off"), true);
					} else {
						BWNCR.listeningPlayer = new WeakReference<>(command.getSource().getPlayer());
						command.getSource().sendSuccess(Component.translatable("bwncr.listening.on"), true);
					}

					return 0;
				});
		dispatcher.register(cmd);
	}
}
