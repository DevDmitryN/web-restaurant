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
    <%@include file="header.jsp"%>
</head>
<body>
<%@include file="menu-nav-bar.jsp"%>
    <div class="default-padding-top">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <c:if test="${orders == null}">
                        <h3>Нет активных заказов</h3>
                    </c:if>
                    <c:forEach var="order" items="${orders}">
                        <p>Номер заказа: ${order.id}</p>
                        <p>Номер столика: ${order.table.id}</p>
                        <p>Сумма: ${order.totalPrice}</p>
                        <p>Статус заказа: ${order.status}</p>
                        <p>Время создания заказа: ${order.creationTime}</p>
                        <p>Время бронирования: ${order.bookingTimeBegin}</p>
                        <p>Список блюд:</p>
                        <c:forEach var="dish" items="${order.dishesInOrder}">
                            <p><bold>${dish.name}</bold>; Описание: ${dish.description}; Цена за одну порцию: ${dish.price}; Кол-во: ${dish.amount}</p>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${order.status == 'Не принят'}">
                                <form action="frontController">
                                    <input hidden name="command" value="show_dishes_of_order"/>
                                    <button type="submit" name="id" value="${order.id}" class="btn btn-success">Редактировать</button>
                                </form>
                                <form action="active/${order.id}/cancel" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <button type="submit" name="id" value="${order.id}" class="btn btn-danger">Отменить заказ</button>
                                </form>
                            </c:when>
                            <c:when test="${order.status == 'Выполняется'}">
                                <span style="color: red">Заказ выполняется, редактирование или отмена невозможны</span>
                            </c:when>
                        </c:choose>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
