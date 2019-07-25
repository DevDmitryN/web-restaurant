<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


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
    <%@include file="header.jsp" %>
</head>
<body>
<%@include file="menu-nav-bar.jsp" %>
<div id="order_filter">
    <div class="container">
        <div class="row align-items-center justify-content-center">
            <div class="col-md-6">
                <form action="/orders/list" method="get" class="form-inline">
                    <label for="table-selector" class="right-margin">Столики</label>
                    <select name="table" class="form-control right-margin" style="width: 100px" id="table-selector">
                            <option value="all">Все</option>
                        <c:forEach var="table" items="${tables}">
                            <option value=${table.id}>${table.id}</option>
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
            <c:if test="${error != null}">
                <span class="error">Заказ уже принят</span>
            </c:if>
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Номер</th>
                    <th scope="col">Номер столика</th>
                    <th scope="col">Статус</th>
                    <th scope="col">Заказанное время</th>
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
                        <td>${order.status}</td>
                        <td>${order.bookingTime}</td>
                        <td>
                            <form action="/order/${order.id}"  method="get">
                                <button type="submit" class="btn btn-success">Полная информация</button>
                            </form>
                        </td>
                        <td>
                            <form action="/order/${order.id}/delete" method="post">
                                <button type="submit" class="btn btn-danger">Удалить</button>
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>
                        </td>
                        <td>
                            <c:if test="${order.isPossibleToChange()}">
                                <form action="/order/${order.id}/setWorker" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <c:choose>
                                        <c:when test="${order.worker == null}">
                                            <button type="submit" class="btn btn-secondary">Принять</button>
                                        </c:when>
                                        <c:when test="${order.worker.email == workerEmail}">
                                            <button type="submit" class="btn btn-secondary">Отказаться</button>
                                        </c:when>
                                    </c:choose>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>
