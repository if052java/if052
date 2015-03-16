package com.softserveinc.if052_webapp.service;

import com.softserveinc.if052_webapp.domain.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestOperations;

import java.net.URI;

/**
 * Created by Hata on 10.03.2015.
 */
public class RestServiceTest {

    @Autowired
    String restUrl;

    private RestOperations restTemplate;

    public RestOperations getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestOperations restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserRole getUserRole(){
        UserRole userRole = restTemplate.getForObject(URI.create(restUrl+"rest/getRoles/1"), UserRole.class);
        return userRole;
    }
    public UserRole getUserRole1(){
        return new UserRole("USER");
    }
}
