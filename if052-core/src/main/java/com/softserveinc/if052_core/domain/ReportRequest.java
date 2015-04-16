package com.softserveinc.if052_core.domain;



import java.util.Date;
import java.util.List;

/**
 * Created by Danylo Tiahun on 13.03.2015.
 */

public class ReportRequest {

    private String locale;
    private Date startDate;
    private Date endDate;
    private String dateFormat;
    private String users;
    private List<Integer> types;

    public ReportRequest() {
    }

    public ReportRequest(String locale, Date startDate, Date endDate, String dateFormat, String users, List<Integer> types) {
        this.locale = locale;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateFormat = dateFormat;
        this.users = users;
        this.types = types;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "locale='" + locale + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dateFormat='" + dateFormat + '\'' +
                ", users='" + users + '\'' +
                ", types=" + types +
                '}';
    }
}