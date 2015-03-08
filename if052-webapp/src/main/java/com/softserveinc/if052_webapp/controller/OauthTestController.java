package com.softserveinc.if052_webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Hata on 08.03.2015.
 */

@Controller
public class OauthTestController {

    @RequestMapping("/profile")
    public String getProfilePage(){
        return "profile";
    }

    @RequestMapping("/login")
    public String getLogInPage(){
        return "login";
    }
}
