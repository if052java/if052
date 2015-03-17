package com.softserveinc.if052_restful.domain;

import com.softserveinc.if052_restful.domain.User;
import com.softserveinc.if052_restful.report.ReportRequest;
import com.softserveinc.if052_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
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
    private String reportRequest;
    private String xmlReport;

    public Report() {
    }

    public Report(Date startDate, Date endDate, List<User> users) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.users = users;
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
