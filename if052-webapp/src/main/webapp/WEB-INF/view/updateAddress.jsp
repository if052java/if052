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

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">

            <h1> UPDATE FORM FOR ADDRESS </h1>

            <c:url var="updateUrl" value="/updateAddress"/>

            <form:form
                    action="${updateUrl}" method="post" modelAttribute="address">
                <sec:csrfInput/>
                <table class="box-table-a">
                    <caption> Update Address</caption>
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
                            <input type="text" name="city" value="${address.city}" required title="Це поле обов'язкове"/>
                        </td>
                        <td> 
                            <input type="text" name="street" value="${address.street}" required title="Це поле обов'язкове"/>
                        </td>
                        <td>
                            <input type="text" name="building" value="${address.building} " required title="Це поле обов'язкове"/>
                        </td>
                        <td>
                            <input type="text" name="apartment" value="${address.apartment}" required title="Це поле обов'язкове"/>
                        </td>
                        <td>
                            <button class="add-button" type="submit">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>