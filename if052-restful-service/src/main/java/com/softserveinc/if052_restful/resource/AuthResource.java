package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Auth;
import com.softserveinc.if052_restful.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by nazar on 3/30/15.
 */
@Path("/auth")
public class AuthResource {

    @Autowired
    AuthService authService;

    @POST
    @Path("checkCredentials")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response checkCredentials(Auth param){
        Auth auth;
        try {
            auth = authService.getAuth(param.getUsername());
        }catch (NullPointerException e){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        if (!auth.getUsername().equals(param.getUsername()) ||
                (!auth.getPassword().equals(param.getPassword())) ){
            return Response.status(Response.Status.ACCEPTED).build();
        }
        return Response.status(Response.Status.OK).entity(auth).build();
    }
}
