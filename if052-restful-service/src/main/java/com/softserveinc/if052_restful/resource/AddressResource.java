package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_restful.service.AddressService;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.UserService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
* Created by valentyn on 2/11/15.
*/
@RestController
@RequestMapping("/rest/addresses")
public class AddressResource {

    @Autowired
    AddressService addressService;

    @Autowired
    UserService userService;

    @Autowired
    WaterMeterService waterMeterService;

    @Autowired
    IndicatorService indicatorService;

    private static Logger LOGGER = Logger.getLogger(AddressResource.class.getName());

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Address> getAddressesByUser(){
        List<Address> addresses = addressService.getAllAddresses();

        if( addresses == null) {
            return new ArrayList<Address>();
        }
        return addresses;
    }

    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET, produces = "application/json")
    public Address getAddress(@PathVariable("addressId") int addressId, HttpServletResponse response) {
        Address address = addressService.getAddressById(addressId);
        if (address == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return address;
    }

    @RequestMapping("/list")
    public List<Address> getAddresses() {
        List<Address> addresses = addressService.getAllAddresses();

        if( addresses == null) {
            return new ArrayList<Address>();
        }
        return addresses;
    }

    @RequestMapping(method=RequestMethod.POST, produces = "application/json")
    public Address createAddress(
        @Valid
        @RequestBody
        Address address,
        HttpServletResponse response){
        addressService.insertAddress(address);
        response.setStatus(HttpServletResponse.SC_CREATED);

        return address;
    }

    @RequestMapping(value = "{addressId}", method = RequestMethod.PUT, produces = "application/json")
    public Address updateAddress(
        @Valid
        @PathVariable("addressId") int addressId,
        @RequestBody
        Address address,
        HttpServletResponse response){

        addressService.updateAddress(address);
        response.setStatus(HttpServletResponse.SC_ACCEPTED);
        return address;
    }

    @RequestMapping(value = "{addressId}", method = RequestMethod.DELETE)
    public void deleteAddress(
        @PathVariable("addressId") int addressId,
        HttpServletResponse response) {
        LOGGER.info("INFO: Deleting a address with id " + addressId + ".");
        if (addressService.getAddressById(addressId) == null){
            LOGGER.info("INFO: Address with requested id " + addressId + " is not found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        try{
            LOGGER.info("INFO : Meter with id " + addressId + " has been successfully deleted.");
            addressService.deleteAddress(addressId);
        } catch ( DataIntegrityViolationException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.warn("WARNING: Address with requester id " + addressId
                + " contains list of meters so it cannot be deleted.", e);
        }
    }
}
