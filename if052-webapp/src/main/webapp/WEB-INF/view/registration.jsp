<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 2/9/15
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class ="body">
            <c:url var="addUrl" value="/addUser"/>
            <form:form 
                    action="${addUrl}" method="post" modelAttribute="user" id="registrationForm" class="form-horizontal">
                <div class="form-group">
                    <label class="col-xs-3 control-label">First name</label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="firstName" placeholder="First name" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Last name </label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="surname" placeholder="Last name" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Middle name </label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="middleName" placeholder="Middle name" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Login</label>
                    <div class="col-xs-5">
                        <input id="login" type="text" class="form-control" name="login" placeholder="login"/>
                        <div id="login-errors" class = "has-error"></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">Password</label>
                    <div class="col-xs-5">
                        <input type="password" class="form-control" name="password" id = "password" placeholder="password"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Confirm password</label>
                    <div class="col-xs-5">
                        <input type="password" class="form-control" name="confirmPassword" placeholder="Confirm password"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-9 col-xs-offset-3">
                        <button id="submit" type="submit" class="btn btn-primary" name="signup" value="Sign up">Submit</button>
                    </div>
                </div>
            </form:form>
        </div>
        
        <script src="/resources/js/jquery.js"></script>
        <script src="/resources/js/jquery-validate.js"></script>
        <script type="text/javascript">
            var restURL = '${restUrl}';
        </script>
        <script src="/resources/js/registration.js"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>