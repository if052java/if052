package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_restful.domain.*;
import com.softserveinc.if052_restful.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;

/**
 * Created by Danylo Tiahun on 16.03.2015.
 */

@Component
public class ReportConverter {

    @Autowired
    private UserService userService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private WaterMeterService waterMeterService;

    @Autowired
    private IndicatorService indicatorService;

    public Report createReport(ReportRequest reportRequest) {
        Report report = new Report();
        report.setStartDate(reportRequest.getStartDate());
        report.setEndDate(reportRequest.getEndDate());
        if (reportRequest.getUsers().trim().equals("ALL")) {
            report.setUsers(userService.getAllUsers());
        } else {
            report.setUsers(Collections.singletonList(userService.getUserByLogin(reportRequest.getUsers())));
        }
        report.setReportRequest(reportRequest.toString());
        return report;
    }

    public StringBuffer convertToXml(Report report) {
        StringBuffer result = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        try {
            result.append("<report>\n");
            result.append("\t<startDate> " + df.format(report.getStartDate()) + " </startDate>\n");
            result.append("\t<endDate> " + df.format(report.getEndDate()) + " </endDate>\n");
            //1) new User report bean
            //2) decouple getting information from DB and XML formation
            //3) decrease nested if/for
            for (User u : report.getUsers()) {
                result.append("\t<user "
                        + "name=\"" + u.getName() + "\" "
                        + "surname=\"" + u.getSurname() + "\">\n");
                for (Address a : addressService.getAllAddressesByUserId(u.getUserId())) {
                    result.append("\t\t<address "
                            + "city=\"" + a.getCity() + "\" "
                            + "street=\"" + a.getStreet() + "\" "
                            + "building=\"" + a.getBuilding() + "\" "
                            + "apartment=\"" + a.getApartment() + "\">\n");
                    for (WaterMeter wm : waterMeterService.getWaterMetersByAddressId(a.getAddressId())) {
                        result.append("\t\t\t<watermeter "
                                + "name=\"" + wm.getName() + "\" "
                                + "description=\"" + wm.getDescription() + "\">\n");
                        result.append("\t\t\t\t<indicators>\n");
                        for (Indicator i : indicatorService.getIndicatorsByWaterMeterId(wm.getWaterMeterId())) {
                            if (i.getDate().compareTo(report.getStartDate()) >= 0
                                    && i.getDate().compareTo(report.getEndDate()) <= 0) {
                                result.append("\t\t\t\t\t<indicator "
                                        + "isPaid=\"" + i.isPaid() + '"' + "> "
                                        + i.getValue() + " </indicator>\n");
                                i.setPublished(true);
                            }
                        }
                        result.append("\t\t\t\t</indicators>\n");
                        result.append("\t\t\t</watermeter>\n");
                    }
                    result.append("\t\t</address>\n");
                }
                result.append("\t</user>\n");
            }
            result.append("</report>\n");
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return result;
    }

}
