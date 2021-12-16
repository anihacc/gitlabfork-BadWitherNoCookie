package com.kreezcraft.badwithernocookiereloaded.client;

import com.kreezcraft.badwithernocookiereloaded.client.config.BwncrConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.server.ServerStopCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class BadwithernocookiereloadedClient implements ClientModInitializer {

    public static BwncrConfig config;
    public static BadwithernocookiereloadedClient instance;

    public static boolean tellPlayerSounds = false;
    public static PlayerEntity playerToTell;

    @Override
    public void onInitializeClient() {
        instance = this;
        AutoConfig.register(BwncrConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(BwncrConfig.class).getConfig();

        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            if(!dedicated) command(dispatcher);
        });

        ServerStopCallback.EVENT.register(event -> {
            if(config.debug && tellPlayerSounds) System.out.println("Disabling Listen");
            tellPlayerSounds = false;
            playerToTell = null;
        });
    }

    public static void saveConfig() {
        AutoConfig.getConfigHolder(BwncrConfig.class).setConfig(config);
        AutoConfig.getConfigHolder(BwncrConfig.class).save();
    }

    private void command(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("listen")
                .requires(source -> source.hasPermissionLevel(2) || source.getServer().isSingleplayer())
                .executes((command) -> {
                    tellPlayerSounds = !tellPlayerSounds;
                    playerToTell = command.getSource().getPlayer();
                    command.getSource().sendFeedback(new TranslatableText("bwncr.listen." + (tellPlayerSounds ? "enable" : "disable")), false);
                    return 0;
                })
                .then(CommandManager.literal("mute").then(CommandManager.argument("sound", StringArgumentType.string()).executes(context -> {
                    String sound = StringArgumentType.getString(context, "sound");
                    config.soundsToSilence.add(sound);
                    saveConfig();
                    context.getSource().sendFeedback(new TranslatableText("bwncr.mute.success"), false);
                    return 0;
                })))
        );

    }
}
