package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_core.domain.ReportRequest;
import com.softserveinc.if052_webapp.utils.FileDownloader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Danylo Tiahun on 4/16/2015.
 */

@Controller
public class ReportConrtoller {

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    private final String START_DATE = "startDate";
    private final String END_DATE = "endDate";
    private final String METER_TYPES = "meterTypes";
    private final String LOGINS = "logins";

    private Date minDate;
    private Date maxDate;
    private DateFormat df;

    private String contentType;
    private String contentDisposition;

    private static Logger LOGGER = Logger.getLogger(ReportConrtoller.class);

    @RequestMapping("/xmlreport")
    public ModelAndView getXmlReportPage() {
        ModelAndView model = new ModelAndView("xmlReport");
        setDate(model);
        setLoginAndTypes(model);
        return model;
    }

    @RequestMapping("/excelreport")
    public ModelAndView getExcelReportPage() {
        ModelAndView model = new ModelAndView("excelReport");
        setDate(model);
        return model;
    }

    @RequestMapping(value = "/createXmlReport", method = RequestMethod.GET)
    public void createXmlReport(@ModelAttribute ReportRequest reportRequest,
                                HttpServletRequest request, HttpServletResponse response) {
        reportRequest.setLocale("");
        byte[] output = getFileEntity(reportRequest, "xml").getBody();
        fileDownloader.downloadFile(request, response, output,
                contentType, contentDisposition);
        LOGGER.info("Downloading xml report for " + reportRequest.getUsers());
    }

    @RequestMapping(value = "/createExcelReport", method = RequestMethod.GET)
    public void createExcelReport(@ModelAttribute ReportRequest reportRequest,
                                  HttpServletRequest request, HttpServletResponse response) {
        reportRequest.setUsers(SecurityContextHolder.getContext().getAuthentication().getName());
        reportRequest.setTypes(new ArrayList<Integer>());
        byte[] output = getFileEntity(reportRequest, "excel").getBody();
        fileDownloader.downloadFile(request, response, output,
                contentType, contentDisposition);
        LOGGER.info("Downloading excel report for " + reportRequest.getUsers());
    }

    private void setDate(ModelAndView model) {
        df = new SimpleDateFormat("yyyy/MM/dd");
        minDate = restTemplate.getForObject(restUrl + "report/mindate", Date.class);
        maxDate = restTemplate.getForObject(restUrl + "report/maxdate", Date.class);
        model.addObject(START_DATE, df.format(minDate));
        model.addObject(END_DATE, df.format(maxDate));
    }

    private void setLoginAndTypes(ModelAndView model) {
        List<String> logins = Arrays.asList(restTemplate.getForObject(restUrl + "users/logins", String[].class));
        List<MeterType> meterTypes = Arrays.asList(restTemplate.getForObject(restUrl + "metertypes/", MeterType[].class));
        model.addObject(METER_TYPES, meterTypes);
        model.addObject(LOGINS, logins);
    }

    private ResponseEntity<byte[]> getFileEntity(ReportRequest reportRequest, String fileType) {
        ResponseEntity<byte[]> postResponseEntity = restTemplate.exchange(restUrl + "report/" + fileType, HttpMethod.POST,
                new HttpEntity<ReportRequest>(reportRequest), byte[].class);
        contentType = postResponseEntity.getHeaders().get("Content-Type").get(0);
        contentDisposition = postResponseEntity.getHeaders().get("Content-Disposition").get(0);
        return postResponseEntity;
    }
}
