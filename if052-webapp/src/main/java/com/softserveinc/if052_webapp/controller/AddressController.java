package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Controller for address
 *
 */
@Controller
public class AddressController {
    private final String ADDRESSES = "addresses";
    private final String REASON = "resource";
    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired 
    @Qualifier("restUrl")
    private String restUrl;

    private String userId = "";

    /**
     * Get page with addresses by user id
     * @param userId - Identificator of user
     * @param model - 
     * @return "address" JSP for showing 
     */
    @RequestMapping(value = "/addresses{userId}")
    public String getAddressPage(int userId, ModelMap model){
        this.userId = String.valueOf(userId);

        Address[] arrayOfAddresses= restTemplate.getForObject(restUrl + "addresses/list/" + userId, Address[].class);
        List < Address > addresses = Arrays.asList(arrayOfAddresses);

        model.addAttribute(ADDRESSES, addresses);

        return "address";
    }

    /**
     * Create new address
     *
     * @param address
     * @return 
     */
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String createAddress(@ModelAttribute Address address){
        User user = restTemplate.getForObject(restUrl+ "users/" + this.userId, User.class);
        address.setUser(user);

        restTemplate.postForObject(restUrl + "addresses/", address, Address.class);

        return "redirect:/addresses?userId=" + this.userId;
    }

    /**
     * Get address for update
     *
     * @param addressId - Identificator of address
     * @param model
     * @return "updateAddress" JSP for showing form to update
     */
    @RequestMapping(value = "/updateAddress{addressId}")
    public String getUpdateAddressPage(int addressId, ModelMap model){
        Address address = restTemplate.getForObject(restUrl + "addresses/" + addressId, Address.class);

        model.addAttribute(ADDRESSES, address);

        return "updateAddress";
    }

    /**
     * Update exists address
     * 
     * @param address
     * @return
     */
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public String updateAddress(@ModelAttribute Address address){
        User user = restTemplate.getForObject(restUrl+ "users/" + this.userId, User.class);
        address.setUser(user);
        restTemplate.put(restUrl + "addresses/" + address.getAddressId(), address);

        return "redirect:/addresses?userId=" + this.userId;
    }

    /**
     * Delete exists address
     *
     * @param addressId
     * @return
     */
    @RequestMapping("/deleteAddress{addressId}")
    public String deleteAddress(int addressId, ModelMap model) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "addresses/" + addressId,
            HttpMethod.DELETE, null, String.class);

        if (responseEntity.getStatusCode().value() == 400) {
            model.addAttribute(REASON, "This address has tied meters so it cannot be deleted.");
            return "error400";
        }
        return "redirect:/addresses?userId=" + this.userId;
    }
}
