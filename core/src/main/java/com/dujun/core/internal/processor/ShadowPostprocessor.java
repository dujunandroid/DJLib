package com.dujun.core.internal.processor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.facebook.imagepipeline.request.BasePostprocessor;

/**
 * Created by dujun on 16/8/24.
 */
public class ShadowPostprocessor extends BasePostprocessor {

    private static final int DEFAULT_SHADOW_COLOR = 0x66000000;

    private int shadowColor = DEFAULT_SHADOW_COLOR;

    public ShadowPostprocessor() {
        super();
    }

    public ShadowPostprocessor(int shadowColor) {
        super();
        this.shadowColor = shadowColor;
    }

    @Override
    public void process(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(shadowColor);
    }
}
