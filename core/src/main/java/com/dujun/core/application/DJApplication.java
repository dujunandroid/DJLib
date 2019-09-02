package com.dujun.core.application;

import android.app.Application;
import android.content.Context;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public class DJApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
