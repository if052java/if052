<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 3/2/15
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div id="container" class="graphs-size"></div>
        
            <c:url var="drawByMonth" value="/graphByMonth" />

            <div id="chooseMonth" class = "check-box">
                <form:form action="${drawByMonth}">
                        Виберіть опцію :
                    <select name="month"  >
                        <option value="1">Січень</option>
                        <option value="2">Лютий</option>
                        <option value="3">Березень</option>
                        <option value="4">Квітень</option>
                        <option value="5">Травень</option>
                        <option value="6">Червень</option>
                        <option value="7">Липень</option>
                        <option value="8">Серпень</option>
                        <option value="9">Вересень</option>
                        <option value="10">Жовтень</option>
                        <option value="11">Листопад</option>
                        <option value="12">Грудень</option>
                    </select>
                    <select name="year">
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                    </select>
                    <select id="address" >
                        <option value="" >Виберіть адресу</option>
                        <c:forEach items="${addresses}" var="address">
                            <option value="${address.addressId}">${address.street}</option>
                        </c:forEach>
                    </select>
                    
                    <select id="waterMeter" name="meter">
                        
                    </select>

                        <div>
                            <button id = 'submit' type="submit" name="draw" >Змоделювати</button>
                        </div>
                    </div>
                </form:form>
        </div>
        <script src="/resources/js/highcharts/highcharts.js" type="text/javascript"></script>
        <script src="/resources/js/highcharts/exporting.js" type="text/javascript"></script>
        <script type="text/javascript">
            var indicatorsData = ${indicatorsData}
            var waterMeters =["bath","garage","toilet"]
        </script>
        <script src="/resources/js/highcharts/graphs.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>

</body>
</html>
