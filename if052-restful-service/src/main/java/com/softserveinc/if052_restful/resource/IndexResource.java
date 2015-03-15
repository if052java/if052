package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.HelloWorld.Greeting;
import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.service.UserService;
import org.glassfish.jersey.server.mvc.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Nazar Ostryzhniuk on 2/3/15.
 */
@Path("/rest/")
public class IndexResource {

    @GET
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    public Response displayLoginPage() {
        return Response.ok(new Viewable("/login")).build();
    }

    @GET
    @Path("login/{authentication_error}")
    @Produces(MediaType.TEXT_HTML)
    public Response LoginPageError(@PathParam("authentication_error") boolean authentication_error){
        return Response.ok(new Viewable("/login", authentication_error)).build();
    }

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
