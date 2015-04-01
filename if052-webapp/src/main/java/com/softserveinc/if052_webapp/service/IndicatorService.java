package com.softserveinc.if052_webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_webapp.controller.WaterMeterController;
import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.*;

/**
 * Created by Maksym on 2/12/2015.
 */
public class IndicatorService {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static Logger LOGGER = Logger.getLogger(IndicatorService.class);


    public ServiceResponse getIndicatorList(int meterId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "watermeters/"
                + meterId, HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();
        if (isError404(serviceResponse, responseEntity)) return serviceResponse;
        try {
            WaterMeter meter = objectMapper.readValue(responseBody, WaterMeter.class);
            serviceResponse.setResponse(meter.getIndicators());
        } catch (IOException e) {
            LOGGER.warn(e.getMessage(), e);
        }
        return serviceResponse;
    }

    public Indicator deleteIndicator(int indicatorId) {
        Indicator indicator = restTemplate.getForObject(restUrl + "indicators/getone/"+ indicatorId, Indicator.class);
        if (!indicator.isPublished()) {
            restTemplate.delete(restUrl + "indicators/" + indicatorId);
        }
        return indicator;
    }

    public ServiceResponse addIndicator(Indicator indicator){
        ServiceResponse serviceResponse = new ServiceResponse();
        if (isDateCorrect(indicator, serviceResponse)) return serviceResponse;
        ResponseEntity<String> indicatorResponseEntity = restTemplate.exchange(restUrl + "indicators/",
                HttpMethod.POST, new HttpEntity<Indicator>(indicator), String.class);
        serviceResponse.setResponse(Arrays.asList(indicator));
        if (isError400(serviceResponse, indicatorResponseEntity)) return serviceResponse;

        return serviceResponse;
    }

    public Indicator updateIndicator(Indicator indicator, int meterId){
        WaterMeter waterMeter = restTemplate.getForObject(restUrl+ "watermeters/" + meterId, WaterMeter.class);
        indicator.setWaterMeter(waterMeter);
        restTemplate.put(restUrl + "indicators/" + indicator.getIndicatorId(), indicator);

        return indicator;
    }

    public WaterMeter getMeterById(int meterId){
        return restTemplate.getForObject(restUrl + "watermeters/" + meterId, WaterMeter.class);
    }

    public Indicator getIndicatorById(int indicatorId){
        return restTemplate.getForObject(restUrl + "indicators/getone/" + indicatorId, Indicator.class);
    }

    private boolean isError404(ServiceResponse serviceResponse, ResponseEntity<String> responseEntity) {
        if (responseEntity.getStatusCode().value() == 404) {
            serviceResponse.setStatus("error404");
            return true;
        }
        return false;
    }
    private boolean isError400(ServiceResponse serviceResponse, ResponseEntity<String> indicatorResponseEntity) {
        if (indicatorResponseEntity.getStatusCode().value() == 400) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Дата вказана невірно");
            return true;
        }
        return false;
    }

    private boolean isDateCorrect(Indicator indicator, ServiceResponse serviceResponse) {
        if (!(indicator.getDate() instanceof Date)) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Дата вказана невірно");
            return true;
        }
        return false;
    }

}