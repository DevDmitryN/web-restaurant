<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.serviceSystem.entity.Dish" %>
<%@ page import="java.util.List" %>
<%@ page import="com.serviceSystem.entity.RestaurantTable" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07-Jun-19
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page for sending order</title>
    <%@include file="veiw/header.html"%>
</head>
<body>
<div>
    <%@include file="veiw/menu-nav-bar.jsp"%>
    <h1>Заполнение заказа</h1>
    <form method="post">
        <p>Меню:</p>

        <c:forEach var="dish" items="${dishes}">
            <p>
                    <%--<input type="checkbox" name=${dish.id} value=${dish.id}>--%>
                <input type="number" min="0" max="20" value="0" style=" width: 50px;" name=<c:out
                        value="dish_${dish.id}"/>> ${dish.name} Цена: ${dish.price}, Описание: ${dish.description}<br>
            </p>
        </c:forEach>

        <p>Выберите столик:</p>
        <p>
            <select required name="table">
                <c:forEach var="table" items="${tables}">
                    <option value=${table.id}>id: ${table.id} Мест: ${table.capacity}
                        Свободен: ${table.freeStatus}</option>
                </c:forEach>
            </select>
        </p>
        <p>Укажите время заказа: <input type="time" name="bookingTime" style="width: 100px; text-align: center;"></p>
        <input required type="submit" value="Отправить">
    </form>
</div>

</body>
</html>
