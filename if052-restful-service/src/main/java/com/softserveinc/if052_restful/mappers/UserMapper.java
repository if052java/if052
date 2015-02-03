package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_restful.domain.User;

import java.util.List;


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

}
