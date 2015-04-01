package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.mappers.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {
    @Autowired
    private AddressMapper addressMapper;

    public Address getAddressById(int addressId)  {
        return addressMapper.getAddressById(addressId);
    }

    public List<Address> getAllAddresses() {
        return addressMapper.getAllAddresses();
    }

    public List<Address> getAllAddressesByUserId(int userId){
        return addressMapper.getAddressesByUserId( userId );
        
    }

    public void insertAddress(Address address) {
        addressMapper.insertAddress(address);
    }

    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }

    public void deleteAddress(int addressId) {
        addressMapper.deleteAddress(addressId);
    }
}
