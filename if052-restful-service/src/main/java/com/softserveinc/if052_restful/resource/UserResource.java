package com.softserveinc.if052_restful.resource;

import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_restful.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by valentyn on 2/11/15.
 */
@RequestMapping("/rest/users")
@RestController
public class UserResource {
    @Autowired
    UserService userService;

    private static Logger LOGGER = Logger.getLogger(UserResource.class.getName());
    
//    @RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
//    public List<User> getAddresses() {
//        LOGGER.info("Searching for the whole collection of users.");
//        List<User> users = userService.getAllUsers();
//        LOGGER.info("The whole collection of users has been found.");
//        return users;
//    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public User getUser(HttpServletResponse response) {
        User user = userService.getCurrentUser();

        if (user == null){
            LOGGER.info("Requested user has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("User with requested id " + user.getUserId() + " has been found.");
        return user;
    }

    @RequestMapping(value = "login/{login}", method = RequestMethod.GET, produces = "application/json")
    public User getUser(@PathVariable("login") String login, HttpServletResponse response) {
        LOGGER.info("Searching for the user with login" + login);
        User user = userService.getUserByLogin(login);

        if (user == null ) {
            LOGGER.info("User with requested login " + login + " has not been found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        LOGGER.info("User with requested login " + login + " has been found.");

        return user;
    }

    @RequestMapping(value = "/logins", method = RequestMethod.GET, produces = "application/json")
    public List<String> getLogins() {
        LOGGER.info("Searching for the whole collection of users login");
        List<String> logins = userService.getLogins();
        LOGGER.info("The whole collection of users login has been found.");
        return logins;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public User createUser(
        @Valid
        @RequestBody
        User user, HttpServletResponse response ){

        try {
            LOGGER.info("Adding a new user.");
            userService.insertUser(user);
            LOGGER.info("User has been successfully added with id " + user.getUserId() + ".");
            response.setStatus(HttpServletResponse.SC_CREATED);
            return user;
        }
        catch (Exception e) {
            LOGGER.info("Internal error");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
    }

    @RequestMapping(value="{userId}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable("userId") int userId, @Valid @RequestBody User user,
                           HttpServletResponse response){
        if (userService.getUserById(userId) == null) {
            LOGGER.info("User with requested id " + userId + " is not found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        try {
            LOGGER.info("Updating a user with id " + userId + ".");
            userService.updateUser(user);
            LOGGER.info("User with id " + userId + " has been successfully updated.");
            return user;

        }
        catch (Exception e) {
            LOGGER.info("Internal error");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.DELETE, produces = "application/json")
    public void deleteUser(
        @PathVariable("userId") int  userId,
        HttpServletResponse response
    ){
        if(userService.getUserById(userId) == null){
            LOGGER.info("User with requested id " + userId + " is not found.");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else
        try {
            LOGGER.info("Deleting a user with id " + userId + ".");
            userService.deleteUser(userId);
            LOGGER.info("User with id " + userId + " has been successfully deleted.");
        }
        catch (Exception e){
            LOGGER.info("Internal error");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
