package com.softserveinc.if052_restful.resource;


import com.softserveinc.if052_core.domain.Report;
import com.softserveinc.if052_restful.report.ReportConverter;
import com.softserveinc.if052_core.domain.ReportRequest;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
* Created by Danylo Tiahun on 14.03.2015.
*/

@RestController
@RequestMapping("/rest/report/")
public class ReportResource {

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportConverter reportConverter;

    @RequestMapping(value = "/mindate", method = RequestMethod.GET, produces = "application/json")
    public  Date getMinDate() {
        Date minDate = indicatorService.getMinDate();
        if (minDate == null) {
            minDate = new Date();
        }
        return minDate;
    }

    @RequestMapping(value = "/maxdate", method = RequestMethod.GET, produces = "application/json")
    public Date getMaxDate() {
        Date maxDate = indicatorService.getMaxDate();
        if (maxDate == null) {
            maxDate = new Date();
        }
        return maxDate;
    }


    @RequestMapping(value = "{reportId}", method = RequestMethod.GET, produces = "application/json")
    public Report getReport(
        @PathVariable("reportId") int reportId,
        HttpServletResponse response) {
        Report report = reportService.getReportById(reportId);
        if (report == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return report;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public Report insertReport(
        @RequestBody
        ReportRequest reportRequest,
        HttpServletResponse response) {
        Report report = reportConverter.createReport(reportRequest);
        report.setReportRequest(reportRequest.toString());
        report.setXmlReport(reportConverter.convertToXml(report).toString());
        reportService.insertReport(report);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setHeader("Location", "/report/" + report.getReportId());

        return report;
    }

}
