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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="body">

            <div class="container">

                <h2>Оберіть фільтри для звіту:</h2>
                <c:url var="createXmlUrl" value="/createXmlReport"/>
                <form:form action="${createXmlUrl}" method="get" modelAttribute="reportRequest" id="xmlForm">
                    <sec:csrfInput/>

                    <div class="form-group">
                        <label for="users">Логін користувача</label>
                        <div class="checkbox-inline">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="users" value="ALL" id="allUsers"/> Усі користувачі
                            </label>
                        </div>
                        <input type="text" name="users" class="form-control" id="users" />
                    </div>
                    <div class="form-group">
                        <label for="startDate">Початкова дата</label>
                        <input type="text" name="startDate" class="form-control" id="startDate" value="${startDate}"/>
                    </div>
                    <div class="form-group">
                        <label for="endDate">Кінцева дата</label>
                        <input type="text" name="endDate" class="form-control" id="endDate"/>
                    </div>

                    <div class="form-group">
                    <div class="checkbox-inline" id="types">
                        <label for="types">Необхідні види лічильників</label>
                        <div class="checkbox-inline">
                            <label class="checkbox-inline">
                                <input type="checkbox" id="allTypes"/> Усі види
                            </label>
                        </div> <br>
                        <c:forEach var="meterType" items="${meterTypes}">
                            <label class="checkbox-inline">
                                <input type="checkbox" name="types" value="${meterType.meterTypeId}"/> ${meterType.type}
                            </label>
                        </c:forEach>
                    </div>
                    </div>

                    <div class="form-group">
                    <div class="radio-inline" id="paid">
                        <label for="paid">Статус оплати</label><br>
                        <label class="radio-inline">
                            <input type="radio" name="paidStatus" value="2" checked> Усі
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="paidStatus" value="1"> Оплачені
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="paidStatus" value="0"> Неоплачені
                        </label>
                    </div>
                    </div>

                    <div class="form-group">
                    <button type="submit" class="btn btn-primary">Скачати xml-звіт</button>
                    </div>
                </form:form>
            </div>

        </div>


        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/xmlReport.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui-i18n.min.js'/>"></script>
        <script>
            $(document).ready(function() {
                var logins = [];
                <c:forEach items="${logins}" var="login">
                logins.push("<c:out value="${login}" />");
                </c:forEach>
                $("#users").autocomplete({
                    source: logins
                });
            });
        </script>

    </tiles:putAttribute>
</tiles:insertDefinition>

