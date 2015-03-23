<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 09.02.2015
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="header">

        <nav class="navbar navbar-default navbar-static-top">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header col-md-2">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">
                        <img alt="Brand" src="https://softserve.ua/wp-content/uploads/2014/01/logosoftserv.fw_1.png"/>
                    </a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <%--<li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>--%>
                        <li><a href="/">
                            <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                            Головна
                        </a></li>
                        <li><a href="/addresses?userId=1">
                            <span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
                            Мої адреси
                        </a></li>
                        <li><a href="/maingraph">
                            <span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
                            Моя статистика
                        </a></li>
                        <li><a href="#">
                            <span class="glyphicon glyphicon-info-sign" aria-hidden="true"></span>
                            Інфо
                        </a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                                <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                                <span class="caret"></span>
                            </a>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">
                                    <%--<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>--%>
                                    Налаштування
                                </a></li>
                                <li class="divider"></li>
                                <li class="sign-up" ><a href="/signup">
                                    <%--<span class="glyphicon glyphicon-user" aria-hidden="true"></span>--%>
                                    Реєстрація
                                </a></li>
                                <li class="log-in"><a href="/signin">
                                    <%--<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>--%>
                                    Вхід
                                </a></li>
                                <li class="log-out"><a href="#">
                                    <%--<span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>--%>
                                    Вихід
                                </a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>
</div>
