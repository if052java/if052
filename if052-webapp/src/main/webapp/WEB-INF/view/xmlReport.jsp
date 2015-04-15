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
                <form:form action="${createXmlUrl}" method="get" modelAttribute="reportRequest" id="xmlForm"
                           autocomplete="off">

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="users">Логін користувача</label>
                                <div class="checkbox-inline">
                                    <label class="checkbox-inline">
                                        <input type="checkbox" name="users" value="ALL" id="allUsers"/> Усі користувачі
                                    </label>
                                </div>
                                <input type="text" name="users" class="form-control" id="users" required/>
                                <label hidden="true" id="noLoginLabel"><font color="red">Такого логіна немає. Оберіть
                                    варіант зі списку.</font></label>
                            </div>
                            <div class="form-group">
                                <label for="startDate">Початкова дата</label>
                                <input type="text" name="startDate" class="form-control" id="startDate"
                                       value="${startDate}" required/>
                            </div>
                            <div class="form-group">
                                <label for="endDate">Кінцева дата</label>
                                <input type="text" name="endDate" class="form-control" id="endDate" value="${endDate}"
                                       required/>
                            </div>
                            <div class="form-group">
                                <div class="checkbox-inline" id="types">
                                    <label for="types">Необхідні види лічильників</label>
                                    <div class="checkbox-inline">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" id="allTypes"/> Усі види
                                        </label>
                                    </div>
                                    <br>
                                    <c:forEach var="meterType" items="${meterTypes}">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" class="checkType" name="types"
                                                   value="${meterType.meterTypeId}"/> ${meterType.type}
                                        </label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="form-group">
                                <button type="submit" id="subBtn" class="btn btn-primary" disabled>Завантажити
                                    xml-звіт
                                </button>
                            </div>
                        </div>
                    </div>
                    
                </form:form>
            </div>

        </div>


        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui-i18n.min.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/jquery/jquery-ui.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/datepicker.js'/>"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/xmlReport.js'/>"></script>
        <script>
            var logins = [];
            $(document).ready(function () {
                <c:forEach items="${logins}" var="login">
                logins.push("<c:out value="${login}" />");
                </c:forEach>

            });
        </script>


    </tiles:putAttribute>
</tiles:insertDefinition>