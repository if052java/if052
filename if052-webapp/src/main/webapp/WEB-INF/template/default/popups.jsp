<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/8/2015
  Time: 7:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="popups">
  <div class="container-fluid popups">

      <div class="warning alert reminder" hidden="hidden">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <strong>Увага!</strong>
          <div class="divider"></div>
          Ви не вносили показників більше 7 днів. Будь ласка, внесіть показник.
      </div>

    <div class="warning alert map-warning" hidden="hidden">
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
      <strong>Увага!</strong>
      <div class="divider"></div>
      Не вдалося знайти одну або декілька адрес.
    </div>

  </div>
</div>
