package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import com.softserveinc.if052_webapp.service.IndicatorService;
import com.softserveinc.if052_webapp.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Maksym on 2/12/2015.
 */
@Controller
public class IndicatorController {

    public static final String WATER_METER = "waterMeter";
    public static final String INDICATORS = "indicators";
    public static final String REDIRECT = "redirect:/indicators?waterMeterId=";
    public static final String INDICATOR = "indicator";
    public static final String REASON = "reason";
    private String meterId = "";

    @Autowired
    private IndicatorService indicatorService;


    @RequestMapping(value = "/indicators{waterMeterId}")
    public String getIndicatorsPage(int waterMeterId, ModelMap model) {
        this.meterId = String.valueOf(waterMeterId);
        ServiceResponse serviceResponse = indicatorService.getIndicatorList(waterMeterId);
        WaterMeter waterMeter = indicatorService.getMeterById(waterMeterId);
        if (serviceResponse.getStatus()=="OK"){
            model.addAttribute(WATER_METER, waterMeter);
            model.addAttribute(INDICATORS, serviceResponse.getResponse());

            return "indicators";
        }
        model.addAttribute(REASON, serviceResponse.getStatus());

        return serviceResponse.getStatus();
    }

    @RequestMapping(value = "/deleteIndicator{indicatorId}")
    public String deleteIndicator(int indicatorId) {
        indicatorService.deleteIndicator(indicatorId);

        return REDIRECT + this.meterId;
    }

    @RequestMapping(value = "/addIndicator", method = RequestMethod.POST)
    public String addIndicator(@ModelAttribute Indicator indicator, ModelMap model){
        WaterMeter waterMeter = indicatorService.getMeterById(Integer.parseInt(meterId));
        indicator.setWaterMeter(waterMeter);
        ServiceResponse serviceResponse = indicatorService.addIndicator(indicator);
        if (serviceResponse.getStatus() == "OK" ) {
            return REDIRECT + this.meterId;
        }
        model.addAttribute(REASON, serviceResponse.getMessage());
        return serviceResponse.getStatus();
    }

    @RequestMapping(value = "/updateIndicator{indicatorId}")
    public String getUpdateIndicatorPage(int indicatorId, ModelMap model){
        Indicator indicator = indicatorService.getIndicatorById(indicatorId);
        if (!indicator.isPublished()) {
            model.addAttribute(INDICATOR, indicator);
        }

        return "updateIndicator";
    }

    @RequestMapping(value = "/updateIndicator", method = RequestMethod.POST)
    public String updateIndicator(@ModelAttribute Indicator indicator){
        indicatorService.updateIndicator(indicator, Integer.parseInt(meterId));

        return REDIRECT + this.meterId;
    }
}