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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<fmt:message var="dateFormat" key="local.date.format"/>
<fmt:message var="locale" key="local.lang"/>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">

            <div class="container">

                <a href="<c:url value='/watermeter?addressId=${waterMeter.address.addressId}'/>"
                            class="btn btn-default"  role="button">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <spring:message code="nav.back"/>
                </a>

                <table class="table table-hover" cellspacing="0" width="100%"
                       <c:if test="${indicators.size()!=0}">id="paginated"</c:if> >

                    <thead>
                    <h4><spring:message code="indicators.thead"/>: ${waterMeter.name}
                        <c:if test="${waterMeter.description !=null && waterMeter.description !=''}">, ${waterMeter.description} </c:if></h4>
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
                                    code="indicators.cost">
                            </spring:message>
                        </th>
                        <th title=<spring:message code="th.paid"/> ><spring:message code="indicators.paid.min"/></th>
                        <th title=<spring:message code="th.published"/> ><spring:message code="indicators.published.min"/></th>
                        <th><spring:message code="indicators.actions"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="indicator" items="${indicators}" varStatus="iterator">
                        <tr>
                            <td>
                                <span style='display:none'><%--this is for proper sorting by date --%>
                                     <fmt:formatDate value="${indicator.date}" pattern="yyyy.MM.dd" />
                                </span>
                                <fmt:formatDate value="${indicator.date}" pattern="${dateFormat}" />
                            </td>
                            <td><c:out value="${indicator.value}"/></td>
                            <td><c:out value="${indicator.tariffPerDate}"/></td>
                            <td>
                                <fmt:formatNumber type="number" maxFractionDigits="3" value="${cost[iterator.index]}"/>
                                <spring:message code="local.money"/>
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
                                <a href="<c:url value="/updateIndicator?indicatorId=${indicator.indicatorId}"/>"
                                        class="btn btn-default <c:if test='${indicator.published}'>disabled</c:if>"  role="button">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </a>
                                <a href="<c:url value="/deleteIndicator?indicatorId=${indicator.indicatorId}"/>"
                                   class="btn btn-default <c:if test='${indicator.published}'>disabled</c:if>"  role="button">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="container" >
                <c:url var="addUrl" value="/addIndicator"/>
                <form:form action="${addUrl}" method="post" modelAttribute="indicator">
                    <sec:csrfInput/>
                    <table class="box-table-a">
                        <caption> <spring:message code="indicators.addIndicator"/> </caption>
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
                                            code="th.paid">
                                    </spring:message>
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input class="form-control" type="text" id="datepicker" name="dateStr"/>
                                    <input hidden="hidden" type="text" name="locale" value="${locale}"/>
                                </td>
                                <td><input class="form-control" type="number" step="1" min="0"
                                           name="value" value="${indicators.size()!=0 ? indicators.get(indicators.size()-1).value : 0}"/></td>
                                <td><input class="checkbox" type="checkbox" name="paid" /></td>
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
        <script>
            var locale = '${locale}'
        </script>

        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui-i18n.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.dataTables.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery/dataTables.bootstrap.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/indicators.js"/>"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>
