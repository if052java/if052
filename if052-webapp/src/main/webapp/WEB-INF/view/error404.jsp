<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 09.03.2015
  Time: 0:16
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
                    404 Not Found</h2>
                <div class="error-details">
                    Sorry, an error has occured, Requested ${resource} not found! </br>
                </div>
            </div>
        </div>
    </div>
</div>