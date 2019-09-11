package com.dujun.core.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author mac
 */
public final class RetrofitUtil {
    private RetrofitUtil() {
    }

    private static Retrofit retrofit;

    public static void init(String baseUrl) {
        retrofit = new Retrofit.Builder()
                //基础URL 建议以 / 结尾
                .baseUrl(baseUrl)
                //设置 Json 转换器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static void init(String baseUrl, OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                //基础URL 建议以 / 结尾
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //设置 Json 转换器
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static void init(Retrofit.Builder builder) {
        retrofit = builder.build();
    }

    private static void checkObjectIsNotNull() {
        if (retrofit == null) {
            throw new RuntimeException("请先初始化再使用");
        }
    }

    public static <T> T getService(Class<T> tClass) {
        checkObjectIsNotNull();
        return retrofit.create(tClass);
    }

}
