package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_restful.mappers.MeterTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Service for work with meter type
 * @see com.softserveinc.if052_core.domain.domain.MeterType
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 */
@Service
@Transactional
public class MeterTypeService {

    @Autowired
    private MeterTypeMapper meterTypeMapper;

    /**
     * Get all meter types
     *
     * @return List<MeterType>
     */
    public List<MeterType> getAllMeterTypes() { return meterTypeMapper.getAllMeterTypes(); }

    /**
     * Get existing meter type by id
     *
     * @param meterTypeId
     * @return MeterType
     */
    public MeterType getMeterTypeById(int meterTypeId) {
        return meterTypeMapper.getMeterTypeById(meterTypeId);
    }

    /**
     * Create new meter type
     *
     * @param meterType
     */
    public void insertMeterType(MeterType meterType) { meterTypeMapper.insertMeterType(meterType); }

    /**
     * Update existing meter type
     *
     * @param meterType
     */
    public void updateMeterType(MeterType meterType) { meterTypeMapper.updateMeterType(meterType);}

    /**
     * Delete existing meter type
     *
     * @param meterTypeId
     */
    public void deleteMeterType(int meterTypeId) { meterTypeMapper.deleteMeterType(meterTypeId); }
}
