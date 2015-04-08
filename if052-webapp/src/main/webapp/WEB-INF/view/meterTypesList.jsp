<%--
  Created by IntelliJ IDEA.
  User: Bogdan Pastushkevych
  Date: 05.04.2015
  Time: 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">

                <table class="table table-hover">
                    <thead>
                    <h4>Довідник типів лічильників</h4>
                    <tr>
                        <th>Тип лічильника</th>
                        <th>Редагувати</th>
                        <th>Видалити</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="meterType" items="${meterTypes}">
                        <tr>
                            <td><c:out value="${meterType.type}"/></td>
                            <td><a href="<c:url value="/updateMeterType?meterTypeId=${meterType.meterTypeId}"/>">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                </button>
                            </a></td>
                            <td><a href="<c:url value="/deleteMeterType?meterTypeId=${meterType.meterTypeId}"/>">
                                <button class="btn btn-default">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <c:url var="addUrl" value="/addMeterType"/>
                <form:form action="${addUrl}" method="post" modelAttribute="meterType">
                    <sec:csrfInput/>
                    <table class="box-table-a">
                        <caption>Додати тип лічильника</caption>
                        <thead>
                        <tr>
                            <th>Найменування типу</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <input class="form-control" type="text" name="type"/>
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
    </tiles:putAttribute>
</tiles:insertDefinition>


