package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maksym on 2/12/2015.
 */
@Controller
public class IndicatorController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    private RestTemplate restTemplate;

    private String waterMeterId = "";

    @RequestMapping(value = "/indicators{waterMeterId}")
    public String getIndicatorsPage(int waterMeterId, ModelMap model) {
        this.waterMeterId = String.valueOf(waterMeterId);
        WaterMeter waterMeter = restTemplate.getForObject(restUrl + "watermeters/" + waterMeterId, WaterMeter.class);
        Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl + "indicators/"+ waterMeterId, Indicator[].class);
        List<Indicator> indicators = Arrays.asList(arrayOfIndicators);
        model.addAttribute("waterMeter", waterMeter);
        model.addAttribute("indicators", indicators);

        java.util.Date currentDate = new java.util.Date();
        model.addAttribute("currentDate", currentDate);

        return "indicators";
    }

    @RequestMapping("/deleteIndicator{indicatorId}")
    public String deleteIndicator(int indicatorId) {
        Indicator indicator = restTemplate.getForObject(restUrl + "indicators/getone/"+ indicatorId, Indicator.class);
        if (!indicator.isPublished()) {
            restTemplate.delete(restUrl + "indicators/" + indicatorId);
        }

        return "redirect:/indicators?waterMeterId=" + this.waterMeterId;
    }

    @RequestMapping(value = "/addIndicator", method = RequestMethod.POST)
    public String createIndicator(@ModelAttribute Indicator indicator){
        WaterMeter waterMeter = restTemplate.getForObject(restUrl+ "watermeters/" + this.waterMeterId, WaterMeter.class);
        indicator.setWaterMeter(waterMeter);
        restTemplate.postForObject(restUrl + "indicators/", indicator, Indicator.class);

        return "redirect:/indicators?waterMeterId=" + this.waterMeterId;
    }

    @RequestMapping(value = "/updateIndicator{indicatorId}")
    public String getUpdateIndicatorPage(int indicatorId, ModelMap model){
        Indicator indicator = restTemplate.getForObject(restUrl + "indicators/getone/" + indicatorId, Indicator.class);
        if (!indicator.isPublished()) {
            model.addAttribute("indicator", indicator);
        }

        return "updateIndicator";
    }

    @RequestMapping(value = "/updateIndicator", method = RequestMethod.POST)
    public String updateIndicator(@ModelAttribute Indicator indicator){
        WaterMeter waterMeter = restTemplate.getForObject(restUrl+ "watermeters/" + waterMeterId, WaterMeter.class);
        indicator.setWaterMeter(waterMeter);
        restTemplate.put(restUrl + "indicators/" + indicator.getIndicatorId(), indicator);

        return "redirect:/indicators?waterMeterId=" + this.waterMeterId;
    }
}