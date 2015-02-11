/**
 * Copyright (c) 2015, SoftServe and/or its affiliates. All rights reserved.
 */
package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_restful.domain.WaterMeter;
import java.util.List;

/**
 * This is interface of water meter mapper
 *
 * @author Namisnyk Valentyn
 * @author Danylo Tiahun
 * @version 1.0
 */
public interface WaterMeterMapper {

    /**
     * Create new water meter
     *  
     * @param waterMeter
     */
    public void insertWaterMeter(WaterMeter waterMeter);

    /**
     * Get exists water meter by id
     * 
     * @param waterMeterId
     * @return WaterMeter
      */
    public WaterMeter getWaterMeterById(int waterMeterId);

    /**
     * Get all water meters
     *  
     * @return List of water meters 
     */
    public List < WaterMeter > getAllWaterMeters();

    /**
     * Update exists water meter
     * 
     * @param waterMeter
     */
    public void updateWaterMeter(WaterMeter waterMeter);

    /**
     * Delete exists water meter
     *
     * @param waterMeterId
     */
    public void deleteWaterMeter(int waterMeterId);

    public List<WaterMeter> getWaterMetersByAddressId(int addressId);

}
