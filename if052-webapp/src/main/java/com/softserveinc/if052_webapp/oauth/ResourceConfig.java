package com.softserveinc.if052_webapp.oauth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Hata on 06.03.2015.
 */
@Configuration
@PropertySource("classpath:rest.properties")
@EnableOAuth2Client
public class ResourceConfig {

    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${userAuthorizationUri}")
    private String userAuthorizationUri;

    @Bean
    public OAuth2ProtectedResourceDetails restRedirect() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("rest/webapp-redirect");
        details.setClientId("webapp");
        details.setClientSecret("secret");
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write"));
        details.setUseCurrentUri(false);
        return details;
    }

    @Bean
    public OAuth2RestTemplate restRedirectRestTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(restRedirect(), clientContext);
    }

    @Bean
    public ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean conversionService = new ConversionServiceFactoryBean();
        conversionService.setConverters(Collections.singleton(new AccessTokenRequestConverter()));
        return conversionService;
    }
}

