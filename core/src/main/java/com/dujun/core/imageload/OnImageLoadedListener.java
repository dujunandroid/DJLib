package com.dujun.core.imageload;

/**
 * Created by dujun on 16/8/23.
 */
public interface OnImageLoadedListener {

    void onImageSet(int imageWidth, int imageHeight);

    void onImageFail(Throwable t);
}
