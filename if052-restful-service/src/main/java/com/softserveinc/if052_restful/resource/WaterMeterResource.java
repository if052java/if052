package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.AddressService;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.h2.jdbc.JdbcSQLException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Danylo Tiahun on 11.02.2015.
 */

@Path("/watermeters")
public class WaterMeterResource {

    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private AddressService addressService;



    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllWaterMeters() {
    List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
    return Response.status(Response.Status.ACCEPTED).entity(waterMeters).build();
    }

    @GET @Path("{waterMeterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        return Response.status(Response.Status.ACCEPTED).entity(waterMeter).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(WaterMeter waterMeter) {
        waterMeterService.insertWaterMeter(waterMeter);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @PUT @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        waterMeterService.updateWaterMeter(waterMeter);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @DELETE @Path("{waterMeterId}")
    public Response deleteWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        for(Indicator i : indicatorService.getAllIndicators()) {
            indicatorService.deleteIndicator(i.getIndicatorId());
        }
        waterMeterService.deleteWaterMeter(waterMeterId);
        return Response.status(Response.Status.OK).build();
    }






}