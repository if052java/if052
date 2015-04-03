package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_restful.service.MeterTypeService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Class of meter type resource
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 */
@Path("/metertypes/")
public class MeterTypeResource {

    @Autowired
    private MeterTypeService meterTypeService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllMeterTypes() {
        List<MeterType> meterTypes = meterTypeService.getAllMeterTypes();
        return Response.status(Response.Status.OK).entity(meterTypes).build();
    }

    @GET
    @Path("{meterTypeId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMeterType(@PathParam("meterTypeId") int meterTypeId) {
        MeterType meterType = meterTypeService.getMeterTypeById(meterTypeId);
        return Response.status(Response.Status.ACCEPTED).entity(meterType).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createMeterType(MeterType meterType){
        meterTypeService.insertMeterType(meterType);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @PUT
    @Path("{meterTypeId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateMeterType(@PathParam("meterTypeId") int meterTypeId, MeterType meterType){
        meterTypeService.updateMeterType(meterType);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @DELETE
    @Path("{meterTypeId}")
    public Response deleteMeterType(@PathParam("meterTypeId") int meterTypeId) {
        meterTypeService.deleteMeterType(meterTypeId);
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
