package com.softserveinc.if052_webapp.controller;


import com.softserveinc.if052_core.domain.Auth;
import com.softserveinc.if052_core.domain.MeterType;
import com.softserveinc.if052_core.domain.Report;
import com.softserveinc.if052_core.domain.ReportRequest;
import com.softserveinc.if052_webapp.utils.FileDownloader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Danylo Tiahun on 24.03.2015.
 */

@Controller
public class XmlReportController {

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private Auth auth;

    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    private final String START_DATE = "startDate";
    private final String END_DATE = "endDate";
    private final String METER_TYPES = "meterTypes";
    private final String LOGINS = "logins";

    private static Logger LOGGER = Logger.getLogger(XmlReportController.class);

    @RequestMapping("/xmlreport")
    public ModelAndView getXmlReportPage() {
        ModelAndView model = new ModelAndView("xmlReport");
        Date minDate = restTemplate.getForObject(restUrl + "report/mindate", Date.class);
        Date maxDate = restTemplate.getForObject(restUrl + "report/maxdate", Date.class);
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        List<String> logins = Arrays.asList(restTemplate.getForObject(restUrl + "users/logins", String[].class));
        List<MeterType> meterTypes = Arrays.asList(restTemplate.getForObject(restUrl + "metertypes/", MeterType[].class));
        model.addObject(START_DATE, df.format(minDate));
        model.addObject(END_DATE, df.format(maxDate));
        model.addObject(METER_TYPES, meterTypes);
        model.addObject(LOGINS, logins);
        return model;
    }

    @RequestMapping(value = "/createXmlReport", method = RequestMethod.GET)
    public void createXmlReport(@ModelAttribute ReportRequest reportRequest,
                                HttpServletRequest request, HttpServletResponse response) {
        ResponseEntity<byte[]> postResponseEntity = restTemplate.exchange(restUrl + "report/xml", HttpMethod.POST,
                new HttpEntity<ReportRequest>(reportRequest), byte[].class);
        String contentType = postResponseEntity.getHeaders().get("Content-Type").get(0);
        String contentDisposition = postResponseEntity.getHeaders().get("Content-Disposition").get(0);
        fileDownloader.downloadFile(request, response, postResponseEntity.getBody(),
                contentType, contentDisposition);
    }

    @RequestMapping(value = "/createExcelReport", method = RequestMethod.GET)
    public void createExcelReport(@ModelAttribute ReportRequest reportRequest,
                                  HttpServletRequest request, HttpServletResponse response) {
        reportRequest.setUsers(auth.getUsername());
        reportRequest.setTypes(new ArrayList<Integer>());
        ResponseEntity<byte[]> postResponseEntity = restTemplate.exchange(restUrl + "report/excel", HttpMethod.POST,
                new HttpEntity<ReportRequest>(reportRequest), byte[].class);
        String contentType = postResponseEntity.getHeaders().get("Content-Type").get(0);
        String contentDisposition = postResponseEntity.getHeaders().get("Content-Disposition").get(0);
        fileDownloader.downloadFile(request, response, postResponseEntity.getBody(),
                contentType, contentDisposition);
    }

}