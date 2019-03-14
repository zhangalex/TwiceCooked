package com.yumong.cloud.support.utils;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class I18nUtil {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        if (StringUtils.startsWith(code, "{")) {
            String key = StringUtils.substring(code, 1, -1);
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(key, null, locale);
        }

        return code;
    }
}
