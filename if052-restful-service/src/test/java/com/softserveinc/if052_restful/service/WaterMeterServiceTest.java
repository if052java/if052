package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class WaterMeterServiceTest {
    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MeterTypeService meterTypeService;

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
    public void testGetFirstMeterByUserId(){
        WaterMeter waterMeter = waterMeterService.getFirstMeterByUserId(1);
        Assert.assertNotNull(waterMeter);
        System.out.println(waterMeter);
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
        waterMeter.setTariff(timestamp/1000000);
        waterMeter.setAddress(addressService.getAddressById(1));
        waterMeter.setMeterType(meterTypeService.getMeterTypeById(1));
        waterMeterService.insertWaterMeter(waterMeter);
        System.out.println(waterMeter);
        Assert.assertTrue(waterMeter.getWaterMeterId() != 0);
        WaterMeter createdWaterMeter = waterMeterService.getWaterMeterById(waterMeter.getWaterMeterId());
        System.out.println(createdWaterMeter);
        Assert.assertNotNull(createdWaterMeter);
        Assert.assertEquals(waterMeter.getName(), createdWaterMeter.getName());
        Assert.assertEquals(waterMeter.getDescription(), createdWaterMeter.getDescription());
        Assert.assertEquals(waterMeter.getTariff(), createdWaterMeter.getTariff(), 0.01);
        Assert.assertEquals(waterMeter.getAddress().getAddressId(), createdWaterMeter.getAddress().getAddressId());
        waterMeterService.deleteWaterMeter(waterMeter.getWaterMeterId());
    }

    @Test
    public void testUpdateWaterMeter() {
        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(1);
        System.out.println(waterMeter);
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("description" + timestamp);
        waterMeter.setTariff(timestamp/1000000);
        waterMeter.setAddress(addressService.getAddressById(1));
        waterMeter.setMeterType(meterTypeService.getMeterTypeById(1));
        System.out.println(waterMeter);
        waterMeterService.updateWaterMeter(waterMeter);
        WaterMeter updatedWaterMeter = waterMeterService.getWaterMeterById(1);
        System.out.println(updatedWaterMeter);
        Assert.assertEquals(waterMeter.getName(), updatedWaterMeter.getName());
        Assert.assertEquals(waterMeter.getDescription(), updatedWaterMeter.getDescription());
        Assert.assertEquals(waterMeter.getTariff(), updatedWaterMeter.getTariff(), 0.01);
        Assert.assertEquals(waterMeter.getAddress().getAddressId(), updatedWaterMeter.getAddress().getAddressId());
    }

    @Test
    public void testDeleteWaterMeter() {
        long timestamp = System.currentTimeMillis();
        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setName("name" + timestamp);
        waterMeter.setDescription("description" + timestamp);
        waterMeter.setTariff(timestamp/1000000);
        waterMeter.setAddress(addressService.getAddressById(1));
        waterMeter.setMeterType(meterTypeService.getMeterTypeById(1));
        waterMeterService.insertWaterMeter(waterMeter);
        waterMeterService.deleteWaterMeter(waterMeter.getWaterMeterId());
        WaterMeter deletedWaterMeter = waterMeterService.getWaterMeterById(waterMeter.getWaterMeterId());
        Assert.assertNull(deletedWaterMeter);
    }
}