package com.softserveinc.if052_restful.resource;


import com.softserveinc.if052_core.domain.Report;
import com.softserveinc.if052_restful.report.ExcelReportConverter;
import com.softserveinc.if052_core.domain.ReportRequest;
import com.softserveinc.if052_restful.report.XmlReportConverter;
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
    private XmlReportConverter xmlReportConverter;

    @Autowired
    private ExcelReportConverter excelReportConverter;

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


    @RequestMapping(value = "/xml", method = RequestMethod.POST, produces = "application/xml")
    public byte[] createXmlReport(
        @RequestBody
        ReportRequest reportRequest,
        HttpServletResponse response) {
        Report report = xmlReportConverter.createReport(reportRequest);
        report.setXmlReport(xmlReportConverter.convert(report).toString());
        reportService.insertReport(report);
        byte[] output = xmlReportConverter.convert(report);
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setHeader("Content-Disposition", "attachment;filename=report.xml");
        return output;
    }

    @RequestMapping(value = "/excel", method = RequestMethod.POST,
            produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public byte[] createExcelReport(
            @RequestBody
            ReportRequest reportRequest,
            HttpServletResponse response) {
        Report report = excelReportConverter.createReport(reportRequest);
        reportService.insertReport(report);
        byte[] output = excelReportConverter.convert(report);
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Content-Disposition", "attachment;filename=report.xlsx");
        return output;
    }

}
