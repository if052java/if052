package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:context.xml"})
public class AuthServiceTest {
    @Autowired
    private AuthService authService;

    @Test
    public void testGetAuth() {
        Auth auth = authService.getAuth("LOGIN111");

        Assert.assertNotNull(auth);
        System.out.println("!--------------!");
        System.out.println(auth.getUsername() + " " + auth.getRole());
    }
}