package com.dujun.lib.bean;

/**
 * Created by KevinLiu on 2018/1/3.
 */

public interface IBaseResponse {

    public static final int STATUS_OK = 200;
    public static final int STATUS_DATA_ERROR = 1;
    public static final int STATUS_NET_ERROR = 2;
    public static final int STATUS_UNKNOWN_ERROR = 3;


    public void setCode(int code);

    public String getDetail();

    public void setDetail(String detail);

    public int getCode();

    public void setTime(long time);

    public long getTime();

    public boolean isSuccess();

}
