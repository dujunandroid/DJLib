package com.dujun.lib.bean;

import java.util.ArrayList;

/**
 * Created by xuan on 2017/8/18.
 */

/**
 * 包装了http code 的数据结果
 *
 * @param <T>
 */
public class BaseResult<T> {

    int code;
    String detail;
    int time;
    private T data;
    private ArrayList<T> list;


    public String getMsg() {
        return detail;
    }

    public void setMsg(String detail) {
        this.detail = detail;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ArrayList<T> getList() {
        return list;
    }

    public void setList(ArrayList<T> list) {
        this.list = list;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "code=" + code +
                ", msg='" + detail + '\'' +
                ", data=" + data +
                ", list=" + list +
                '}';
    }
}
