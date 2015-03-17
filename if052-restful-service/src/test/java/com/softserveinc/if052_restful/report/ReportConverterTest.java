package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_restful.domain.Report;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml",
        "classpath:h2-datasource.xml"})
public class ReportConverterTest {

    @Autowired
    private ReportConverter reportConverter;

    @Autowired
    private UserService userService;

    @Autowired
    private IndicatorService indicatorService;

    @Test
    public void testConvertToXml() {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Report report = reportConverter.createReport(new ReportRequest(new java.util.Date("2013/01/01"), new java.util.Date(), "ALL"));

        System.out.println(reportConverter.convertToXml(report));
    }

}
