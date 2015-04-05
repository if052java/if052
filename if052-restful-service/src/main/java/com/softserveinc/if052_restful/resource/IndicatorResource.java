package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.WaterMeter;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.WaterMeterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
* Created by Maksym on 2/12/2015.
*/
@RestController
@RequestMapping("/rest/indicators")
public class IndicatorResource {

    public final String start = "-01-01 00:00:00";
    public final String end = "-31-31 23:59:59";
    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private WaterMeterService waterMeterService;

    @RequestMapping(value = "{waterMeterId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicators(@PathVariable("waterMeterId") int waterMeterId) {
        WaterMeter waterMeter = waterMeterService.getWaterMeterById(waterMeterId);
        List<Indicator> indicators = indicatorService.getIndicatorsByWaterMeter(waterMeter);
        if (indicators == null) {
            indicators = new ArrayList<Indicator>();
        }
        return indicators;
    }

    @RequestMapping(value = "/list/byuser/{userId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicatorsByUserId(@PathVariable("userId") int userId,
                                          @RequestParam("number") int number) {
        List<Indicator> indicators = indicatorService.getIndicatorsByUserId(userId, number);
        if (indicators == null) {
            indicators = new ArrayList<Indicator>();
        }
        return indicators;
    }

    @RequestMapping(value = "/byYear/{meterId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicatorsByYear(@PathVariable("meterId") int meterId,
                                  @RequestParam("year") int year ) {
        String startDate = year + start;
        String endDate = year + end;

        List < Indicator > indicators =
            indicatorService.getIndicatorsByDates(meterId, startDate, endDate);
        if (indicators == null) {
            indicators = new ArrayList<Indicator>();
        }
        return indicators;
    }

    @RequestMapping(value="/byDates/{meterId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicatorsByDates(@PathVariable("meterId") int meterId,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        HttpServletResponse response) {
        List < Indicator > indicators =
            indicatorService.getIndicatorsByDates(meterId, startDate, endDate);
        if (indicators == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        return indicators;
    }

    @RequestMapping(value = "/getone/{indicatorId}", method = RequestMethod.GET, produces = "application/json")
    public Indicator getIndicator(@PathVariable("indicatorId") int indicatorId) {
        Indicator indicator = indicatorService.getIndicatorById(indicatorId);

        return indicator;
    }

    @RequestMapping(value = "{indicatorId}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteIndicator(@PathVariable("indicatorId") int indicatorId) {
        indicatorService.deleteIndicator(indicatorId);

    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Indicator createIndicator(
        @RequestBody
        Indicator indicator){
        indicatorService.insertIndicator(indicator);

        return indicator;
    }

    @RequestMapping(value = "{indicatorId}", method = RequestMethod.PUT, produces = "application/json")
    public Indicator updateIndicator(
        @PathVariable("indicatorId") int indicatorId,
        @RequestBody
        Indicator indicator){
        indicatorService.updateIndicator(indicator);

        return indicator;
    }
}
