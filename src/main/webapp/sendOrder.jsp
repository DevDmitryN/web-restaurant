<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false"%>
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
</head>
<body>
<div>
    <h1>Заполнение заказа</h1>
    <form method="post">
        <p>Меню:</p>
        <p>
            <c:forEach var="dish" items="${dishes}">
                <input type="checkbox" name=${dish.id} value=${dish.id}> ${dish.name} Цена: ${dish.price}<br>
            </c:forEach>
        </p>
        <p>Выберите столик:</p>
        <p>
            <select name="table">
                <c:forEach var="table" items="${tables}">
                    <c:if test="${table.freeStatus == true}">
                        <option value=${table.id}>Мест: ${table.capacity}</option>
                    </c:if>
                </c:forEach>
            </select>
        </p>
        <input type="submit" value="Отправить">
    </form>
</div>

</body>
</html>
