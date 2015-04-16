<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 12.02.2015
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">

        <h2> Редагування даних лічильника </h2>

<c:url var="updateUrl" value="/updateWaterMeter"/>

            <form:form
                    action="${updateUrl}" method="post" id="updateMeter" modelAttribute="waterMeter">
                <sec:csrfInput/>
                <table class="box-table-a">
                    <thead>
                    <tr>
                        <th>Назва</th>
                        <th>Опис</th>
                        <th>Тариф</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="hidden" name="waterMeterId" value="${waterMeter.waterMeterId}"/>
                            <input type="text" class="form-control" name="name" value="${waterMeter.name}"/>
                        </td>
                        <td> <input type="text" class="form-control" name="description" value="${waterMeter.description}"/></td>
                        <td>
                            <input type="number" class="form-control" step="0.01" name="tariff" value="${waterMeter.tariff}"/>
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
        <script src="/resources/js/jquery/jquery-validate.js"></script>
        <script type="text/javascript">
            var messages = new Array();
            messages['maxlength.name'] = "<spring:message code='maxlength.name' javaScriptEscape='true' />"
            messages['maxlength.description'] = "<spring:message code='maxlength.description' javaScriptEscape='true' />"
            messages['required.field'] = "<spring:message code='required.field' javaScriptEscape='true' />"
        </script>
        <script src="/resources/js/updateMeter.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>
