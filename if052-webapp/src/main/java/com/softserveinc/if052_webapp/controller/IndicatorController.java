package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
import com.softserveinc.if052_webapp.service.IndicatorService;
import com.softserveinc.if052_webapp.service.MeterService;
import com.softserveinc.if052_webapp.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Maksym on 2/12/2015.
 */
@Controller
public class IndicatorController {

    public static final String WATER_METER = "waterMeter";
    public static final String INDICATORS = "indicators";
    public static final String REDIRECT = "redirect:/indicators?meterId=";
    public static final String INDICATOR = "indicator";
    public static final String REASON = "reason";
    private String meterId = "";

    @Autowired
    @Qualifier("indicatorService")
    private IndicatorService indicatorService;

    @Autowired
    @Qualifier("meterService")
    private MeterService meterService;


    @RequestMapping(value = "/indicators{meterId}")
    public String getIndicatorsPage(int meterId, ModelMap model) {
        this.meterId = String.valueOf(meterId);
        ServiceResponse indServResponse = indicatorService.getIndicatorList(meterId);
        if (isError(model, indServResponse)) return indServResponse.getStatus();

        ServiceResponse meterServResponce = meterService.getMeterById(meterId);
        if (isError(model, meterServResponce)) return meterServResponce.getStatus();
        WaterMeter waterMeter = (WaterMeter) meterServResponce.getResponse().get(0);

        model.addAttribute(WATER_METER, waterMeter);
        model.addAttribute(INDICATORS, indServResponse.getResponse());

        return "indicators";
    }

    @RequestMapping(value = "/deleteIndicator{indicatorId}")
    public String deleteIndicator(int indicatorId, ModelMap model) {
        ServiceResponse serviceResponse = indicatorService.deleteIndicator(indicatorId);
        if (isError(model, serviceResponse)) return serviceResponse.getStatus();

        return REDIRECT + this.meterId;
    }

    @RequestMapping(value = "/addIndicator", method = RequestMethod.POST)
    public String addIndicator(@ModelAttribute Indicator indicator,
                               ModelMap model,
                               @RequestParam("dateStr") String dateStr,
                               @RequestParam("locale") String locale){
        ServiceResponse serviceResponse = indicatorService.addIndicator(indicator, meterId, dateStr + locale);
        if (isError(model, serviceResponse)) return serviceResponse.getStatus();

        return REDIRECT + this.meterId;
    }

    @RequestMapping(value = "/updateIndicator{indicatorId}")
    public String getUpdateIndicatorPage(int indicatorId, ModelMap model){
        ServiceResponse serviceResponse = indicatorService.getIndicatorById(indicatorId);
        if (isError(model, serviceResponse)) return serviceResponse.getStatus();
        Indicator indicator = (Indicator) serviceResponse.getResponse().get(0);
        model.addAttribute(INDICATOR, indicator);

        return "updateIndicator";
    }

    @RequestMapping(value = "/updateIndicator", method = RequestMethod.POST)
    public String updateIndicator(@ModelAttribute Indicator indicator,
                                  ModelMap model,
                                  @RequestParam("dateStr") String dateStr,
                                  @RequestParam("locale") String locale){
        ServiceResponse serviceResponse = indicatorService.updateIndicator(indicator, meterId, dateStr + locale);
        if (isError(model, serviceResponse)) return serviceResponse.getStatus();

        return REDIRECT + this.meterId;
    }

    private boolean isError(ModelMap model, ServiceResponse serviceResponse) {
        if (serviceResponse.getStatus() != "OK"){
            model.addAttribute(REASON, serviceResponse.getStatus());
            return true;
        }
        return false;
    }
}