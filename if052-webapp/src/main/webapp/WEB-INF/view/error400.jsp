<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 09.03.2015
  Time: 0:45
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
                    ${reason}
                </div>
            </div>
        </div>
    </div>
</div>