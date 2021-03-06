package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_restful.mappers.AddressMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    private static Logger LOGGER = Logger.getLogger(IndicatorService.class);

    public Address getAddressById(int addressId)  {
        return addressMapper.getAddressById(addressId);
    }

    public List<Address> getAllAddresses() {
        Integer userId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        return addressMapper.getAddressesByUserId(userId);
    }

    public List<Address> getAllAddressesByUserId(int userId){
        return addressMapper.getAddressesByUserId( userId );
    }

    public void insertAddress(Address address) {
        Integer userId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
        User user = new User();
        user.setUserId(userId);
        address.setUser(user);
        addressMapper.insertAddress(address);
    }

    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }

    public void deleteAddress(int addressId) {
        addressMapper.deleteAddress(addressId);
    }
}
