/**
 * Copyright (c) 2015, SoftServe and/or its affiliates. All rights reserved.
 */

package com.softserveinc.if052_restful.mappers;

import java.util.List;
import com.softserveinc.if052_restful.domain.Address;

/**
 * This interface is for the "ADDRESS" mapper
 * 
 * @author Andrii Gurskyi
 * @author Bogdan Pastushkevych
 * @version 1.0
 */

public interface AddressMapper {
	
	/**
	 * Create new address of user
	 * 
	 * @param address
	 */
	public void insertAddress(Address address);
	
	/**
	 * Select one address by "Id"
	 * 	
	 * @param addressId
	 * @return Address
	 */
	public Address getAddressById(int addressId);
	
	/**
	 * Select all addresses
	 * 
	 * @return List of addresses
	 * 
	 */
	public List<Address> getAllAddresses();
	
	/**
	 * Select all addresses (zero or more) of user
	 * 
	 * @param userId
	 * @return List of addresses
	 */
	public List<Address> getAddressesByUserId(int userId);
	
	/**
	 * Update existing address of user
	 * 
	 * @param address
	 */
	public void updateAddress(Address address);
	
	/**
	 * Delete existing address of user
	 * 
	 * @param addressId
	 */
	public void deleteAddress(int addressId);
}
