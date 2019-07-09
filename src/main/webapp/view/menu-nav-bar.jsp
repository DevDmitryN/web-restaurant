<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 20-Jun-19
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
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
                <a class="nav-link" href="index.jsp">Главная <span class="sr-only">(current)</span></a>
            </li>
            <c:if test="${role == 'client'}">
                <li class="nav-item">
                    <a class="nav-link" href="makeOrder">Сделать заказ</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="frontController?command=show_active_orders">Активные заказы</a>
                </li>
            </c:if>
            <c:if test="${role == 'worker' || role == 'admin'}">
                <li class="nav-item">
                    <a class="nav-link" href="frontController?command=show_orders">Заказы</a>
                </li>
                <li class="nav-item">
                    <a href="frontController?command=show_tables" class="nav-link">Столики</a>
                </li>
            </c:if>
            <c:if test="${role == 'admin'}">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Расширенные настройки
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="frontController?command=show_staff">Сотрудники</a>
                        <a class="dropdown-item" href="#">Клиенты</a>
                        <a class="dropdown-item" href="signUpWorker.jsp">Добавить сотрудника</a>
                    </div>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="frontController?command=logout">Выйти</a>
            </li>
        </ul>
    </div>
</nav>