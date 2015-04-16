package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_core.domain.Address;
import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_core.domain.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by student on 3/30/2015.
 */
@Controller
public class RegistrationController {

    private static final String VALIDATIONERROR = "error";

    private static Logger LOGGER = Logger.getLogger(RegistrationController.class.getName());
    
    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    @Qualifier("credentialsTemplate")
    private RestOperations restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping("signup")
    public String registration(ModelMap model){

        model.addAttribute("restUrl", restUrl);
        return "registration";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user, ModelMap model){

        user.setName(user.getName().trim());
        user.setSurname(user.getSurname().trim());
        user.setMiddleName(user.getMiddleName().trim());
        user.setRole("USER");
        ResponseEntity<String> userResponseEntity = restTemplate.exchange(restUrl + "users/create",
            HttpMethod.POST, new HttpEntity<User>(user), String.class);


        if (userResponseEntity.getStatusCode().value() != 201) {
            try {
                ValidationError error = objectMapper.readValue(userResponseEntity.getBody(), ValidationError.class);

                if (error.getFieldErrors().size() > 0) {
                    model.addAttribute("fieldErrors", error.getFieldErrors());
                    return "validationError";
                }
            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }

        return "redirect:/";
    }
}
