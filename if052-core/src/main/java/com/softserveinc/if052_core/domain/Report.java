package com.softserveinc.if052_core.domain;

import java.util.Date;
import java.util.List;

/**
 * Created by Danylo Tiahun on 14.03.2015.
 */

public class Report {
    private int reportId;
    private Date startDate;
    private Date endDate;
    private List<User> users;
    private List<MeterType> meterTypes;
    private String reportRequest;
    private String xmlReport;

    public Report() {
    }

    public Report(int reportId, Date startDate, Date endDate,
                  List<User> users, List<MeterType> meterTypes,
                  String reportRequest, String xmlReport) {
        this.reportId = reportId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
        this.meterTypes = meterTypes;
        this.reportRequest = reportRequest;
        this.xmlReport = xmlReport;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<MeterType> getMeterTypes() {
        return meterTypes;
    }

    public void setMeterTypes(List<MeterType> meterTypes) {
        this.meterTypes = meterTypes;
    }

    public String getReportRequest() {
        return reportRequest;
    }

    public void setReportRequest(String reportRequest) {
        this.reportRequest = reportRequest;
    }

    public String getXmlReport() {
        return xmlReport;
    }

    public void setXmlReport(String xmlReport) {
        this.xmlReport = xmlReport;
    }
}