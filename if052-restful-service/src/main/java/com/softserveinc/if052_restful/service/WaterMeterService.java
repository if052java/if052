package com.softserveinc.if052_restful.service;


import com.softserveinc.if052_restful.domain.WaterMeter;
import com.softserveinc.if052_restful.mappers.WaterMeterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WaterMeterService {

    @Autowired
    private WaterMeterMapper waterMeterMapper;

    public WaterMeter getWaterMeterById(int waterMeterId) {
        return waterMeterMapper.getWaterMeterById(waterMeterId);
    }

    public List<WaterMeter> getAllWaterMeters() {
        return waterMeterMapper.getAllWaterMeters();
    }

    public void insertWaterMeter(WaterMeter waterMeter) {
        waterMeterMapper.insertWaterMeter(waterMeter);
    }

    public void updateWaterMeter(WaterMeter waterMeter) {
        waterMeterMapper.updateWaterMeter(waterMeter);
    }

    public void deleteWaterMeter(int waterMeterId) {
        waterMeterMapper.deleteWaterMeter(waterMeterId);
    }

}
