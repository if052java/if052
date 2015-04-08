package com.softserveinc.if052_restful.config;

import com.softserveinc.if052_core.domain.Auth;
import com.softserveinc.if052_restful.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 3/30/2015.
 */
@Service
public class CustomDetailsService implements UserDetailsService {
        @Autowired
        AuthService authService;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Auth auth = authService.getAuth(username);
            if (auth != null) {
                System.out.println(auth.getUserId());
                List<GrantedAuthority> authorities =new ArrayList<GrantedAuthority>();
                authorities.add(new SimpleGrantedAuthority(auth.getRole()));
                System.out.println();
                return new User(
                        auth.getUserId(),
                        auth.getPassword(),
                        authorities);
            }
            throw new UsernameNotFoundException("User '" + username + "' not found.");
        }
    }

