package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
* Created by Danylo Tiahun on 11.02.2015.
*/

@RestController
@RequestMapping("/rest/watermeters")
public class WaterMeterResource {

    @Autowired
    private WaterMeterService waterMeterService;

    private static Logger LOGGER = Logger.getLogger(WaterMeterResource.class.getName());

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<WaterMeter> getAllWaterMeters() {
        LOGGER.info("INFO: Searching for the whole collection of meters.");
        List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
        LOGGER.info("INFO: The whole collection of meters has been found.");
        return waterMeters;
    }

    @RequestMapping(value = "{waterMeterId}", method = RequestMethod.GET, produces = "application/json")
    public WaterMeter getWaterMeter(
        @PathVariable("waterMeterId") int waterMeterId,
        HttpServletResponse response) {
        LOGGER.info("INFO: Searching for the meter with id " + waterMeterId + ".");
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        if (waterMeter == null) {
            LOGGER.info("INFO: Meter with requested id " + waterMeterId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("INFO: Meter with requested id " + waterMeterId + " has been successfully found.");
        return waterMeter;
    }

    @RequestMapping(value = "/firstMeter/{userId}", method = RequestMethod.GET, produces = "application/json")
    public WaterMeter getFirstMeteByUserId(
        @PathVariable("userId") int userId,
        HttpServletResponse response) {
        LOGGER.info("INFO: Searching for first meter for user with id " + userId+ ".");
        WaterMeter waterMeter = waterMeterService.getFirstMeterByUserId(userId);
        if (waterMeter == null) {
            LOGGER.info("INFO: First meter for user with request " + userId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("INFO:First meter with requested id " + userId + " has been successfully found.");
        return waterMeter;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public WaterMeter insertWaterMeter(
        @RequestBody
        WaterMeter waterMeter,
        HttpServletResponse response) {
        LOGGER.info("INFO: Adding a new meter.");
//        if (waterMeter.getName().length() < 1) {
//            LOGGER.warn("WARNING: Meter name cannot be empty.");
//            return Response
//                    .status(Response.Status.BAD_REQUEST)
//                    .build();
//        }
        try {
            waterMeterService.insertWaterMeter(waterMeter);
            LOGGER.info("INFO: Meter has been successfully added with id " + waterMeter.getWaterMeterId() + ".");
            return waterMeter;
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: Meter with this name already exist.", e);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @RequestMapping(value = "{waterMeterId}", method = RequestMethod.POST)
    public void insertWaterMeter(
        @PathVariable("waterMeterId") int waterMeterId, 
        @RequestBody
        WaterMeter waterMeter,
        HttpServletResponse response) {
        LOGGER.info("INFO: Meter id cannot be provided by the request.");
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public WaterMeter updateWaterMeter(
        @RequestBody
        WaterMeter waterMeter,
        HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        LOGGER.info("INFO: The whole collection of meters cannot be updated.");
        return waterMeter;
    }

    @RequestMapping(value = "{waterMeterId}", method = RequestMethod.PUT, produces = "application/json")
    public WaterMeter updateWaterMeter(
        @PathVariable("waterMeterId") int waterMeterId, 
        @RequestBody
        WaterMeter waterMeter,
        HttpServletResponse response) {
        LOGGER.info("INFO: Updating a meter with id " + waterMeterId + ".");
//        if (waterMeter.getName().length() < 1) {
//            LOGGER.warn("WARNIGN: Meter name cannot be empty.");
//            return Response
//                    .status(Response.Status.BAD_REQUEST)
//                    .build();
//        }
        if (waterMeterService.getWaterMeterById(waterMeterId) == null) {
            LOGGER.info("INFO: Meter with requested id " + waterMeterId + " is not found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        try {
            waterMeterService.updateWaterMeter(waterMeter);
            LOGGER.info("INFO: Meter with id " + waterMeterId + " has been successfully updated.");
            return waterMeter;
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: Meter with this name already exist.", e);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, produces = "application/json")
    public void deleteWaterMeter(HttpServletResponse response) {
        LOGGER.info("INFO: The whole collection of meters cannot be deleted.");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            
    }

    @RequestMapping(value = "{waterMeterId}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteWaterMeter(
        @PathVariable("waterMeterId") int waterMeterId,
        HttpServletResponse response) {
        LOGGER.info("INFO: Deleting a meter with id " + waterMeterId + ".");
//        if (waterMeterService.getWaterMeterById(waterMeterId) == null) {
//            LOGGER.info("INFO: Meter with requested id " + waterMeterId + " is not found.");
//            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
//        }
        try {
            waterMeterService.deleteWaterMeter(waterMeterId);
            LOGGER.info("INFO : Meter with id " + waterMeterId + " has been successfully deleted.");
        } catch (DataIntegrityViolationException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.warn("WARNING: Meter with requester id " + waterMeterId
                        + " contains list of indicators so it cannot be deleted.", e);
        }
    }


}