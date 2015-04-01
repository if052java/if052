package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.AddressService;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by valentyn on 2/11/15.
 */
@Path("/addresses")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @Autowired
    WaterMeterService waterMeterService;

    @Autowired
    IndicatorService indicatorService;

    private static Logger LOGGER = Logger.getLogger(WaterMeterResource.class.getName());

    @GET
    @Path("/list/{userId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddressesByUserId(@PathParam("userId") String userId) {

        List<Address> addresses = addressService.getAllAddressesByUserId(Integer.valueOf(userId));

        if( addresses == null) {
            addresses = new ArrayList<Address>();
        }
        return Response.status(Response.Status.OK).entity(addresses).build();
    }

    @GET @Path("/{addressId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddress(@PathParam("addressId") int addressId) {
        Address address = addressService.getAddressById(addressId);
        if (address == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(address).build();
        }
        return Response.status(Response.Status.OK).entity(address).build();
    }

    @GET @Path("/list")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return Response.status(Response.Status.OK).entity(addresses).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createAddress(Address address){
        addressService.insertAddress(address);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT @Path("{addressId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateAddress(
        @PathParam("addressId") int addressId,
        Address address){
        addressService.updateAddress(address);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{addressId}")
    public Response deleteAddress(@PathParam("addressId") int addressId) {
        LOGGER.info("INFO: Deleting a address with id " + addressId + ".");
        if (addressService.getAddressById(addressId) == null){
            LOGGER.info("INFO: Address with requested id " + addressId + " is not found.");
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        try{
            LOGGER.info("INFO : Meter with id " + addressId + " has been successfully deleted.");
            addressService.deleteAddress(addressId);
            return Response.status(Response.Status.OK).build();
        } catch ( DataIntegrityViolationException e){
            LOGGER.warn("WARNING: Address with requester id " + addressId
                + " contains list of meters so it cannot be deleted.", e);  
        }

        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
