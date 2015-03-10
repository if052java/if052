<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 2/11/2015
  Time: 1:14 PM
  To change this template use File | Settings | File Templates.
  Author: Nazar Ostryzhniuk
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class = body>
            <div class="page-header">
                <h1>Sign In <small></small></h1>
                <h1>${message}</h1>
            </div>
            <form method="POST"
                  action="<c:url value='j_spring_security_check' />"
                  class="form-horizontal">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <div class="login">
                            <input name="name" type="text" class="form-control" id="inputEmail3" placeholder="Email">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <div class="login">
                            <input name="password" type="password" class="form-control" id="inputPassword3" placeholder="Password">
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>

