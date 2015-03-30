/**
 * Copyright (c) 2015, SoftServe and/or its affiliates. All rights reserved.
 */
package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_restful.domain.Auth;
import com.softserveinc.if052_restful.domain.User;
import java.util.List;

/**
 * This is interface of user mapper
 *
 * @author Namisnyk Valentyn
 * @author Danylo Tiahun
 * @version 1.0
 */
public interface UserMapper {

    /**
     * Create new user
     * 
     * @param user
     */
    public void insertUser(User user);

    /**
     * Get exists user by id 
     * 
     * @param userId
     * @return
     */
    public User getUserById(int userId);

    /**
     * Get all users
     *
     * @return List of user
     */
    public List < User > getAllUsers();

    /**
     * Get user by login 
     *
     * @return user
     */
    public User getUserByLogin(String login);

    /**
     * Update exists user
     * 
     * @param user
     */
    public void updateUser(User user);

    /**
     * Delete user by id
     *
     * @param UserId
     */
    public void deleteUser(int UserId);

    public List<User> getAllReportUsers();

    public User getReportUserByLogin(String login);

    public List<String> getLogins();

    public Auth getAuth(String login);

}
