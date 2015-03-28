package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by valentyn on 2/11/15.
 */

@Path("/users")
public class UserResource {
    @Autowired
    UserService userService;

    @GET @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddresses() {
        List< User > users = userService.getAllUsers();
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("userId") int userId) {
        User user = userService.getUserById(userId);

        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("login") String login) {
        try {
            User user = userService.getUserByLogin(login);
    
            if (user == null ) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.status(Response.Status.OK).entity(user).build();
        }
        catch (ConstraintViolationException e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        catch (Exception e){
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/logins")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLogins() {
        List<String> logins = userService.getLogins();
        return Response
                .status(Response.Status.OK)
                .entity(logins)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(
        @Valid
        User user ){
        try {
            userService.insertUser(user);

            return Response.status(Response.Status.CREATED).entity(user).build();
        }
        catch (ConstraintViolationException e){

            return Response.status(Response.Status.FORBIDDEN).build();
        } 
        catch (Exception e) {

             return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @PUT @Path("{userId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateUser(
        @PathParam("userId") int userId,
        @Valid
        User user){
        try {
            userService.updateUser(user);

            return Response.status(Response.Status.OK).build();
        }
        catch (ConstraintViolationException e){

            return Response.status(Response.Status.FORBIDDEN).build();
        }
        catch (Exception e) {

            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @DELETE
    @Path("{userId}")
    public Response deleteUser(
        @PathParam("userId") int  userId
    ){
        try {
            userService.deleteUser(userId);

            return Response.status(Response.Status.OK).build();
        }
        catch (Exception e){

            return Response.status(Response.Status.FORBIDDEN).build();
        }
    } 
}
