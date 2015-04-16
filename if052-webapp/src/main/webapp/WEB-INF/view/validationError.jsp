<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 4/16/15
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="<c:url value="/resources/css/error.css"/>"/>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1>
                    Oops!</h1>
                <h2>
                    400 Bad Request</h2>
                <div class="error-details">
                    Sorry, your request is invalid.
                </div>
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Field</th>
                        <th>Validation error</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="fieldError" items="${fieldErrors}">
                    <tr>
                        <td><c:out value="${fieldError.field}"/>  </td>
                        <td><c:out value="${fieldError.message}"/></td>

                    </tr>
                    </c:forEach>
            </div>
        </div>
    </div>
</div>
