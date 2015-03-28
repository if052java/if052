package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.User;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by valentyn on 2/11/15.
 */
@Controller
public class UserController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    private static Logger logger = Logger.getLogger(WaterMeterController.class);
    
    @RequestMapping("signup")
    public String registration(ModelMap model){

        model.addAttribute("restUrl", restUrl);
        return "registration";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String createAddress(@ModelAttribute User user){

        user.setName(user.getName().trim());
        user.setSurname(user.getSurname().trim());
        user.setMiddleName(user.getMiddleName().trim());
        
        user = restTemplate.postForObject(restUrl + "users/", user, User.class);

        return "redirect:/addresses?userId=" + user.getUserId();
    }

    @RequestMapping("defaultgraph")
    public String getMainGraph(ModelMap model){

        //- Get all addresses of user for select-//
        Address[] arrayOfAddress = restTemplate.getForObject(restUrl + "addresses/list/" + 1, Address[].class);
        List < Address > addresses = Arrays.asList(arrayOfAddress);

        //- Get first meter for user -//
        ResponseEntity < String > responseEntity = restTemplate.exchange(restUrl + "watermeters/firstMeter/" + 1,
            HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();

        if (responseEntity.getStatusCode().value() == 404) {
            model.addAttribute("resource", "watermeter");
            return "error404";
        }
        try {
            WaterMeter meter = objectMapper.readValue(responseBody, WaterMeter.class);

            model.addAttribute("meterName", meter.getName());
            model.addAttribute("meterType", meter.getMeterType().getType());

            Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl + "indicators/" + meter.getWaterMeterId(), Indicator[].class);
            //- Get all indicators of first meter for graph -//
            List < Indicator > indicators = Arrays.asList(arrayOfIndicators);

            //- Create simple date format -//
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            //- Get current year -//
            int year = Calendar.getInstance().get(Calendar.YEAR);

            try {
                //- Get start and end date-//
                Date startDate;
                Date endDate;
                
                startDate = sdf.parse(year + "/01/01 00:00:00");
                endDate = sdf.parse(year + "/12/31 23:59:59");

                //- GET current indicators for graph -//
                List < Indicator > indicatorsData = new ArrayList<Indicator>();

                for(Indicator indicator : indicators){
                    if(indicator.getDate().compareTo(startDate) >= 0
                        && indicator.getDate().compareTo(endDate) <= 0) {
                        indicatorsData.add(indicator);
                    }
                }

                long[][] arrayOfData = new long[indicatorsData.size()][2];

                if (indicators.get(0).getDate() != null) {
                    for (int i = 0; i < indicatorsData.size(); i++) {
                        arrayOfData[i][0] = indicatorsData.get(i).getDate().getTime() + 7200000;
                        arrayOfData[i][1] = indicatorsData.get(i).getValue();
                    }
                }

                String masAsString = Arrays.deepToString(arrayOfData);
                model.addAttribute("indicatorsData", masAsString);
            } catch ( ParseException e){
                logger.warn(e.getMessage(), e);
            }

            model.addAttribute("year", year);

        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
        
        model.addAttribute("addresses", addresses);
        return "graphs";
    }

    @RequestMapping(value="graphByOption", method=RequestMethod.POST)
    public String getGraphByOption(
                                 @RequestParam("meter") Integer meterId,
                                 @RequestParam("month") Integer month,
                                 @RequestParam("year") Integer year,
                                 ModelMap model) {
        //- Get list of indicators -//
        Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl + "indicators/" + meterId, Indicator[].class);
        List < Indicator > indicators = Arrays.asList(arrayOfIndicators);

        //- Get list of addresses for select-//
        Address[] arrayOfAddress = restTemplate.getForObject(restUrl + "addresses/list/" + 1, Address[].class);
        List<Address> addresses = Arrays.asList(arrayOfAddress);

        //- Get watermeter by id-//
        ResponseEntity < String > responseEntity = restTemplate.exchange(restUrl + "watermeters/" + meterId,
            HttpMethod.GET, null, String.class);
        String responseBody = responseEntity.getBody();
        
        if (responseEntity.getStatusCode().value() == 404) {
            model.addAttribute("resource", "watermeter");
            return "error404";
        }
        try {
            WaterMeter meter= objectMapper.readValue(responseBody, WaterMeter.class);
            model.addAttribute("meterName", meter.getName());
            model.addAttribute("meterType", meter.getMeterType().getType());
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
        //- Get start and end date-//
        Date startDate = null;
        Date endDate = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        GregorianCalendar cal =
            (GregorianCalendar) GregorianCalendar.getInstance();
        boolean isLeapYear = cal.isLeapYear(year);
            try{
                if(month!=13) {
                    switch (month) {
                        case 1: {
                            startDate = sdf.parse(year + "/01/01 00:00:00");
                            endDate = sdf.parse(year + "/01/31 23:59:59");
                            break;
                        }
                        case 2: {
                            if (!isLeapYear) {
                                startDate = sdf.parse(year + "/02/01 00:00:00");
                                endDate = sdf.parse(year + "/02/28 23:59:59");
                            } else {
                                startDate = sdf.parse(year + "/02/01 00:00:00");
                                endDate = sdf.parse(year + "/02/29 23:59:59");
                            }
                            break;
                        }
                        case 3: {
                            startDate = sdf.parse(year + "/03/01 00:00:00");
                            endDate = sdf.parse(year + "/03/31 23:59:59");
                            break;
                        }
                        case 4: {
                            startDate = sdf.parse(year + "/04/01 00:00:00");
                            endDate = sdf.parse(year + "/04/30 23:59:59");
                            break;
                        }
                        case 5: {
                            startDate = sdf.parse(year + "/05/01 00:00:00");
                            endDate = sdf.parse(year + "/05/31 23:59:59");
                            break;
                        }
                        case 6: {
                            startDate = sdf.parse(year + "/06/01 00:00:00");
                            endDate = sdf.parse(year + "/06/30 23:59:59");
                            break;
                        }
                        case 7: {
                            startDate = sdf.parse(year + "/07/01 00:00:00");
                            endDate = sdf.parse(year + "/07/31 23:59:59");
                            break;
                        }
                        case 8: {
                            startDate = sdf.parse(year + "/08/01 00:00:00");
                            endDate = sdf.parse(year + "/08/31 23:59:59");
                            break;
                        }
                        case 9: {
                            startDate = sdf.parse(year + "/09/01 00:00:00");
                            endDate = sdf.parse(year + "/09/30 23:59:59");
                            break;
                        }
                        case 10: {
                            startDate = sdf.parse(year + "/10/01 00:00:00");
                            endDate = sdf.parse(year + "/10/31 23:59:59");
                            break;
                        }
                        case 11: {
                            startDate = sdf.parse(year + "/11/01 00:00:00");
                            endDate = sdf.parse(year + "/11/30 23:59:59");
                            break;
                        }
                        case 12: {
                            startDate = sdf.parse(year + "/12/01 00:00:00");
                            endDate = sdf.parse(year + "/12/31 23:59:59");
                            break;
                        }
                    }
                } else{
                    startDate = sdf.parse(year + "/01/01 00:00:00");
                    endDate = sdf.parse(year + "/12/31 23:59:59");
                }
        } catch(ParseException e){
                logger.warn(e.getMessage(), e);
            }
        
        //- GET current indicators for graph -//
        List < Indicator > indicatorsData = new ArrayList<Indicator>();

        //- Checking, if list of indicators is empty-//
        if (indicators.get(0).getDate() != null) {
            for (Indicator indicator : indicators) {
                if (indicator.getDate().compareTo(startDate) >= 0
                    && indicator.getDate().compareTo(endDate) <= 0) {
                    indicatorsData.add(indicator);
                }
            }
        }

        long[][] arrayOfData = new long[indicatorsData.size()][2];

        for (int i = 0; i < indicatorsData.size(); i++) {
            arrayOfData[ i ] [ 0 ] = indicatorsData.get(i).getDate().getTime() + 7200000;
            arrayOfData[ i ] [ 1 ] = indicatorsData.get(i).getValue();
        }
        String masAsString = Arrays.deepToString(arrayOfData);
        
        model.addAttribute("month", month);
        model.addAttribute("addresses", addresses);
        model.addAttribute("indicatorsData", masAsString);
        model.addAttribute("year", year);
        
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
            List<WaterMeter> waterMeters = address.getWaterMeters();
            json = new Gson().toJson(waterMeters);
            return json;
        } catch (IOException e) {
            logger.warn(e.getMessage(), e);
        }
        
        return json;
    }

    @RequestMapping(value = "/map{userId}")
    public String getAddressPage(int userId, ModelMap model){
        Address[] arrayOfAddresses= restTemplate.getForObject(restUrl + "addresses/list/" + userId, Address[].class);

        String gMapData = "";
        for (int i=0; i<arrayOfAddresses.length; i++) {
            gMapData+= (arrayOfAddresses[i].getCity() + ", вул. "
                    + arrayOfAddresses[i].getStreet() + " " + arrayOfAddresses[i].getBuilding() + "~");
        }
        // delete last '~' in string
        gMapData = gMapData.substring(0, gMapData.length()-1);

        model.addAttribute("gMapData", gMapData);
        model.addAttribute("userId", userId);

        return "map";
    }
}

