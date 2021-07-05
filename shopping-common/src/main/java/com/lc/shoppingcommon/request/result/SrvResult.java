package com.lc.shoppingcommon.request.result;

import lombok.Data;

/**
 * @author 刘晨
 * @description 请求返回结果封装
 * @create 2021/5/28 0028
 * @since 1.0.0
 */
@Data
public class SrvResult<T> {
    private boolean status;
    private T data;
    private String message;

    public static <T> SrvResult<T> creat(boolean status, T data){
        SrvResult srvResult = new SrvResult();
        srvResult.setStatus(status);
        srvResult.setData(data);
        return srvResult;
    }

    public static <T> SrvResult<T> creat(boolean status, String message){
        SrvResult srvResult = new SrvResult();
        srvResult.setStatus(status);
        srvResult.setMessage(message);
        return srvResult;
    }

    public static <T> SrvResult<T> creat(boolean status, T data, String message){
        SrvResult srvResult = new SrvResult();
        srvResult.setStatus(status);
        srvResult.setMessage(message);
        srvResult.setData(data);
        return srvResult;
    }
}
