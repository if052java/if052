<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 13.03.2015
  Time: 19:24
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
            <script src="<c:url value="/resources/js/jquery/jquery-ui.js"/>" type="text/javascript"></script>
            <script src="<c:url value="/resources/js/xmlReport.js"/>" type="text/javascript"></script>

            <div class="container">
                <h2>Оберіть фільтри для звіту:</h2>
                <c:url var="createXmlUrl" value="/createXmlReport"/>
                <form:form action="${createXmlUrl}" method="get" modelAttribute="reportRequest">
                    <div class="form-group">
                        <label for="users">Логін користувача ("ALL" для усіх)</label>
                        <input type="text" name="users" class="form-control" id="users" placeholder="Choose users">
                    </div>
                    <div class="form-group">
                        <label for="startDate">Початкова дата</label>
                        <input type="text" name="startDate" class="form-control" id="startDate" value="${startDate}"/>
                    </div>
                    <div class="form-group">
                        <label for="endDate">Кінцева дата</label>
                        <input type="text" name="endDate" class="form-control" id="endDate">
                    </div>


                    <div class="checkbox-inline" id="types">
                        <label for="types">Необхідні види лічильників</label><br>
                        <c:forEach var="meterType" items="${meterTypes}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="types" value="${meterType.meterTypeId}"> ${meterType.type}
                            </label>
                        </c:forEach>
                    </div>

                    <br><br>
                    <div class="checkbox-inline" id="paid">
                        <label for="paid">Статус оплати</label><br>
                        <label class="checkbox-inline">
                            <input type="radio" name="paidStatus" value="2" checked> Усі
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="paidStatus" value="1"> Оплачені
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="paidStatus" value="0"> Неоплачені
                        </label>
                    </div>
                    <br><br>

                    <button type="submit" class="btn btn-default">Скачати xml-звіт</button>
                </form:form>

            </div>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
