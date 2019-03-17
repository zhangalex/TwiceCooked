package com.yumong.cloud.support.config;

import com.yumong.cloud.support.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

@Configuration
public class I18nConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private ResourceLoader rl;

    @Bean
    public Properties yamlProperties() throws IOException {
        YamlPropertiesFactoryBean bean = new YamlPropertiesFactoryBean();
        bean.setResources(ResourcePatternUtils.getResourcePatternResolver(rl).getResources("classpath:i18n/*.yml"));
        return bean.getObject();
    }

    @Bean
    public MessageSource messageSource() throws IOException {
        Locale.setDefault(Locale.SIMPLIFIED_CHINESE);
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCommonMessages(yamlProperties());
        return messageSource;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() throws IOException {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        try {
            return validator();
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }
}
