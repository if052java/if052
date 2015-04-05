package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_restful.service.MeterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @Autowired
    private MeterTypeService meterTypeService;

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<MeterType> getAllMeterTypes() {
        List<MeterType> meterTypes = meterTypeService.getAllMeterTypes();
        return meterTypes;
    }

    @RequestMapping(value = "{meterTypeId}", method = RequestMethod.GET, produces = "application/json")
    public MeterType getMeterType(@PathVariable("meterTypeId") int meterTypeId) {
        MeterType meterType = meterTypeService.getMeterTypeById(meterTypeId);
        return meterType;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public MeterType createMeterType(
        @RequestBody
        MeterType meterType){
        meterTypeService.insertMeterType(meterType);
        return meterType;
    }

    @RequestMapping(value = "{meterTypeId}", method = RequestMethod.PUT, produces = "application/json")
    public MeterType updateMeterType(@PathVariable("meterTypeId") int meterTypeId, MeterType meterType){
        meterTypeService.updateMeterType(meterType);
        return meterType;
    }

    @RequestMapping(value = "{meterTypeId}", method = RequestMethod.DELETE)
    public void deleteMeterType(
        @PathVariable("meterTypeId") int meterTypeId) {
        meterTypeService.deleteMeterType(meterTypeId);
    }
}
