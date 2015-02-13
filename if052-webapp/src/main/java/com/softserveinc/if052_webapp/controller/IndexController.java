package com.softserveinc.if052_webapp.controller;

/**
 * Created by nazar on 1/28/15.
 */
import com.softserveinc.if052_webapp.HelloWorld.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld(){
        return "Hello World!";
    }

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value = "name",
            required = false, defaultValue = "World") String name,
                           ModelMap model){
        RestTemplate restTemplate = new RestTemplate();
        Greeting greeting = restTemplate.getForObject(restUrl + "greeting/"+name, Greeting.class);

        model.addAttribute("message", greeting.getContent());
        return "hello";
    }
}