<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 11.02.2015
  Time: 12:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">

        <div class="body">

            <div class="container">
                    <%--<p>Усі лічильники для адреси м.${address.city} вул. ${address.street} ${addrses.building}, кв. ${address.apartment}</p>--%>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Назва</th>
                        <th>Опис</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="waterMeter" items="${waterMeters}">
                    <tr>
                        <td><c:out value="${waterMeter.name}"/>  </td>
                        <td><c:out value="${waterMeter.description}"/></td>
                        <td><a href="/deleteWaterMeter?waterMeterId=${waterMeter.waterMeterId}">
                            <button>DELETE</button></a></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
