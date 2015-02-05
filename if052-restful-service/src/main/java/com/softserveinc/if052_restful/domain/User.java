package com.softserveinc.if052_restful.domain;

import java.util.List;

public class User {

                        //- Properties -//
    private int userId;
    private String name;
    private String surname;
    private String middleName;
    private String login;
    private String password;
    private double tariff;
    private List < Address > addresses;
    
                        //- Getters -//
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public double getTariff() {
        return tariff;
    }

    public List < Address > getAddresses() {
        return addresses;
    }

                        //- Setters -//
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middlName) {
        this.middleName = middlName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTariff(double tariff) {
        this.tariff = tariff;
    }

    public void setAddresses(List < Address > addresses) {
        this.addresses = addresses;
    }

                        //- toString -//
    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", middle_name='" + middleName+ '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", tariff=" + tariff +
            '}';
    }
}
