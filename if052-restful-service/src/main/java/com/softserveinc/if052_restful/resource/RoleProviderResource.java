package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.UserRole;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Hata on 09.03.2015.
 */
@Path("/rest/getRoles")
public class RoleProviderResource {

    @GET
    @Path("/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getRole(@PathParam("userId") Integer userId){
        UserRole userRole = new UserRole("USER");
        return Response.status(Response.Status.OK).entity(userRole).build();
    }

}
