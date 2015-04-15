<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 14.04.2015
  Time: 12:10
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

                <h2>Оберіть період, за який необхідно сформувати звіт: </h2>

                <c:url var="createExcelUrl" value="/createExcelReport"/>
                <form:form action="${createExcelUrl}" method="get" modelAttribute="reportRequest" id="excelForm"
                           autocomplete="off">
                    <div class="row">
                        <div class="col-lg-4">
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
                                <button type="submit" id="subBtn" class="btn btn-primary">Завантажити звіт (MS Excel)
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


    </tiles:putAttribute>
</tiles:insertDefinition>