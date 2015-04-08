package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_restful.service.MeterTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class of meter type resource
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 */
@RestController
@RequestMapping("/rest/metertypes/")
public class MeterTypeResource {
    private static Logger LOGGER = Logger.getLogger(WaterMeterResource.class.getName());

    @Autowired
    private MeterTypeService meterTypeService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<MeterType> getAllMeterTypes() {
        return meterTypeService.getAllMeterTypes();
    }

    @RequestMapping(value = "{meterTypeId}", method = RequestMethod.GET,
            produces = "application/json")
    public MeterType getMeterType(@PathVariable("meterTypeId") int meterTypeId) {
        return meterTypeService.getMeterTypeById(meterTypeId);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public MeterType insertMeterType(@RequestBody MeterType meterType,
                                     HttpServletResponse response) {
        LOGGER.info("INFO: Adding a new meter type.");
        try {
            meterTypeService.insertMeterType(meterType);
            LOGGER.info("INFO: New meter type with id=" + meterType.getMeterTypeId()
                    + " has been successfully added");
            return meterType;
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: This meter type name is already in use", e);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    public MeterType updateMeterType(@RequestBody MeterType meterType,
                                     HttpServletResponse response) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return meterType;
    }

    @RequestMapping(value = "{meterTypeId}", method = RequestMethod.PUT,
            produces = "application/json")
    public MeterType updateMeterType(@PathVariable("meterTypeId") int meterTypeId,
                                     @RequestBody MeterType meterType,
                                     HttpServletResponse response) {
        try {
            meterTypeService.updateMeterType(meterType);
            LOGGER.info("INFO: Meter type with id=" + meterTypeId
                    + " has been successfully updated");
            return meterType;
        } catch (DataIntegrityViolationException e) {
            LOGGER.warn("WARNING: This meter type name is already in use", e);
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @RequestMapping(value = "{meterTypeId}", method = RequestMethod.DELETE)
    public void deleteMeterType(@PathVariable("meterTypeId") int meterTypeId,
                                HttpServletResponse response) {
        LOGGER.info("INFO: Deleting a meter type with id=" + meterTypeId + "");
        try {
            meterTypeService.deleteMeterType(meterTypeId);
            LOGGER.info("INFO : Meter type with id=" + meterTypeId
                    + " has been successfully deleted");
        } catch (DataIntegrityViolationException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.warn("WARNING: Meter type with id=" + meterTypeId
                    + " is already in use and cannot be deleted", e);
        }
    }
}
