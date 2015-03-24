package com.softserveinc.if052_webapp.controller;

/**
 * Created by nazar on 1/28/15.
 */
import com.softserveinc.if052_webapp.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }

    @RequestMapping(value = "/map{userId}")
    public String getAddressPage(int userId, ModelMap model){
        Address[] arrayOfAddresses= restTemplate.getForObject(restUrl + "addresses/list/" + userId, Address[].class);
        List< Address > addresses = Arrays.asList(arrayOfAddresses);
        List< String > addressesAsStrings = new ArrayList<String>();
        for (Address a : addresses) {
            addressesAsStrings.add(a.getCity() + ", вул. " +a.getStreet() + " " + a.getBuilding());
        }

        model.addAttribute("addressesAsStrings", addressesAsStrings);

        return "map";
    }
}