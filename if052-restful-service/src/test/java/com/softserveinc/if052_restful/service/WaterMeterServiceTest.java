package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import junit.framework.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class WaterMeterServiceTest {

    @Autowired
    private WaterMeterService waterMeterService;

    @Test
    public void testGetWaterMeterById() {
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(1);
        Assert.assertNotNull(waterMeter);
        System.out.println(waterMeter);
        List<Indicator> indicators = waterMeter.getIndicators();
        for (Indicator indicator : indicators) {
            System.out.println(indicator);
        }
    }



    @Test
    public void testGetAllWaterMeters() {
        List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
        // List<Indicator> indicators;
        Assert.assertNotNull(waterMeters);
        for(WaterMeter waterMeter : waterMeters) {
            System.out.println(waterMeter);
            /*indicators = waterMeter.getIndicators();
            for(Indicator indicator : indicators) {
                System.out.println(indicator);
            }*/
        }
    }


    @Test
    public void testInsertWaterMeter() {

        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("des" + timestamp);
        //waterMeter.setAddressId(1);
        waterMeterService.insertWaterMeter(waterMeter);
        Assert.assertTrue(waterMeter.getWaterMeterId() != 0);
        WaterMeter createdWaterMeter = waterMeterService.getWaterMeterById(waterMeter.getWaterMeterId());
        Assert.assertNotNull(createdWaterMeter);
        Assert.assertEquals(waterMeter.getName(), createdWaterMeter.getName());
    }


    @Test
    public void testUpdateWaterMeter() {
        long timestamp = System.currentTimeMillis();
        int lastId = waterMeterService.getAllWaterMeters().get(waterMeterService.getAllWaterMeters().size()-1).getWaterMeterId();
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(lastId);
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("des" + timestamp);
        //waterMeter.setAddressId(1);
        System.out.println(waterMeter);
        waterMeterService.updateWaterMeter(waterMeter);
        WaterMeter updatedWaterMeter = waterMeterService.getWaterMeterById(lastId);
        System.out.println(updatedWaterMeter);
        Assert.assertEquals(waterMeter.getName(), updatedWaterMeter.getName());
        Assert.assertEquals(waterMeter.getDescription(), updatedWaterMeter.getDescription());
        //Assert.assertEquals(waterMeter.getAddressId(), updatedWaterMeter.getAddressId());
    }



    @Test
    public void testDeleteWaterMeter() {
        /*int lastId = waterMeterService.getAllWaterMeters().get(waterMeterService.getAllWaterMeters().size()-1).getWaterMeterId();
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(lastId);*/
        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("des" + timestamp);
       // waterMeter.setAddressId(1);
        waterMeterService.insertWaterMeter(waterMeter);
        waterMeterService.deleteWaterMeter(waterMeter.getWaterMeterId());
        WaterMeter deletedWaterMeter = waterMeterService.getWaterMeterById(waterMeter.getWaterMeterId());
        Assert.assertNull(deletedWaterMeter);
    }

}