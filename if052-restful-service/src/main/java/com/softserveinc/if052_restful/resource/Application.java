package com.softserveinc.if052_restful.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * Created by Nazar Ostryzhniuk on 2/3/15.
 */
public class Application extends ResourceConfig{
    /**
     * Register JAX-RS application components.
     */
    public Application(){
        register(RequestContextFilter.class); /*  Spring filter that provides a bridge
                                        between JAX-RS and Spring request attributes */

        register(JacksonFeature.class); /* feature that registers Jackson JSON providers –
                                   you need it for the application to understand JSON data */

        register(IndexResource.class); /*service component that exposes the REST API via annotations */
        //register(GreetingResource.class);

        register(AddressResource.class);

        register(WaterMeterResource.class);

        register(UserResource.class);

        register(IndicatorResource.class);

        register(RoleProviderResource.class);

    }

}
