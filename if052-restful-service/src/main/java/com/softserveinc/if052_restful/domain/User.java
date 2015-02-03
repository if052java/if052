package com.softserveinc.if052_restful.domain;

public class User {
                //- Properties -//

    private int userId;
    private String name;
    private String surname;
    private String middle_name;
    private String login;
    private String password;
    private double tariff;
    
                //- Getters -//

    public int getId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddle_name() {
        return middle_name;
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

                //- Setters -//

    public void setId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
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
                //- toString -//

    @Override
    public String toString() {
        return "User{" +
            "id=" + userId +
            ", name='" + name + '\'' +
            ", surname='" + surname + '\'' +
            ", middle_name='" + middle_name + '\'' +
            ", login='" + login + '\'' +
            ", password='" + password + '\'' +
            ", tariff=" + tariff +
            '}';
    }
}
