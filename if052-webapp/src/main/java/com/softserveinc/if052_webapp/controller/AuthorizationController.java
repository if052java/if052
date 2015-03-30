package com.softserveinc.if052_webapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserveinc.if052_webapp.domain.Auth;
import com.softserveinc.if052_webapp.domain.AuthInterface;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nazar on 3/29/15.
 */
@Controller
public class AuthorizationController {
    private static Logger LOGGER = Logger.getLogger(AuthorizationController.class);

    @Autowired
    @Qualifier("credentialsTemplate")
    private RestOperations credentialsTemplate;

    @Autowired
    @Qualifier("passwordTemplate")
    private OAuth2RestOperations passwordTemplate;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AuthInterface authBean;


    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginDo(@ModelAttribute Auth auth, ModelMap modelMap, HttpServletRequest request) {
        //Auth receivedAuth = restTemplate.postForObject(restUrl + "auth/checkCredentials", auth, Auth.class);

        ResponseEntity<String> responseEntity = credentialsTemplate.exchange(restUrl + "auth/checkCredentials",
                HttpMethod.POST, new HttpEntity<Auth>(auth), String.class);

        if (responseEntity.getStatusCode().value() == 202) {
            return "redirect:/login.jsp?authentication_error=true";
        }

        if (responseEntity.getStatusCode().value() == 200) {
            try {
                String responseBody = responseEntity.getBody();
                Auth receivedAuth = objectMapper.readValue(responseBody, Auth.class);

                Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority("ROLE_" + receivedAuth.getRole()));

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        receivedAuth.getUsername(), receivedAuth.getPassword(), authorities);
                token.setDetails(new WebAuthenticationDetails(request));

                LOGGER.debug("Logging in with " + token.getPrincipal().toString());
                SecurityContextHolder.getContext().setAuthentication(token);
                LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getAuthorities());

                OAuth2ProtectedResourceDetails resourceDetails = passwordTemplate.getResource();
                ResourceOwnerPasswordResourceDetails passwordResource = (ResourceOwnerPasswordResourceDetails) resourceDetails;
                passwordResource.setUsername(receivedAuth.getUsername());
                passwordResource.setPassword(receivedAuth.getPassword());

                authBean.setUserId(receivedAuth.getUserId());
                authBean.setUsername(receivedAuth.getUsername());
                authBean.setRole(receivedAuth.getRole());

            } catch (IOException e) {
                LOGGER.warn(e.getMessage(), e);
            }
        }
        return "redirect:/";
    }

    @RequestMapping(value = "checkCredentials", method = RequestMethod.GET)
    public String checkCredentials(ModelMap modelMap) {
        Auth auth = new Auth(1, "theUser", "password");
        Auth receivedAuth = credentialsTemplate.postForObject(restUrl + "auth/checkCredentials", auth, Auth.class);
        modelMap.addAttribute("auth", receivedAuth);
        return "JspForTest";
    }

    @RequestMapping("signin")
    public String autologin(HttpServletRequest request) {

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "password");
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken("someuser", "password", authorities);
        token.setDetails(new WebAuthenticationDetails(request));
        //Authentication authentication = new
        //token.setAuthenticated(true);
        LOGGER.debug("Logging in with " + token.getPrincipal().toString());
        SecurityContextHolder.getContext().setAuthentication(token);
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getPrincipal() + "!");
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().getAuthorities() + "!");

        return "redirect:/";
    }
}
