package com.softserveinc.if052_restful.report;

import com.softserveinc.if052_core.domain.*;
import com.softserveinc.if052_restful.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Danylo Tiahun on 14.04.2015.
 */

@Component
public class XmlReportConverter extends ReportConverter {

    @Autowired
    private IndicatorService indicatorService;

    private Report report;

    private StringBuffer result = new StringBuffer();

    @Override
    public byte[] convert(Report report) {
        this.report = report;
        result = new StringBuffer();
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        result.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        result.append("\n<report>\n");
        result.append("\t<startDate> " + df.format(report.getStartDate()) + " </startDate>\n");
        result.append("\t<endDate> " + df.format(report.getEndDate()) + " </endDate>\n");
        for (User u : report.getUsers()) {
            writeUser(u);
        }
        result.append("</report>\n");
        return result.toString().getBytes(Charset.forName("UTF-8"));
    }

    private void writeUser(User u) {
        result.append("\t<user "
                + "name=\"" + u.getName() + "\" "
                + "surname=\"" + u.getSurname() + "\" "
                + "login=\"" + u.getLogin() + "\" "
                + "email=\"" + u.getEmail()
                + "\">\n");
        for (Address a : u.getAddresses()) {
            writeAddress(a);
        }
        result.append("\t</user>\n");
    }

    private void writeAddress(Address a) {
        result.append("\t\t<address "
                + "city=\"" + a.getCity() + "\" "
                + "street=\"" + a.getStreet() + "\" "
                + "building=\"" + a.getBuilding() + "\" "
                + "apartment=\"" + a.getApartment() + "\">\n");
        for (WaterMeter wm : a.getWaterMeters()) {
            writeWaterMeter(wm);
        }
        result.append("\t\t</address>\n");
    }

    private void writeWaterMeter(WaterMeter wm) {
        if (report.getMeterTypes().contains(wm.getMeterType())) {
            result.append("\t\t\t<meter "
                    + "name=\"" + wm.getName() + "\" "
                    + "description=\"" + wm.getDescription() + "\" "
                    + "tariff=\"" + wm.getTariff() + "\" "
                    + "meterType=\"" + wm.getMeterType().getType() + "\">\n");
            result.append("\t\t\t\t<indicators>\n");
            for (Indicator i : wm.getIndicators()) {
                writeIndicator(i);
            }
            result.append("\t\t\t\t</indicators>\n");
            result.append("\t\t\t</meter>\n");
        }
    }

    private void writeIndicator(Indicator i) {
        if (i.getDate().compareTo(report.getStartDate()) >= 0
                && i.getDate().compareTo(report.getEndDate()) <= 0) {
            result.append("\t\t\t\t\t<indicator "
                    + "tariffPerDate=\"" + i.getTariffPerDate() + '"' + "> "
                    + i.getValue() + " </indicator>\n");
            i.setPublished(true);
            indicatorService.updateIndicator(i);
        }
    }

}
