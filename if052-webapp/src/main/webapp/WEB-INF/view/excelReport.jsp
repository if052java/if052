<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 14.04.2015
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sptring" uri="http://www.springframework.org/tags" %>
<fmt:message var="dateFormat" key="local.date.format"/>
<fmt:message var="locale" key="local.lang"/>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="body">

            <div class="container">

                <h2><sptring:message code="report.choosePeriod"/>:</h2>

                <c:url var="createExcelUrl" value="/createExcelReport"/>
                <form:form action="${createExcelUrl}" method="get" modelAttribute="reportRequest" id="excelForm"
                           autocomplete="off">
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <label for="startDate"><sptring:message code="report.startDate"/></label>
                                <input type="text" name="startDate" class="form-control" id="startDate"
                                       value="${startDate}"
                                       pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                       required/>
                            </div>
                            <div class="form-group">
                                <label for="endDate"><sptring:message code="report.endDate"/></label>
                                <input type="text" name="endDate" class="form-control" id="endDate" value="${endDate}"
                                       pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                       required/>
                            </div>
                            <input hidden="hidden" type="text" name="dateFormat" value="${dateFormat}"/>
                            <input hidden="hidden" type="text" name="locale" value="${locale}"/>
                            <div class="form-group">
                                <button type="submit" id="subBtn" class="btn btn-primary">
                                    <sptring:message code="report.downloadReport"/> (MS Excel)
                                </button>
                            </div>
                        </div>
                    </div>
                </form:form>

            </div>

        </div>

        <script>
            var locale = '${locale}'
        </script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui-i18n.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/datepicker.js'/>"></script>


    </tiles:putAttribute>
</tiles:insertDefinition>