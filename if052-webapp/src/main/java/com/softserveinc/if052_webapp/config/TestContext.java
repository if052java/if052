package com.softserveinc.if052_webapp.config;

import com.softserveinc.if052_webapp.service.IndicatorService;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class TestContext {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);

        return messageSource;
    }

    @Bean
    IndicatorService indicatorService() {return Mockito.mock(IndicatorService.class); }
}
