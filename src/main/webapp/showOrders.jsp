<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 18-Jun-19
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
    <%@include file="veiw/header.html" %>
</head>
<body>
<%
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
%>
<%@include file="veiw/menu-nav-bar.jsp" %>
<div id="order_filter">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-6">
                <form method="post" class="form-inline">
                    <label for="table-selector" class="right-margin">Столики</label>
                    <select name="tableId" class="form-control right-margin" style="width: 100px" id="table-selector">
                            <option value="all">Все</option>
                        <c:forEach var="table" items="${tables}">
                            <option>${table.id}</option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="right-margin btn btn-outline-primary">Найти заказы по столику</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="list-of-orders">
    <div class="container-fluid">
        <div class="row">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Номер</th>
                    <th scope="col">номер столика</th>
                    <th scope="col">клиент</th>
                    <th scope="col">работник</th>
                    <th scope="col">создан</th>
                    <th scope="col">заказанное время</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                <tr/>
                </thead>
                <tbody>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <th scope="row">${order.id}</th>
                        <td>${order.table.id}</td>
                        <td>${order.client.email}</td>
                        <td>${order.worker.name} ${order.worker.surname}</td>
                        <td>${order.creationTime}</td>
                        <td>${order.bookingTime}</td>
                        <td>
                            <form action="/showDishesOfOrder" method="get">
                                <button type="submit" name="id" value="${order.id}" class="btn btn-success">Список блюд</button>
                            </form>
                        </td>
                        <td>
                            <form action="/deleteOrder" method="post">
                                <button type="submit" name="id" value="${order.id}" class="btn btn-danger">Удалить</button>
                            </form>
                        </td>
                        <td>
                            <form action="/updateOrder" method="get">
                                <button type="submit" name="id" value="${order.id}" class="btn btn-secondary">Редактировать</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<%--    <h2>Заказы</h2>--%>
<%--    <c:forEach var="order" items="${orders}">--%>
<%--        <ul>--%>
<%--            <li>id: ${order.id}, столик: ${order.table.id}--%>
<%--                клиент: ${order.client.id}, работник: ${order.worker.id}--%>
<%--                время поступления: ${order.creationTime}, на какое время: ${order.bookingTime}--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--        <hr/>--%>
<%--    </c:forEach>--%>
</body>
</html>
