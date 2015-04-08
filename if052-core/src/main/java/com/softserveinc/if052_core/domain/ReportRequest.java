package com.softserveinc.if052_core.domain;



import java.util.Date;
import java.util.List;

/**
 * Created by Danylo Tiahun on 13.03.2015.
 */

public class ReportRequest {

    private Date startDate;
    private Date endDate;
    private String users;
    private List<Integer> types;

    public ReportRequest() {
    }

    public ReportRequest(Date startDate, Date endDate, String users, List<Integer> types, Integer paidStatus) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
        this.types = types;
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

    public List<Integer> getTypes() {
        return types;
    }

    public void setTypes(List<Integer> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "ReportRequest{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", users='" + users + '\'' +
                ", types=" + types +
                '}';
    }
}