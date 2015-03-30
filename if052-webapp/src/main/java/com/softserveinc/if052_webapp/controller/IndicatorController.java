package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import com.softserveinc.if052_webapp.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;

/**
 * Created by Maksym on 2/12/2015.
 */
@Controller
public class IndicatorController {

    public static final String WATER_METER = "waterMeter";
    public static final String INDICATORS = "indicators";
    public static final String REDIRECT = "redirect:/indicators?waterMeterId=";
    public static final String INDICATOR = "indicator";
    private String meterId = "";

    @Autowired
    private IndicatorService indicatorService;


    @RequestMapping(value = "/indicators{waterMeterId}")
    public String getIndicatorsPage(int waterMeterId, ModelMap model) {
        this.meterId = String.valueOf(waterMeterId);
        List<Indicator> indicators = indicatorService.getIndicatorList(waterMeterId);
        WaterMeter waterMeter = indicatorService.getMeterById(waterMeterId);
        model.addAttribute(WATER_METER, waterMeter);
        model.addAttribute(INDICATORS, indicators);

        return "indicators";
    }

    @RequestMapping(value = "/deleteIndicator{indicatorId}")
    public String deleteIndicator(int indicatorId) {
        indicatorService.deleteIndicator(indicatorId);

        return REDIRECT + this.meterId;
    }

    @RequestMapping(value = "/addIndicator", method = RequestMethod.POST)
    public String addIndicator(@ModelAttribute Indicator indicator){
        WaterMeter waterMeter = indicatorService.getMeterById(Integer.parseInt(meterId));
        indicator.setWaterMeter(waterMeter);
        indicatorService.addIndicator(indicator);

        return REDIRECT + this.meterId;
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