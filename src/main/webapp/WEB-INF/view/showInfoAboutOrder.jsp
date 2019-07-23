<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21-Jun-19
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dishes for order ${order.id}</title>
    <%@include file="header.jsp" %>
</head>
<body>
<%@include file="menu-nav-bar.jsp" %>
<div class="info-row">
    Список блюд для заказа номер: ${order.id}
</div>
<div id="list-of-orders">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <c:forEach items="${order.dishes}" var="dish">
                    <p>${dish}</p>
                    <hr>
                </c:forEach>
                <p>Общая сумма: ${order.totalPrice}</p>
                <p>Статус: ${order.status}</p>
                <p>Столик: ${order.table.id}</p>
                <p>Клиент: ${order.client}</p>
                <p>Обслуживал: ${order.worker}</p>
                <p>Время создания: ${order.creationTime}</p>
                <p>Время бронирования: ${order.bookingTime}</p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
