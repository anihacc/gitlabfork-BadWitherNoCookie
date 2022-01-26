package com.kreezcraft.badwithernocookiereloaded;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collections;
import java.util.List;

public class BWNCR_Config {
	
	private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
	
	public static class General {
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceWither;
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceDragon;
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceLightning;
		public final ForgeConfigSpec.ConfigValue<Boolean> debugMode;
		public final ForgeConfigSpec.ConfigValue<Boolean> silenceTrader;
		public final ForgeConfigSpec.ConfigValue<List<? extends String>> silenceUs;
		
		public General(ForgeConfigSpec.Builder builder) {
			builder.push("General");
			silenceWither = builder
					.comment("Silence the server-wide Wither spawn and death broadcast sounds.")
					.translation("config.silenceWither")
					.define("silenceWither", true);
			silenceTrader = builder
					.comment("Silence the wandering trader's ambient sound.")
					.translation("config.silenceTrader")
					.define("silenceTrader",true);
			silenceDragon = builder
					.comment("Silence the server-wide Ender Dragon Death broadcast sound.")
					.translation("config.silenceDragon")
					.define("silenceDragon", true);
			silenceLightning = builder
					.comment("Silence the server-wide Thunder broadcast sound caused by the Lightning event")
					.translation("config.silenceLightning")
					.define("silenceLightning", true);
			silenceUs = builder
					.comment("A list of sounds to silence, discoverable with the toggle command /listen ",
							"enter one sound event per line with no commas.")
					.translation("config.silenceUs")
					.defineListAllowEmpty(Collections.singletonList("silenceUs"), () -> Collections.singletonList(""), o -> (o instanceof String));
			debugMode = builder
					.comment("If enabled the console will load up spam showing what sounds are being received and whether or not they are being canceled")
					.translation("config.debugMode")
					.define("debugMode",false);
			builder.pop();
		}
	}

	public static final ForgeConfigSpec spec = BUILDER.build();
}
