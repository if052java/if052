/**
 * Copyright (c) 2015, SoftServe and/or its affiliates. All rights reserved.
 */

package com.softserveinc.if052_core.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
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

    @NotNull
    @NotEmpty
	private String city;

    @NotNull
    @NotEmpty
	private String street;

    @NotNull
    @NotEmpty
	private String building;

    @NotNull
    @NotEmpty
	private String apartment;

    @NotNull
	private User user;

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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
        return "Address{" +
                "addressId=" + addressId +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
