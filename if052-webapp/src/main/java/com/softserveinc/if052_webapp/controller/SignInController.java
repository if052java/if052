package com.softserveinc.if052_webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by student on 2/11/2015.
 */
@Controller
@RequestMapping("/signin")
public class SignInController {

    @RequestMapping(method = RequestMethod.GET)
    public String signIn(ModelMap modelMap){
        return "signin";
    }
}
