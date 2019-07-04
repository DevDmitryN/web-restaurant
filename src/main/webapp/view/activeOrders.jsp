<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04-Jul-19
  Time: 14:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Active orders</title>
    <%@include file="header.html"%>
</head>
<body>
<%@include file="menu-nav-bar.jsp"%>
    <div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:forEach var="order" items="${orders}">
                        <p>Номер заказа: ${order.id}</p>
                        <p>Номер столика: ${order.table.id}</p>
                        <p>Сумма: ${order.totalPrice}</p>
                        <p>Статус заказа:
                            <c:choose>
                                <c:when test="${order.status == 'NOT_TAKEN'}"><span>Не принят</span></c:when>
                                <c:when test="${order.status == 'BEING_PERFORMED'}"><span>Выполняется</span></c:when>
                            </c:choose>
                        </p>
                        <p>Время создания заказа: ${order.creationTime}</p>
                        <p>Время бронирования: ${order.bookingTime}</p>
                        <p>Список блюд:</p>
                        <c:forEach var="dish" items="${order.dishes}">
                            <p><bold>${dish.name}</bold>; Описание: ${dish.description}; Цена за одну порцию: ${dish.price}; Кол-во: ${dish.amount}</p>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${order.status == 'NOT_TAKEN'}">
                                <form action="frontController">
                                    <input hidden name="command" value="show_dishes_of_order"/>
                                    <button type="submit" name="id" value="${order.id}" class="btn btn-success">Редактировать</button>
                                </form>
                                <form action="frontController">
                                    <input hidden name="command" value="cancel_order">
                                    <button type="submit" name="id" value="${order.id}" class="btn btn-danger">Отменить заказ</button>
                                </form>
                            </c:when>
                            <c:when test="${order.status == 'BEING_PERFORMED'}">
                                <span style="color: red">Заказ выполняется, редактирование или отмена невозможны</span>
                            </c:when>
                        </c:choose>

                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
