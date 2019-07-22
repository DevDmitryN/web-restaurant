<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20-Jun-19
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Logo</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="/">Главная <span class="sr-only">(current)</span></a>
            </li>
            <sec:authorize url="/orders/creating">
                <li class="nav-item">
                    <a class="nav-link" href="/orders/creating">Сделать заказ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="f#">Активные заказы</a>
                </li>
            </sec:authorize>
            <sec:authorize url="/list">
                <li class="nav-item">
                    <a class="nav-link" href="/list">Заказы</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">Столики</a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasRole('ADMIN')">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                       data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Расширенные настройки
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="#">Сотрудники</a>
                        <a class="dropdown-item" href="#">Клиенты</a>
                        <a class="dropdown-item" href="signUpWorker.jsp">Добавить сотрудника</a>
                    </div>
                </li>
            </sec:authorize>
            <form action="/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <li class="nav-item">
                    <button type="submit" class="btn btn-outline-dark">Выйти</button>
                </li>
            </form>
        </ul>
    </div>
</nav>