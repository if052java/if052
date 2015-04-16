package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_restful.service.AddressService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

    private static Logger LOGGER = Logger.getLogger(AddressResource.class.getName());

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Address> getAddresses(){
        LOGGER.info("INFO: Searching for the collection of addresses.");
        List<Address> addresses = addressService.getAllAddresses();
        if (addresses == null) {
            LOGGER.info("INFO: The collection of addresses has not been found.");
            addresses = new ArrayList<Address>();
        } else {
            LOGGER.info("INFO: The collection of addresses has been found.");
        }
        return addresses;
    }

//    @PostAuthorize("returnObject.user.userId == principal.username")
    @PostAuthorize("hasPermission(returnObject, 'get')")
    @RequestMapping(value = "/{addressId}", method = RequestMethod.GET, produces = "application/json")
    public Address getAddress(@PathVariable("addressId") int addressId, HttpServletResponse response) {
        LOGGER.info("INFO: Searching for the address with id " + addressId + ".");
        Address address = addressService.getAddressById(addressId);
        if (address == null) {
            LOGGER.info("INFO: Address with requested id " + addressId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("INFO: Address with requested id " + addressId + " has been successfully found.");
        return address;
    }

    @RequestMapping(method=RequestMethod.POST, produces = "application/json")
    public Address createAddress(
        @Valid
        @RequestBody
        Address address,
        HttpServletResponse response){
        try {
            LOGGER.info("INFO: Adding a new address.");
            addressService.insertAddress(address);
            LOGGER.info("INFO: Address has been successfully added with id " + address.getAddressId() + ".");
            response.setStatus(HttpServletResponse.SC_CREATED);
            return address;
        }
        catch (Exception e) {
            LOGGER.info("INFO: Internal error");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @RequestMapping(value = "{addressId}", method = RequestMethod.PUT, produces = "application/json")
    public Address updateAddress(
        @PathVariable("addressId") int addressId,
        @Valid
        @RequestBody
        Address address,
        HttpServletResponse response){
        if(addressService.getAddressById(addressId) == null){
            LOGGER.info("INFO: Address with requested id " + addressId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } else
            try {
                LOGGER.info("INFO: Updating an address with id " + addressId + ".");
                addressService.updateAddress(address);
                LOGGER.info("INFO: Address with id " + addressId + " has been successfully updated.");
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                return address;
            }
            catch (Exception e) {
                LOGGER.info("INFO: Internal error");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return null;
            }
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
            addressService.deleteAddress(addressId);
            LOGGER.info("INFO : Address with id " + addressId + " has been successfully deleted.");
        } catch ( DataIntegrityViolationException e){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            LOGGER.warn("WARNING: Address with requested id " + addressId
                + " contains list of meters so it cannot be deleted.", e);
        }
    }
}
