package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.softserveinc.if052_webapp.domain.*;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by valentyn on 2/11/15.
 */
@Controller
public class GraphController {

    private final String YEAR= "year";
    private final String INDICATORS_DATA = "indicatorsData";
    private final String MONTH = "month";
    private final String ADDRESSES = "addresses";
    private final String METER_NAME = "meterName";
    private final String METER_TYPE = "meterType";
    private final String REASON = "resource";
    private final String ERROR = "error";

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthInterface authBean;

    private static Logger logger = Logger.getLogger(WaterMeterController.class);


    @RequestMapping("defaultgraph")
    public String getMainGraph(ModelMap model){

        //- Get all addresses of user for select-//
        Address[] arrayOfAddress = restTemplate.getForObject(restUrl + "addresses/list/" + authBean.getUserId(), Address[].class);
        List < Address > addresses = Arrays.asList(arrayOfAddress);

        //- Get first meter for user -//
        ResponseEntity < String > responseEntity = restTemplate.exchange(restUrl + "watermeters/firstMeter/" + authBean.getUserId(),
            HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();

        if (responseEntity.getStatusCode().value() == 404) {
            model.addAttribute(REASON, "watermeters");
            return "error404";
        }
        try {
            WaterMeter meter = objectMapper.readValue(responseBody, WaterMeter.class);

            model.addAttribute(METER_NAME, meter.getName());
            model.addAttribute(METER_TYPE, meter.getMeterType().getType());

            //- Get current year -//
            int year = Calendar.getInstance().get(Calendar.YEAR);

            //- Get all indicators of first meter for graph -//
            Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl
                + "indicators/byYear/" + meter.getWaterMeterId() + ";year=" + year, Indicator[].class);
            List < Indicator > indicators = Arrays.asList( arrayOfIndicators );

            if( indicators.size() == 0){
                model.addAttribute(ERROR,"Лічильник не має введених показників");
            }

            long[][] arrayOfData = new long[indicators.size()][2];

                for (int i = 0; i < indicators.size(); i++) {
                    arrayOfData[i][0] = indicators.get(i).getDate().getTime() + 7200000;
                    arrayOfData[i][1] = indicators.get(i).getValue();
                }

            String arrAsString = Arrays.deepToString(arrayOfData);
            model.addAttribute(INDICATORS_DATA, arrAsString);

            model.addAttribute(YEAR, year);

        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
        
        model.addAttribute(ADDRESSES, addresses);
        return "graphs";
    }

    @RequestMapping(value="graphByOption", method=RequestMethod.POST)
    public String getGraphByOption(
                                 @RequestParam("meter") Integer meterId,
                                 @RequestParam("month") Integer month,
                                 @RequestParam("year") Integer year,
                                 ModelMap model) {
        //- Get list of addresses for select-//
        Address[] arrayOfAddress = restTemplate.getForObject(restUrl + "addresses/list/" + authBean.getUserId(), Address[].class);
        List<Address> addresses = Arrays.asList(arrayOfAddress);

        //- Get watermeter by id-//
        ResponseEntity < String > responseEntity = restTemplate.exchange(restUrl + "watermeters/" + meterId,
            HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();
        
        if (responseEntity.getStatusCode().value() == 404) {
            model.addAttribute(REASON, "watermeter");
            return "error404";
        }
        try {
            WaterMeter meter= objectMapper.readValue(responseBody, WaterMeter.class);
            model.addAttribute(METER_NAME, meter.getName());
            model.addAttribute(METER_TYPE, meter.getMeterType().getType());
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
        //-Get start and end date-//
        String[] dates = new String[2];
        dates = getStartEndDate(month, year);

        //- Get list of indicators -//
        Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl
            + "indicators/byDates/" + meterId
            + ";startDate=" + dates[0] + ";endDate=" + dates[1], Indicator[].class);
        List < Indicator > indicators = Arrays.asList(arrayOfIndicators);

        long[][] arrayOfData = new long[indicators.size()][2];

        if( indicators.size() == 0){
            model.addAttribute(ERROR,"Лічильник не має введених показників");
        }
        for (int i = 0; i < indicators.size(); i++) {
            arrayOfData[ i ] [ 0 ] = indicators.get(i).getDate().getTime() + 7200000;
            arrayOfData[ i ] [ 1 ] = indicators.get(i).getValue();
        }
        String arrAsString = Arrays.deepToString(arrayOfData);

        model.addAttribute(MONTH, month);
        model.addAttribute(ADDRESSES, addresses);
        model.addAttribute(INDICATORS_DATA, arrAsString);
        model.addAttribute(YEAR, year);

        return "graphs";
    }

    @RequestMapping("/watermeterlist{addressId}")
    public @ResponseBody String getwatermetersByAddress(ModelMap model, int addressId) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "addresses/" + addressId,
            HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();
        String json = "";

        try {
            Address address = objectMapper.readValue(responseBody, Address.class);
            List < WaterMeter > waterMeters = address.getWaterMeters();
            json = new Gson().toJson(waterMeters);
            return json;
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }

        return json;
    }

    /**
     * Get start and end date
     *
     * @param month - from select in graphs.jsp
     * @param year - from select in graphs.jsp
     * @return dates[] - array with two dates
     */
    public String[] getStartEndDate(int month, int year){

        //- Get information for leap of year-//
        GregorianCalendar cal =
            (GregorianCalendar) GregorianCalendar.getInstance();
        boolean isLeapYear = cal.isLeapYear(year);
        //- Get start and end date-//
        String[] dates = new String[2];
        if(month!=-1) {
            switch (month) {
                case 1:{
                    dates[0] =year + "-01-01 00:00:00";
                    dates[1]= year + "-01-31 23:59:59";
                    break;
                }
                case 2: {
                    if (!isLeapYear) {
                        dates[0] = year + "-02-01 00:00:00";
                        dates[1] = year + "-02-28 23:59:59";
                    } else {
                        dates[0] = year + "-02-01 00:00:00";
                        dates[1] = year + "-02-29 23:59:59";
                    }
                    break;
                }
                case 3: {
                    dates[0] = year + "-03-01 00:00:00";
                    dates[1] = year + "-03-31 23:59:59";
                    break;
                }
                case 4: {
                    dates[0] = year + "-04-01 00:00:00";
                    dates[1] = year + "-04-30 23:59:59";
                    break;
                }
                case 5: {
                    dates[0] = year + "-05-01 00:00:00";
                    dates[1] = year + "-05-31 23:59:59";
                    break;
                }
                case 6: {
                    dates[0] = year + "-06-01 00:00:00";
                    dates[1] = year + "-06-30 23:59:59";
                    break;
                }
                case 7: {
                    dates[0] = year + "-07-01 00:00:00";
                    dates[1] = year + "-07-31 23:59:59";
                    break;
                }
                case 8: {
                    dates[0] = year + "-08-01 00:00:00";
                    dates[1] = year + "-08-31 23:59:59";
                    break;
                }
                case 9: {
                    dates[0] = year + "-09-01 00:00:00";
                    dates[1] = year + "-09-30 23:59:59";
                    break;
                }
                case 10: {
                    dates[0] = year + "-10-01 00:00:00";
                    dates[1] = year + "-10-31 23:59:59";
                    break;
                }
                case 11: {
                    dates[0] = year + "-11-01 00:00:00";
                    dates[1] = year + "-11-30 23:59:59";
                    break;
                }
                case 12: {
                    dates[0] = year + "-12-01 00:00:00";
                    dates[1] = year + "-12-31 23:59:59";
                    break;
                }
            }
        } else{
            dates[0] = year + "-01-01 00:00:00";
            dates[1] = year + "-12-31 23:59:59";
        }
        return dates;
    }

}

