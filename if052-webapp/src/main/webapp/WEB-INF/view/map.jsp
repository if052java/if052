<%--
  Created by IntelliJ IDEA.
  User: Maksym
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">
            <div class="container">
                <input value="${gMapData}" hidden="hidden" id="gMapData">

                <div id="map-canvas"></div>
            </div>
        </div>

        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/google-map.js'/>"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>