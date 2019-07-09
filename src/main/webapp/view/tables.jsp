<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 7/9/2019
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Столики</title>
    <%@include file="header.html" %>
</head>
<body>
<%@include file="menu-nav-bar.jsp" %>
<div class="default-padding-top">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <table class="table-full-width">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Номер</th>
                            <th scope="col">Вместимость</th>
                            <th scope="col">Статус</th>
                            <th scope="col"></th>
                        <tr/>
                    </thead>
                    <tbody>
                    <c:forEach var="table" items="${tables}">
                    <tr>
                        <th scope="row">${table.id}</th>
                        <td>${table.capacity}</td>
                        <td>${table.getFreeStatusAsString()}</td>
                        <td>
                            <form action="frontController?command=change_table_status&capacity=${table.capacity}&status=${table.freeStatus}" method="post">
                                <button type="submit" name="id" value="${table.id}" class="btn btn-secondary">Изменить статус</button>
                            </form>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
