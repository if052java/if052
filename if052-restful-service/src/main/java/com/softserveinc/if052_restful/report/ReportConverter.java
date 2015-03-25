package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_restful.domain.*;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.MeterTypeService;
import com.softserveinc.if052_restful.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Danylo Tiahun on 24.03.2015.
 */

@Component
public class ReportConverter {

    @Autowired
    private UserService userService;

    @Autowired
    private MeterTypeService meterTypeService;

    @Autowired
    private IndicatorService indicatorService;

    private Report report;

    public Report createReport(ReportRequest reportRequest) {
        Report report = new Report();
        report.setStartDate(reportRequest.getStartDate());
        report.setEndDate(reportRequest.getEndDate());
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
        report.setPaidStatus(reportRequest.getPaidStatus());
        report.setReportRequest(reportRequest.toString());
        return report;
    }

    public StringBuffer convertToXml(Report report) {
        this.report = report;
        StringBuffer result = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        result.append("<report>\n");
        result.append("\t<startDate> " + df.format(report.getStartDate()) + " </startDate>\n");
        result.append("\t<endDate> " + df.format(report.getEndDate()) + " </endDate>\n");
        for (User u : report.getUsers()) {
            result.append(writeUser(u));
        }
        result.append("</report>\n");
        return result;
    }

    public StringBuffer writeUser(User u) {
        StringBuffer result = new StringBuffer();
        result.append("\t<user "
                + "name=\"" + u.getName() + "\" "
                + "surname=\"" + u.getSurname() + "\" "
                + "login=\"" + u.getLogin() + "\" "
                + "email=\"" + u.getEmail()
                + "\">\n");
        for (Address a : u.getAddresses()) {
            result.append(writeAddress(a));
        }
        result.append("\t</user>\n");

        return result;
    }

    public StringBuffer writeAddress(Address a) {
        StringBuffer result = new StringBuffer();
        result.append("\t\t<address "
                + "city=\"" + a.getCity() + "\" "
                + "street=\"" + a.getStreet() + "\" "
                + "building=\"" + a.getBuilding() + "\" "
                + "apartment=\"" + a.getApartment() + "\">\n");
        for (WaterMeter wm : a.getWaterMeters()) {
            result.append(writeWaterMeter(wm));
        }
        result.append("\t\t</address>\n");

        return result;
    }

    public StringBuffer writeWaterMeter(WaterMeter wm) {
        StringBuffer result = new StringBuffer();
        if (report.getMeterTypes().contains(wm.getMeterType())) {
            result.append("\t\t\t<watermeter "
                    + "name=\"" + wm.getName() + "\" "
                    + "description=\"" + wm.getDescription() + "\" "
                    + "tariff=\"" + wm.getTariff() + "\" "
                    + "meterType=\"" + wm.getMeterType().getType() + "\">\n");
            result.append("\t\t\t\t<indicators>\n");
            for (Indicator i : wm.getIndicators()) {
                result.append(writeIndicator(i));
            }
            result.append("\t\t\t\t</indicators>\n");
            result.append("\t\t\t</watermeter>\n");
        }
        return result;
    }

    public StringBuffer writeIndicator(Indicator i) {
        StringBuffer result = new StringBuffer();

        if (i.getDate().compareTo(report.getStartDate()) >= 0
                && i.getDate().compareTo(report.getEndDate()) <= 0) {
            switch (report.getPaidStatus()) {
                case 0:
                    if (!i.isPaid()) {
                        result.append(writeIndicatorValue(i));
                    }
                    break;
                case 1:
                    if (i.isPaid()) {
                        result.append(writeIndicatorValue(i));
                    }
                    break;
                default:
                    result.append(writeIndicatorValue(i));
                    break;
            }
        }

        return result;
    }

    public StringBuffer writeIndicatorValue(Indicator i) {
        StringBuffer result = new StringBuffer();
        result.append("\t\t\t\t\t<indicator "
                + "isPaid=\"" + i.isPaid() + '"' + "> "
                + i.getValue() + " </indicator>\n");
        i.setPublished(true);
        indicatorService.updateIndicator(i);
        return result;
    }


}
