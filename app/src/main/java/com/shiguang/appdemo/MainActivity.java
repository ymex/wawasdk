package com.shiguang.appdemo;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.shiguang.wawa.OnLoadingProgressListener;
import com.shiguang.wawa.WawaFragment;


public class MainActivity extends AppCompatActivity implements OnLoadingProgressListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WawaFragment fragment = WawaFragment.instance(
                "12345678901",
                "94654343",
                "2850EA81FE1F6C0433DC2DC0AD99AF06",
                "77WWCFU09607OTTIDLVYIAV8K");
        getSupportFragmentManager().beginTransaction().replace(R.id.vContent, fragment).commit();
    }

    @Override
    public void onPostCreate(@Nullable  Bundle savedInstanceState,  @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        }
    }

    @Override
    public void onLoadingProgress(int progress) {
        System.out.println("-------------on load progress " + progress);
    }
}
