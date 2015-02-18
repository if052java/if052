package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Maksym on 2/12/2015.
 */
@Path("/indicators")
public class IndicatorResource {

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private WaterMeterService waterMeterService;

    @GET
    @Path("{waterMeterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getIndicators(@PathParam("waterMeterId") int waterMeterId) {
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        List<Indicator> indicators = indicatorService.getIndicatorsByWaterMeter(waterMeter);
        return Response.status(Response.Status.ACCEPTED).entity(indicators).build();
    }

    @GET
    @Path("/get/{indicatorId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getIndicator(@PathParam("indicatorId") int indicatorId) {
        Indicator indicator = indicatorService.getIndicatorById(indicatorId);
        return Response.status(Response.Status.ACCEPTED).entity(indicator).build();
    }

    @DELETE
    @Path("{indicatorId}")
    public Response deleteIndicator(@PathParam("indicatorId") int indicatorId) {
        indicatorService.deleteIndicator(indicatorId);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createIndicator(Indicator indicator){
        indicatorService.insertIndicator(indicator);
        return Response.status(Response.Status.ACCEPTED).build();
    }

    @PUT
    @Path("{indicatorId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateIndicator(@PathParam("indicatorId") int indicatorId, Indicator indicator){
        indicatorService.updateIndicator(indicator);
        return Response.status(Response.Status.ACCEPTED).build();
    }
}
