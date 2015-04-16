package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.Auth;
import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_core.domain.ValidationError;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestOperations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for address
 *
 */
@Controller
public class AddressController {
    public static final String REDIRECT_ADDRESSES = "redirect:/addresses";
    private final String ADDRESSES = "addresses";
    private final String ADDRESS = "address";
    private final String REASON = "reason";
    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired 
    @Qualifier("restUrl")
    private String restUrl;

    private static Logger LOGGER = Logger.getLogger(WaterMeterController.class);
    
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Get page with addresses by user id
     * @param model -
     * @return "address" JSP for showing
     */
    @RequestMapping(value = "/addresses")
    public String getAddressPage(ModelMap model){
        Address[] arrayOfAddresses= restTemplate.getForObject(restUrl + "addresses/", Address[].class);
        List<Address> addresses = Arrays.asList(arrayOfAddresses);
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
    public String createAddress(@ModelAttribute Address address, ModelMap model) {
        ResponseEntity<String> addressResponseEntity = restTemplate.exchange(restUrl + "addresses/",
            HttpMethod.POST, new HttpEntity<Address>(address), String.class);


        if (addressResponseEntity.getStatusCode().value() != 201) {
            try {
                ValidationError error = objectMapper.readValue(addressResponseEntity.getBody(), ValidationError.class);

                if (error.getFieldErrors().size() > 0) {
                    model.addAttribute("fieldErrors", error.getFieldErrors());
                    return "validationError";
                }
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
        return REDIRECT_ADDRESSES;
    }
    /**
     * Get address for update
     *
     * @param addressId - Identificator of address
     * @param model
     * @return "updateAddress" JSP for showing form to update
     */
    @RequestMapping(value = "/updateAddress")
    public String getUpdateAddressPage(@RequestParam("addressId") int addressId, ModelMap model){
        Address address = restTemplate.getForObject(restUrl + "addresses/" + addressId, Address.class);
        model.addAttribute(ADDRESS, address);
        return "updateAddress";
    }

    /**
     * Update exists address
     *
     * @param address
     * @return
     */
    @RequestMapping(value = "/updateAddress", method = RequestMethod.POST)
    public String updateAddress(@ModelAttribute Address address, ModelMap model){

        ResponseEntity<String> addressResponseEntity = restTemplate.exchange(restUrl + "addresses/" + address.getAddressId(),
            HttpMethod.PUT, new HttpEntity<Address>(address), String.class);


        if (addressResponseEntity.getStatusCode().value() != 202) {
            try {
                ValidationError error = objectMapper.readValue(addressResponseEntity.getBody(), ValidationError.class);

                if (error.getFieldErrors().size() > 0) {
                    model.addAttribute("fieldErrors", error.getFieldErrors());
                    return "validationError";
                }
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }

        return REDIRECT_ADDRESSES;
    }

    /**
     * Delete exists address
     *
     * @param addressId
     * @return
     */
    @RequestMapping("/deleteAddress")
    public String deleteAddress(@RequestParam("addressId") int addressId, ModelMap model) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl + "addresses/" + addressId,
            HttpMethod.DELETE, null, String.class);

        if (responseEntity.getStatusCode().value() == 400) {
            model.addAttribute(REASON, "This address has tied meters so it cannot be deleted.");
            return "error400";
        }
        return REDIRECT_ADDRESSES;
    }
}
