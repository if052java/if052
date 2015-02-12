package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
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

    @RequestMapping("/addresses")
    public String getAddressPage(@RequestParam(value = "userId",
        required = true, defaultValue = "1") String userId, ModelMap model) {
        RestTemplate restTemplate = new RestTemplate();
        Address[] arrayOfAddresses= restTemplate.getForObject("http://localhost:8080/address/list/" + userId, Address[].class);
        List < Address > addresses = Arrays.asList(arrayOfAddresses);

        model.addAttribute("addresses", addresses);

        return "address";
    }

    @RequestMapping("/deleteAddress")
    public String deleteAddress(@RequestParam(value = "addressId",
        required = true) int addressId, ModelMap model) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/address/deleteAddress"+addressId);


        //return "redirect:/addresses?userId=" ;
        return "0";
    }
}
