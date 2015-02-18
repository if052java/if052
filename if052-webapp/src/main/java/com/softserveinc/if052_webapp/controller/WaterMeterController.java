package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    private String addressId = "";

    private RestTemplate restTemplate;

    @RequestMapping(value = "/watermeter{addressId}")
    public String getWaterMetersPage(int addressId, ModelMap model) {
        this.addressId = String.valueOf(addressId);
        restTemplate = new RestTemplate();
        Address address = restTemplate.getForObject(restUrl + "address/" + addressId, Address.class);
        List<WaterMeter> waterMeters = address.getWaterMeters();
        model.addAttribute("address", address);
        model.addAttribute("waterMeters", waterMeters);
        return "waterMeters";
    }

    @RequestMapping(value = "/addWaterMeter", method = RequestMethod.POST)
    public String addWaterMeter(@ModelAttribute WaterMeter waterMeter, ModelMap model) {
        restTemplate = new RestTemplate();
        Address address = restTemplate.getForObject(restUrl + "address/" + addressId, Address.class);
        waterMeter.setAddress(address);
        restTemplate.postForObject(restUrl + "watermeters/", waterMeter, WaterMeter.class);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }

    @RequestMapping(value = "/deleteWaterMeter{waterMeterId}")
    public String deleteWaterMeter(int waterMeterId, ModelMap model) {
        restTemplate = new RestTemplate();
        restTemplate.delete(restUrl + "watermeters/" + waterMeterId);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }


    @RequestMapping(value = "/updateWaterMeter{waterMeterId}")
    public String getUpdateWaterMeterPage(int waterMeterId, ModelMap model) {
        restTemplate = new RestTemplate();
        WaterMeter waterMeter = restTemplate.getForObject(restUrl + "watermeters/" + waterMeterId, WaterMeter.class);
        model.addAttribute("waterMeter", waterMeter);
        return "updateWaterMeter";
    }

    @RequestMapping(value = "/updateWaterMeter", method = RequestMethod.POST)
    public String updateWaterMeter(@ModelAttribute WaterMeter waterMeter) {
        restTemplate = new RestTemplate();
        Address address = restTemplate.getForObject(restUrl + "address/" + addressId, Address.class);
        waterMeter.setAddress(address);
        restTemplate.put(restUrl + "watermeters/" + waterMeter.getWaterMeterId(), waterMeter);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }





}
