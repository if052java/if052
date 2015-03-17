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

    private static Logger logger = Logger.getLogger(WaterMeterResource.class.getName());

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllWaterMeters() {
        logger.info("INFO: Searching for the whole collection of watermeters.");
        List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
        logger.info("INFO: The whole collection of watermeter has been found.");
        return Response
                .status(Response.Status.OK)
                .entity(waterMeters)
                .build();
    }

    @GET
    @Path("{waterMeterId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        logger.info("INFO: Searching for the watermeter with id " + waterMeterId + ".");
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        if (waterMeter == null) {
            logger.info("INFO: Watermeter with requested id " + waterMeterId + " has not been found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        logger.info("INFO: Watermeter with requested id " + waterMeterId + " has been successfully found.");
        return Response
                .status(Response.Status.OK)
                .entity(waterMeter)
                .build();

    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(WaterMeter waterMeter) {
        logger.info("INFO: Adding a new watermeter.");
        if (waterMeter.getName().length() < 1) {
            logger.warn("WARNING: Watermeter name cannot be empty.");
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        try {
            waterMeterService.insertWaterMeter(waterMeter);
            logger.info("INFO: Watermeter has been successfully added with id " + waterMeter.getWaterMeterId() + ".");
            return Response
                    .status(Response.Status.CREATED)
                    .header("Location", "/watermeters" + waterMeter.getWaterMeterId())
                    .build();
        } catch (DataIntegrityViolationException e) {
            logger.warn("WARNING: Watermeter with this name already exist.", e);
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    @POST
    @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        logger.info("INFO: Watermeter id cannot be provided by the request.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(WaterMeter waterMeter) {
        logger.info("INFO: The whole collection of watermeters cannot be updated.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @PUT
    @Path("{waterMeterId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response updateWaterMeter(@PathParam("waterMeterId") int waterMeterId, WaterMeter waterMeter) {
        logger.info("INFO: Updating a watermeter with id " + waterMeterId + ".");
        if (waterMeter.getName().length() < 1) {
            logger.warn("WARNIGN: Watermeter name cannot be empty.");
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
        if (waterMeterService.getWaterMeterById(waterMeterId) == null) {
            logger.info("INFO: Watermeter with requested id " + waterMeterId + " is not found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            waterMeterService.updateWaterMeter(waterMeter);
            logger.info("INFO: Watermeter with id " + waterMeterId + " has been successfully updated.");
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (DataIntegrityViolationException e) {
            logger.warn("WARNING: Watermeter with this name already exist.", e);
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }

    @DELETE
    public Response deleteWaterMeter() {
        logger.info("INFO: The whole collection of watermeters cannot be deleted.");
        return Response
                .status(Response.Status.NOT_FOUND)
                .build();
    }

    @DELETE
    @Path("{waterMeterId}")
    public Response deleteWaterMeter(@PathParam("waterMeterId") int waterMeterId) {
        logger.info("INFO: Deleting a watermeter with id " + waterMeterId + ".");
        if (waterMeterService.getWaterMeterById(waterMeterId) == null) {
            logger.info("INFO: Watermeter with requested id " + waterMeterId + " is not found.");
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        try {
            waterMeterService.deleteWaterMeter(waterMeterId);
            logger.info("INFO : Watermeter with id " + waterMeterId + " has been successfully deleted.");
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .build();
        } catch (DataIntegrityViolationException e) {
            logger.warn("WARNING: Watermeter with requester id " + waterMeterId
                        + " contains list of indicators so it cannot be deleted.", e);
        }
        return Response
                .status(Response.Status.BAD_REQUEST)
                .build();
    }
public void test() {System.out.println("SDFSDFDSFDFF" + waterMeterService.getWaterMeterById(1).getName());}

}