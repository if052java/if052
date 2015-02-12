<%--
  Created by IntelliJ IDEA.
  User: Maksym
  Date: 07.02.2015
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">


        <div class="body">

            <div class="container">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Дата</th>
                        <th>Значення</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="indocators" items="${indicators}">
                    <tr>
                        <td><c:out value="${indocators.date}"/>  </td>
                        <td><c:out value="${indocators.value}"/></td>
                        <td><button>EDIT</button></td>
                        <td><button>DELETE</button></td>
                    </tr>
                    </c:forEach>
                </table>
            </div>


        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
