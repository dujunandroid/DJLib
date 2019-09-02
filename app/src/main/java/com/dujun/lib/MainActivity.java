package com.dujun.lib;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dujun.core.basemvp.BaseActivity;
import com.dujun.core.basemvp.BasePresenter;
import com.dujun.core.imageload.DJImage;
import com.dujun.core.imageload.DJImageView;
import com.dujun.core.imageload.DJPhotoView;

public class MainActivity extends BaseActivity {

    private DJImageView test;
    private DJPhotoView photoView;

    @Nullable
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initBaseView(Bundle bundle) {
        test = findViewById(R.id.iv_test);
        photoView = findViewById(R.id.pv_test);
        loadImage();
    }

    @Override
    protected int getBaseLayoutId() {
        return R.layout.activity_main;
    }

    private void loadImage() {
        test.placeholder(R.drawable.ic_launcher_background).asRoundRect(12).load("http://faceme-test.oss-cn-beijing.aliyuncs.com/image/tools/20190430/78458f9ad02c9af8b4032d4c80f0c8cd.png");
//        test.processor(DJImage.Processors.SHADOW).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565604314006&di=d259f3560280313e83d0ccd14760c5ca&imgtype=0&src=http%3A%2F%2Fpic16.nipic.com%2F20111006%2F6239936_092702973000_2.jpg");
        photoView.processor(DJImage.Processors.BLUR).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565604314006&di=e8b876de09718040b925a58b1f5fe059&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20141219%2Fzhongguofengdaodeliyizhanbanzhijing_3744115.jpg");
    }
}
