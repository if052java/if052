package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Controller for address
 *
 */
@Controller
public class AddressController {

    @Autowired @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("/addresses")
    public String getAddressPage(@RequestParam(value = "userId",
        required = true, defaultValue = "1") String userId, ModelMap model) {
        RestTemplate restTemplate = new RestTemplate();
        Address[] arrayOfAddresses= restTemplate.getForObject(restUrl + "address/list/" + userId, Address[].class);
        List < Address > addresses = Arrays.asList(arrayOfAddresses);

        model.addAttribute("addresses", addresses);

        return "address";
    }

    @RequestMapping("/deleteAddress")
    public String deleteAddress(@RequestParam(value = "addressId",
        required = true) int addressId, ModelMap model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(restUrl + "address/deleteAddress"+addressId);


        //return "redirect:/addresses?userId=" ;
        return "0";
    }
}
