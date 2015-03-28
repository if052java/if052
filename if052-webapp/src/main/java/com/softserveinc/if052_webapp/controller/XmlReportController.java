package com.softserveinc.if052_webapp.controller;


import com.softserveinc.if052_webapp.domain.MeterType;
import com.softserveinc.if052_webapp.utils.FileDownloader;
import com.softserveinc.if052_webapp.utils.ReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


/**
 * Created by Danylo Tiahun on 24.03.2015.
 */

@Controller
public class XmlReportController {

    @Autowired
    @Qualifier("credentialsTemplate")
    private RestOperations restTemplate;

    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    private final String START_DATE = "startDate";
    private final String METER_TYPES = "meterTypes";

    @RequestMapping("/xmlreport")
    public String getXmlReportPage(ModelMap model) {
        Date minDate = restTemplate.getForObject(restUrl + "report/mindate", Date.class);
        String date = new SimpleDateFormat("yyyy/MM/dd").format(minDate);
        model.addAttribute(START_DATE, date);
        model.addAttribute(METER_TYPES, Arrays.asList(restTemplate.getForObject(restUrl + "metertypes/", MeterType[].class)));
        return "xmlReport";
    }

    @RequestMapping(value = "/createXmlReport", method = RequestMethod.GET)
    public void createReportRequest(@ModelAttribute ReportRequest reportRequest,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseEntity<String> postResponseEntity = restTemplate.exchange(restUrl + "report", HttpMethod.POST,
                new HttpEntity<ReportRequest>(reportRequest), String.class);
        String uri = postResponseEntity.getHeaders().get("Location").get(0);
        ResponseEntity<String> responseEntity2 = restTemplate.exchange(uri, HttpMethod.GET,
                null, String.class);
        fileDownloader.downloadFile(request, response, responseEntity2.getBody());
    }



}