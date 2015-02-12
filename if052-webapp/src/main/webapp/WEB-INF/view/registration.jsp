<%--
  Created by IntelliJ IDEA.
  User: valentyn
  Date: 2/9/15
  Time: 10:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="container">
            <div class="row">
                <form role="form" action="/addUser" method="post" id="user">
                    <div class="col-lg-6">
                        <div class="well well-sm"><strong><span class="glyphicon glyphicon-asterisk"></span>Required Field</strong></div>
                        <div class="form-group">
                            <label >Enter Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control"  name="name" id="InputName" placeholder="Name" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label >Last Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="lastName" id="InputLastName" placeholder="Enter Last Name" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label >Middle Name</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="middleName" id="InputMiddleName" placeholder="Middle Name" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label >Login</label>
                            <div class="input-group">
                                <input type="text" class="form-control" name="login" id="InputLogin" placeholder="Login" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label >Enter Password</label>
                            <div class="input-group">
                                <input type="password" class="form-control" id="InputPasswordFirst" name="password" placeholder="Enter password" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label >Confirm Password</label>
                            <div class="input-group">
                                <input type="" class="form-control" id="InputPasswordSecond" name="confirmPassword" placeholder="Confirm Password" required>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-asterisk"></span></span>
                            </div>
                        </div>
                        <input type="submit" name="submit" id="submit" value="Submit" class="btn btn-info pull-right">
                    </div>
                </form>
                <%--<div class="col-lg-5 col-md-push-1">--%>
                    <%--<div class="col-md-12">--%>
                        <%--<div class="alert alert-success">--%>
                            <%--<strong><span class="glyphicon glyphicon-ok"></span> Success!</strong>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>