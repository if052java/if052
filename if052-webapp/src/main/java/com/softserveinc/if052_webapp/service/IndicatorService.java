package com.softserveinc.if052_webapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

    @Autowired
    @Qualifier("meterService")
    private MeterService meterService;

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

    public ServiceResponse deleteIndicator(int indicatorId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Indicator indicator = (Indicator) getIndicatorById(indicatorId).getResponse().get(0);
        if (isIndicatorPublished(serviceResponse, indicator)) return serviceResponse;
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                restUrl + "indicators/" + indicatorId,
                HttpMethod.DELETE, null, String.class);
        if (isError400(serviceResponse, responseEntity)) return serviceResponse;

        return serviceResponse;
    }

    public ServiceResponse addIndicator(Indicator indicator, String meterId, String dateStr){
        ServiceResponse serviceResponse = new ServiceResponse();
        parseMeter(indicator, meterId, serviceResponse);
        parseDate(indicator, dateStr, serviceResponse);
        ResponseEntity<String> indicatorResponseEntity = restTemplate.exchange(
                restUrl + "indicators/",
                HttpMethod.POST,
                new HttpEntity<Indicator>(indicator),
                String.class);
        if (isError400(serviceResponse, indicatorResponseEntity)) return serviceResponse;
        if (isError404(serviceResponse, indicatorResponseEntity)) return serviceResponse;
        serviceResponse.setResponse(Arrays.asList(indicator));

        return serviceResponse;
    }

    public ServiceResponse updateIndicator(Indicator indicator, String meterId, String dateStr){
        ServiceResponse serviceResponse = new ServiceResponse();
        parseMeter(indicator, meterId, serviceResponse);
        parseDate(indicator, dateStr, serviceResponse);
        // check is indicator published
        ServiceResponse isPublServResponce = getIndicatorById(indicator.getIndicatorId());
        if (isPublServResponce.getStatus() != "OK") {
            serviceResponse.setStatus(isPublServResponce.getStatus());
            serviceResponse.setMessage( isPublServResponce.getMessage() );
            return serviceResponse;
        }
        Indicator isPublIndicator = (Indicator) isPublServResponce.getResponse().get(0);
        if (isIndicatorPublished(serviceResponse, isPublIndicator)) return serviceResponse;

        ResponseEntity<String> indicatorResponseEntity = restTemplate.exchange(
                restUrl + "indicators/" + indicator.getIndicatorId(),
                HttpMethod.PUT,
                new HttpEntity<Indicator>(indicator),
                String.class);
        if (isError400(serviceResponse, indicatorResponseEntity)) return serviceResponse;
        if (isError404(serviceResponse, indicatorResponseEntity)) return serviceResponse;
        serviceResponse.setResponse(Arrays.asList(indicator));

        return serviceResponse;
    }

    public ServiceResponse getIndicatorById(int indicatorId){
        ServiceResponse serviceResponse = new ServiceResponse();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                restUrl + "indicators/getone/"
                        + indicatorId, String.class);
        try {
            Indicator indicator = objectMapper.readValue(responseEntity.getBody(), Indicator.class);
            serviceResponse.setResponse(Arrays.asList(indicator));
        } catch (IOException e) {
            serviceResponse.setStatus("error404");
            serviceResponse.setMessage(e.getMessage());
            LOGGER.warn(e.getMessage(), e);
        }

        return serviceResponse;
    }

    public WaterMeter getMeterById(int meterId){
        return restTemplate.getForObject(restUrl + "watermeters/" + meterId, WaterMeter.class);
    }

    private void parseMeter(Indicator indicator, String meterId, ServiceResponse serviceResponse) {
        try {
            Integer intMeterId = Integer.parseInt(meterId);
            WaterMeter waterMeter = meterService.getMeterById(intMeterId);
            indicator.setWaterMeter(waterMeter);
        } catch (Exception e) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Помилковий запит.");
            LOGGER.warn(e.getMessage(), e);
        }
    }
    private void parseDate(Indicator indicator, String dateStr, ServiceResponse serviceResponse) {
        try {
            System.out.println(dateStr);

            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            Date date = formatter.parse(dateStr);
            indicator.setDate(date);

            serviceResponse.setResponse(Arrays.asList(indicator));
        } catch (Exception e) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Помилка введення дати");
            LOGGER.warn(e.getMessage(), e);
        }
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
            serviceResponse.setMessage("Некоректний запит. Будь ласка, перевірте правильність введених даних");
            return true;
        }
        return false;
    }
    private boolean isIndicatorPublished(ServiceResponse serviceResponse, Indicator indicator) {
        if (indicator.isPublished()) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Немає доступу. Цей показник вже опубліковано.");
            return true;
        }
        return false;
    }
}