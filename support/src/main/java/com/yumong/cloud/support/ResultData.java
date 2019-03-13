package com.yumong.cloud.support;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.time.ZonedDateTime;

public class ResultData<T> {

    private Integer status = 200;
    private String message = "";
    private ZonedDateTime ts;
    private T data;

    public ResultData() {
    }

    public ResultData(Integer code, T data) {
        this.status = code;
        this.data = data;
    }

    public ResultData(Integer code) {
        this.status = code;
    }

    public ResultData(Integer code, String msg) {
        this.status = code;
        setMessage(msg);
    }

    public ResultData(Integer code, BindingResult bindingResult) {
        this.status = code;

        StringBuffer sb = new StringBuffer();

        for (ObjectError objectError : bindingResult.getAllErrors()) {
            if (sb.length() > 0) {
                sb.append("|");
            }
            sb.append(getValidateMessage(objectError.getDefaultMessage()));
        }

        this.message = sb.toString();
    }

    public ResultData(Integer code, String msg, T data) {
        this.status = code;
        setMessage(msg);
        this.data = data;
    }

    public ResultData(T data) {
        this.data = data;
    }

    public static <T>ResultData<T> of(T data) {
        return new ResultData<>(data);
    }

    public static <T>ResultData<T> of(String message, T data) {
        return new ResultData<>(0, message, data);
    }

    public static <T>ResultData<T> of() {
        return new ResultData<>();
    }

    public static ResultData SUCCESS() {
        ResultData resultData = new ResultData();
        resultData.setStatus(200);
        resultData.setMessage("success");
        return resultData;
    }

    public static <T>ResultData<T> SUCCESS(T data) {
        ResultData resultData = new ResultData(data);
        resultData.setStatus(200);
        resultData.setMessage("success");
        return resultData;
    }

    public static ResultData ERROR(Integer status, String message) {
        return new ResultData(status, message);
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setTimestamp(ZonedDateTime ts) {
        this.ts = ts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
//        this.message = getValidateMessage(message);
        this.message = message;
    }

    public String getValidateMessage(String validKey) {
//
//        if (!StringUtils.startsWith(validKey, "{")) {
//            return validKey;
//        }
//        String key = StringUtils.substring(validKey, 1, -1);
//        return SpringUtil.bean(I18nUtil.class).getMessage(key);
        return validKey;
    }
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    public ZonedDateTime getTimestamp() {
//        if (this.timestamp == null) {
//            this.timestamp = ZonedDateTime.now();
//        }
//
//        return timestamp;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
