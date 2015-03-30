package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Auth;
import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by student on 3/30/2015.
 */
@Service
public class AuthService {
    @Autowired
    private UserMapper userMapper;

    public Auth getAuth(String login) {
        User user = userMapper.getAuth(login);
        Auth auth = new Auth();
        auth.setUserId(user.getUserId());
        auth.setUsername(user.getLogin());
        auth.setPassword(user.getPassword());
        auth.setRole(user.getRole());
        return auth;
    }
}
