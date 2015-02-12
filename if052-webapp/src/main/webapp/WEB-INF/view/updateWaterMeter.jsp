<%--
  Created by IntelliJ IDEA.
  User: Danylo Tiahun
  Date: 12.02.2015
  Time: 0:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
        <div class="body">

        <h1> UPDATE FORM WILL BE HERE </h1>

            <form:form action="/updateWaterMeter" method="post" modelAttribute="waterMeter">
                <table class="box-table-a">
                    <caption> Update Water Meter</caption>
                    <thead>
                    <tr>
                        <th> Name </th>
                        <th> Description </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>
                            <input type="hidden" name="waterMeterId" value="${waterMeter.waterMeterId}"/>
                            <input type="text" name="name" value="${waterMeter.name}"/>
                        </td>
                        <td> <input type="text" name="description" value="${waterMeter.description}"/></td>
                        <td>
                            <button class="add-button" type="submit">update</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form:form>




        </div>
    </tiles:putAttribute>
</tiles:insertDefinition>
