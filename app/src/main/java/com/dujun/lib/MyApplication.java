package com.dujun.lib;

import android.app.Application;

import com.dujun.core.DJImage;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DJImage.initialize(this);
    }
}
