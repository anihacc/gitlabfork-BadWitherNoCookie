package com.kreezxil.bwncr;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.lang.ref.WeakReference;

public class BWNCR {
    public static final String MOD_ID = "bwncr";

    public static WeakReference<Player> listeningPlayer;

    public static boolean shouldCancel(String sound) {
        if(listeningPlayer != null && listeningPlayer.get() != null) {
            listeningPlayer.get().sendSystemMessage(Component.translatable("bwncr.listening.sound", sound));
        }

        return BWNCRConfig.getSounds().contains(sound);
    }
}