package com.kreezxil.bwncr;

import dev.architectury.injectables.annotations.ExpectPlatform;

import java.util.List;

public class BWNCRConfig {
    @ExpectPlatform
    public static boolean isDebugEnabled() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static List<String> getSounds() {
        throw new AssertionError();
    }
}
