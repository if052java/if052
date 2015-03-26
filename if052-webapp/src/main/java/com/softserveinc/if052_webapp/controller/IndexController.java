package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

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