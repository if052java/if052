package com.softserveinc.if052_restful.resource;


import com.softserveinc.if052_core.domain.Report;
import com.softserveinc.if052_restful.report.ReportConverter;
import com.softserveinc.if052_restful.report.ReportRequest;
import com.softserveinc.if052_restful.service.IndicatorService;
import com.softserveinc.if052_restful.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Created by Danylo Tiahun on 14.03.2015.
 */

@Path("/report/")
public class ReportResource {

    @Autowired
    private IndicatorService indicatorService;

    @Autowired
    private ReportService reportService;

    @Autowired
    private ReportConverter reportConverter;


    @GET
    @Path("/mindate")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMinDate() {
        Date minDate = indicatorService.getMinDate();
        if (minDate == null) {
            minDate = new Date();
        }
        return Response.status(Response.Status.OK).entity(minDate).build();
    }

    @GET
    @Path("/maxdate")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMaxDate() {
        Date maxDate = indicatorService.getMaxDate();
        if (maxDate == null) {
            maxDate = new Date();
        }
        return Response.status(Response.Status.OK).entity(maxDate).build();
    }


    @GET
    @Path("{reportId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getReport(@PathParam("reportId") int reportId) {
        Report report = reportService.getReportById(reportId);
        if (report == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
        return Response
                .status(Response.Status.OK)
                .entity(report.getXmlReport().toString())
                .build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response insertReport(ReportRequest reportRequest) {
        Report report = reportConverter.createReport(reportRequest);
        report.setReportRequest(reportRequest.toString());
        report.setXmlReport(reportConverter.convertToXml(report).toString());
        reportService.insertReport(report);
        return Response
                .status(Response.Status.CREATED)
                .header("Location", "/rest/report/" + report.getReportId())
                .build();
    }

}
