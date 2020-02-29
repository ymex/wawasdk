# 集成

## 权限 

```

   <uses-permission android:name="android.permission.INTERNET"/>
    <uses-permission android:name="android.permission.RECORD_AUDIO" />
    <uses-permission android:name="android.permission.MODIFY_AUDIO_SETTINGS" />
    <uses-permission android:name="android.permission.WRITE_SYNC_SETTINGS"/>

    <!--可选-->
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```

## 步骤
1,引入wawa-sdk
```
implementation 'com.shiguang:wawa-sdk:0.0.2'
```

2,在应用的build.gradle配置ndk 

```
defaultConfig {
        
        ndk {
            abiFilters "armeabi", "armeabi-v7a", "x86", "mips"
        }
}
```

3,application 中初始化 WawaConfig.

```
WawaConfig.init(this,"https://wawah5.szsget.cn/channel/");
```

4,在需要的页面代码引入WawaFrament， 不支持xml 方式引入 WawaFragment。

```
//必须通过WawaFragment.instance 去获取 wawafragment ， 
WawaFragment fragment = WawaFragment.instance(
                "12345678901",
                "94654343",
                "2850EA81FE1F6C0433DC2DC0AD99AF06",
                "77WWCFU09607OTTIDLVYIAV8K");
getSupportFragmentManager().beginTransaction().replace(R.id.vContent, fragment).commit();
```

具体参考demo