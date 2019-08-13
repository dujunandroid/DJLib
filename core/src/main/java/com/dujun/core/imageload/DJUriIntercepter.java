package com.dujun.core.imageload;

/**
 * Uri拦截器。有些云平台支持url添加参数,请求不同质量图片。
 * <p/>
 * Created by dujun on 16/8/19.
 */
public interface DJUriIntercepter {

    String intercept(DJImageView imageView, String uri);

}
