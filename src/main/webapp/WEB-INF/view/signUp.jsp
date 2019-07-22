<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
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
    <title>Sign Up</title>
    <%@include file="header.jsp" %>

</head>
<body>
<div class="default-padding-top">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-10">
                <h2>Регистрация</h2>
                <form:form id="signIn" action="/users/signUpForClient" method="post" modelAttribute="client">
                    <div class="form-group">
                        <form:errors path="name" cssClass="error"/> <br>
                        <label for="name">Имя</label>
                        <form:input path="name"  value="${client.name}" type="text" class="form-control signUp-field" id="name" placeholder="Введите имя"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="surname" cssClass="error"/> <br>
                        <label for="surname">Фамилия</label>
                        <form:input path="surname"  required="true" type="text" class="form-control signUp-field" id="surname" placeholder="Введите фамилию" />
                    </div>
                    <div class="form-group">
                        <form:errors path="email" cssClass="error"/> <br>
                        <label for="email">Электронная почта</label>
                        <form:input path="email"  type="email" class="form-control signUp-field" id="email" aria-describedby="emailHelp" placeholder="Адресс электронной почты"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="password" cssClass="error"/> <br>
                        <label for="password">Пароль</label>
                        <form:input path="password"   type="password" class="form-control signUp-field" id="password"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="confirmPassword" cssClass="error"/> <br>
                        <label for="confirm-password">Повторите пароль</label>
                        <form:input path="confirmPassword"  required="true" type="password" class="form-control signUp-field" id="confirm-password"/>
                        <span id="password-message" style="color: red;"></span>
                    </div>
                    <div class="form-group">
                        <form:errors path="phoneNumber" cssClass="error"/> <br>
                        <label for="phoneNumber">Номер мобильного телефона</label>
                        <form:input path="phoneNumber" required="true" pattern="^(44|29|25|17)(\s|-)?\d{3}(\s|-)?\d{2}(\s|-)?\d{2}$"
                               type="tel" class="form-control signUp-field" id="phoneNumber" placeholder="XX XXX XX XX" title="С кодом оператора 44,29,25,17"/>
                    </div>
                    <div class="form-group">
                        <form:errors path="cardNumber" cssClass="error"/> <br>
                        <label for="cardNumber">Номер банковской карточки</label>
                        <form:input path="cardNumber"  title="Введите данные в формате AAAA BBBB CCCC DDDD" required="true" pattern="^([A-Z0-9]\s?){16}$" type="text" class="form-control signUp-field" id="cardNumber" placeholder="XXXX XXXX XXXX XXXX"/>
                    </div>
                    <button  type="submit" class="btn btn-primary signUp-field">Зарегистрироваться</button>
                </form:form>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="scripts/check-signIn-fields.js"></script>
</html>
