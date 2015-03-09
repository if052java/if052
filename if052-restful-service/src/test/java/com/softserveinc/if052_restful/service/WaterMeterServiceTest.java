package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml",
                                 "classpath:h2-datasource.xml"})
@ActiveProfiles(profiles = "h2")
public class WaterMeterServiceTest {
    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private AddressService addressService;

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
    public void testGetWaterMetersByAddressId() {
        List<WaterMeter> waterMeters = waterMeterService.getWaterMetersByAddressId(1);
        Assert.assertNotNull(waterMeters);
        for(WaterMeter waterMeter : waterMeters) {
            System.out.println(waterMeter);
        }
    }

    @Test
    public void testGetAllWaterMeters() {
        List<WaterMeter> waterMeters = waterMeterService.getAllWaterMeters();
        Assert.assertNotNull(waterMeters);
        for(WaterMeter waterMeter : waterMeters) {
            System.out.println(waterMeter);
        }
    }

    @Test
    public void testInsertWaterMeter() {
        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("des" + timestamp);
        waterMeter.setAddress(addressService.getAddressById(1));
        waterMeterService.insertWaterMeter(waterMeter);
        Assert.assertTrue(waterMeter.getWaterMeterId() != 0);
        WaterMeter createdWaterMeter = waterMeterService.getWaterMeterById(waterMeter.getWaterMeterId());
        Assert.assertNotNull(createdWaterMeter);
        Assert.assertEquals(waterMeter.getName(), createdWaterMeter.getName());
        Assert.assertEquals(waterMeter.getDescription(), createdWaterMeter.getDescription());
        Assert.assertEquals(waterMeter.getAddress().getAddressId(), createdWaterMeter.getAddress().getAddressId());
    }

    @Test
    public void testUpdateWaterMeter() {
        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(1);
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("description" + timestamp);
        waterMeter.setAddress(addressService.getAddressById(1));
        System.out.println(waterMeter);
        waterMeterService.updateWaterMeter(waterMeter);
        WaterMeter updatedWaterMeter = waterMeterService.getWaterMeterById(1);
        System.out.println(updatedWaterMeter);
        Assert.assertEquals(waterMeter.getName(), updatedWaterMeter.getName());
        Assert.assertEquals(waterMeter.getDescription(), updatedWaterMeter.getDescription());
        Assert.assertEquals(waterMeter.getAddress().getAddressId(), updatedWaterMeter.getAddress().getAddressId());
    }

    @Test
    public void testDeleteWaterMeter() {
        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("description" + timestamp);
        waterMeter.setAddress(addressService.getAddressById(1));
        waterMeterService.insertWaterMeter(waterMeter);
        waterMeterService.deleteWaterMeter(waterMeter.getWaterMeterId());
        WaterMeter deletedWaterMeter = waterMeterService.getWaterMeterById(waterMeter.getWaterMeterId());
        Assert.assertNull(deletedWaterMeter);
    }
}