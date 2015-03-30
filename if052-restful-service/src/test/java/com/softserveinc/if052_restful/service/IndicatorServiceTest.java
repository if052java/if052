package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

/**
 * Created by Maksym Vynnyk on 02.02.2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class IndicatorServiceTest {
    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private WaterMeterService waterMeterService;

    @Test
    public void testGetIndicatorById() {
        Indicator indicator = indicatorService.getIndicatorById(3);
        Assert.assertNotNull(indicator);
        System.out.println(indicator);
    }

    @Test
    public void testGetAllIndicators() {
        List<Indicator> indicators = indicatorService.getAllIndicators();
        Assert.assertNotNull(indicators);
        for (Indicator indicator : indicators) {
            System.out.println(indicator);
        }
    }

    @Test
    public void testGetIndicatorsByWaterMeter() {
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(1);
        List<Indicator> indicators = indicatorService.getIndicatorsByWaterMeter(waterMeter);
        Assert.assertNotNull(indicators);
        for (Indicator indicator : indicators) {
            System.out.println(indicator);
        }
    }

    @Test
    public void testGetIndicatorsByDates(){
        List < Indicator > indicators =
        indicatorService.getIndicatorsByDates(1, "2015-01-01 00:00:00", "2015-31-31 23:59:59");
        Assert.assertNotNull(indicators);
        for (Indicator indicator : indicators) {
        	//CODEREVIEW NO : Consider using hamcrest mathchers to validate collections
        	//CODEREVIEW NO : Even in tests please use loggers, not sysouts 
            System.out.println(indicator);
        }
    }

    @Test
    public void testInsertIndicator() {
        Indicator indicator = new Indicator();
        indicator.setDate(new java.util.Date());
        indicator.setTariffPerDate(waterMeterService.getWaterMeterById(1).getTariff());
        indicator.setValue(1001);
        indicator.setPaid(true) ;
        indicator.setPublished(false);
        indicator.setWaterMeter(waterMeterService.getWaterMeterById(1));
        indicatorService.insertIndicator(indicator);
        System.out.println(indicator);

        Assert.assertTrue(indicator.getIndicatorId() != 0);
        Indicator createdIndicator = indicatorService.getIndicatorById(indicator.getIndicatorId());
        System.out.println(createdIndicator);
        Assert.assertNotNull(createdIndicator);
        Assert.assertEquals(indicator.getIndicatorId(), createdIndicator.getIndicatorId());
        // Assert.assertEquals(indicator.getDate().getTime(), createdIndicator.getDate().);
        Assert.assertEquals(indicator.getTariffPerDate(), createdIndicator.getTariffPerDate(), 0.01);
        Assert.assertEquals(indicator.getValue(), createdIndicator.getValue());
        Assert.assertEquals(indicator.isPaid(), createdIndicator.isPaid());
        Assert.assertEquals(indicator.isPublished(), createdIndicator.isPublished());
        Assert.assertEquals(indicator.getWaterMeter().getWaterMeterId(),
                createdIndicator.getWaterMeter().getWaterMeterId());
    }

    @Test
    public void testUpdateIndicator() {
        // searching of last record id for update
        List<Indicator> indicators = indicatorService.getAllIndicators();
        int lastId = indicators.get(indicators.size() - 1).getIndicatorId();

        Indicator indicator = indicatorService.getIndicatorById(lastId);
        indicator.setDate(new java.util.Date());
        indicator.setTariffPerDate(0.09);
        indicator.setValue(1234);
        indicator.setPaid(true);
        indicator.setPublished(false);
        indicatorService.updateIndicator(indicator);
        Indicator updatedIndicator = indicatorService.getIndicatorById(lastId);
        // Assert.assertEquals(indicator.getDate(), updatedIndicator.getDate());
        Assert.assertEquals(indicator.isPaid(), updatedIndicator.isPaid());
        Assert.assertEquals(indicator.isPublished(), updatedIndicator.isPublished());
        Assert.assertEquals(indicator.getValue(), updatedIndicator.getValue());
        Assert.assertEquals(indicator.getTariffPerDate(), updatedIndicator.getTariffPerDate(), 0.00);
    }

    @Test
    public void testDeleteIndicator() {
        // searching of last record id for delete
        List<Indicator> indicators = indicatorService.getAllIndicators();
        int lastId = indicators.get(indicators.size() - 1).getIndicatorId();

        Indicator indicator = indicatorService.getIndicatorById(lastId);
        indicatorService.deleteIndicator(indicator.getIndicatorId());
        Indicator deletedIndicator = indicatorService.getIndicatorById(lastId);
        Assert.assertNull(deletedIndicator);
    }
}