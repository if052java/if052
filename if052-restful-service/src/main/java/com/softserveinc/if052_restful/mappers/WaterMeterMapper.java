package com.softserveinc.if052_restful.mappers;


import com.softserveinc.if052_restful.domain.WaterMeter;

import java.util.List;

public interface WaterMeterMapper {

    public void insertWaterMeter(WaterMeter waterMeter);

    public WaterMeter getWaterMeterById(int waterMeterId);

    public List<WaterMeter> getAllWaterMeters();

    public void updateWaterMeter(WaterMeter waterMeter);

    public void deleteWaterMeter(int waterMeterId);

}
