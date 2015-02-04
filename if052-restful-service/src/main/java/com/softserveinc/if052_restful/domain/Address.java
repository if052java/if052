/**
 * Copyright (c) 2015, SoftServe and/or its affiliates. All rights reserved.
 */

package com.softserveinc.if052_restful.domain;

import java.util.List;

/**
 * This class is a model of the datatable "ADDRESS"
 * 
 * @author Andrii Gurskyi
 * @author Bogdan Pastushkevych
 * @version 1.0
 */

public final class Address {
	private int addressId;
	private String city;
	private String street;
	private String building;
	private String apartment;
	private int userId;
	private List<WaterMeter> waterMeters;


	/**
	 * @return the addressId
	 */
	public int getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * @return the apartment
	 */
	public String getApartment() {
		return apartment;
	}

	/**
	 * @param apartment the apartment to set
	 */
	public void setApartment(String apartment) {
		this.apartment = apartment;
	}
	

	/**
	 * @return the user_id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the waterMeters
	 */
	public List<WaterMeter> getWaterMeters() {
		return waterMeters;
	}

	/**
	 * @param waterMeters the waterMeters to set
	 */
	public void setWaterMeters(List<WaterMeter> waterMeters) {
		this.waterMeters = waterMeters;
	}

	@Override
	public String toString() {
		return "Address: city=" + city + ", street=" + street
				+ ", building=" + building + ", apartment="
				+ apartment + ", userId=" + userId;		
	}
}
