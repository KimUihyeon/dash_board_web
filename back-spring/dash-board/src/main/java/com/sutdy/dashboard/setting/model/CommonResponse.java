package com.sutdy.dashboard.setting.model;

import lombok.Data;

@Data
public class CommonResponse<T> {

    public boolean success;
    public T resData;
    public String errorCode;
    public String errorMsg;

    public CommonResponse(String errorCode , String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public CommonResponse(T data){
        this.success = true;
        this.resData = data;
    }
}
