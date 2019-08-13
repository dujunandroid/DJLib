package com.dujun.lib.service;

import com.dujun.lib.bean.BaseResult;
import com.dujun.lib.bean.ConfigInfo;
import com.dujun.lib.bean.SmsCodeResponse;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {

    @POST("/common/commonApi/AppStart")
    Observable<BaseResult<ConfigInfo>> appStart();

}
