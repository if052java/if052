package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.UserRole;
import com.softserveinc.if052_webapp.service.RestServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import java.net.URI;

/**
 * Created by Hata on 08.03.2015.
 */

@Controller
public class OauthTestController {

    @Autowired
    RestServiceTest restServiceTest;

    @RequestMapping("/rest/redirect")
    public String getRedirected(Model model){
        String resource = restServiceTest.getResource();
        model.addAttribute("resource", "Redirected page; received " + resource);
        return "resource";
    }

    @RequestMapping("/getJersey")
    public String getJersey(Model model){
        String resource = restServiceTest.getJersey();
        model.addAttribute("resource", "Received " + resource);
        //return "getResource";
        return "resource";
    }

    @RequestMapping("/getResource")
    public String getResource(Model model){
        String resource = restServiceTest.getResource();
        model.addAttribute("resource", "Received " + resource);
        //return "getResource";
        return "resource";
    }

    @RequestMapping("/profile")
    public String getProfilePage(Model model){
        UserRole userRole = restServiceTest.getUserRole();
        //UserRole userRole = restServiceTest.getUserRole1();
        model.addAttribute("role", userRole);
        return "profile";
    }

    @RequestMapping("/login")
    public String getLogInPage(){
        return "login";
    }
}
