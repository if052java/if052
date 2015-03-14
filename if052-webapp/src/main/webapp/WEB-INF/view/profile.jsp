<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/12/2015
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <body>
        ${role.roleName}
        <c:url value="/logout" var="logoutUrl" />
            <h2>Profile Page | You are now logged in</h2>
            <h3><a href="${logoutUrl}">Logout</a></h3>
        </body>
        </html>
    </tiles:putAttribute>
</tiles:insertDefinition>