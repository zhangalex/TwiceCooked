package com.yumong.cloud.support;

import com.yumong.cloud.support.utils.I18nUtil;
import com.yumong.cloud.support.utils.SpringUtil;

public class BusinessException extends RuntimeException {
    private Integer code = 400;

    public BusinessException(Integer code, String message) {
        super(getLocaleMessage(message));
        this.code = code;
    }

    public BusinessException(String message) {
        super(getLocaleMessage(message));
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return code;
    }

    private static String getLocaleMessage(String message) {
        return SpringUtil.bean(I18nUtil.class).getMessage(message);
    }
}