<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/8/2015
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="popups">
  <div class="container-fluid popups">

      <div class="warning alert reminder" hidden="hidden">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <strong>
            <spring:message
                    code="strong.attention">
            </spring:message>
        </strong>
          <div class="divider"></div>
          <spring:message
                  code="strong.indicatorsAtt">
          </spring:message>
      </div>

    <div class="warning alert map-warning" hidden="hidden">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
      <strong>
          <spring:message
                  code="strong.attention">
          </spring:message>
      </strong>
      <div class="divider"></div>
        <spring:message
            code="strong.addressAtt">
        </spring:message>
    </div>

  </div>
</div>
