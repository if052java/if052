<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
	<div class="container">

		<c:if test="${not empty param.authentication_error}">
			<h1>Woops!</h1>

			<p class="error">Your login attempt was not successful.</p>
		</c:if>

		<form action="${base}restlogin.do" method="post" role="form">
			<fieldset>
				<legend>
					<h2>Login in webapp</h2>
				</legend>
				<div class="form-group">
					<label for="j_username">Username:</label> <input id="j_username"
						class="form-control" type='text' name='username' value="user" />
				</div>
				<div class="form-group">
					<label for="j_password">Password:</label> <input id="j_password"
						class="form-control" type='text' name='password' value="password" />
				</div>
				<button class="btn btn-primary" type="submit">Login</button>
                <input type="hidden" name="client_id" value="my-trusted-client-with-secret">
                <input type="hidden" name="client_secret" value="somesecret">
                <input type="hidden" name="grant_type" value="password">


				<%--<input type="hidden" name="${_csrf.parameterName}"--%>
					<%--value="${_csrf.token}" />--%>
			</fieldset>
		</form>

	</div>
    </tiles:putAttribute>
</tiles:insertDefinition>