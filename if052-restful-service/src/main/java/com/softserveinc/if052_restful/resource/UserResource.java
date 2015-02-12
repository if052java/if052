package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by valentyn on 2/11/15.
 */

@Path("/user")
public class UserResource {
//    @Autowired
//    private UserService userService;

//    @POST
//    @Path("new/")
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response create(
//        @RequestBody
//        User user,
//
//        HttpServletResponse response
//    ){
//        try {
//            //- Set HTTP status -//
//            response.setStatus( HttpStatus.CREATED.value() );
//
//            //- Success. Return created staff-//
//            userService.insertUser(user);
//
//            return Response.status(Response.Status.CREATED).entity(user).build();
//        }
//        catch ( ConstraintViolationException e ) {
//            //- Failure. Can not to create staff-//
//            response.setStatus( HttpStatus.FORBIDDEN.value() );
//        }
//        catch ( Exception e ) {
//            //- Failure. Can not to create staff -//
//            response.setStatus( HttpStatus.FORBIDDEN.value() );
//        }
//        return null;
//    }
}
