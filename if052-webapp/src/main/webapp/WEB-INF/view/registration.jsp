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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class ="body">
            <c:url var="addUrl" value="/addUser"/>
            <form:form 
                    action="${addUrl}" method="post" modelAttribute="user" id="registrationForm" class="form-horizontal">
                <sec:csrfInput/>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message 
                                code="label.firstname">
                        </spring:message>
                        
                    </label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="name" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message
                                code="label.surname">
                        </spring:message>
                    </label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="surname" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message
                                code="label.middleName">
                        </spring:message>
                    </label>
                    <div class="col-xs-5">
                        <input type="text" class="form-control" name="middleName" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message
                                code="label.email">
                        </spring:message>
                    </label>
                    <div class="col-xs-5">
                        <input type="email" class="form-control" name="email" />
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message
                                code="label.login">
                        </spring:message>
                    </label>
                    <div class="col-xs-5">
                        <input id="login" type="text" class="form-control" name="login"/>
                        <div id="login-errors" class ='login-error'></div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message
                                code="label.password">
                        </spring:message>
                    </label>
                    <div class="col-xs-5">
                        <input type="password" class="form-control" name="password" id = "password" />
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">
                        <spring:message
                                code="label.confirmPassword">
                        </spring:message>
                    </label>
                    <div class="col-xs-5">
                        <input type="password" class="form-control" name="confirmPassword" />
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-9 col-xs-offset-3">
                        <button id="submit" name="signup" type="submit" class="btn btn-primary" name="sign up" value="">
                            <spring:message
                                    code="button.signup">
                            </spring:message>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>

        <script src="/resources/js/jquery/jquery-validate.js"></script>
        <script type="text/javascript">
            var restURL = '${restUrl}';
            var strings =  new Array()
            strings['required.field'] = "<spring:message code='required.field' javaScriptEscape='true' />"
            strings['rangelength.name'] = "<spring:message code='rangelength.name' javaScriptEscape='true' />"
            strings['rangelength.surname'] = "<spring:message code='rangelength.surname' javaScriptEscape='true' />"
            strings['rangelength.middleName'] = "<spring:message code='rangelength.middleName' javaScriptEscape='true' />"
            strings['rangelength.login'] = "<spring:message code='rangelength.login' javaScriptEscape='true' />"
            strings['rangelength.password'] = "<spring:message code='rangelength.password' javaScriptEscape='true' />"
            strings['rangelength.confirmPassword'] = "<spring:message code='rangelength.confirmPassword' javaScriptEscape='true' />"
            strings['equalTo.confirmPassword'] = "<spring:message code='equalTo.confirmPassword' javaScriptEscape='true' />"
            strings['email.email'] = "<spring:message code='email.email' javaScriptEscape='true' />"
        </script>
        <script src="/resources/js/registration.js"></script>
        
    </tiles:putAttribute>
</tiles:insertDefinition>