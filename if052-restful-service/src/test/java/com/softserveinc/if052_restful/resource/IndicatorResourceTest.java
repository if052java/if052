package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Address;
import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_core.domain.WaterMeter;
import com.softserveinc.if052_restful.service.IndicatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml", "classpath*:TestContext.xml"})
@WebAppConfiguration
public class IndicatorResourceTest {

    private MockMvc mockMvc;

    private MeterType meterType;
    private WaterMeter waterMeter;
    private Indicator indicator1;
    private Indicator indicator2;

    @Autowired
    @Qualifier("indicatorServiceMock")
    private IndicatorService indicatorServiceMock;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        /* We have to reset our mock between tests because the mock objects
          are managed by the Spring container. If we would not reset them,
          stubbing and verified behavior would "leak" from one test to another. */
//        Mockito.reset(indicatorServiceMock);

        meterType = new MeterType(1, "Холодна вода");
        waterMeter = new WaterMeter(4, "ванна", "червоний", 0.6, new Address(), meterType, null);
        indicator1 = new Indicator(1, new Date(), 0.5, 503, true, false, waterMeter);
        indicator2 = new Indicator(2, new Date(), 0.6, 608, false, false, waterMeter);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetIndicators() throws Exception {

        when(indicatorServiceMock.getIndicatorsByMeterId(4)).thenReturn(Arrays.asList(indicator1, indicator2));

        mockMvc.perform(get("/rest/indicators/4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].indicatorId", is(1)))
                .andExpect(jsonPath("$[0].tariffPerDate", is(0.5)))
                .andExpect(jsonPath("$[0].value", is(503)))
                .andExpect(jsonPath("$[1].indicatorId", is(2)))
                .andExpect(jsonPath("$[1].tariffPerDate", is(0.6)))
                .andExpect(jsonPath("$[1].value", is(608)));

//        verify(indicatorServiceMock, times(1)).getIndicatorsByMeterId(4);
//        verifyNoMoreInteractions(indicatorServiceMock);
    }


    @Test
    public void testGetIndicatorsForUser() throws Exception {
        when(indicatorServiceMock.getIndicatorsForUser(10)).thenReturn(Arrays.asList(indicator1, indicator2));

        mockMvc.perform(get("/rest/indicators/list/byuser/10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].indicatorId", is(1)))
                .andExpect(jsonPath("$[0].tariffPerDate", is(0.5)))
                .andExpect(jsonPath("$[0].value", is(503)))
                .andExpect(jsonPath("$[1].indicatorId", is(2)))
                .andExpect(jsonPath("$[1].tariffPerDate", is(0.6)))
                .andExpect(jsonPath("$[1].value", is(608)));

//        verify(indicatorServiceMock, times(1)).getIndicatorsForUser(1,10);
    }

    @Test
    public void testGetIndicatorsByYear() throws Exception {
        when(indicatorServiceMock.getIndicatorsByDates(4, 2015 + "-01-01 00:00:00", 2015 + "-12-31 23:59:59")).thenReturn(Arrays.asList(indicator1, indicator2));

        mockMvc.perform(get("/rest/indicators/byYear/4?year=2015"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].indicatorId", is(1)))
                .andExpect(jsonPath("$[0].tariffPerDate", is(0.5)))
                .andExpect(jsonPath("$[0].value", is(503)))
                .andExpect(jsonPath("$[1].indicatorId", is(2)))
                .andExpect(jsonPath("$[1].tariffPerDate", is(0.6)))
                .andExpect(jsonPath("$[1].value", is(608)));

    }

    @Test
    public void testGetIndicatorsByDates() throws Exception {
        when(indicatorServiceMock.getIndicatorsByDates(4,"2015-01-01 00:00:00", "2015-12-31 23:59:59")).thenReturn(Arrays.asList(indicator1, indicator2));

        mockMvc.perform(get("/rest/indicators/byDates/4?startDate=2015-01-01 00:00:00&endDate=2015-12-31 23:59:59"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].indicatorId", is(1)))
                .andExpect(jsonPath("$[0].tariffPerDate", is(0.5)))
                .andExpect(jsonPath("$[0].value", is(503)))
                .andExpect(jsonPath("$[1].indicatorId", is(2)))
                .andExpect(jsonPath("$[1].tariffPerDate", is(0.6)))
                .andExpect(jsonPath("$[1].value", is(608)));
    }

    @Test
    public void testGetIndicator() throws Exception {
        when(indicatorServiceMock.getIndicatorById(1)).thenReturn(indicator1);

        mockMvc.perform(get("/rest/indicators/getone/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.indicatorId", is(1)))
                .andExpect(jsonPath("$.tariffPerDate", is(0.5)))
                .andExpect(jsonPath("$.value", is(503)));
    }

//    @Test
//    public void testUpdateIndicator() throws Exception {
//        indicatorServiceMock.updateIndicator(indicator1);
//
//        mockMvc.perform(put("/rest/indicators/{indicatorId}", indicator1))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.indicatorId", is(1)))
//                .andExpect(jsonPath("$.tariffPerDate", is(0.5)))
//                .andExpect(jsonPath("$.value", is(503)));
//    }

//    @Test
//    public void testCreateIndicator() throws Exception {
//        indicatorServiceMock.insertIndicator(indicator1);
//        indicatorServiceMock.getIndicatorById(indicator1.getIndicatorId());
//
//        mockMvc.perform(post("/rest/indicators/")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.indicatorId", is(1)))
//                .andExpect(jsonPath("$.tariffPerDate", is(0.5)))
//                .andExpect(jsonPath("$.value", is(503)));
//    }

//    @Test
//    public void testUpdateIndicator() throws Exception {
//
//    }
}
