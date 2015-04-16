package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_core.domain.ValidationError;
import com.softserveinc.if052_core.domain.WaterMeter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Danylo Tiahun on 12.02.2015.
 */

@Controller
public class WaterMeterController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private String addressId = "";

    private final String REASON = "reason";
    private final String RESOURCE = "resource";
    private final String ADDRESS = "address";
    private final String WATER_METER = "waterMeter";
    private final String WATER_METERS = "waterMeters";
    private final String METER_TYPES = "meterTypes";

    private static Logger LOGGER = Logger.getLogger(WaterMeterController.class);


    @RequestMapping(value = "/watermeter{addressId}")
    public String getWaterMetersPage(int addressId, ModelMap model) {
        this.addressId = String.valueOf(addressId);
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "addresses/" + addressId,
                HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();
        if (responseEntity.getStatusCode().value() == 404) {
            model.addAttribute(RESOURCE, "address");
            return "error404";
        }
        try {
            Address address = objectMapper.readValue(responseBody, Address.class);
            List<WaterMeter> waterMeters = address.getWaterMeters();
            model.addAttribute(ADDRESS, address);
            model.addAttribute(WATER_METERS, waterMeters);
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        List<MeterType> mt = Arrays.asList(restTemplate.getForObject(restUrl + "metertypes/", MeterType[].class));
        model.addAttribute(METER_TYPES, mt);
        return "waterMeters";
    }

    @RequestMapping(value = "/addWaterMeter", method = RequestMethod.POST)
    public String addWaterMeter(@ModelAttribute WaterMeter waterMeter, ModelMap model, @RequestParam int typeId) {
        waterMeter.setName(waterMeter.getName().trim());
        waterMeter.setMeterType(restTemplate.getForObject(restUrl + "metertypes/" + typeId, MeterType.class));

        ResponseEntity addressResponseEntity = restTemplate.getForEntity(restUrl + "addresses/" + addressId, Address.class);
        waterMeter.setAddress((Address) addressResponseEntity.getBody());

        ResponseEntity<String> waterMeterResponseEntity = restTemplate.exchange(restUrl + "watermeters/",
                HttpMethod.POST, new HttpEntity<WaterMeter>(waterMeter), String.class);


        if(waterMeterResponseEntity.getStatusCode().value()!=201){
            try {
                ValidationError error = objectMapper.readValue(waterMeterResponseEntity.getBody(), ValidationError.class );

                if(error.getFieldErrors().size()>0){
                    model.addAttribute("fieldErrors", error.getFieldErrors());
                    return "validationError";
                }
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }

        }


        return "redirect:/watermeter?addressId=" + this.addressId;
    }

    @RequestMapping(value = "/deleteWaterMeter{waterMeterId}")
    public String deleteWaterMeter(int waterMeterId, ModelMap model) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "watermeters/" + waterMeterId,
                HttpMethod.DELETE, null, String.class);
        if (responseEntity.getStatusCode().value() == 400) {
            model.addAttribute(REASON, "This meter has tied indicators so it cannot be deleted.");
            return "error400";
        }
        return "redirect:/watermeter?addressId=" + this.addressId;
    }

    @RequestMapping(value = "/updateWaterMeter")
    public String getUpdateWaterMeterPage(@RequestParam("waterMeterId")int waterMeterId, ModelMap model) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(restUrl + "watermeters/"
                + waterMeterId, String.class);
        try {
            model.addAttribute(WATER_METER, objectMapper.readValue(responseEntity.getBody(), WaterMeter.class));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return "updateWaterMeter";
    }

    @RequestMapping(value = "/updateWaterMeter", method = RequestMethod.POST)
    public String updateWaterMeter(@ModelAttribute WaterMeter waterMeter, ModelMap model) {
        waterMeter.setName(waterMeter.getName().trim());
        ResponseEntity<String> addressResponseEntity = restTemplate.getForEntity(restUrl + "addresses/" + addressId, String.class);
        
        try {
            waterMeter.setAddress(objectMapper.readValue(addressResponseEntity.getBody(), Address.class));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        ResponseEntity<String> waterMeterResponseEntity = restTemplate.exchange(restUrl + "watermeters/" + waterMeter.getWaterMeterId(),
                HttpMethod.PUT, new HttpEntity<WaterMeter>(waterMeter), String.class);
        if (waterMeterResponseEntity.getStatusCode().value() != 202) {
            try {
                ValidationError error = objectMapper.readValue(waterMeterResponseEntity.getBody(), ValidationError.class );

                if(error.getFieldErrors().size()>0){
                    model.addAttribute("fieldErrors", error.getFieldErrors());
                    return "validationError";
                }
                return "error400";
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
        return "redirect:/watermeter?addressId=" + this.addressId;
    }

}
