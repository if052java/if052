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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div id="container" class="graphs-size"></div>
            <div id="error" class="error">${error}</div>
        
            <c:url var="drawByMonth" value="/graphByOption" />

            <div id="chooseMonth" class = "check-box">
                <form:form action="${drawByMonth}">
                <sec:csrfInput/>
                        Виберіть опцію :
                    <select name="month"  >
                        <option value="-1">
                            <spring:message
                                    code="option.month.0">
                            </spring:message>
                        </option>
                        <option value="1">
                            <spring:message
                                    code="option.month.1">
                            </spring:message>
                        </option>
                        <option value="2">
                            <spring:message
                                    code="option.month.2">
                            </spring:message>
                        </option>
                        <option value="3">
                            <spring:message
                                    code="option.month.3">
                            </spring:message>
                        </option>
                        <option value="4">
                            <spring:message
                                    code="option.month.4">
                            </spring:message>
                        </option>
                        <option value="5">
                            <spring:message
                                code="option.month.5">
                            </spring:message>
                        </option>
                        <option value="6">
                            <spring:message
                                    code="option.month.6">
                            </spring:message>
                        </option>
                        <option value="7">
                            <spring:message
                                    code="option.month.7">
                            </spring:message>
                        </option>
                        <option value="8">
                            <spring:message
                                    code="option.month.8">
                            </spring:message>
                        </option>
                        <option value="9">
                            <spring:message
                                    code="option.month.9">
                            </spring:message>
                        </option>
                        <option value="10">
                            <spring:message
                                code="option.month.10">
                            </spring:message>
                        </option>
                        <option value="11">
                            <spring:message
                                    code="option.month.11">
                            </spring:message>
                        </option>
                        <option value="12">
                            <spring:message
                                    code="option.month.12">
                            </spring:message>
                        </option>
                    </select>
                    <select name="year">
                        <option value="2015">2015</option>
                        <option value="2016">2016</option>
                    </select>
                    <select id="address" >
                        <option value="" >
                            <spring:message
                                    code="option.address.0">
                            </spring:message>
                        </option>
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
            var year = '${year}'
            var meterName = '${meterName}'
            var meterType = '${meterType}'
            var messages = new Array();
            messages['graphs.meter'] = "<spring:message code='graphs.meter' javaScriptEscape='true' />"
            messages['graphs.consumption'] = "<spring:message code='graphs.consumption' javaScriptEscape='true' />"
            messages['graphs.type'] = "<spring:message code='graphs.type' javaScriptEscape='true' />"
            messages['graphs.value'] = "<spring:message code='graphs.value' javaScriptEscape='true' />"

        </script>
        <script src="/resources/js/highcharts/graphs.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>

</body>
</html>
