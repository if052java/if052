package com.softserveinc.if052_webapp.controller;

import com.softserveinc.if052_webapp.domain.Auth;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestOperations;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nazar on 3/29/15.
 */
@Controller
public class AuthorizationController {
    private static Logger logger = Logger.getLogger(AuthorizationController.class);

    @Autowired
    @Qualifier("credentialsTemplate")
    private RestOperations restTemplate;

    @Autowired
    @Qualifier("restUrl")
    private String restUrl;

    @RequestMapping(value = "checkCredentials", method = RequestMethod.GET)
    public String checkCredentials(ModelMap modelMap){
        Auth auth = new Auth(1, "theUser", "password");
        Auth receivedAuth = restTemplate.postForObject(restUrl + "auth/checkCredentials", auth, Auth.class);
        modelMap.addAttribute("auth", receivedAuth);
        return "JspForTest";
    }

    @RequestMapping("signin")
    public String autologin(HttpServletRequest request){

//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("user", "password");
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

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
}
