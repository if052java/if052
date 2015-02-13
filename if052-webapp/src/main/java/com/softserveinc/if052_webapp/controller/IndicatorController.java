package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Indicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

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

    private String waterMeterId = "";

    @RequestMapping("/indicators")
    public String GetIndicators(@RequestParam(value = "waterMeterId",
            required = true, defaultValue = "1") String waterMeterId, ModelMap model) {
        RestTemplate restTemplate = new RestTemplate();
        Indicator[] temp = restTemplate.getForObject(restUrl + "indicators/"+waterMeterId, Indicator[].class);
        List<Indicator> indicators = Arrays.asList(temp);
        this.waterMeterId = waterMeterId;
        model.addAttribute("indicators", indicators);

        return "indicators";
    }

    //delete

    //insert

    //update
}