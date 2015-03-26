package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Address;
import com.softserveinc.if052_webapp.domain.User;
import com.softserveinc.if052_webapp.domain.UserRole;
import ognl.enhance.ContextClassLoader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nazar on 3/25/15.
 */
@Controller
//@Scope("request")
public class LoginController {

    @Autowired
    @Qualifier("restUrl")
    String restUrl;

    @Autowired
    @Qualifier("oAuthRestTemplatePassword")
    RestOperations restTemplate;
//
//    @Autowired
//    OAuth2ClientContext oAuth2ClientContext;


    private static Logger logger = Logger.getLogger(LoginController.class);

    @RequestMapping("/signin")
    public String autologin(HttpServletRequest request){

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "password");
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("CLIENT"));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("someuser", "password", authorities);
        token.setDetails(new WebAuthenticationDetails(request));
        //Authentication authentication = new
        //token.setAuthenticated(true);
        logger.debug("Logging in with " + token.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(token);
        logger.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal() + "!");
        logger.debug(SecurityContextHolder.getContext().getAuthentication().getAuthorities() + "!");

        return "redirect:/";
    }

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping("restin")
    public String restIn(Model model){


        OAuth2RestTemplate oAuth2RestTemplate = (OAuth2RestTemplate) restTemplate;
        OAuth2ProtectedResourceDetails resourceDetails = oAuth2RestTemplate.getResource();
        ResourceOwnerPasswordResourceDetails passwordResource = (ResourceOwnerPasswordResourceDetails) resourceDetails;
        //ResourceOwnerPasswordResourceDetails passwordResource = (ResourceOwnerPasswordResourceDetails)((OAuth2RestTemplate) restTemplate).getResource();
        passwordResource.setUsername("marissa");
        passwordResource.setPassword("koala");



        String resource = restTemplate.getForObject(restUrl + "resource", String.class);
        model.addAttribute("resource", "Password grant; received " + resource+" "+ restTemplate.getClass().getName());
        return "resource";
    }

    @Autowired
    @Qualifier("credentialsTemplate")
    RestOperations credentialsTemplate;

    @RequestMapping("check")
    public String check(Model model){
        String resource = credentialsTemplate.getForObject(restUrl + "rest/check", String.class);
        model.addAttribute("resource", "received " + resource);
        return "resource";
    }

    @RequestMapping("check2")
    public String check2(Model model){
        String resource = credentialsTemplate.postForObject(restUrl + "rest/check2", "check message", String.class);
        model.addAttribute("resource", "received " + resource);
        return "resource";
    }

    @RequestMapping(value = "createAddress1", method = RequestMethod.GET)
    public String createAddress1(Model model){
        Address address = new Address();
        //User user = restTemplate.getForObject(restUrl+ "rest/createAddress", User.class);
        //address.setUser(user);

        credentialsTemplate.postForObject(restUrl+ "rest/createAddress1", address, Address.class);

        model.addAttribute("resource", "address");
        return "resource";
    }

    @RequestMapping(value = "createAddress", method = RequestMethod.GET)
    public String createAddress(Model model){
        Address address = new Address();
        address.setAddressId(1);
        //User user = restTemplate.getForObject(restUrl+ "rest/createAddress", User.class);
        //address.setUser(user);
        Address receivedAddress;
        receivedAddress = credentialsTemplate.postForObject(restUrl+ "rest/createAddress", address, Address.class);

        model.addAttribute("resource", "address " + receivedAddress.getAddressId() + " city " + receivedAddress.getCity());
        return "resource";
    }

    @RequestMapping(value = "userRole", method = RequestMethod.GET)
    public String userRole(Model model){
        UserRole userRole = new UserRole("USER");

        UserRole receivedUserRole;
        receivedUserRole = credentialsTemplate.postForObject(restUrl+ "rest/userRole", userRole, UserRole.class);

        model.addAttribute("resource", "user " + receivedUserRole.getRoleName() + " name " + receivedUserRole.getUser());
        return "resource";
    }

}
