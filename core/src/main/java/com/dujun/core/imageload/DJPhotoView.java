package com.dujun.core.imageload;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.View;

import androidx.annotation.Nullable;

import com.dujun.core.imageload.internal.photoview.PhotoDraweeView;
import com.dujun.core.imageload.internal.processor.BlurPostprocessor;
import com.dujun.core.imageload.internal.processor.ShadowPostprocessor;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by dujun on 16/8/23.
 */
public class DJPhotoView extends PhotoDraweeView {

    public interface OnPhotoTapListener {
        void onPhotoTap(DJPhotoView DJPhotoView);
    }

    public interface OnPhotoLongClickListener {
        boolean onPhotoLongClick(DJPhotoView DJPhotoView);
    }

    private OnPhotoTapListener onPhotoTapListener;
    private OnPhotoLongClickListener onPhotoLongClickListener;
    private OnImageLoadedListener onImageLoadedListener;


    public DJPhotoView(Context context) {
        super(context);
    }

    public DJPhotoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 支持的图片图片处理效果。
     */
    private int processor = DJImage.Processors.NONE;
    /**
     * 设置图片效果
     *
     * @param processor
     * @return
     */
    public DJPhotoView processor(int processor) {
        this.processor = processor;
        return this;
    }

    public void load(String uri) {
        DisplayMetrics dm = getResources().getDisplayMetrics();

        ImageRequestBuilder imageRequestBuilder = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uri));
        imageRequestBuilder.setResizeOptions(new ResizeOptions(dm.widthPixels/2, dm.heightPixels/2));
        if (processor > DJImage.Processors.NONE) {
            BasePostprocessor basePostprocessor = getPostprocessor(getContext(), processor);
            if (basePostprocessor != null) {
                imageRequestBuilder.setPostprocessor(basePostprocessor);
            }
        }
        imageRequestBuilder.setRotationOptions(RotationOptions.autoRotate());

        ImageRequest imageRequest = imageRequestBuilder.build();

        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        controller.setImageRequest(imageRequest);
        controller.setOldController(getController());
        controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null) {
                    return;
                }
                update(imageInfo.getWidth(), imageInfo.getHeight());
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageSet(imageInfo.getWidth(), imageInfo.getHeight());
                }
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                if (onImageLoadedListener != null) {
                    onImageLoadedListener.onImageFail(throwable);
                }
            }
        });
        setController(controller.build());
    }

    @Override
    public void setPhotoUri(Uri uri) {
        throw new RuntimeException("请勿调用此方法");
    }

    @Override
    public void setPhotoUri(Uri uri, @Nullable Context context) {
        throw new RuntimeException("请勿调用此方法");
    }

    public void setOnPhotoTapListener(final OnPhotoTapListener onPhotoTapListener) {
        this.onPhotoTapListener = onPhotoTapListener;
        super.setOnPhotoTapListener(new com.dujun.core.imageload.internal.photoview.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                if (DJPhotoView.this.onPhotoTapListener != null) {
                    DJPhotoView.this.onPhotoTapListener.onPhotoTap(DJPhotoView.this);
                }
            }
        });
    }

    public void setOnPhotoLongClickListener(OnPhotoLongClickListener onPhotoLongClickListener) {
        this.onPhotoLongClickListener = onPhotoLongClickListener;
        super.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (DJPhotoView.this.onPhotoLongClickListener != null) {
                    return DJPhotoView.this.onPhotoLongClickListener.onPhotoLongClick(DJPhotoView.this);
                }
                return false;
            }
        });
    }

    public void setOnImageLoadedListener(OnImageLoadedListener onImageLoadedListener) {
        this.onImageLoadedListener = onImageLoadedListener;
    }

    @Override
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener listener) {
        throw new RuntimeException("请勿调用此方法");
    }

    @Override
    public void setOnLongClickListener(View.OnLongClickListener listener) {
        throw new RuntimeException("请勿调用此方法");
    }

    @Override
    public void setOnPhotoTapListener(com.dujun.core.imageload.internal.photoview.OnPhotoTapListener listener) {
        throw new RuntimeException("请勿调用此方法");
    }

    private static BasePostprocessor getPostprocessor(Context context, int type) {
        switch (type) {
            case DJImage.Processors.BLUR:
                return new BlurPostprocessor(context);
            case DJImage.Processors.SHADOW:
                return new ShadowPostprocessor();
        }
        return null;
    }
}
