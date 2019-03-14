package com.yumong.cloud.support.utils;

import com.yumong.cloud.support.BusinessException;

public class ErrorUtil {

    public static void raise(boolean condition, Integer code, String message) {
        if (condition) {
            throw new BusinessException(code, message);
        }
    }

    public static void raise(boolean condition, String message) {
        if (condition) {
            throw new BusinessException(400, message);
        }
    }

    public static void raise(Integer code, String message) {
        raise(true, code, message);
    }

    public static void raise(String message) {
        throw new BusinessException(message);
    }

    public static void raise(String message, Throwable cause) {
        throw new BusinessException(message, cause);
    }

    public static void raise(Throwable ex) {
        throw new BusinessException(ex);
    }

}