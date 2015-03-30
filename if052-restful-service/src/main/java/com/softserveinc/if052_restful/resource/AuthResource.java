package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Auth;

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

    @POST
    @Path("checkCredentials")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response checkCredentials(Auth auth){
        auth.setUserId(48);
        //if (!auth.getPassword().equals("password")) return Response.status(Response.Status.ACCEPTED).build();
        return Response.status(Response.Status.OK).entity(auth).build();
    }
}
