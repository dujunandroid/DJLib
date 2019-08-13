package com.dujun.lib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dujun.core.imageload.DJImage;
import com.dujun.core.imageload.DJImageView;
import com.dujun.core.imageload.DJPhotoView;

public class MainActivity extends AppCompatActivity {

    private DJImageView test;
    private DJPhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test = findViewById(R.id.iv_test);
        photoView = findViewById(R.id.pv_test);
        loadImage();
    }

    private void loadImage() {
        test.processor(DJImage.Processors.SHADOW).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565604314006&di=d259f3560280313e83d0ccd14760c5ca&imgtype=0&src=http%3A%2F%2Fpic16.nipic.com%2F20111006%2F6239936_092702973000_2.jpg");
        photoView.processor(DJImage.Processors.BLUR).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1565604314006&di=e8b876de09718040b925a58b1f5fe059&imgtype=0&src=http%3A%2F%2Fimg.redocn.com%2Fsheji%2F20141219%2Fzhongguofengdaodeliyizhanbanzhijing_3744115.jpg");
    }
}
