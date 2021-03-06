package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_restful.service.IndicatorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
* Created by Maksym on 2/12/2015.
*/
@RestController
@RequestMapping("/rest/indicators")
public class IndicatorResource {

    public final String start = "-01-01 00:00:00";
    public final String end = "-12-31 23:59:59";
    @Autowired
    private IndicatorService indicatorService;

    private static Logger LOGGER = Logger.getLogger(IndicatorResource.class.getName());

    @PreAuthorize("hasPermission(#meterId, 'udWaterMeter')")
    @RequestMapping(value = "{meterId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicators(@PathVariable("meterId") int meterId) {
        LOGGER.info("INFO: Searching for the collection of indicators by meter with id " + meterId + ".");
        List<Indicator> indicators = indicatorService.getIndicatorsByMeterId(meterId);
        if (indicators == null) {
            LOGGER.info("INFO: The collection of indicators for the meter with id " + meterId + " has not been found.");
            indicators = new ArrayList<Indicator>();
        } else {
            LOGGER.info("INFO: The collection of indicators has been found for meter with id " + meterId + ".");
        }
        return indicators;
    }

    @RequestMapping(value = "/list/byuser/{number}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicatorsByUserId(@PathVariable("number") int number) {
        LOGGER.info("INFO: Searching for the indicators for current user .");
        List<Indicator> indicators = indicatorService.getIndicatorsForUser(number);
        if (indicators == null) {
            LOGGER.info("INFO: The collection of indicators for the current user has not been found.");
            indicators = new ArrayList<Indicator>();
        } else {
            LOGGER.info("INFO: The collection of indicators for the current user has been found");
        }
        return indicators;
    }

    @PreAuthorize("hasPermission(#meterId, 'udWaterMeter')")
    @RequestMapping(value = "/byYear/{meterId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicatorsByYear(@PathVariable("meterId") int meterId,
                                  @RequestParam("year") int year ) {
        String startDate = year + start;
        String endDate = year + end;
        LOGGER.info("INFO: Searching for the indicators by year " + year + ".");
        List < Indicator > indicators =
            indicatorService.getIndicatorsByDates(meterId, startDate, endDate);
        if (indicators == null) {
            LOGGER.info("INFO: The collection of indicators for the year " + year + " has not been found.");
            indicators = new ArrayList<Indicator>();
        } else {
            LOGGER.info("INFO: The collection of indicators has been found for the year " + year + ".");
        }
        return indicators;
    }

    @PreAuthorize("hasPermission(#meterId, 'udWaterMeter')")
    @RequestMapping(value="/byDates/{meterId}", method = RequestMethod.GET, produces = "application/json")
    public List<Indicator> getIndicatorsByDates(@PathVariable("meterId") int meterId,
                                        @RequestParam("startDate") String startDate,
                                        @RequestParam("endDate") String endDate,
                                        HttpServletResponse response) {
        LOGGER.info("INFO: Searching for the indicators since " + startDate + " till " + endDate + ".");
        List < Indicator > indicators =
            indicatorService.getIndicatorsByDates(meterId, startDate, endDate);
        if (indicators == null){
            LOGGER.info("INFO: The collection of indicators since " + startDate + " till " + endDate + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("INFO: The collection of indicators since " + startDate + " till " + endDate + " has been successfully found.");
        return indicators;
    }

    @PreAuthorize("hasPermission(#indicatorId, 'indicator')")
    @RequestMapping(value = "/getone/{indicatorId}", method = RequestMethod.GET, produces = "application/json")
    public Indicator getIndicator(
            @PathVariable("indicatorId") int indicatorId,
            HttpServletResponse response) {
        LOGGER.info("INFO: Searching for the indicator with id " + indicatorId + ".");
        Indicator indicator = indicatorService.getIndicatorById(indicatorId);
        if (indicator == null) {
            LOGGER.info("INFO: Indicator with requested id " + indicatorId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("INFO: Indicator with requested id " + indicatorId + " has been successfully found.");
        return indicator;
    }

    @PreAuthorize("hasPermission(#indicatorId, 'indicator')")
    @RequestMapping(value = "{indicatorId}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteIndicator(
            @PathVariable("indicatorId") int indicatorId,
            HttpServletResponse response) {
        if(indicatorService.getIndicatorById(indicatorId) == null){
            LOGGER.info("INFO: Indicator with requested id " + indicatorId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else
            try {
                LOGGER.info("INFO: Deleting an indicator with id " + indicatorId + ".");
                indicatorService.deleteIndicator(indicatorId);
                LOGGER.info("INFO: Indicator with requested id " + indicatorId + " has been successfully deleted.");
            }
            catch (Exception e){
                LOGGER.info("INFO: Internal error");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Indicator createIndicator(
        @Valid
        @RequestBody
        Indicator indicator,
        HttpServletResponse response){
        try {
            LOGGER.info("INFO: Adding a new indicator.");
            indicatorService.insertIndicator(indicator);
            LOGGER.info("INFO: Indicator has been successfully added with id " + indicator.getIndicatorId() + ".");
            response.setStatus(HttpServletResponse.SC_CREATED);
            return indicator;
        }
        catch (Exception e) {
            LOGGER.info("INFO: Internal error");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @PreAuthorize("hasPermission(#indicator.indicatorId, 'indicator')")
    @RequestMapping(value = "{indicatorId}", method = RequestMethod.PUT, produces = "application/json")
    public Indicator updateIndicator(
        @PathVariable("indicatorId") int indicatorId,
        @Valid
        @RequestBody
        Indicator indicator,
        HttpServletResponse response){
        if(indicatorService.getIndicatorById(indicatorId) == null){
            LOGGER.info("INFO: Indicator with requested id " + indicatorId + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        } else
        try {
            LOGGER.info("INFO: Updating an indicator with id " + indicatorId + ".");
            indicatorService.updateIndicator(indicator);
            LOGGER.info("INFO: Indicator with id " + indicatorId + " has been successfully updated.");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return indicator;
        }
        catch (Exception e) {
            LOGGER.info("INFO: Internal error");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }
}
