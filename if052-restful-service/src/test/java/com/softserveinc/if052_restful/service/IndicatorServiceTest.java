package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Indicator;
import com.softserveinc.if052_restful.domain.WaterMeter;
import org.junit.Assert;
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
@ContextConfiguration(locations={"classpath:applicationContext.xml",
                                 "classpath:h2-datasource.xml"})
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
    public void testInsertIndicator() {
        Indicator indicator = new Indicator();
        indicator.setDate(new java.util.Date());
        indicator.setTariffPerDate(0.1);
        indicator.setValue(1001);
        indicator.setPaid(true) ;
        indicator.setPublished(false);
        indicator.setWaterMeter(waterMeterService.getWaterMeterById(1));
        indicatorService.insertIndicator(indicator);

        System.out.println(indicator);

        Assert.assertTrue(indicator.getIndicatorId() != 0);
        Indicator createdIndicator = indicatorService.getIndicatorById(indicator.getIndicatorId());
        Assert.assertNotNull(createdIndicator);
        Assert.assertEquals(indicator.getIndicatorId(), createdIndicator.getIndicatorId());
        // Assert.assertEquals(indicator.getDate().getTime(), createdIndicator.getDate().);
        Assert.assertEquals(indicator.getTariffPerDate(), createdIndicator.getTariffPerDate(), 0.00);
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