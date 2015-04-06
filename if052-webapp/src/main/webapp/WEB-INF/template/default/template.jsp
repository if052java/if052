<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Consumption Tracker</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/dataTables.bootstrap.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/pace.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/tiles.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/graphs.css"/>"/>

    <script type="text/javascript" src="<c:url value="/resources/js/jquery/jquery.js"/>"></script>

</head>
<body>
<div class="page">
    <tiles:insertAttribute name="header"/>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2"></div>
                <div class ="col-md-8">
                    <tiles:insertAttribute name="body"/>
                    <tiles:insertAttribute name="footer"/>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </div>
</div>
</body>

<script type="text/javascript" src="<c:url value="/resources/js/pace.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>


</html>