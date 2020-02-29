package com.shiguang.wawa;

import android.app.Application;

public class WawaConfig {
    private static String baseUrl;
    private static boolean isConfig = false;

    public static void init(Application application, String url) {
        baseUrl = url;
        Browser.debug(true);
        Browser.create(application);
        isConfig = true;
    }

    public static void setBaseUrl(String baseUrl) {
        WawaConfig.baseUrl = baseUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    public static boolean isConfig() {
        return isConfig;
    }
}
