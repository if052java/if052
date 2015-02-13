package com.softserveinc.if052_webapp.controller;

/**
 * Created by student on 2/12/2015.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignInController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("signin")
    public ModelAndView getLoginForm(
            @RequestParam(required = false) String authfailed, String logout) {
        String message = "";
        if (authfailed != null) {
            message = "Invalid username of password, try again !";
        } else if (logout != null) {
            message = "Logged Out successfully, login again to continue !";
        }
        return new ModelAndView("signin", "message", message);
    }

    @RequestMapping("profile")
    public String geProfilePage() {
        return "profile";
    }
}