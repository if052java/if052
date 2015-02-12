<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/12/2015
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Profile Page | www.beingjavaguys.com</title>
</head>
<body>
<c:url value="/j_spring_security_logout" var="logoutUrl" />
    <h2>Profile Page | You are now logged in</h2>
    <h3><a href="${logoutUrl}">Logout</a></h3>
</body>
</html>