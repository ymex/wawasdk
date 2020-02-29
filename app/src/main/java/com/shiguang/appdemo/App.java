package com.shiguang.appdemo;

import android.app.Application;

import com.shiguang.wawa.WawaConfig;

/**
 * Created by ymex on 2020/2/28.
 * About:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        WawaConfig.init(this,"https://wawah5.szsget.cn/channel/");
    }
}
