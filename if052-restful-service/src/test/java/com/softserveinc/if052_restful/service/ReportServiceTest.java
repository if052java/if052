package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Report;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Danylo Tiahun on 14.03.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Test
    public void testGetReportById() {
        Report report = new Report();
        report.setReportRequest("REQUEST");
        report.setXmlReport("REPORT");
        reportService.insertReport(report);
        report = reportService.getReportById(report.getReportId());
        Assert.assertNotNull(report);
        System.out.println(report.getReportRequest() + " " +report.getXmlReport());
    }

    @Test
    public void testInsertReport() {
        Report report = new Report();
        report.setReportRequest("REQUEST");
        report.setXmlReport("REPORT");
        reportService.insertReport(report);
        Report createdReport = reportService.getReportById(report.getReportId());
        Assert.assertNotNull(createdReport);
        Assert.assertEquals(report.getReportRequest(), createdReport.getReportRequest());
        Assert.assertEquals(report.getXmlReport(), createdReport.getXmlReport());
    }


}
