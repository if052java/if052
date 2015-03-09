package com.softserveinc.if052_webapp.controller;

/**
 * Created by nazar on 1/28/15.
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String getIndexPage(){
        return "index";
    }

}