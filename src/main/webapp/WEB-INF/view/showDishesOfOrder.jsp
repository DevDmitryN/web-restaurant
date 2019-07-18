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
    <title>Dishes for order ${orderId}</title>
    <%@include file="header.html" %>
</head>
<body>
<%@include file="menu-nav-bar.jsp" %>
<div class="info-row">
    Список блюд для заказа номер: ${orderId}.
</div>
<div id="list-of-orders">
    <div class="container-fluid">
        <div class="row">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Номер</th>
                    <th scope="col">Название</th>
                    <th scope="col">Цена</th>
                    <th scope="col">Описание</th>
                    <th scope="col">Количество</th>
                <tr/>
                </thead>
                <tbody>
                <c:forEach var="dish" items="${orderDishComposites}">
                    <tr>
                        <th scope="row">${dish.id}</th>
                        <td>${dish.name}</td>
                        <td>${dish.price}</td>
                        <td>${dish.description}</td>
                        <td>${dish.amount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>