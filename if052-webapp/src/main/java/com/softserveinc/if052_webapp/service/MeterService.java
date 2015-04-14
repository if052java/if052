package com.softserveinc.if052_webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.WaterMeter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.Arrays;

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

    public ServiceResponse getMeterById(int meterId){
        ServiceResponse serviceResponse = new ServiceResponse();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                restUrl + "watermeters/" + meterId,
                String.class);
        try {
            WaterMeter waterMeter = objectMapper.readValue(responseEntity.getBody(), WaterMeter.class);
            serviceResponse.setResponse(Arrays.asList(waterMeter));
        } catch (IOException e) {
            serviceResponse.setStatus("error404");
            serviceResponse.setMessage(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }

        return serviceResponse;
    }
}
