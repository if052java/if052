package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Indicator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpringIndicatorServiceTest
{
    @Autowired
    private IndicatorService indicatorService;

    @Test
    public void testGetIndicatorById()
    {
        Indicator indicator = indicatorService.getIndicatorById(3);
        Assert.assertNotNull(indicator);
        System.out.println(indicator);
    }

    @Test
    public void testGetAllIndicators()
    {
        List<Indicator> indicators = indicatorService.getAllIndicators();
        Assert.assertNotNull(indicators);
        for (Indicator indicator : indicators)
        {
            System.out.println(indicator);
        }

    }

    @Test
    public void testInsertIndicator() {
        Indicator indicator = new Indicator();
      //  indicator.setIndicatorId(400);
        indicator.setDate(new java.util.Date());
        indicator.setValue(1001);
        indicator.setPaid(true) ;
        indicator.setPublished(false);
        indicator.setWaterMeterId(2);

        indicatorService.insertIndicator(indicator);

        Assert.assertTrue(indicator.getIndicatorId() != 0);
        Indicator createdIndicator = indicatorService.getIndicatorById(indicator.getIndicatorId());
        Assert.assertNotNull(createdIndicator);
        Assert.assertEquals(indicator.getIndicatorId(), createdIndicator.getIndicatorId());
  //    Assert.assertEquals(indicator.getDate().getTime(), createdIndicator.getDate().);
        Assert.assertEquals(indicator.getValue(), createdIndicator.getValue());
        Assert.assertEquals(indicator.isPaid(), createdIndicator.isPaid());
        Assert.assertEquals(indicator.isPublished(), createdIndicator.isPublished());
        Assert.assertEquals(indicator.getWaterMeterId(), createdIndicator.getWaterMeterId());
    }
    @Test
    public void testUpdateIndicator()
    {
        Indicator indicator = indicatorService.getIndicatorById(2);
        indicator.setDate(new java.util.Date());
        indicator.setValue(1234);
        indicator.setPaid(true);
        indicator.setPublished(false);
        indicatorService.updateIndicator(indicator);
        Indicator updatedIndicator = indicatorService.getIndicatorById(2);
        //    Assert.assertEquals(indicator.getDate(), updatedIndicator.getDate());
        Assert.assertEquals(indicator.isPaid(), updatedIndicator.isPaid());
        Assert.assertEquals(indicator.isPublished(), updatedIndicator.isPublished());
        Assert.assertEquals(indicator.getValue(), updatedIndicator.getValue());
    }

    @Test
    public void testDeleteIndicator()
    {
        Indicator indicator = indicatorService.getIndicatorById(37);
        indicatorService.deleteIndicator(indicator.getIndicatorId());
        Indicator deletedIndicator = indicatorService.getIndicatorById(37);
        Assert.assertNull(deletedIndicator);
    }
}


