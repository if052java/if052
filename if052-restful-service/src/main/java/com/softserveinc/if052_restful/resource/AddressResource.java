package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by valentyn on 2/11/15.
 */
@Path("address")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @GET
    @Path("/list/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response makeWaterMeter(@PathParam("userId") String userId) {
        List < Address > addresses = addressService.getAllAddressesByUserId(Integer.valueOf(userId));
        return Response.status(Response.Status.ACCEPTED).entity(addresses).build();
    }

    @GET @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public Response makeWaterMeters() {
        List<Address> addresses = addressService.getAllAddresses();
        return Response.status(Response.Status.ACCEPTED).entity(addresses).build();
    }

    @DELETE
    @Path("deleteAddress/{addressId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteWaterMeter(@PathParam("addressId") int addressId) {
        addressService.deleteAddress(addressId);
        return Response.ok().build();
    }
}
