package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     *
     *
     * @param userId
     * @return
     */
    public User getUserById(int userId)  {
        return userMapper.getUserById(userId);
    }

    public List < User > getAllUsers() {
        return userMapper.getAllUsers();
    }

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(int userId) {
        userMapper.deleteUser(userId);
    }
}
