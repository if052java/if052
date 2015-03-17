<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Water Consumption Tracker</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/tiles.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/pace.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables_themeroller.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>"/>

    <script type="text/javascript" src="<c:url value="/resources/js/pace.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
    <script>
        $(document).ready(function() {
            $('#paginated').dataTable({
                "lengthMenu":[ [5, 10, 15, 25, -1], [5, 10, 15, 25, "All"]],
                "info":     false
            });
        });
    </script>
</head>
<body>
<div class="page">
    <tiles:insertAttribute name="header"/>
    <div class="content">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-2" id="body-left"></div>
                <div class = "col-lg-8" id="body-center"><tiles:insertAttribute name="body"/></div>
                <div class="col-lg-2" id="body-right"></div>
            </div>
        </div>
    </div>
    <tiles:insertAttribute name="footer"/>
</div>
</body>
</html>