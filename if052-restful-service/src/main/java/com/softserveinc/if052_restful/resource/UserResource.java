package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by valentyn on 2/11/15.
 */

@Path("/user")
public class UserResource {
    @Autowired
    UserService userService;

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("userId") int userId) {
        User user = userService.getUserById(userId);
        return Response.status(Response.Status.ACCEPTED).entity(user).build();
    }
}
