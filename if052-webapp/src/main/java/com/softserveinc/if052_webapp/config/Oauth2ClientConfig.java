package com.softserveinc.if052_webapp.config;

import com.softserveinc.if052_core.domain.Auth;
import com.softserveinc.if052_webapp.errorHandler.CustomErrorResponseHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@PropertySource("classpath:rest.properties")
public class Oauth2ClientConfig extends WebMvcConfigurerAdapter {

    @Value("${restAddress}")
    private String restAddress;

    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${userAuthorizationUri}")
    private String userAuthorizationUri;

    @Bean
    public CustomErrorResponseHandler customErrorResponseHandler(){
        return new CustomErrorResponseHandler();
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestTemplate passwordTemplate() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(restAddress + accessTokenUri);
        resource.setClientId("trusted");
        resource.setId("rest/trusted");
        resource.setScope(Arrays.asList("trust", "client"));
        resource.setClientSecret("somesecret");

        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource);
        oAuth2RestTemplate.setErrorHandler(customErrorResponseHandler());
        return oAuth2RestTemplate;
    }

    @Bean
    public OAuth2RestOperations credentialsTemplate() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();

        resource.setAccessTokenUri(restAddress + accessTokenUri);
        resource.setClientId("credentials");
        resource.setId("rest/credentials");
        resource.setScope(Arrays.asList("client"));
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource);
        oAuth2RestTemplate.setErrorHandler(customErrorResponseHandler());
        return oAuth2RestTemplate;
    }

    @Bean
    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Auth auth(){
        Auth auth = new Auth();
        return auth;
    }
}


