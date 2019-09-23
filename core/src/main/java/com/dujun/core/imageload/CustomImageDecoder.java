package com.dujun.core.imageload;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.SharedReference;
import com.facebook.imagepipeline.animated.factory.AnimatedFactory;
import com.facebook.imagepipeline.animated.factory.AnimatedFactoryProvider;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactoryProvider;
import com.facebook.imagepipeline.cache.BitmapCountingMemoryCacheFactory;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.CloseableReferenceFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.debug.CloseableReferenceLeakTracker;
import com.facebook.imagepipeline.decoder.DefaultImageDecoder;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import com.facebook.imagepipeline.platform.PlatformDecoderFactory;

/**
 * @author dujun
 * Created on 2019-09-20
 */
public class CustomImageDecoder {
    public static ImageDecoder getCustomImageDecoder(final Context mContext) {
        ImagePipelineConfig mConfig = ImagePipelineConfig
                .newBuilder(mContext)
                .build();
        PlatformDecoder mPlatformDecoder =
                PlatformDecoderFactory.buildPlatformDecoder(
                        mConfig.getPoolFactory(), mConfig.getExperiments().isGingerbreadDecoderEnabled());
        PlatformBitmapFactory mPlatformBitmapFactory =
                PlatformBitmapFactoryProvider.buildPlatformBitmapFactory(mConfig.getPoolFactory(), mPlatformDecoder, new CloseableReferenceFactory(new MyTrackListener()));
        CountingMemoryCache<CacheKey, CloseableImage> mBitmapCountingMemoryCache =
                BitmapCountingMemoryCacheFactory.get(
                        mConfig.getBitmapMemoryCacheParamsSupplier(),
                        mConfig.getMemoryTrimmableRegistry(),
                        mConfig.getBitmapMemoryCacheTrimStrategy());
        final AnimatedFactory animatedFactory = AnimatedFactoryProvider.getAnimatedFactory(
                mPlatformBitmapFactory,
                mConfig.getExecutorSupplier(),
                mBitmapCountingMemoryCache,
                mConfig.getExperiments().shouldDownscaleFrameToDrawableDimensions());
        ImageDecoder gifDecoder = null;
        ImageDecoder webPDecoder = null;

        if (animatedFactory != null) {
            gifDecoder = animatedFactory.getGifDecoder(mConfig.getBitmapConfig());
            webPDecoder = animatedFactory.getWebPDecoder(mConfig.getBitmapConfig());
        }
        ImageDecoder mImageDecoder = new DefaultImageDecoder(gifDecoder, webPDecoder, mPlatformDecoder) {

            @Override
            public CloseableImage decode(EncodedImage encodedImage, int length, QualityInfo qualityInfo, ImageDecodeOptions options) {

                CloseableImage mDecode = super.decode(encodedImage, length, qualityInfo, options);
                if (mDecode instanceof CloseableStaticBitmap) {
                    Bitmap mUnderlyingBitmap = ((CloseableStaticBitmap) mDecode).getUnderlyingBitmap();
                    if (mUnderlyingBitmap != null) {
                        mUnderlyingBitmap.setDensity(mContext.getResources().getDisplayMetrics().densityDpi);
                    }
                }
                return mDecode;
            }
        };
        return mImageDecoder;
    }

    static class MyTrackListener implements CloseableReferenceLeakTracker {

        @Override
        public void trackCloseableReferenceLeak(SharedReference<Object> reference, @Nullable Throwable stacktrace) {

        }

        @Override
        public void setListener(@Nullable Listener listener) {

        }

        @Override
        public boolean isSet() {
            return false;
        }
    }

}
