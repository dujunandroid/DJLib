package com.dujun.common.basebean;

import java.util.List;

/**
 * @author dujun
 * Created on 2019-09-10
 */
public class BaseResult<T> {
    public int code;
    public String message;
    public T data;
    public List<T> datas;
}
