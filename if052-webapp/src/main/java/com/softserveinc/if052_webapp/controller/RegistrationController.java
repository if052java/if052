package com.softserveinc.if052_webapp.controller;

import org.apache.log4j.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_core.domain.ValidationError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        user = restTemplate.postForObject(restUrl + "users/create", user, User.class);
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setErrorHandler(new ResponseErrorHandler() {
                @Override
                public boolean hasError(ClientHttpResponse response) throws IOException {
                    return false;
                }

                @Override
                public void handleError(ClientHttpResponse response) throws IOException {

                }
            });
            try {
                ResponseEntity<String> userResponseEntity = restTemplate.postForEntity(restUrl
                    + "users/", user, String.class);

                if(userResponseEntity.getStatusCode().value() == 400) {
                    String errorBody = userResponseEntity.getBody();

                    ValidationError validationError = objectMapper.readValue(
                        errorBody, ValidationError.class);
                    model.addAttribute(VALIDATIONERROR, validationError.getFieldErrors());
                }

            } catch (IOException e1) {
                LOGGER.warn("IO exception. Reason: get validation error from JSON");
            }
        return "redirect:/";
    }
}
