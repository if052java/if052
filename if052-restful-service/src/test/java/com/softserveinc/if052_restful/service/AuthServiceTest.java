package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_core.domain.Auth;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Test
    public void testGetAuthByLogin() {
        Auth auth = authService.getAuthByLogin("LOGIN111");

        Assert.assertNotNull(auth);
        System.out.print("testGetAuthByLogin: ");
        System.out.println(auth.getUserId() + " " + auth.getUsername() + " " + auth.getRole());
    }

    @Test
    public void testGetAuth() {
        Auth auth = authService.getAuth("2");

        Assert.assertNotNull(auth);
        System.out.print("testGetAuth: ");
        System.out.println(auth.getUserId() + " " + auth.getUsername() + " " + auth.getRole());
    }
}