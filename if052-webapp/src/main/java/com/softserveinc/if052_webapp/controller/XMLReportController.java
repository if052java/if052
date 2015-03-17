package com.softserveinc.if052_webapp.controller;

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
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class XMLReportController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("/xmlreport")
    public String getXmlReportPage(ModelMap model) {
        Date minDate = restTemplate.getForObject(restUrl + "report/mindate", Date.class);
        String date = new SimpleDateFormat("yyyy/MM/dd").format(minDate);
        model.addAttribute("startDate", date);
        return "createXMLReport";
    }

    @RequestMapping(value = "/createXmlReport", method = RequestMethod.GET)
    public void createReportRequest(@ModelAttribute ReportRequest reportRequest, ModelMap model,
                                      HttpServletRequest request, HttpServletResponse response) throws IOException{
        ResponseEntity<String> postResponseEntity = restTemplate.exchange(restUrl + "report", HttpMethod.POST,
                new HttpEntity<ReportRequest>(reportRequest), String.class);
        System.out.println(postResponseEntity.getHeaders().get("Location"));
        String uri = postResponseEntity.getHeaders().get("Location").get(0);
        System.out.println(uri);
        ResponseEntity<String> responseEntity2 = restTemplate.exchange(uri, HttpMethod.GET,
                null, String.class);
        System.out.println(responseEntity2.getBody());
        model.addAttribute("xml", responseEntity2.getBody());

        fileDownloader.downloadFile(request, response, responseEntity2.getBody());


    }



}
