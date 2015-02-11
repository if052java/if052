package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.WaterMeter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Danylo Tiahun on 12.02.2015.
 */


@Controller
public class WaterMeterController {

    private String addressId = "";

    private RestTemplate restTemplate;

    @RequestMapping(value = "/watermeter")
    public String getWaterMeterPage(@RequestParam(value = "addressId",
            required = true) int addressId, ModelMap model) {
        this.addressId = String.valueOf(addressId);
        restTemplate = new RestTemplate();
        WaterMeter[] temp = restTemplate.getForObject("http://localhost:8080/watermeter/" + addressId, WaterMeter[].class);
        List<WaterMeter> waterMeters = Arrays.asList(temp);
        model.addAttribute("waterMeters", waterMeters);
        return "waterMeters";
    }

    @RequestMapping(value = "/deleteWaterMeter")
    public String deleteWaterMeter(@RequestParam(value = "waterMeterId", required = true)
                                   int waterMeterId, ModelMap model) {
        restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/watermeter/" + waterMeterId);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }


    @RequestMapping(value = "/updateWaterMeter")
    public String getUpdateWaterMeterPage(@RequestParam(value = "waterMeterId", required = true)
                                          int waterMeterId, ModelMap model) {
        //restTemplate = new RestTemplate();
        //WaterMeter waterMeter = restTemplate.getForObject("http://localhost:8080/watermeter" + waterMeterId, WaterMeter.class);
        //model.addAttribute("waterMeter", waterMeter);
        return "updateWaterMeter";
    }



}
