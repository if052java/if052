package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_restful.mappers.MeterTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for work with meter type
 *
 * @author Bogdan Pastushkevych
 * @version 1.0
 * @see com.softserveinc.if052_core.domain.MeterType
 */
@Service
@Transactional
public class MeterTypeService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private MeterTypeMapper meterTypeMapper;

    /**
     * Get all meter types
     *
     * @return List<MeterType>
     */
    public List<MeterType> getAllMeterTypes() {
        return meterTypeMapper.getAllMeterTypes();
    }

    /**
     * Get existing meter type by id
     *
     * @param meterTypeId Id of meter type
     * @return MeterType
     */
    public MeterType getMeterTypeById(int meterTypeId) {
        return meterTypeMapper.getMeterTypeById(meterTypeId);
    }

    /**
     * Create new meter type
     *
     * @param meterType meter type
     */
    public void insertMeterType(MeterType meterType) {
        meterTypeMapper.insertMeterType(meterType);
    }

    /**
     * Update existing meter type
     *
     * @param meterType meter type
     */
    public void updateMeterType(MeterType meterType) {
        meterTypeMapper.updateMeterType(meterType);
    }

    /**
     * Delete existing meter type
     *
     * @param meterTypeId Id of meter type
     */
    public void deleteMeterType(int meterTypeId) {
        meterTypeMapper.deleteMeterType(meterTypeId);
    }
}
