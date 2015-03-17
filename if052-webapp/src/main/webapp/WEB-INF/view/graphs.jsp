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

<link rel="stylesheet" href="<c:url value="/resources/css/graphs.css"/>"/>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div>
            <div id="container" class="graphs-size"></div>
        
            <c:url var="drawByMonth" value="/graphByMonth" />

            <div id="chooseMonth" class = "check-box">
                <form:form action="${drawByMonth}">
                        Please choose an option :
                    <select name="month"  >
                        <option value="1">January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                    <select name="year">
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                        <option value="2017">2017</option>
                    </select>
                    <select id="waterMeter">
                    </select>
                        <div>
                            <button id = 'submit' type="submit" name="draw" >Draw</button>
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
