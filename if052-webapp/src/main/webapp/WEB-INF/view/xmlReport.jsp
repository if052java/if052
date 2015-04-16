<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 13.03.2015
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<fmt:message var="dateFormat" key="local.date.format"/>
<fmt:message var="locale" key="local.lang"/>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="body">

            <div class="container">

                <h2><spring:message code="report.chooseFilters"/>:</h2>
                <c:url var="createXmlUrl" value="/createXmlReport"/>
                <form:form action="${createXmlUrl}" method="get" modelAttribute="reportRequest" id="xmlForm"
                           autocomplete="off">

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="users"><spring:message code="report.login"/></label>

                                <div class="checkbox-inline">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="users" value="ALL" id="allUsers"/> <spring:message
                                            code="report.allUsers"/>
                                    </label>
                                </div>
                                <input type="text" name="users" class="form-control" id="users" required/>
                                <label hidden="true" id="noLoginLabel"><font color="red"><spring:message
                                        code="report.noLogin"/></font></label>
                            </div>
                            <div class="form-group">
                                <label for="startDate"><spring:message code="report.startDate"/></label>
                                <input type="text" name="startDate" class="form-control" id="startDate"
                                       value="${startDate}"
                                       pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                       required/>
                            </div>
                            <div class="form-group">
                                <label for="endDate"><spring:message code="report.endDate"/></label>
                                <input type="text" name="endDate" class="form-control" id="endDate"
                                       value="${endDate}"
                                       pattern="[0-9]{4}/(0[1-9]|1[012])/(0[1-9]|1[0-9]|2[0-9]|3[01])"
                                       required/>
                            </div>
                            <input hidden="hidden" type="text" name="dateFormat" value="${dateFormat}"/>
                            <div class="form-group">
                                <div class="checkbox-inline" id="types">
                                    <label for="types"><spring:message code="report.meterTypes"/></label>

                                    <div class="checkbox-inline">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" id="allTypes"/> <spring:message
                                                code="report.allTypes"/>
                                        </label>
                                    </div>
                                    <br>
                                    <c:forEach var="meterType" items="${meterTypes}">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" class="checkType" name="types"
                                                   value="${meterType.meterTypeId}"/> ${meterType.type}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" id="subBtn" class="btn btn-primary" disabled>
                                    <spring:message code="report.downloadReport"/> (XML)
                                </button>
                            </div>
                        </div>
                    </div>

                </form:form>
            </div>

        </div>

        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui-i18n.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/datepicker.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/xmlReport.js'/>"></script>
        <script>
            var logins = [];
            var locale = '${locale}';
            $(document).ready(function () {
                <c:forEach items="${logins}" var="login">
                logins.push("<c:out value="${login}" />");
                </c:forEach>
            });
        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>