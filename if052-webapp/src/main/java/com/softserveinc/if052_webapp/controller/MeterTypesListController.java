package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.MeterType;
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
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This class is a controller of meter types list
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 */
@Controller
public class MeterTypesListController {
    private static Logger LOGGER = Logger.getLogger(MeterTypesListController.class);
    private String reason = "reason";

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("/metertypeslist")
    public String getMeterTypesListPage(ModelMap model) {
        List<MeterType> meterTypes = Arrays.asList(restTemplate.getForObject(restUrl
                + "metertypes/", MeterType[].class));
        model.addAttribute("meterTypes", meterTypes);
        return "meterTypesList";
    }

    @RequestMapping(value = "/addMeterType", method = RequestMethod.POST)
    public String addMeterType(@ModelAttribute MeterType meterType, ModelMap model) {
        if (checkMinLengthTypeName(meterType, model)) return "error400";
        ResponseEntity<String> meterTypeResponseEntity = restTemplate.exchange(restUrl + "metertypes/",
                HttpMethod.POST, new HttpEntity<MeterType>(meterType), String.class);
        if (checkForDuplicateTypeName(model, meterTypeResponseEntity)) return "error400";
        return "redirect:/metertypeslist";
    }

    @RequestMapping(value = "/updateMeterType{meterTypeId}")
    public String getUpdateMeterTypePage(int meterTypeId, ModelMap model) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(restUrl + "metertypes/"
                + meterTypeId, String.class);
        try {
            model.addAttribute("meterType", objectMapper.readValue(responseEntity.getBody(), MeterType.class));
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return "updateMeterType";
    }

    @RequestMapping(value = "/updateMeterType", method = RequestMethod.POST)
    public String updateMeterType(@ModelAttribute MeterType meterType, ModelMap model) {
        if (checkMinLengthTypeName(meterType, model)) return "error400";
        ResponseEntity<String> meterTypeResponseEntity = restTemplate.exchange(restUrl +
                        "metertypes/" + meterType.getMeterTypeId(), HttpMethod.PUT,
                new HttpEntity<MeterType>(meterType), String.class);
        if (checkForDuplicateTypeName(model, meterTypeResponseEntity)) return "error400";
        return "redirect:/metertypeslist";
    }

    @RequestMapping(value = "/deleteMeterType{meterTypeId}")
    public String deleteMeterType(int meterTypeId, ModelMap model) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "metertypes/" + meterTypeId,
                HttpMethod.DELETE, null, String.class);
        if (responseEntity.getStatusCode().value() == 400) {
            model.addAttribute(reason, "This meter type is already in use and cannot be deleted");
            return "error400";
        }
        return "redirect:/metertypeslist";
    }

    private boolean checkMinLengthTypeName(@ModelAttribute MeterType meterType, ModelMap model) {
        meterType.setType(meterType.getType().trim());
        if (meterType.getType().length() < 3) {
            model.addAttribute(reason, "Meter type name must contain at least 3 characters");
            return true;
        }
        return false;
    }

    private boolean checkForDuplicateTypeName(ModelMap model, ResponseEntity<String> meterTypeResponseEntity) {
        if (meterTypeResponseEntity.getStatusCode().value() == 400) {
            model.addAttribute(reason, "This meter type name is already in use");
            return true;
        }
        return false;
    }
}
