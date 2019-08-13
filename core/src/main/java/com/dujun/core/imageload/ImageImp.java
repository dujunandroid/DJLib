package com.dujun.core.imageload;

import android.content.Context;

/**
 * 用于抽象图片框架
 * Created by dujun on 2016/7/29.
 */
interface ImageImp {

    void initialize(Context context, Object initParam);

    void loadBitmap(String uri, DJImageCallback callback);

    void loadBitmap(String uri, int width, int height, DJImageCallback callback);

}
