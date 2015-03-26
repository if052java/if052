package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URL;
import java.text.DateFormat;
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
    @Qualifier("oAuthRestTemplate")
    private RestTemplate restTemplate;
    
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
        List < Indicator > indicators = Arrays.asList(arrayOfIndicators);
        List<Integer> indicatorValues = new ArrayList<Integer>();
        List<Long> indicatorsDate = new ArrayList<Long>();
        long[][] arrayOfDates = new long[indicators.size()][2];
        
        for(Indicator indicator : indicators){
            indicatorValues.add((indicator.getValue()));
        }

        for(Indicator indicator : indicators){
        indicatorsDate.add(indicator.getDate().getTime() + 7200000);
        }
        
        for (int i = 0; i < indicators.size(); i++) {
                arrayOfDates[ i ] [ 0 ] = indicatorsDate.get(i);
                arrayOfDates[ i ] [ 1 ] = indicatorValues.get(i);
        }

        String a = Arrays.deepToString(arrayOfDates);
        
        model.addAttribute("indicatorsData", a);

        ModelAndView mav = new ModelAndView("graphs");
        
        Map< String, String > month = new HashMap<String, String>();
        month.put("January", "Jan");
        month.put("February", "Feb");
        month.put("March", "Mar");

        mav.addObject("monthsList", month);
        return "graphs";
    }

    @RequestMapping(value="graphByMonth", method=RequestMethod.POST)
    public String getGraphByDate(@RequestParam("month") String month, ModelMap model){

        model.addAttribute("month", month);
        
        return "graphs";
    }
}
