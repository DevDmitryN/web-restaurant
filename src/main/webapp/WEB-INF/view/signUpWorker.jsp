<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 7/9/2019
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация нового сотрудника</title>
    <%@include file="header.html"%>
</head>
<body>
    <%@include file="menu-nav-bar.jsp"%>
    <div class="default-padding-top">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-10">
                    <h2>Регистрация</h2>
                    <form id="signIn" action="frontController?command=sign_up" method="post">
                        <c:if test="${error == 'incorrectFields'}">
                            <span style="color: red">Не правильно заполнены поля</span>
                        </c:if>
                        <div class="form-group">
                            <label for="name">Имя</label>
                            <input name="name" required type="text" class="form-control signUp-field" id="name" placeholder="Введите имя">
                        </div>
                        <div class="form-group">
                            <label for="surname">Фамилия</label>
                            <input name="surname" required type="text" class="form-control signUp-field" id="surname" placeholder="Введите фамилию" >
                        </div>
                        <div class="form-group">
                            <label for="email">Электронная почта</label>
                            <c:if test="${error == 'existedEmail'}">
                                <span style="color: red">Пользователь с таким адрессом уже существует</span>
                            </c:if>
                            <input name="email" required type="email" class="form-control signUp-field" id="email" aria-describedby="emailHelp" placeholder="Адресс электронной почты">
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input name="password" required type="password" class="form-control signUp-field" id="password">
                        </div>
                        <div class="form-group">
                            <label for="confirm-password">Повторите пароль</label>
                            <input name="confirmPassword" required type="password" class="form-control signUp-field" id="confirm-password">
                            <span id="password-message" style="color: red;"></span>
                        </div>
                        <div class="form-group">
                            <label for="phoneNumber">Номер мобильного телефона</label>
                            <input name="phoneNumber" required pattern="^(44|29|25|17)(\s|-)?\d{3}(\s|-)?\d{2}(\s|-)?\d{2}$"
                                   type="tel" class="form-control signUp-field" id="phoneNumber" placeholder="XX XXX XX XX" title="С кодом оператора 44,29,25,17">
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="role" id="user" value="USER" checked>
                            <label class="form-check-label" for="user">
                                USER
                            </label>
                            <input class="form-check-input" type="radio" name="role" id="admin" value="ADMIN" checked>
                            <label class="form-check-label" for="admin">
                                ADMIN
                            </label>
                        </div>
                        <button  type="submit" class="btn btn-primary signUp-field">Добавить</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
<script src="scripts/check-signIn-fields.js"></script>
</body>
</html>
