package com.dujun.lib.http;


import com.dujun.common.basebean.BaseResult;
import com.dujun.lib.bean.ConfigInfo;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface ApiService {

    /**
     * 启动
     * @return
     */
    @POST("/common/commonApi/AppStart")
    Observable<BaseResult<ConfigInfo>> appStart();

}
