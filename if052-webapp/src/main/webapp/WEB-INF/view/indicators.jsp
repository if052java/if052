<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 07.02.2015
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">

            <div class="container">

                <table class="table table-hover" id="paginated">

                    <thead>
                    <h4>Показники лічильника: ${waterMeter.name}<c:if test="${waterMeter.description}!=null">, ${waterMeter.description} </c:if></h4>
                    <tr>
                        <th>Дата</th>
                        <th>Показник</th>
                        <th>Тариф</th>
                        <th>Вартість</th>
                        <th>Оплачено</th>
                        <th>Опубліковано</th>
                        <th>Дії</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--We use "previousValue" to calculate a subtraction of two indicators--%>
                    <c:set var="previousValue" value="0"/>
                    <c:forEach var="indicator" items="${indicators}">
                        <tr>
                            <td>
                                <span style='display:none'><fmt:formatDate value="${indicator.date}" pattern="MM/dd/yyyy" /></span>
                                <fmt:formatDate value="${indicator.date}" pattern="dd/MM/yyyy" />
                            </td>
                            <td><c:out value="${indicator.value}"/></td>
                            <td><c:out value="${indicator.tariffPerDate}"/></td>
                            <td><fmt:formatNumber type="number" maxFractionDigits="3"
                                                  value="${(indicator.value - previousValue)*indicator.tariffPerDate}"/>
                                грн.
                            </td>
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
                            <td>
                                <a href="<c:url value="/updateIndicator?indicatorId=${indicator.indicatorId}"/>">
                                    <button <c:if test="${indicator.published}">disabled="disabled"</c:if> >
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                    </button>
                                </a>
                                <a href="<c:url value="/deleteIndicator?indicatorId=${indicator.indicatorId}"/>">
                                    <button <c:if test="${indicator.published}">disabled="disabled"</c:if> >
                                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                    </button>
                                </a>
                            </td>
                        </tr>
                        <c:set var="previousValue" value="${indicator.value}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="container" >
                <c:url var="addUrl" value="/addIndicator"/>
                <form:form action="${addUrl}" method="post" modelAttribute="indicator">
                    <table class="box-table-a">
                        <caption> Додати показник </caption>
                        <thead>
                        <tr>
                            <th>Дата</th>
                            <th>Поточний тариф</th>
                            <th>Показник</th>
                            <th>Оплачено</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input type="text" id="datepicker" name="date" value="<fmt:formatDate value='${currentDate}' pattern='dd/MM/yyyy' />" />
                            </td>
                            <td><input type="number" step="0.1" name="tariffPerDate" value="${waterMeter.tariff}"/></td>
                            <td><input type="number" step="1" name="value" value="${indicators.get(indicators.size()-1).value}"/></td>
                            <td><input type="checkbox" name="paid" /></td>
                            <td>
                                <button class="add-button" type="submit">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>

        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui-i18n.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/datepicker.js'/>"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>
