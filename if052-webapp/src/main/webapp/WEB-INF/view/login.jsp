<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
</head>
<body>
<c:url value="/" var="base"/>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">

                <c:if test="${not empty param.authentication_error}">
                    <h1><spring:message code="authentication.error"/></h1>

                    <p class="error"><spring:message code="authentication.unsuccsessfull"/></p>
                </c:if>
                <c:if test="${not empty param.authorization_error}">
                    <h1><spring:message code="authorization.error"/></h1>

                    <p class="error"><spring:message code="authorization.hasNotGrants"/></p>
                </c:if>

                <form action="${base}login" method="post" role="form">
                    <fieldset>
                        <legend>
                            <h2><spring:message code="header.login"/></h2>
                        </legend>
                        <div class="row">
                            <div class="col-lg-4">
                                <div class="form-group">
                                    <label for="username"><spring:message code="login.login"/></label> <input id="username"
                                                                                class="form-control" type='text'
                                                                                name='username' value="LOGIN111"/>
                                </div>
                                <div class="form-group">
                                    <label for="password"><spring:message code="login.password"/></label> <input id="password"
                                                                                 class="form-control" type='password'
                                                                                 name='password' value="PASS1111"/>
                                </div>
                                <button class="btn btn-primary" type="submit"><spring:message code="login.siginin"/></button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </div>
                        </div>

                    </fieldset>
                </form>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
</body>
</html>