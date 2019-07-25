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
            <sec:authorize url="/order/creating">
                <li class="nav-item">
                    <a class="nav-link" href="/order/creating">Сделать заказ</a>
                </li>
            </sec:authorize>
            <sec:authorize url="/orders/list">
                <li class="nav-item">
                    <a class="nav-link" href="/orders/list">Заказы</a>
                </li>
                <li class="nav-item">
                    <a href="/tables/list" class="nav-link">Столики</a>
                </li>
            </sec:authorize>
            <sec:authorize url="/client">
                <li class="nav-item">
                    <form action="/client" method="get">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-outline-dark">Профиль</button>
                    </form>
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