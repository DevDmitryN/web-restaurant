<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: WAITER
  Date: 7/9/2019
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сотрудники</title>
    <%@include file="header.html" %>
</head>
<body>
<%@include file="menu-nav-bar.jsp"%>
<div>
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="table">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Номер</th>
                        <th scope="col">Имя</th>
                        <th scope="col">Фамилия</th>
                        <th scope="col">email</th>
                        <th scope="col">Телефон</th>
                        <th scope="col">Права</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="worker" items="${workers}">
                        <tr>
                            <th scope="row">${worker.id}</th>
                            <td>${worker.name}</td>
                            <td>${worker.surname}</td>
                            <td>${worker.email}</td>
                            <td>${worker.phoneNumber}</td>
                            <td>${worker.role}</td>
                            <td>
                                <form action="frontController?command=change_worker_data">
                                    <button name="id" value="${worker.id}" type="button" class="btn btn-info">Изменить</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
