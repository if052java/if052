<%--
  Created by IntelliJ IDEA.
  User: Максим
  Date: 18.02.2015
  Time: 1:30
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

            <h1> UPDATE FORM FOR INDICATOR </h1>

            <c:url var="updateUrl" value="/updateIndicator"/>
            <form:form action="${updateUrl}" method="post" modelAttribute="indicator">
                <table class="box-table-a">
                    <caption> Update Indicator</caption>
                    <thead>
                        <tr>
                            <th>Дата</th>
                            <th>Значення</th>
                            <th>Оплачено</th>
                            <th>Дії</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="hidden" name="indicatorId" value="${indicator.indicatorId}"/>
                                <script src="<c:url value="/resources/js/jquery.js"/>"></script>
                                <script src="<c:url value="/resources/js/jquery-ui.js"/>"></script>
                                <script type="text/javascript">
                                    $(function() {
                                        $( "#datepicker" ).datepicker();
                                    });
                                </script>
                                <input type="text" id="datepicker" name="date" value="<fmt:formatDate value='${indicator.date}' pattern='MM/dd/yyyy' />" />
                            </td>
                            <td>
                                <input type="number" step="1" name="value" value="${indicator.value}"/>
                            </td>
                            <td>
                                <input type="checkbox" name="paid" <c:if test="${indicator.paid}">checked="checked"</c:if> />
                            </td>
                            <td><button class="add-button" type="submit">UPDATE</button></td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
