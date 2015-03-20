<%--
  Created by IntelliJ IDEA.
  User: nazar
  Date: 3/19/15
  Time: 10:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <br><br><br><br><br>
            ${resource}
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>