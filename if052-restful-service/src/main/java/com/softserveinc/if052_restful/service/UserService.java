/**
 * Copyright (c) 2015, SoftServe and/or its affiliates. All rights reserved.
 */
package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.User;
import com.softserveinc.if052_restful.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for work with User.
 * @see com.softserveinc.if052_core.domain.domain.User
 *
 * @version 1.0
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * Get exists user by id
     *
     * @param userId - Identification of user
     * @return User
     */
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    /**
     * Get exists user by id
     *
     * @param login - Identification of user
     * @return User
     */
    public User getUserByLogin(String login) {
        
        return userMapper.getUserByLogin(login);
    }
    /**
     * Get all users
     * 
     * @return List of users
     */
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    /**
     * Create new user
     *  
     * @param user - New user
     */
    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    /**
     * Update exists user
     *  
     * @param user - Exists user
     */
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * Delete exists user
     *  
     * @param userId - Identification of user
     */
    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }

    public List<User> getAllReportUsers() {
        return userMapper.getAllReportUsers();
    }

    public User getReportUserByLogin(String login) {
        return userMapper.getReportUserByLogin(login);
    }

    public List<String> getLogins() {
        return userMapper.getLogins();
    }
}
