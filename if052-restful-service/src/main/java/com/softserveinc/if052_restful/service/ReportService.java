package com.softserveinc.if052_restful.service;

import com.softserveinc.if052_restful.mappers.ReportMapper;
import com.softserveinc.if052_restful.domain.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Danylo Tiahun on 14.03.2015.
 */

@Service
@Transactional
public class ReportService {

    @Autowired
    private ReportMapper reportMapper;

    public void insertReport(Report report) {
        reportMapper.insertReport(report);
    }

    public Report getReportById(int reportId) {
        return reportMapper.getReportById(reportId);
    }

}
