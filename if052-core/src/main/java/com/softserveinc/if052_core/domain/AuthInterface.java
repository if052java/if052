package com.softserveinc.if052_core.domain;

/**
 * Created by student on 3/31/2015.
 */
public interface AuthInterface {
    public int getUserId();

    public void setUserId(int userId) ;

    public String getUsername() ;

    public void setUsername(String username);

    public String getPassword();

    public void setPassword(String password);

    public String getRole() ;

    public void setRole(String role) ;
}
