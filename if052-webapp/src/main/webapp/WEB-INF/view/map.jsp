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
                <a href="<c:url value='/addresses'/>">
                    <button class="btn btn-default btn-back" >
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        Назад
                    </button>
                </a>

                <div id="map-canvas"></div>
            </div>
        </div>

        <script type="text/javascript">
            var gMapData = '${gMapData}'
        </script>
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js"></script>
        <script type="text/javascript" src="<c:url value='/resources/js/google-map.js'/>"></script>
    </tiles:putAttribute>
</tiles:insertDefinition>