package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.Address;
import com.softserveinc.if052_restful.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml",
                                 "classpath:h2-datasource.xml"})
@ActiveProfiles(profiles = "h2")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(1);
        
        List <Address> addresses;
        addresses = user.getAddresses();
        Assert.assertNotNull(user);

        System.out.println(user);

        for(Address address : addresses) {
            System.out.println(address);
        }
    }

    @Test
    public void testGetUserByLogin() {
        User user = userService.getUserByLogin("LOGIN111");

        Assert.assertNotNull(user);
        System.out.println("!!!!!!!!!!!!!!!!");
        System.out.println(user);
    }

    @Test
    public void testGetAllUser() {
        List < User > users = userService.getAllUsers();
        Assert.assertNotNull(users);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {
        long generateOrigin = System.currentTimeMillis();

        User user = new User();
        user.setName("name" + generateOrigin);
        user.setSurname("surname" + generateOrigin);
        user.setMiddleName("middle_name" + generateOrigin);
        user.setLogin("login" + generateOrigin);
        user.setPassword("password" + generateOrigin);

        userService.insertUser(user);

        Assert.assertTrue(user.getUserId()!= 0);

        User createdUser = userService.getUserById(user.getUserId());

        Assert.assertNotNull(createdUser);
        Assert.assertEquals(user.getName(), createdUser.getName());
        Assert.assertEquals(user.getSurname(), createdUser.getSurname());
        Assert.assertEquals(user.getMiddleName(), createdUser.getMiddleName());
        Assert.assertEquals(user.getLogin(), createdUser.getLogin());
        Assert.assertEquals(user.getPassword(), createdUser.getPassword());
    }

    @Test
    public void testUpdateUser() {
        int lastElement = userService.getAllUsers().get(userService.getAllUsers().size() - 1).getUserId();
        User user = userService.getUserById(lastElement);
        user.setName("Valentyn");
        user.setSurname("Namisnyk");
        user.setMiddleName("YA");
        user.setLogin("55555");
        user.setPassword("1111");

        userService.updateUser(user);

        User updatedUser = userService.getUserById(lastElement);
        
        Assert.assertEquals(user.getName(), updatedUser.getName());
        Assert.assertEquals(user.getSurname(), updatedUser.getSurname());
        Assert.assertEquals(user.getMiddleName(), updatedUser.getMiddleName());
        Assert.assertEquals(user.getLogin(), updatedUser.getLogin());
        Assert.assertEquals(user.getPassword(), updatedUser.getPassword());
    }

    @Test
    public void testDeleteUser() {
        int lastElement = userService.getAllUsers().get(userService.getAllUsers().size() - 1).getUserId();

        User user = userService.getUserById(lastElement);
        userService.deleteUser(user.getUserId());

        User deletedUser = userService.getUserById(lastElement);
        Assert.assertNull(deletedUser);
    }
}