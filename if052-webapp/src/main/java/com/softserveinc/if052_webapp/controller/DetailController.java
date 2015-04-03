package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_core.domain.AuthInterface;
import com.softserveinc.if052_core.domain.Indicator;
import com.softserveinc.if052_core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestOperations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by student on 3/30/2015.
 */
@Controller
public class DetailController {

    public static final int COUNT = 10;
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String MIDDLE_NAME = "middlename";
    public static final String LAST_INDICATORS = "lastIndicators";
    public static final String LIMIT = "limit";

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("passwordTemplate")
    private RestOperations restTemplate;

    @Autowired
    private AuthInterface authBean;

    @RequestMapping(value = "/")
    public String getIndexPage( ModelMap model){
        User user = restTemplate.getForObject(restUrl + "users/" + authBean.getUserId(), User.class);

        model.addAttribute(NAME, user.getName());
        model.addAttribute(SURNAME, user.getSurname());
        model.addAttribute(MIDDLE_NAME, user.getMiddleName());

        Indicator[] arrOfIndicators = restTemplate.getForObject(restUrl + "indicators/list/byuser/" + authBean.getUserId() + ";number=" + COUNT, Indicator[].class);

        List<Indicator> indicators = Arrays.asList(arrOfIndicators);

        model.addAttribute(LAST_INDICATORS, indicators);
        model.addAttribute(LIMIT, COUNT);

        return "index";
    }
}
