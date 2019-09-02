package com.dujun.core.retrofit;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitUtil {
    private RetrofitUtil() {}
    private static Retrofit retrofit;

    public static void init(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)//基础URL 建议以 / 结尾
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
                .build();
    }

    public static void init(String baseUrl, OkHttpClient client) {
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)//基础URL 建议以 / 结尾
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//设置 Json 转换器
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

    private static Map<String, Object> classMap = new HashMap<>();

    public static <T> T getService(Class<T> tClass) {
        checkObjectIsNotNull();
        if (classMap.containsKey(tClass.getSimpleName()) && classMap.get(tClass.getSimpleName()) != null) {
            return (T) classMap.get(tClass.getSimpleName());
        } else {
            T t = retrofit.create(tClass);
            classMap.put(tClass.getSimpleName(), tClass);
            return t;
        }
    }

}
