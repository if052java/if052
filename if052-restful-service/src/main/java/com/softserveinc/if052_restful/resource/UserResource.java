package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.service.UserService;
import org.apache.log4j.Logger;
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

    private static Logger LOGGER = Logger.getLogger(UserResource.class.getName());

    @GET @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddresses() {
        LOGGER.info("INFO: Searching for the whole collection of users.");
        List< User > users = userService.getAllUsers();
        LOGGER.info("INFO: The whole collection of users has been found.");
        return Response.status(Response.Status.OK).entity(users).build();
    }

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("userId") int userId) {
        LOGGER.info("INFO: Searching for the user with id" + userId);
        
        User user = userService.getUserById(userId);

        if (user == null){
            LOGGER.info("INFO: User with requested id " + userId + " has not been found.");

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOGGER.info("INFO: User with requested id " + userId + " has been found.");
        return Response.status(Response.Status.OK).entity(user).build();
    }

    @GET
    @Path("login/{login}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("login") String login) {
        LOGGER.info("INFO: Searching for the user with login" + login);
        User user = userService.getUserByLogin(login);
    
        if (user == null ) {
            LOGGER.info("INFO: User with requested login " + login + " has not been found.");

            return Response.status(Response.Status.NOT_FOUND).build();
        }
        LOGGER.info("INFO: User with requested login " + login + " has been found.");
            return Response.status(Response.Status.OK).entity(user).build();
        }

    @GET
    @Path("/logins")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getLogins() {
        LOGGER.info("INFO: Searching for the whole collection of users login");
        List<String> logins = userService.getLogins();
        LOGGER.info("INFO: The whole collection of users login has been found.");
        return Response.status(Response.Status.OK).entity(logins).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createUser(
        @Valid
        User user ){
        try {
            LOGGER.info("INFO: Adding a new user.");
            userService.insertUser(user);
            LOGGER.info("INFO: User has been successfully added with id " + user.getUserId() + ".");
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
        if (userService.getUserById(userId) == null) {
            LOGGER.info("INFO: User with requested id " + userId + " is not found.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try {
            LOGGER.info("INFO: Updating a user with id " + userId+ ".");
            userService.updateUser(user);
            LOGGER.info("INFO: User with id " + userId + " has been successfully updated.");
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
        if (userService.getUserById(userId) == null) {
            LOGGER.info("INFO: User with requested id " + userId + " is not found.");
            return Response
                .status(Response.Status.NOT_FOUND)
                .build();
        }
        try {
            LOGGER.info("INFO: Deleting a user with id " + userId+ ".");
            userService.deleteUser(userId);
            LOGGER.info("INFO : User with id " + userId + " has been successfully deleted.");
            return Response.status(Response.Status.OK).build();
        }
        catch (Exception e){

            return Response.status(Response.Status.FORBIDDEN).build();
        }
    } 
}
