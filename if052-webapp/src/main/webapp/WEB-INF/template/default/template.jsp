<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Water Consumption Tracker</title>

    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/pace.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables_themeroller.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery.dataTables.min.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/tiles.css"/>"/>
    <link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/graphs.css"/>"/>

    <script type="text/javascript" src="<c:url value="/resources/js/jquery.js"/>"></script>

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
<script type="text/javascript" src="<c:url value="/resources/js/jquery.dataTables.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script type="text/javascript" >
    $(document).ready(function() {
        $('#paginated').dataTable({
            "lengthMenu":[ [5, 10, 15, 25, -1], [5, 10, 15, 25, "All"]],
            "info":     false,
            "columns": [
                null,
                null,
                { "orderable": false },
                { "orderable": false },
                { "orderable": false }
            ],
            "order": [[ 0, 'desc' ]],
            "language": {
                "url": "//cdn.datatables.net/plug-ins/f2c75b7247b/i18n/Ukranian.json"
            }
        });
    });
</script>
</html>