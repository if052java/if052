<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 11.02.2015
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">


        <div class="body">

            <div class="container">

                <a href="<c:url value='/addresses?userId=${address.user.userId}'/>"
                   class="btn btn-default" role="button">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <spring:message code="nav.back"/>
                </a>

                <table class="table table-hover">
                    <thead>
                    <h4><spring:message code="meter.metersForAddress"/> ${address.city}, ${address.street}
                            ${address.building}/${address.apartment}: </h4>
                    <tr>
                        <th><spring:message code="meter.name"/></th>
                        <th><spring:message code="meter.description"/></th>
                        <th><spring:message code="meter.type"/></th>
                        <th><spring:message code="meter.tariff"/></th>
                        <th><spring:message code="meter.indicators"/></th>
                        <th><spring:message code="meter.edit"/></th>
                        <th><spring:message code="meter.remove"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="waterMeter" items="${waterMeters}">
                    <tr>
                        <td><c:out value="${waterMeter.name}"/></td>
                        <td><c:out value="${waterMeter.description}"/></td>
                        <td><c:out value="${waterMeter.meterType.type}"/></td>
                        <td><c:out value="${waterMeter.tariff}"/></td>
                        <td><a href="<c:url value="/indicators?meterId=${waterMeter.waterMeterId}"/>">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
                            </button>
                        </a></td>
                        <td><a href="<c:url value="/updateWaterMeter?waterMeterId=${waterMeter.waterMeterId}"/>">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </button>
                        </a></td>
                        <td><a href="<c:url value="/deleteWaterMeter?waterMeterId=${waterMeter.waterMeterId}"/>">
                            <button class="btn btn-default">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </button>
                        </a></td>

                    </tr>
                    </c:forEach>
                </table>
                <c:url var="addUrl" value="/addWaterMeter"/>
                <form:form action="${addUrl}" method="post" id="addMeter" modelAttribute="waterMeter">
                    <sec:csrfInput/>
                    <table class="box-table-a">
                        <h4><spring:message code="meter.add"/>:</h4>
                        <thead>
                        <tr>
                            <th><spring:message code="meter.name"/></th>
                            <th><spring:message code="meter.description"/></th>
                            <th><spring:message code="meter.type"/></th>
                            <th><spring:message code="meter.tariff"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>


                                <input class="form-control" type="text" name="name"/>
                            </td>
                            <td><input class="form-control" type="text" name="description"/></td>
                            <td>
                                <select class="form-control" name="typeId">
                                    <c:forEach var="meterType" items="${meterTypes}">
                                        <option value="${meterType.meterTypeId}">${meterType.type}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input class="form-control" name="tariff"/>
                            </td>
                            <td>
                                <button class="btn btn-default" type="submit">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>

            </div>
        </div>

        <script src="/resources/js/jquery/jquery-validate.js"></script>
        <script type="text/javascript">
            var messages = new Array();
            messages['maxlength.name'] = "<spring:message code='maxlength.name' javaScriptEscape='true' />"
            messages['maxlength.description'] = "<spring:message code='maxlength.description' javaScriptEscape='true' />"
            messages['required.field'] = "<spring:message code='required.field' javaScriptEscape='true' />"
            messages['number.min'] = "<spring:message code='number.min' javaScriptEscape='true' />"
            messages['number.field'] = "<spring:message code='number.field' javaScriptEscape='true' />"
        </script>
        <script src="/resources/js/meter.js"></script>

    </tiles:putAttribute>
</tiles:insertDefinition>
