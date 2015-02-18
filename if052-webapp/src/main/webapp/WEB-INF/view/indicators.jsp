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

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Дата</th>
                            <th>Значення</th>
                            <th>Оплачено</th>
                            <th>Опубліковано</th>
                            <th>Дії</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="indicators" items="${indicators}">
                            <tr>
                                <td><c:out value="${indicators.date}"/></td>
                                <td><c:out value="${indicators.value}"/></td>
                                <td><c:out value="${indicators.isPaid() ? 'Yes': 'No'}"/></td>
                                <td><c:out value="${indicators.isPublished() ? 'Yes': 'No'}"/></td>
                                <td>
                                    <a href="<c:url value="/deleteIndicator?indicatorId=${indicators.indicatorId}"/>">
                                        <button>DELETE</button>
                                    </a>
                                    <a href="<c:url value="/updateIndicator?indicatorId=${indicators.indicatorId}"/>">
                                        <button>UPDATE</button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                </table>
                <c:url var="addUrl" value="/addIndicator"/>
                <form:form action="${addUrl}" method="post" modelAttribute="indicator">
                    <table class="box-table-a">
                        <caption> Додати показник </caption>
                        <thead>
                        <tr>
                            <th>Значення</th>
                            <th>Оплачено</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><input type="text" name="value" /></td>
                            <td><input type="checkbox" name="isPaid" /></td><%-- doesn't work--%>
                            <td>
                                <button class="add-button" type="submit">Add</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form:form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
