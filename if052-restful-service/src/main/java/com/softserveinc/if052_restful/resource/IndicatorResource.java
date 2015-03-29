package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
        if (indicators == null) {
            indicators = new ArrayList<Indicator>();
        }
        return Response.status(Response.Status.ACCEPTED).entity(indicators).build();
    }

    @GET
    @Path("/byYear/{meterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getIndicatorsByYear(@PathParam("meterId") int meterId,
                                  @MatrixParam("year") int year ) {
        String startDate = year + "-01-01 00:00:00";
        String endDate = year + "-31-31 23:59:59";

        List < Indicator > indicators = 
            indicatorService.getIndicatorsByDates(meterId, startDate, endDate);
        if (indicators == null) {
            indicators = new ArrayList<Indicator>();
        }
        return Response.status(Response.Status.ACCEPTED).entity(indicators).build();
    }

    @GET
    @Path("/byDates/{meterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getIndicatorsByDates(@PathParam("meterId") int meterId,
                                        @MatrixParam("startDate") String startDate,
                                        @MatrixParam("endDate") String endDate) {

        List < Indicator > indicators =
            indicatorService.getIndicatorsByDates(meterId, startDate, endDate);
        if (indicators == null) {
            indicators = new ArrayList<Indicator>();
        }
        return Response.status(Response.Status.ACCEPTED).entity(indicators).build();
    }

    @GET
    @Path("/getone/{indicatorId}")
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
