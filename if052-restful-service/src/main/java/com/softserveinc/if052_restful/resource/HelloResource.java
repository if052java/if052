package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.HelloWorld.Greeting;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Nazar Ostryzhniuk on 2/3/15.
 */
@Path("/")
public class HelloResource {

    private static final String TEMPLATE = "Hello, %s from Jersey REST! ";

    @GET
    public Response makeHello(){
        return Response.status(Response.Status.OK).entity("Hello world!").build();
    }

    @GET @Path("greeting/{name}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response makeGreeting(@PathParam("name") String name){
        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        return Response.status(Response.Status.ACCEPTED).entity(greeting).build();
    }

}
