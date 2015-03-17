package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Report;
import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.report.ReportConverter;
import com.softserveinc.if052_restful.report.ReportRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Danylo Tiahun on 14.03.2015.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml",
        "classpath:h2-datasource.xml"})
public class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @Autowired
    private ReportConverter reportConverter;

    @Test
    public void testGetReportById() {
        Report report = new Report();
        report.setReportRequest("S");
        report.setXmlReport("DSF");
        reportService.insertReport(report);

        Report report2 = reportService.getReportById(1);
        System.out.println(report2.getXmlReport());
    }

    @Test
    public void testInsertReport() {
        Report report = new Report();
        report.setReportRequest("S");
        report.setXmlReport("DSF");
        reportService.insertReport(report);

    }


}
