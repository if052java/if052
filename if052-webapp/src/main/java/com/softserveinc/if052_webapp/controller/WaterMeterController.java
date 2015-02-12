package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.WaterMeter;
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

    private String addressId = "";

    private RestTemplate restTemplate;

    @RequestMapping(value = "/watermeter{addressId}")
    public String getWaterMetersPage(int addressId, ModelMap model) {
        this.addressId = String.valueOf(addressId);
        restTemplate = new RestTemplate();
        Address address = restTemplate.getForObject("http://localhost:8080/watermeter/address/" + addressId, Address.class);
        List<WaterMeter> waterMeters = address.getWaterMeters();
        model.addAttribute("address", address);
        model.addAttribute("waterMeters", waterMeters);
        return "waterMeters";
    }

    @RequestMapping(value = "/addWaterMeter", method = RequestMethod.POST)
    public String addWaterMeter(@ModelAttribute WaterMeter waterMeter, ModelMap model) {
        restTemplate = new RestTemplate();
        Address address = restTemplate.getForObject("http://localhost:8080/watermeter/address/" + addressId, Address.class);
        waterMeter.setAddress(address);
        restTemplate.postForObject("http://localhost:8080/watermeter/", waterMeter, WaterMeter.class);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }

    @RequestMapping(value = "/deleteWaterMeter{waterMeterId}")
    public String deleteWaterMeter(int waterMeterId, ModelMap model) {
        restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/watermeter/" + waterMeterId);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }


    @RequestMapping(value = "/updateWaterMeter{waterMeterId}")
    public String getUpdateWaterMeterPage(int waterMeterId, ModelMap model) {
        restTemplate = new RestTemplate();
        WaterMeter waterMeter = restTemplate.getForObject("http://localhost:8080/watermeter/" + waterMeterId, WaterMeter.class);
        model.addAttribute("waterMeter", waterMeter);
        return "updateWaterMeter";
    }

    @RequestMapping(value = "/updateWaterMeter", method = RequestMethod.POST)
    public String updateWaterMeter(@ModelAttribute WaterMeter waterMeter) {
        restTemplate = new RestTemplate();
        Address address = restTemplate.getForObject("http://localhost:8080/watermeter/address/" + addressId, Address.class);
        waterMeter.setAddress(address);
        restTemplate.put("http://localhost:8080/watermeter/" + waterMeter.getWaterMeterId(), waterMeter);
        return "redirect:/watermeter?addressId=" + this.addressId;
    }





}
