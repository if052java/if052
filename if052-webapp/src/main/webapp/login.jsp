<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<c:url value="/" var="base"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">

                <c:if test="${not empty param.authentication_error}">
                    <h1>Woops!</h1>

                    <p class="error">Your login attempt was not successful.</p>
                </c:if>
                <c:if test="${not empty param.authorization_error}">
                    <h1>Woops!</h1>

                    <p class="error">You are not permitted to access that resource.</p>
                </c:if>

                <p>Credentials "user" and "password" </p>

                <form action="${base}login" method="post" role="form">
                    <fieldset>
                        <legend>
                            <h2>Login</h2>
                        </legend>
                        <div class="form-group">
                            <label for="username">Username:</label> <input id="username"
                                                                           class="form-control" type='text'
                                                                           name='username' value="user"/>
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label> <input id="password"
                                                                           class="form-control" type='text'
                                                                           name='password' value="password"/>
                        </div>
                        <button class="btn btn-primary" type="submit">Login</button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </fieldset>
                </form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>

</html>