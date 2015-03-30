<%--
  Created by IntelliJ IDEA.
  User: nazar
  Date: 3/30/15
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<title>JspForTest</title>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class ="body">
            <h1>JspForTest</h1>
            <br> userId     ${auth.userId}
            <br> username   ${auth.username}
            <br> password   ${auth.password}
            <br> role       ${auth.role}
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>