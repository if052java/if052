package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.config.WebMvcConfig;
import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_core.domain.WaterMeter;
import com.softserveinc.if052_webapp.service.IndicatorService;
import com.softserveinc.if052_webapp.service.MeterService;
import com.softserveinc.if052_webapp.service.ServiceResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.MatcherAssertionErrors.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebMvcConfig.class, TestContext.class})
@WebAppConfiguration
public class IndicatorControllerTest {

    private MockMvc mockMvc;

    MeterType meterType;
    WaterMeter waterMeter;
    Indicator first;
    Indicator second;
    ServiceResponse indServResponse;
    ServiceResponse meterServResponce;

    @Autowired
    @Qualifier("indicatorService")
    private IndicatorService indicatorServiceMock;

    @Autowired
    @Qualifier("meterService")
    private MeterService meterServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        /* We have to reset our mock between tests because the mock objects
          are managed by the Spring container. If we would not reset them,
          stubbing and verified behavior would "leak" from one test to another. */
        Mockito.reset(indicatorServiceMock);
        Mockito.reset(meterServiceMock);

        //initializing
        meterType = new MeterType(1, "Холодна вода");
        waterMeter = new WaterMeter(4, "ванна", "червоний", 0.6, new Address(), meterType, null);
        first = new Indicator(1, new Date(), 0.5, 503, true, false, waterMeter);
        second = new Indicator(2, new Date(), 0.6, 608, false, false, waterMeter);
        indServResponse = new ServiceResponse(Arrays.asList(first, second));
        meterServResponce = new ServiceResponse(Arrays.asList(waterMeter));


        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetIndicatorsPage() throws Exception {
        when(meterServiceMock.getMeterById(4)).thenReturn(meterServResponce);
        when(indicatorServiceMock.getIndicatorList(4)).thenReturn(indServResponse);

        mockMvc.perform(get("/indicators?meterId=4"))
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
        verify(meterServiceMock, times(1)).getMeterById(4);
        verifyNoMoreInteractions(indicatorServiceMock);
        verifyNoMoreInteractions(meterServiceMock);
    }

    @Test
    public void testAddIndicator() throws Exception {
        when(meterServiceMock.getMeterById(4)).thenReturn(meterServResponce);
        when(indicatorServiceMock.getIndicatorList(4)).thenReturn(new ServiceResponse());
        when(indicatorServiceMock.addIndicator(org.mockito.Mockito.isA(Indicator.class),eq("4"), eq("11-04-2015"))).thenReturn(indServResponse);
        mockMvc.perform(get("/indicators?meterId=4"));
        mockMvc.perform(post("/addIndicator")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("indicatorId", "1")
                        .param("dateStr", "11-04-2015")
                        .param("value", "503")
                        .param("paid", "true")
                        .sessionAttr("indicator", new Indicator())
                        .requestAttr("dateStr", "11-04-2015"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/indicators?meterId=4"));

        ArgumentCaptor<Indicator> formObjectArgument = ArgumentCaptor.forClass(Indicator.class);
        verify(indicatorServiceMock, times(1)).getIndicatorList(4);
        verify(indicatorServiceMock, times(1)).addIndicator(formObjectArgument.capture(), eq("4"), eq("11-04-2015"));
        verify(meterServiceMock, times(1)).getMeterById(4);
        verifyNoMoreInteractions(indicatorServiceMock);
        verifyNoMoreInteractions(meterServiceMock);

        Indicator formObject = formObjectArgument.getValue();

        assertThat(formObject.getValue(), is(503));
        assertNotNull(formObject.getIndicatorId());
        assertThat(formObject.isPaid(), is(true));
    }
}