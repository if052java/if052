package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Created by valentyn on 2/11/15.
 */
@Controller
public class UserController {
    @RequestMapping("user/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "user/addUser", method = RequestMethod.POST)
    public String addUser(User user) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://localhost:8080/restful/new", user, User.class);
//        model.addAttribute("name");
//        String str =model.get();
//        User user;
//        restTemplate.postForObject("http://localhost:8080/new", user, User.class)
        return "registrationDetails";
    }
}
