package com.dujun.core.imageload;

import android.content.Context;
import android.graphics.Bitmap;

import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
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
        ImageDecoder mImageDecoder = new DefaultImageDecoder(null, null, mPlatformDecoder) {
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
}
