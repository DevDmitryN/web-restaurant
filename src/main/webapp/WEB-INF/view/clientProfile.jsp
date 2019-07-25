<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 7/24/2019
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <%@include file="header.jsp"%>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <a href="/client/${user.id}/orders/active">Активные заказы</a>
            </div>
        </div>
    </div>
</body>
</html>
