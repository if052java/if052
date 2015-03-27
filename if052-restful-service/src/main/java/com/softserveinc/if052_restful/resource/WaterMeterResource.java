package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    private static Logger LOGGER = Logger.getLogger(WaterMeterResource.class.getName());

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllWaterMeters() {
        LOGGER.info("INFO: Searching for the whole collection of meters.");
        List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
        LOGGER.info("INFO: The whole collection of meters has been found.");
        return Response
                .status(Response.Status.OK)
                .entity(waterMeters)
                .build();
    }

    @GET
    @Path("{waterMeterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        LOGGER.info("INFO: Searching for the meter with id " + waterMeterId + ".");
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        if (waterMeter == null) {
            LOGGER.info("INFO: Meter with requested id " + waterMeterId + " has not been found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        LOGGER.info("INFO: Meter with requested id " + waterMeterId + " has been successfully found.");
        return Response
                .status(Response.Status.OK)
                .entity(waterMeter)
                .build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(WaterMeter waterMeter) {
        LOGGER.info("INFO: Adding a new meter.");
        if (waterMeter.getName().length() < 1) {
            LOGGER.warn("WARNING: Meter name cannot be empty.");
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        try {
            waterMeterService.insertWaterMeter(waterMeter);
            LOGGER.info("INFO: Meter has been successfully added with id " + waterMeter.getWaterMeterId() + ".");
            return Response
                    .status(Response.Status.CREATED)
                    .header("Location", "/rest/watermeters" + waterMeter.getWaterMeterId())
                    .build();
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: Meter with this name already exist.", e);
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    @POST
    @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        LOGGER.info("INFO: Meter id cannot be provided by the request.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(WaterMeter waterMeter) {
        LOGGER.info("INFO: The whole collection of meters cannot be updated.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        LOGGER.info("INFO: Updating a meter with id " + waterMeterId + ".");
        if (waterMeter.getName().length() < 1) {
            LOGGER.warn("WARNIGN: Meter name cannot be empty.");
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        if (waterMeterService.getWaterMeterById(waterMeterId) == null) {
            LOGGER.info("INFO: Meter with requested id " + waterMeterId + " is not found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            waterMeterService.updateWaterMeter(waterMeter);
            LOGGER.info("INFO: Meter with id " + waterMeterId + " has been successfully updated.");
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: Meter with this name already exist.", e);
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    @DELETE
    public Response deleteWaterMeter() {
        LOGGER.info("INFO: The whole collection of meters cannot be deleted.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @DELETE
    @Path("{waterMeterId}")
    public Response deleteWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        LOGGER.info("INFO: Deleting a meter with id " + waterMeterId + ".");
        if (waterMeterService.getWaterMeterById(waterMeterId) == null) {
            LOGGER.info("INFO: Meter with requested id " + waterMeterId + " is not found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            waterMeterService.deleteWaterMeter(waterMeterId);
            LOGGER.info("INFO : Meter with id " + waterMeterId + " has been successfully deleted.");
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: Meter with requester id " + waterMeterId
                        + " contains list of indicators so it cannot be deleted.", e);
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }


}