package com.softserveinc.if052_restful;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by nazar on 3/15/15.
 */
@Controller
public class TestController {

    @RequestMapping("/oauth/test")
    public String test(){
        return "oauth test";
    }
}


