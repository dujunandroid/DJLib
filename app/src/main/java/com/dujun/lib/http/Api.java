package com.dujun.lib.http;

import com.dujun.core.retrofit.RetrofitUtil;

/**
 * @author dujun
 * Created on 2019-09-10
 */
public class Api {

    public static ApiService get() {
        return RetrofitUtil.getService(ApiService.class);
    }
}
