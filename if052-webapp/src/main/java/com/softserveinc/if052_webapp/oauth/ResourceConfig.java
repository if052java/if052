package com.softserveinc.if052_webapp.oauth;

import com.softserveinc.if052_webapp.service.RestServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestOperations;

import java.util.Arrays;

/**
 * Created by Hata on 06.03.2015.
 */
@Configuration
@PropertySource("classpath:rest.properties")
@EnableOAuth2Client
public class ResourceConfig {

    @Autowired
    String restUrl;

    //@Value("${accessTokenUri}")
    private String accessTokenUri = "http://localhost:8080/" + "oauth/token";

    //@Value("${userAuthorizationUri}")
    private String userAuthorizationUri = "http://localhost:8080/" + "oauth/authorize";

    @Bean
    public OAuth2ProtectedResourceDetails rest() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("rest/webapp");
        details.setClientId("webapp");
        details.setClientSecret("secret");
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write"));
        return details;
    }

    @Bean
    public OAuth2ProtectedResourceDetails restRedirect() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("rest/webapp-redirect");
        details.setClientId("webapp-with-redirect");
        details.setClientSecret("secret");
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write"));
        details.setUseCurrentUri(false);
        return details;
    }


    @Bean
    public OAuth2RestTemplate oAuth2restTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(rest(), clientContext);
    }

    @Bean
    public OAuth2RestTemplate oAuth2restTemplateRedirect(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(restRedirect(), clientContext);
    }

    @Bean
    public RestServiceTest restService(@Qualifier("oAuth2restTemplateRedirect") RestOperations restOperations) {
        RestServiceTest restServiceTest = new RestServiceTest();
        restServiceTest.setRestTemplate(restOperations);
        return restServiceTest;
    }
}