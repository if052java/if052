package com.softserveinc.if052_webapp.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_webapp.domain.MeterType;
import com.softserveinc.if052_webapp.utils.FileDownloader;
import com.softserveinc.if052_webapp.utils.ReportRequest;
import org.apache.log4j.Logger;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


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
    private final String LOGINS = "logins";

    private static Logger LOGGER = Logger.getLogger(XmlReportController.class);

    @RequestMapping("/xmlreport")
    public ModelAndView getXmlReportPage() {
        ModelAndView model = new ModelAndView("xmlReport");
        Date minDate = restTemplate.getForObject(restUrl + "report/mindate", Date.class);
        String date = new SimpleDateFormat("yyyy/MM/dd").format(minDate);
        List<String> logins = Arrays.asList(restTemplate.getForObject(restUrl + "users/logins", String[].class));
        List<MeterType> meterTypes = Arrays.asList(restTemplate.getForObject(restUrl + "metertypes/", MeterType[].class));
        model.addObject(START_DATE, date);
        model.addObject(METER_TYPES, meterTypes);
        model.addObject(LOGINS, logins);
        return model;
    }

    @RequestMapping(value = "/createXmlReport", method = RequestMethod.GET)
    public void createReportRequest(@ModelAttribute ReportRequest reportRequest,
                                    HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResponseEntity<String> postResponseEntity = restTemplate.exchange(restUrl + "report/", HttpMethod.POST,
                new HttpEntity<ReportRequest>(reportRequest), String.class);
        try {
            String uri = postResponseEntity.getHeaders().get("Location").get(0);
            ResponseEntity<String> responseEntity2 = restTemplate.exchange(uri, HttpMethod.GET,
                    null, String.class);
            fileDownloader.downloadFile(request, response, responseEntity2.getBody());
        } catch (NullPointerException e) {
            LOGGER.warn(e.getMessage(), e);
        }
    }



}