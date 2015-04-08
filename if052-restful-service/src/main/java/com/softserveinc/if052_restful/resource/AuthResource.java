package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.Auth;
import com.softserveinc.if052_restful.service.AuthService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
* Created by nazar on 3/30/15.
*/
@RestController
@RequestMapping("/rest/auth")
public class AuthResource {

    @Autowired
    AuthService authService;

    private static Logger LOGGER = Logger.getLogger(AuthResource.class);

    @RequestMapping(value = "/checkCredentials", method = RequestMethod.POST, produces = "application/json")
    public Auth checkCredentials(@RequestBody Auth param, HttpServletResponse response){
        Auth auth = null;
        auth = authService.getAuthByLogin(param.getUsername());
        if (auth == null) {
            LOGGER.debug("auth = null");
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
            return auth;
        }
        if (!auth.getUsername().equals(param.getUsername()) ||
                (!auth.getPassword().equals(param.getPassword())) ){
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        }
        return auth;
    }
}
