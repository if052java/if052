<%--
  Created by IntelliJ IDEA.
  User: Java
  Date: 27.02.2015
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<fmt:message var="dateFormat" key="local.date.format"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">

                <blockquote><h2>
                    <spring:message
                        code="blockquote.welcome">
                </spring:message>,  ${surname} ${name}!</h2></blockquote>

                <c:if test="${notification == null}">
                <table class="table table-hover" class="display">
                <caption>
                ${limit} <spring:message
                            code="caption.lastIndicator">
                        </spring:message>
                </caption>
                <thead>
                <tr>
                    <th>
                        <spring:message
                                code="th.date">
                        </spring:message>
                    </th>
                    <th>
                        <spring:message
                                code="th.value">
                        </spring:message>
                    </th>
                    <th>
                        <spring:message
                                code="th.tariff">
                        </spring:message>
                    </th>
                    <th>
                        <spring:message
                                code="th.paid">
                        </spring:message>
                    </th>
                    <th>
                        <spring:message
                                code="th.published">
                        </spring:message>
                    </th>
                    <th>
                        <spring:message
                                code="th.meter">
                        </spring:message>
                    </th>
                </tr>
                </thead>
                <tbody>
                    <%--We use "previousValue" to calculate a subtraction of two indicators--%>
                <c:forEach var="indicator" items="${lastIndicators}">
                    <tr>
                        <td>
                            <fmt:formatDate value="${indicator.date}" pattern="${dateFormat}" />
                        </td>
                        <td><c:out value="${indicator.value}"/></td>
                        <td><c:out value="${indicator.tariffPerDate}"/></td>
                        <td>
                                <span <c:if test="${indicator.paid}">class="glyphicon glyphicon-ok" </c:if>
                                      <c:if test="${!indicator.paid}">class="glyphicon glyphicon-minus" </c:if>
                                      aria-hidden="true">
                                </span>
                        </td>
                        <td>
                                <span <c:if test="${indicator.published}">class="glyphicon glyphicon-ok" </c:if>
                                      <c:if test="${!indicator.published}">class="glyphicon glyphicon-minus" </c:if>
                                      aria-hidden="true">
                                </span>
                        </td>
                        <td><c:out value="${indicator.waterMeter.name}"/>,
                            <c:out value="${indicator.waterMeter.description}"/></td>
                    </tr>
                </c:forEach>
                </tbody>
                </table>
                </c:if>
                <c:if test="${notification != null}">
                    <div class="text-center">
                        <h2>${notification}</h2>
                        <a href="<c:url value='/addresses'/>"
                           class="btn btn-default"  role="button">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                            <spring:message code="indicators.addIndicator"/>
                        </a>
                    </div>
                </c:if>
            </div>
        </div>
        <script type="text/javascript">
            var untrackedDays;
            if ('${notification}' == "") {
                untrackedDays = '${untrackedDays}';
            } else {
                untrackedDays = 0;
            }
        </script>
        <script type="text/javascript" src="<c:url value="/resources/js/reminder.js"/>"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>


