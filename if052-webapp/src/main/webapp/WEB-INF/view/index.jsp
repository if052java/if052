<%--&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: Java--%>
  <%--Date: 27.02.2015--%>
  <%--Time: 18:21--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>--%>
<%--<tiles:insertDefinition name="defaultTemplate">--%>
    <%--<tiles:putAttribute name="body">--%>
        <%--<div class="body">--%>
            <%--<div class="container">--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
                <%--<br>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</tiles:putAttribute>--%>
<%--</tiles:insertDefinition>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <script src="<c:url value="/resources/js/jquery/jquery-ui.js"/>" type="text/javascript"></script>
            <script type="text/javascript">
                $(function() {
                    $( "#startDate" ).datepicker({dateFormat:"yy/mm/dd"});
                });
                $(function() {
                    $( "#endDate" ).datepicker({dateFormat:"yy/mm/dd"}).datepicker("setDate", new Date());
                });
                $(function() {
                    var startDate = $("#startDate").val();
                    var endDate = $("#endDate").val();

                });
            </script>

            <div class="container">
                <h2>Choose filters for the report:</h2>
                <c:url var="createXmlUrl" value="/createXmlReport"/>
                <form:form action="${createXmlUrl}" method="get" modelAttribute="reportRequest">
                    <div class="form-group">
                        <label for="users">Users:</label>
                        <input type="text" name="users" class="form-control" id="users" placeholder="Choose users">
                    </div>
                    <div class="form-group">
                        <label for="startDate">Start Date</label>
                        <input type="text" name="startDate" class="form-control" id="startDate" value="${startDate}"/>
                    </div>
                    <div class="form-group">
                        <label for="endDate">endDate</label>
                        <input type="text" name="endDate" class="form-control" id="endDate">
                    </div>

                    <div class="checkbox-inline">
                        <label class="checkbox-inline">
                            <input type="checkbox" name="types" value="1"> cold water
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="types" value="2"> hot water
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="types" value="3"> gas
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" name="types" value="4"> electricity
                        </label>
                    </div>

                    <div class="checkbox-inline">
                        <label class="checkbox-inline">
                            <input type="radio" name="paidStatus" value="2" checked> All
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="paidStatus" value="1"> Paid
                        </label>
                        <label class="checkbox-inline">
                            <input type="radio" name="paidStatus" value="0"> Not paid
                        </label>
                    </div>
                    <br><br>

                    <button type="submit" class="btn btn-default">Download XML-Report</button>
                </form:form>

            </div>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
