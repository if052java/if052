<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 2/13/15
  Time: 11:43 PM
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

            <h1> Редагування адреси </h1>

            <c:url var="updateUrl" value="/updateAddress"/>

            <form:form
                    action="${updateUrl}" method="post" id="updateAddress" modelAttribute="address">
                <sec:csrfInput/>
                <table class="box-table-a">
                    <thead>
                    <tr>
                        <th>Місто</th>
                        <th>Вулиця</th>
                        <th>Будинок</th>
                        <th>Квартира</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="hidden" name="addressId" value="${address.addressId}"/>
                            <input type="text" class="form-control" name="city" value="${address.city}" />
                        </td>
                        <td> 
                            <input type="text" class="form-control" name="street" value="${address.street}" />
                        </td>
                        <td>
                            <input type="text" class="form-control" name="building" value="${address.building} " />
                        </td>
                        <td>
                            <input class="form-control" name="apartment" value="${address.apartment}" />
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
            messages['maxlength.city'] = "<spring:message code='maxlength.city' javaScriptEscape='true' />"
            messages['maxlength.street'] = "<spring:message code='maxlength.street' javaScriptEscape='true' />"
            messages['maxlength.building'] = "<spring:message code='maxlength.building' javaScriptEscape='true' />"
            messages['required.field'] = "<spring:message code='required.field' javaScriptEscape='true' />"
            messages['number.min'] = "<spring:message code='number.min' javaScriptEscape='true' />"
            messages['number.field'] = "<spring:message code='number.field' javaScriptEscape='true' />"
        </script>
        <script src="/resources/js/updateAddress.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>