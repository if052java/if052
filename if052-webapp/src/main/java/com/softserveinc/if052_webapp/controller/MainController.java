package com.softserveinc.if052_webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Java on 27.02.2015.
 */
@Controller
public class MainController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }
}
