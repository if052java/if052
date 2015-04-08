<%--
  Created by IntelliJ IDEA.
  User: Bogdan Pastushkevych
  Date: 07.04.2015
  Time: 10:37
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
            <h4>Редагування типу лічильника</h4>

            <c:url var="updateUrl" value="/updateMeterType"/>

            <form:form action="${updateUrl}" method="post" modelAttribute="meterType">
                <sec:csrfInput/>
                <table class="box-table-a">
                    <thead>
                    <tr>
                        <th>Найменування типу</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="hidden" name="meterTypeId" value="${meterType.meterTypeId}"/>
                            <input type="text" class="form-control" name="type" value="${meterType.type}"/>
                        </td>
                        <td>
                            <button class="btn btn-default" type="submit">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form:form>

        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
