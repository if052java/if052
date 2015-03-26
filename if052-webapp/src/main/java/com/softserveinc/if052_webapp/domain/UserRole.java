package com.softserveinc.if052_webapp.domain;

/**
 * Created by Hata on 09.03.2015.
 */
public class UserRole {
    private String roleName;

    private String user;
    private String password;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public UserRole(){}

    public UserRole(String roleName) {
        this.roleName = roleName;
    }
}
