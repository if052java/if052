package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetUserById() {
        User user = userService.getUserById(1);
        Assert.assertNotNull(user);
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
        user.setMiddle_name("middle_name" + generateOrigin);
        user.setLogin("login" + generateOrigin);
        user.setPassword("password" + generateOrigin);
        user.setTariff( generateOrigin / 1000000000 );

        userService.insertUser(user);

        System.out.println(user);
        Assert.assertTrue(user.getId()!= 0);

        User createdUser = userService.getUserById(user.getId());

        System.out.println(createdUser);

        Assert.assertNotNull(createdUser);
        Assert.assertEquals(user.getName(), createdUser.getName());
        Assert.assertEquals(user.getSurname(), createdUser.getSurname());
        Assert.assertEquals(user.getMiddle_name(), createdUser.getMiddle_name());
        Assert.assertEquals(user.getLogin(), createdUser.getLogin());
        Assert.assertEquals(user.getPassword(), createdUser.getPassword());
        Assert.assertEquals(user.getTariff(), createdUser.getTariff(), 0.0001);
    }

    @Test
    public void testUpdateUser()
    {
        int lastElement = userService.getAllUsers().get(userService.getAllUsers().size() - 1).getId();
        User user = userService.getUserById(lastElement);
        user.setName("Valentyn");
        user.setSurname("Namisnyk");
        user.setMiddle_name("YA");
        user.setLogin("55555");
        user.setPassword("1111");
        user.setTariff(50);

        userService.updateUser(user);

        User updatedUser = userService.getUserById(lastElement);
        
        Assert.assertEquals(user.getName(), updatedUser.getName());
        Assert.assertEquals(user.getSurname(), updatedUser.getSurname());
        Assert.assertEquals(user.getMiddle_name(), updatedUser.getMiddle_name());
        Assert.assertEquals(user.getLogin(), updatedUser.getLogin());
        Assert.assertEquals(user.getPassword(), updatedUser.getPassword());
        Assert.assertEquals(user.getTariff(), updatedUser.getTariff(), 0.1);
    }

    @Test
    public void testDeleteUser()
    {
        int lastElement = userService.getAllUsers().get(userService.getAllUsers().size() - 1).getId();

        User user = userService.getUserById(lastElement);
        userService.deleteUser(user.getId());

        User deletedUser = userService.getUserById(lastElement);
        Assert.assertNull(deletedUser);
    }
}
