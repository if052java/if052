<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 2/9/15
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <p></p>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Місто</th>
                    <th>Вулиця</th>
                    <th>Будинок</th>
                    <th>Квартира</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="address" items="${addresses}">
                    <tr>
                        <td><c:out value="${address.city}"/>  </td>
                        <td><c:out value="${address.street}"/></td>
                        <td><c:out value="${address.building}"/></td>
                        <td><c:out value="${address.apartment}"/></td>
                        <td><a ><button>UPDATE</button></a></td>
                        <td><button>DELETE</button></td>

                    </tr>
                </c:forEach>
            </table>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
