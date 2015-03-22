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
                        <th>Значення</th>
                        <th>Оплачено</th>
                        <th>Опубліковано</th>
                        <th>Дії</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="indicator" items="${indicators}">
                        <tr>
                            <td><fmt:formatDate value="${indicator.date}" pattern="MM/dd/yyyy" /></td>
                            <td><c:out value="${indicator.value}"/></td>
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
                            <th>Значення</th>
                            <th>Оплачено</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <script src="<c:url value="/resources/js/jquery-ui.js"/>" type="text/javascript"></script>
                                <script type="text/javascript">
                                    $(function() {
                                        $( "#datepicker" ).datepicker();
                                    });
                                </script>
                                <input type="text" id="datepicker" name="date" value="<fmt:formatDate value='${currentDate}' pattern='MM/dd/yyyy' />" />
                            </td>
                            <td><input type="number" step="1" name="value" value="0"/></td>
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
    </tiles:putAttribute>
</tiles:insertDefinition>
