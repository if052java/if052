package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.AddressService;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import com.sun.xml.internal.ws.client.sei.ResponseBuilder;
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
    return Response
            .status(Response.Status.OK)
            .entity(waterMeters)
            .build();
    }

    @GET @Path("{waterMeterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        if(waterMeterService.getWaterMeterById(waterMeterId) == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        return Response
                .status(Response.Status.OK)
                .entity(waterMeter)
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(WaterMeter waterMeter) {
        waterMeterService.insertWaterMeter(waterMeter);
        return Response
                .status(Response.Status.CREATED)
                .build();
    }

    @POST @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(WaterMeter waterMeter) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        if(waterMeterService.getWaterMeterById(waterMeterId) == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        waterMeterService.updateWaterMeter(waterMeter);
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @DELETE
    public Response deleteWaterMeter() {
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @DELETE @Path("{waterMeterId}")
    public Response deleteWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        if(waterMeterService.getWaterMeterById(waterMeterId) == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        if(waterMeterService.getWaterMeterById(waterMeterId).getIndicators() != null) {
            for(Indicator indicator : waterMeterService.getWaterMeterById(waterMeterId).getIndicators()) {
                indicatorService.deleteIndicator(indicator.getIndicatorId());
            }
        }
        waterMeterService.deleteWaterMeter(waterMeterId);
        return Response
                .status(Response.Status.OK)
                .build();
    }


}