package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_core.domain.*;
import com.softserveinc.if052_restful.service.MeterTypeService;
import com.softserveinc.if052_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Danylo Tiahun on 24.03.2015.
 */

public abstract class ReportConverter {

    @Autowired
    private UserService userService;

    @Autowired
    private MeterTypeService meterTypeService;

    public Report createReport(ReportRequest reportRequest) {
        Report report = new Report();
        report.setLocale(reportRequest.getLocale());
        report.setStartDate(reportRequest.getStartDate());
        report.setEndDate(reportRequest.getEndDate());
        report.setDateFormat(reportRequest.getDateFormat());
        if (reportRequest.getUsers().trim().equals("ALL")) {
            report.setUsers(userService.getAllReportUsers());
        } else {
            report.setUsers(Collections.singletonList(userService.getReportUserByLogin(reportRequest.getUsers())));
        }
        List<MeterType> mt = new ArrayList<MeterType>();
        for (Integer i : reportRequest.getTypes()) {
            mt.add(meterTypeService.getMeterTypeById(i));
        }
        report.setMeterTypes(mt);
        report.setReportRequest(reportRequest.toString());
        return report;
    }

    public abstract byte[] convert(Report report);

}
