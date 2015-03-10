package com.softserveinc.if052_webapp.oauth;

import com.softserveinc.if052_webapp.service.RestServiceTest;
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

    @Qualifier("restUrl")
    String restUrl;

    //@Value("${accessTokenUri}")
    private String accessTokenUri = restUrl + "oauth/token";

    //@Value("${userAuthorizationUri}")
    private String userAuthorizationUri = restUrl + "oauth/authorize";

    @Bean
    public OAuth2ProtectedResourceDetails rest() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("webappResource");
        details.setClientId("webapp");
        details.setClientSecret("secret");
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write"));
        return details;
    }

    @Bean
    public OAuth2RestTemplate restTemplate(OAuth2ClientContext clientContext) {
        return new OAuth2RestTemplate(rest(), clientContext);
    }

    @Bean
    public RestServiceTest restService(@Qualifier("restTemplate") RestOperations restOperations) {
        RestServiceTest restServiceTest = new RestServiceTest();
        restServiceTest.setRestTemplate(restOperations);
        return restServiceTest;
    }
}