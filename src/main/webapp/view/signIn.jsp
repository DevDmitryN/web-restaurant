<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05-Jul-19
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Sign In</title>
    <%@include file="header.html" %>
</head>
<body>
<div style="padding-top: 2em;">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <h2>Регистрация</h2>
                <form action="frontController?command=sign_in" method="post">
                    <div class="form-group">
                        <label for="name">Имя</label>
                        <input name="name" required type="text" class="form-control signIn-field" id="name" placeholder="Введите имя">
                    </div>
                    <div class="form-group">
                        <label for="surname">Фамилия</label>
                        <input name="surname" required type="text" class="form-control signIn-field" id="surname" placeholder="Введите фамилию" >
                    </div>
                    <div class="form-group">
                        <label for="email">Электронная почта</label>
                        <c:if test="${error == 'error'}">
                            <span style="color: red">Пользователь с таким адрессом уже существует</span>
                        </c:if>
                        <input name="email" required type="email" class="form-control signIn-field" id="email" aria-describedby="emailHelp" placeholder="Адресс электронной почты">
                    </div>
                    <div class="form-group">
                        <label for="password">Пароль</label>
                        <input name="password" required type="password" class="form-control signIn-field" id="password">
                    </div>
                    <div class="form-group">
                        <label for="confirm-password">Повторите пароль</label>
                        <input required type="password" class="form-control signIn-field" id="confirm-password">
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Номер мобильного телефона</label>
                        <input name="phoneNumber"required pattern="\A((44)|(29)|(25)|(17))-?(\d){3}-?(\d){2}-?(\d){2}$\z"
                               type="tel" class="form-control signIn-field" id="phoneNumber" placeholder="XX XXX XX XX" title="С кодом оператора 44,29,25,17">
                    </div>
                    <div class="form-group">
                        <label for="cardNumber">Номер банковской карточки</label>
                        <input name="cardNumber" required pattern="\A([A-Z0-9]\s?){16}\z" type="text" class="form-control signIn-field" id="cardNumber" placeholder="XXXX XXXX XXXX XXXX">
                    </div>
                    <button type="submit" class="btn btn-primary signIn-field">Зарегистрироваться</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
