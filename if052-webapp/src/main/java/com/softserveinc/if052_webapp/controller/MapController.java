package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

/**
 * Created by student on 3/30/2015.
 */
@Controller
public class MapController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private Auth authBean;

    @RequestMapping(value = "/map")
    public String getMapPage(ModelMap model){
        Integer userId = authBean.getUserId();
        Address[] arrayOfAddresses= restTemplate.getForObject(restUrl + "addresses/list/" + userId, Address[].class);

        String gMapData = "";

        for (int i = 0; i < arrayOfAddresses.length; i++) {
            gMapData += (arrayOfAddresses[i].getCity() + ", вул. "
                    + arrayOfAddresses[i].getStreet() + " " + arrayOfAddresses[i].getBuilding() + "~");
        }
        // delete last '~' in string
        gMapData = gMapData.substring(0, gMapData.length()-1);

        model.addAttribute("gMapData", gMapData);
        model.addAttribute("userId", userId);

        return "map";
    }
}
