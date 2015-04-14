package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Auth;
import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_restful.mappers.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by student on 3/30/2015.
 */
@Service
public class AuthService {
    @Autowired
    private UserMapper userMapper;

    public Auth getAuthByLogin(String login) {
        User user = userMapper.getAuthByLogin(login);

        if (user == null) return null;

        Auth auth = new Auth();
        auth.setUserId(Integer.toString(user.getUserId()));
        auth.setUsername(user.getLogin());
        auth.setPassword(user.getPassword());
        auth.setRole(user.getRole());
        return auth;
    }
    public Auth getAuth(String stringUserId) {
        Integer userId = Integer.parseInt(stringUserId);
        User user = userMapper.getAuth(userId);

        if (user == null) return null;

        Auth auth = new Auth();
        auth.setUserId(Integer.toString(user.getUserId()));
        auth.setUsername(user.getLogin());
        auth.setPassword(user.getPassword());
        auth.setRole(user.getRole());
        return auth;
    }
}
