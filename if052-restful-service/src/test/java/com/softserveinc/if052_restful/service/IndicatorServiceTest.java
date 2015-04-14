package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Indicator;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    private static Logger LOGGER = Logger.getLogger(AddressServiceTest.class);

    @BeforeClass
    public static void setAuth(){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                "1", "PASS1111", authorities);
        HttpServletRequest request = new MockHttpServletRequest();

        token.setDetails(new WebAuthenticationDetails(request));

        LOGGER.debug("Logging in with " + token.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(token);
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }

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
    public void testGetIndicatorsByMeterId() {
        List<Indicator> indicators = indicatorService.getIndicatorsByMeterId(1);
        Assert.assertNotNull(indicators);
        for (Indicator indicator : indicators) {
            System.out.println(indicator);
        }
    }

    @Test
    public void testGetIndicatorsByUserId() {
        List<Indicator> indicators = indicatorService.getIndicatorsForUser(10);
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