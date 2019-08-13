package com.dujun.lib.bean;

/**
 * Created by KevinLiu on 2017/12/5.
 */

/**
 * 包含再data里面的数据结构
 */
public class BaseResponse implements IBaseResponse {

    private int code;
    private String detail;
    private long time;

    int total;
    int limit;
    int page;

    @Override
    public long getTime() {
        return time;
    }

    @Override
    public void setTime(long time) {
        this.time = time;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int total_page;


    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getCode() {
        return code;
    }



    @Override
    public void setCode(int code) {
        this.code = code;
    }


    @Override
    public boolean isSuccess() {
        return code == STATUS_OK;
    }


    /**
     * 是否是网络错误
     *
     * @return
     */
    public boolean isNetError() {
        return code == IBaseResponse.STATUS_NET_ERROR;
    }

    /**
     * 是否是数据错误
     *
     * @return
     */
    public boolean isDataError() {
        return code == IBaseResponse.STATUS_DATA_ERROR;
    }

    /**
     * 是否是数据错误
     *
     * @return
     */
    public boolean isUnknownError() {
        return code == IBaseResponse.STATUS_UNKNOWN_ERROR;
    }
}
