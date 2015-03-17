package com.softserveinc.if052_webapp.utils;

import com.softserveinc.if052_webapp.domain.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Danylo Tiahun on 13.03.2015.
 */

public class ReportRequest {

    private Date startDate;
    private Date endDate;
    private String users;

    public ReportRequest() {
    }

    public ReportRequest(Date startDate, Date endDate, String users) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
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

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", users='" + users + '\'' +
                '}';
    }

}
