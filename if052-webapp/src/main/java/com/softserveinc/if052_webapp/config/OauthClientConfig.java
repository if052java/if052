package com.softserveinc.if052_webapp.config;

import com.softserveinc.if052_webapp.errorHandler.CustomErrorResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableOAuth2Client
@PropertySource("classpath:rest.properties")
public class OauthClientConfig extends WebMvcConfigurerAdapter {

    @Value("${restAddress}")
    private String restAddress;

    @Value("${accessTokenUri}")
    private String accessTokenUri;

    @Value("${userAuthorizationUri}")
    private String userAuthorizationUri;

    @Bean
    public OAuth2ProtectedResourceDetails rest() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("rest/webapp");
        details.setClientId("webapp");
        details.setClientSecret("secret");
        details.setAccessTokenUri(restAddress + accessTokenUri);
        details.setUserAuthorizationUri(restAddress + userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write"));
        return details;
    }

    @Bean
    public OAuth2ProtectedResourceDetails restRedirect() {
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setId("rest/webapp-redirect");
        details.setClientId("webapp-with-redirect");
        details.setClientSecret("secret");
        details.setAccessTokenUri(restAddress + accessTokenUri);
        details.setUserAuthorizationUri(restAddress + userAuthorizationUri);
        details.setScope(Arrays.asList("read", "write"));
        details.setUseCurrentUri(false);
        return details;
    }

    @Autowired
    private OAuth2ClientContext oauth2Context;

    @Bean
    //@Primary
    public OAuth2RestTemplate oAuthRestTemplate() {
        return new OAuth2RestTemplate(rest(), oauth2Context);
        //return new OAuth2RestTemplate(restRedirect(), oauth2Context);
    }

//        @Bean
//        public RestServiceTest restService(@Qualifier("oAuthRestTemplate") RestOperations restOperations) {
//            RestServiceTest restServiceTest = new RestServiceTest();
//            restServiceTest.setRestTemplate(restOperations);
//            return restServiceTest;
//        }


    @Bean
    //@Scope("session")
    //@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
    public OAuth2RestOperations oAuthRestTemplatePassword() {
        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
        resource.setAccessTokenUri(restAddress + accessTokenUri);
        resource.setClientId("trusted");
        resource.setId("rest/trusted");
        resource.setScope(Arrays.asList("trust", "read", "write"));
        resource.setClientSecret("somesecret");
//            resource.setUsername("marissa");
//            resource.setPassword("koala");
        return new OAuth2RestTemplate(resource);
        //return new OAuth2RestTemplate(restRedirect(), oauth2Context);
    }

    @Bean
    public OAuth2RestOperations credentialsTemplate() {
        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();

        resource.setAccessTokenUri(restAddress + accessTokenUri);
        resource.setClientId("credentials");
        resource.setId("rest/credentials");
        resource.setScope(Arrays.asList("trust", "read"));
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(resource);
        oAuth2RestTemplate.setErrorHandler(new CustomErrorResponseHandler());
        return oAuth2RestTemplate;
    }

}


