package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.config.TestContext;
import com.softserveinc.if052_webapp.config.WebMvcConfig;
import com.softserveinc.if052_webapp.domain.Indicator;
import com.softserveinc.if052_webapp.domain.MeterType;
import com.softserveinc.if052_webapp.domain.WaterMeter;
import com.softserveinc.if052_webapp.service.IndicatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class, TestContext.class})
@WebAppConfiguration
public class IndicatorControllerTest {

    private MockMvc mockMvc;

    @Autowired
    @Qualifier("indicatorService")
    private IndicatorService indicatorServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        //We have to reset our mock between tests because the mock objects
        //are managed by the Spring container. If we would not reset them,
        //stubbing and verified behavior would "leak" from one test to another.
        Mockito.reset(indicatorServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetIndicatorsPage() throws Exception {
        MeterType meterType = new MeterType();
        meterType.setMeterTypeId(1);
        meterType.setType("вода");

        WaterMeter waterMeter = new WaterMeter();
        waterMeter.setWaterMeterId(4);
        waterMeter.setName("ванна");
        waterMeter.setDescription("червоний");
        waterMeter.setTariff(0.6);
        waterMeter.setMeterType(meterType);

        Indicator first = new Indicator();
        first.setIndicatorId(1);
        first.setDate(new Date());
        first.setValue(503);
        first.setTariffPerDate(0.5);
        first.setPaid(true);
        first.setWaterMeter(waterMeter);

        Indicator second = new Indicator();
        second.setIndicatorId(2);
        second.setDate(new Date());
        second.setValue(608);
        second.setTariffPerDate(0.6);
        second.setWaterMeter(waterMeter);

        when(indicatorServiceMock.getMeterById(4)).thenReturn(waterMeter);
        when(indicatorServiceMock.getIndicatorList(4)).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/indicators?waterMeterId=4"))
                .andExpect(status().isOk())
                .andExpect(view().name("indicators"))
                .andExpect(forwardedUrl("/WEB-INF/view/indicators.jsp"))
                .andExpect(model().attribute("indicators", hasSize(2)))
                .andExpect(model().attribute("indicators", hasItem(
                        allOf(
                                hasProperty("indicatorId", is(1)),
                                hasProperty("value", is(503)),
                                hasProperty("paid", is(true))
                        )
                )))
                .andExpect(model().attribute("indicators", hasItem(
                        allOf(
                                hasProperty("indicatorId", is(2)),
                                hasProperty("value", is(608)),
                                hasProperty("paid", is(false))
                        )
                )))
                .andExpect((model().attribute("waterMeter", hasProperty("name", is("ванна"))
                )));
        verify(indicatorServiceMock, times(1)).getIndicatorList(4);
        verify(indicatorServiceMock, times(1)).getMeterById(4);
        verifyNoMoreInteractions(indicatorServiceMock);
    }
}