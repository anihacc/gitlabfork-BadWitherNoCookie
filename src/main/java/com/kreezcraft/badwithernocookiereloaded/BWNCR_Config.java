package com.kreezcraft.badwithernocookiereloaded;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.ArrayList;
import java.util.List;

public class BWNCR_Config {
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
	
	public static class General {
		
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceWither;
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceDragon;
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceLightning;
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceSuccess;
		private static List<String> DefaultValue = new ArrayList<String>();
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> silenceUs;
		
		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			silenceWither = builder
					.comment("Silence the server-wide Wither spawn broadcast sound.")
					.translation("config.silenceWither")
					.define("silenceWither", true);
			silenceDragon = builder
					.comment("Silence the server-wide Ender Dragon Death broadcast sound.")
					.translation("config.silenceDragon")
					.define("silenceDragon", true);
			silenceLightning = builder
					.comment("Silence the server-wide Thunder broadcast sound caused by the Lightning event")
					.translation("config.silenceLightning")
					.define("silenceLightning", true);
			silenceSuccess = builder
					.comment("Silence the server messages in console for when any of these sounds have been silenced.")
					.translation("config.silenceSuccess")
					.define("silenceSuccess", true);
			silenceUs = builder
					.comment("A list of sounds to silence, discoverable with the toggle command /listen ",
							"enter one sound event per line with no commas.")
					.translation("config.silenceUs")
					.define("silenceUs", DefaultValue);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec spec = BUILDER.build();
}
