package com.dujun.lib;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.dujun.common.activity.CrashInfoActivity;
import com.dujun.core.application.DJApplication;
import com.dujun.core.imageload.DJImage;

import java.io.File;

/**
 * @author dujun
 * Created on 2019-09-02
 */
public class MyApplication extends DJApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        DJImage.initialize(this);
        initCrashHandler();
    }

    private void initCrashHandler() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                PermissionUtils.permission(PermissionConstants.STORAGE)
                        .callback(new PermissionUtils.SimpleCallback() {
                            @Override
                            public void onGranted() {
                                if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                    CrashUtils.init(getGlobalPath(), new MyCrashListener());
                                }
                            }

                            @Override
                            public void onDenied() {

                            }
                        }).request();
                return;
            } else {
                CrashUtils.init(getGlobalPath(), new MyCrashListener());
            }
        } else {
            CrashUtils.init(getGlobalPath(), new MyCrashListener());
        }
    }

    class MyCrashListener implements CrashUtils.OnCrashListener {

        @Override
        public void onCrash(String crashInfo, Throwable e) {
            startActivity(CrashInfoActivity.getIntent(crashInfo));
        }
    }

    private String getGlobalPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "dujun/crash" + File.separator;
    }

}
