package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class WaterMeterServiceTest {
    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private MeterTypeService meterTypeService;


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
    public void testGetFirstMeter(){
        WaterMeter waterMeter = waterMeterService.getFirstMeter();
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