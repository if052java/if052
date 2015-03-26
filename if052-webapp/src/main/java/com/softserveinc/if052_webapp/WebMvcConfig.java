package com.softserveinc.if052_webapp;

import com.softserveinc.if052_webapp.service.RestServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.accept.ContentNegotiationManagerFactoryBean;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.util.Arrays;

@Configuration
@ComponentScan
@EnableWebMvc
@PropertySource("classpath:rest.properties")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Bean
    @Value("${restAddress}")
    public String restUrl(String restUrl){
        return restUrl;
    }

    @Bean
    public TilesConfigurer tilesConfigurer(){
        TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions("/WEB-INF/tiles/tiles-definitions.xml");
        return tilesConfigurer;
    }

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public ContentNegotiatingViewResolver contentViewResolver() throws Exception {
		ContentNegotiatingViewResolver contentViewResolver = new ContentNegotiatingViewResolver();
		ContentNegotiationManagerFactoryBean contentNegotiationManager = new ContentNegotiationManagerFactoryBean();
		contentNegotiationManager.addMediaType("json", MediaType.APPLICATION_JSON);
		contentViewResolver.setContentNegotiationManager(contentNegotiationManager.getObject());
		contentViewResolver.setDefaultViews(Arrays.<View> asList(new MappingJacksonJsonView()));
		return contentViewResolver;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

    @Configuration
    @EnableOAuth2Client
    public static class ResourceConfig {

        @Autowired
        @Qualifier("restUrl")
        private String restUrl;

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
            details.setAccessTokenUri(restUrl + accessTokenUri);
            details.setUserAuthorizationUri(restUrl + userAuthorizationUri);
            details.setScope(Arrays.asList("read", "write"));
            return details;
        }

        @Bean
        public OAuth2ProtectedResourceDetails restRedirect() {
            AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
            details.setId("rest/webapp-redirect");
            details.setClientId("webapp-with-redirect");
            details.setClientSecret("secret");
            details.setAccessTokenUri(restUrl + accessTokenUri);
            details.setUserAuthorizationUri(restUrl + userAuthorizationUri);
            details.setScope(Arrays.asList("read", "write"));
            details.setUseCurrentUri(false);
            return details;
        }

        @Autowired
        private OAuth2ClientContext oauth2Context;

        @Bean
        @Primary
        public OAuth2RestTemplate oAuthRestTemplate() {
            return new OAuth2RestTemplate(rest(), oauth2Context);
            //return new OAuth2RestTemplate(restRedirect(), oauth2Context);
        }

        @Bean
        public RestServiceTest restService(@Qualifier("oAuthRestTemplate") RestOperations restOperations) {
            RestServiceTest restServiceTest = new RestServiceTest();
            restServiceTest.setRestTemplate(restOperations);
            return restServiceTest;
        }


        @Bean
        //@Scope("session")
        @Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
        public OAuth2RestOperations oAuthRestTemplatePassword() {
            ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
            resource.setAccessTokenUri(restUrl + accessTokenUri);
            resource.setClientId("trusted");
            resource.setId("rest/trusted");
            resource.setScope(Arrays.asList("trust", "read", "write"));
            resource.setClientSecret("somesecret");
            resource.setUsername("marissa");
            resource.setPassword("koala");
            return new OAuth2RestTemplate(resource);
            //return new OAuth2RestTemplate(restRedirect(), oauth2Context);
        }

    }

}
