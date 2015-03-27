package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.User;
import com.softserveinc.if052_webapp.domain.WaterMeter;
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

    @RequestMapping("maingraph")
    public String getMainGraph(ModelMap model){

        Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl + "indicators/"+ 1, Indicator[].class);

        Address[] arrayOfAddress = restTemplate.getForObject(restUrl + "addresses/list/" + 1, Address[].class);
        List<Address> addresses = Arrays.asList(arrayOfAddress);
        
        List < Indicator > indicators = Arrays.asList(arrayOfIndicators);
        List<Integer> indicatorValues = new ArrayList<Integer>();
        List<Long> indicatorsDate = new ArrayList<Long>();
        
        long[][] arrayOfData = new long[indicators.size()][2];
        
        for(Indicator indicator : indicators){
            indicatorValues.add((indicator.getValue()));
        }

        for(Indicator indicator : indicators){
        indicatorsDate.add(indicator.getDate().getTime() + 7200000);
        }
        
        for (int i = 0; i < indicators.size(); i++) {
                arrayOfData[ i ] [ 0 ] = indicatorsDate.get(i);
                arrayOfData[ i ] [ 1 ] = indicatorValues.get(i);
        }

        String masAsString = Arrays.deepToString(arrayOfData);
        
        model.addAttribute("indicatorsData", masAsString);
        model.addAttribute("addresses", addresses);
        return "graphs";
    }

    @RequestMapping(value="graphByMonth", method=RequestMethod.POST)
    public String getGraphByDate(
                                 @RequestParam("meter") Integer meterId,
                                 @RequestParam("month") Integer month,
                                 @RequestParam("year") Integer year,
                                 ModelMap model) {
        Indicator[] arrayOfIndicators = restTemplate.getForObject(restUrl + "indicators/"+ meterId, Indicator[].class);
        List < Indicator > indicators = Arrays.asList(arrayOfIndicators);
        List < Indicator > indicatorsData = new ArrayList<Indicator>();

        //- Get list of addresses -//
        Address[] arrayOfAddress = restTemplate.getForObject(restUrl + "addresses/list/" + 1, Address[].class);
        List<Address> addresses = Arrays.asList(arrayOfAddress);
        model.addAttribute("addresses", addresses);

        Date startDate = null;
        Date endDate = null;
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        GregorianCalendar cal =
            (GregorianCalendar) GregorianCalendar.getInstance();
        boolean isLeapYear = cal.isLeapYear(year);
        
            try{
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
        } catch(ParseException e){
                logger.warn(e.getMessage(), e);
            }
        //GET DATA 
        for(Indicator indicator : indicators){
            if(indicator.getDate().compareTo(startDate) >= 0
                && indicator.getDate().compareTo(endDate) <= 0) {
                    indicatorsData.add(indicator);
            }
        }

        long[][] arrayOfData = new long[indicatorsData.size()][2];

        for (int i = 0; i < indicatorsData.size(); i++) {
            arrayOfData[ i ] [ 0 ] = indicatorsData.get(i).getDate().getTime() + 7200000;
            arrayOfData[ i ] [ 1 ] = indicatorsData.get(i).getValue();
        }
        String masAsString = Arrays.deepToString(arrayOfData);
        
        model.addAttribute("month", month);
        model.addAttribute("indicatorsData", masAsString);
        
        return "graphs";
    }

    @RequestMapping("/watermeterlist{addressId}")
    public @ResponseBody String getwatermetersByAddress(ModelMap model, int addressId) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "addresses/" + addressId,
            HttpMethod.GET, null, String.class);
        model.addAttribute("attr","shjda");
        String responseBody = responseEntity.getBody();
        String json = "";
        try {
            Address address = objectMapper.readValue(responseBody, Address.class);
            List<WaterMeter> waterMeters = address.getWaterMeters();
            json = new Gson().toJson(waterMeters);
            System.out.println(json);
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

        return "map";
    }
}

