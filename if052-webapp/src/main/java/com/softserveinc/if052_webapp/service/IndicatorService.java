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
 * Class for handling REST operations
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

    /**
     * Recieves a waterMeter from RESTful and puts it into ServiceResponce instance
     * @param meterId
     * @return a ServiceResponce instance ( status (by default is "OK" ), status message, responce (a List) )
     */
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

    /**
     * Sends request ot RESTful for deleting an indicator with id=indicatorId
     * @param indicatorId
     * @return a ServiceResponce instance ( status (by default = "OK" ), status message,
     *          responce (a List with the indicator if status still = "OK") )
     */
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

    /**
     * Sends request ot RESTful for adding an indicator
     * @param indicator
     * @param meterId
     * @param dateStr (for parsing date in local(Ukrainian) format)
     * @return a ServiceResponce instance ( status (by default = "OK" ), status message,
     *          responce (a List with the indicator if status still = "OK") )
     */
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

    /**
     * Sends request ot RESTful for updating an indicator
     * @param indicator
     * @param meterId
     * @param dateStr
     * @return a ServiceResponce instance ( status (by default = "OK" ), status message,
     *          responce (a List with the indicator if status still = "OK") )
     */
    public ServiceResponse updateIndicator(Indicator indicator, String meterId, String dateStr){
        ServiceResponse serviceResponse = new ServiceResponse();
        parseMeter(indicator, meterId, serviceResponse);
        parseDate(indicator, dateStr, serviceResponse);
        // check is indicator published. If it is then we haven't access for changing or deleting it
        ServiceResponse isPublServResponce = getIndicatorById(indicator.getIndicatorId());
        if (isStatusNotOK(serviceResponse, isPublServResponce)) return serviceResponse;
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

    /**
     * @param indicatorId
     * @return an Indicator instance which id=indicatorId
     */
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

    /**
     * Parses meterId and sets a waterMeter with this id to the indicator
     * @param indicator
     * @param meterId
     * @param serviceResponse
     */
    private void parseMeter(Indicator indicator, String meterId, ServiceResponse serviceResponse) {
        try {
            Integer intMeterId = Integer.parseInt(meterId);
            
            ServiceResponse meterServResponce = meterService.getMeterById(intMeterId);
            isStatusNotOK(serviceResponse, meterServResponce);
            WaterMeter waterMeter = (WaterMeter) meterServResponce.getResponse().get(0);

            indicator.setWaterMeter(waterMeter);
        } catch (Exception e) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Помилковий запит.");
            LOGGER.warn(e.getMessage(), e);
        }
    }

    /**
     * Parses dateStr to java.util.Date in local format and sets as indicator's date
     * @param indicator
     * @param dateStr
     * @param serviceResponse
     */
    private void parseDate(Indicator indicator, String dateStr, ServiceResponse serviceResponse) {
        try {
            SimpleDateFormat formatter;
            int strLng = dateStr.length();
            if (dateStr.substring(strLng-2).equals("uk")) {
                formatter  = new SimpleDateFormat("dd-MM-yyyy");
            } else {
                formatter = new SimpleDateFormat("MM/dd/yyyy");
            }
            dateStr = dateStr.substring(0, strLng-2);
            Date date = formatter.parse(dateStr);
            indicator.setDate(date);

            serviceResponse.setResponse(Arrays.asList(indicator));
        } catch (Exception e) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Помилка введення дати");
            LOGGER.warn(e.getMessage(), e);
        }
    }

    /**
     * Checks if responseEntity returned an error 404 and changes serviceResponse status
     * @param serviceResponse
     * @param responseEntity
     * @return true | false
     */
    private boolean isError404(ServiceResponse serviceResponse, ResponseEntity<String> responseEntity) {
        if (responseEntity.getStatusCode().value() == 404) {
            System.out.println(404);
            serviceResponse.setStatus("error404");
            return true;
        }
        return false;
    }

    /**
     * Checks if responseEntity returned an error 400 and changes serviceResponse status with message
     * @param serviceResponse
     * @param indicatorResponseEntity
     * @return true | false
     */
    private boolean isError400(ServiceResponse serviceResponse, ResponseEntity<String> indicatorResponseEntity) {
        if (indicatorResponseEntity.getStatusCode().value() == 400) {
            System.out.println(400);
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Некоректний запит. Будь ласка, перевірте правильність введених даних");
            return true;
        }
        return false;
    }

    /**
     * Checks if the indicator is published.
     * If it is then we haven't access for changing or deleting it
     * @param serviceResponse
     * @param indicator
     * @return true | false
     */
    private boolean isIndicatorPublished(ServiceResponse serviceResponse, Indicator indicator) {
        if (indicator.isPublished()) {
            serviceResponse.setStatus("error400");
            serviceResponse.setMessage("Немає доступу. Цей показник вже опубліковано.");
            return true;
        }
        return false;
    }

    /**
     * Checks if ServiceResponse's status is not "OK" when we use one service method in other one.
     * If an error is exist sets it's status and message as current.
     * @param thisServiceResponse
     * @param otherServResponce
     * @return
     */
    private boolean isStatusNotOK(ServiceResponse thisServiceResponse, ServiceResponse otherServResponce) {
        if (otherServResponce.getStatus() != "OK") {
            thisServiceResponse.setStatus(otherServResponce.getStatus());
            thisServiceResponse.setMessage(otherServResponce.getMessage() );
            return true;
        }
        return false;
    }
}