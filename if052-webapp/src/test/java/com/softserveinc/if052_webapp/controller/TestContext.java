package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.service.IndicatorService;
import com.softserveinc.if052_webapp.service.MeterService;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class TestContext {

    @Bean
    IndicatorService indicatorService() {return Mockito.mock(IndicatorService.class); }

    @Bean
    MeterService meterService() {return Mockito.mock(MeterService.class); }
}
