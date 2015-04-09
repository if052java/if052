package com.softserveinc.if052_webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.WaterMeter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestOperations;

/**
 * Created by Maxwellt on 09.04.2015.
 */
public class MeterService {
    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static Logger LOGGER = Logger.getLogger(IndicatorService.class);

    public WaterMeter getMeterById(int meterId){
        return restTemplate.getForObject(restUrl + "watermeters/" + meterId, WaterMeter.class);
    }
}
