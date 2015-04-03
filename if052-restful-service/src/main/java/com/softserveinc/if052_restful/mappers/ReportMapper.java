package com.softserveinc.if052_restful.mappers;

import com.softserveinc.if052_core.domain.Report;

/**
 * Created by Danylo Tiahun on 14.03.2015.
 */
public interface ReportMapper {

    public void insertReport(Report report);

    public Report getReportById(int reportId);

}
