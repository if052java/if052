package com.softserveinc.if052_webapp.domain;

/**
 * Created by Hata on 09.03.2015.
 */
public class UserRole {
    private String roleName;

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
