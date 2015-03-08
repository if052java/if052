<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.02.2015
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header">
    <nav class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2" id="header-left">
                    <a class="navbar-brand" href="#">
                        <img class="img-responsive" src="https://softserve.ua/wp-content/uploads/2014/01/logosoftserv.fw_1.png"/>
                    </a>
                </div>
                <div class = "col-lg-8" id="header-center">
                    <ul class="nav navbar-nav">
                        <li class="home"><a href="/">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                            Головна
                        </a></li>
                        <li class="addresses"><a href="/addresses?userId=1">
                            <span class="glyphicon glyphicon-list" aria-hidden="true"></span>
                            Мої адреси
                        </a></li>
                        <li class="addresses"><a href="/maingraph">
                            <span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
                            Моя статистика
                        </a></li>
                        <li class="config"><a href="#">
                            <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                            Налаштування
                        </a></li>
                        <li class="info"><a href="#">
                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                            Інфо
                        </a></li>
                    </ul>
                </div>
                <div class="col-lg-2" id="header-right">
                    <ul class="nav navbar-nav">
                        <li class="sign-up" ><a href="/signup">
                            <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                            Sign Up
                        </a></li>
                        <li class="log-in"><a href="/login">
                            <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
                            Log In
                        </a></li>
                        <li class="log-out"><a href="#">
                            <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
                            Log Out
                        </a></li>
                    </ul>
                </div>
            </div>
        </div>
    </nav>
</div>
