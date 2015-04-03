package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_core.domain.MeterType;
import java.util.List;

/**
 * This interface is for the "METER_TYPE" mapper
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 */
public interface MeterTypeMapper {

    /**
     * Create new meter type
     *
     * @param meterType
     */
    public void insertMeterType(MeterType meterType);

    /**
     * Get exists meter type by id
     *
     * @param meterTypeId
     * @return meterType
     */
    public MeterType getMeterTypeById(int meterTypeId);

    /**
     * Get all meter types
     *
     * @return List of meter types
     */
    public List<MeterType> getAllMeterTypes();

    /**
     * Update exists meter type
     *
     * @param meterType
     */
    public void updateMeterType(MeterType meterType);

    /**
     * Delete exists meter type
     *
     * @param meterTypeId
     */
    public void deleteMeterType(int meterTypeId);
}
