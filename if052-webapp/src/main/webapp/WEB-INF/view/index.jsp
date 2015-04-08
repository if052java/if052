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

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">

                <blockquote><h2>Привіт,  ${surname} ${name}!
                    <%--Popup for reminding if there aren't recently added indicators is placed here--%>
                <div class="reminder" tabindex="0" data-toggle="popover" data-trigger="focus" data-placement="left"
                     data-content="Ви не вносили показників більше 7 днів. Будь ласка, внесіть показник.">
                </div>
                </h2>
                </blockquote>

                <c:if test="${notification == null}">
                <table class="table table-hover" class="display">
                <caption>${limit} останніх занесених показів </caption>
                <thead>
                <tr>
                    <th>Дата</th>
                    <th>Показник</th>
                    <th>Тариф</th>
                    <th>Оплачено</th>
                    <th>Опубліковано</th>
                    <th>Лічильник</th>
                </tr>
                </thead>
                <tbody>
                    <%--We use "previousValue" to calculate a subtraction of two indicators--%>
                <c:forEach var="indicator" items="${lastIndicators}">
                    <tr>
                        <td>
                            <fmt:formatDate value="${indicator.date}" pattern="dd-MM-yyyy" />
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
                            Додати
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


