package com.shiguang.wawa;

import android.app.Application;

public class WawaConfig {
    static String baseUrl;
    static boolean isConfig = false;

    public static void init(Application application, String url) {
        baseUrl = url;
        Browser.debug(true);
        Browser.create(application);
        isConfig = true;
    }
}
