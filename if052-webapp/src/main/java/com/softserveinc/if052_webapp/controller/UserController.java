package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;

/**
 * Created by valentyn on 2/11/15.
 */
@Controller
public class UserController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("signup")
    public String registration(ModelMap model){
        model.addAttribute("restUrl", restUrl);
        return "registration";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String createAddress(@ModelAttribute User user){
        RestTemplate restTemplate = new RestTemplate();

        user = restTemplate.postForObject(restUrl + "users/", user, User.class);

        //URI uri = restTemplate.postForLocation(restUrl + "users/", user, User.class);
        //User createdUser = restTemplate.getForObject(restUrl + "users/login/" + user.getLogin(), User.class);
        
        return "redirect:/addresses?userId=" + user.getUserId();
    }
}
