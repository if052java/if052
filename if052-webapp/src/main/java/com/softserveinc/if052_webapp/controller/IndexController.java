package com.softserveinc.if052_webapp.controller;

/**
 * Created by nazar on 1/28/15.
 */
import com.softserveinc.if052_webapp.HelloWorld.Greeting;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class IndexController {

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld(){
        return "Hello World!";
    }

    @RequestMapping("/greeting")
    @ResponseBody
    public String greeting(@RequestParam(value = "name",
            required = false, defaultValue = "World") String name){
        RestTemplate restTemplate = new RestTemplate();
        Greeting greeting = restTemplate.getForObject("http://localhost:8080/restful/greeting/"+name, Greeting.class);
        return greeting.getContent();
    }
}
