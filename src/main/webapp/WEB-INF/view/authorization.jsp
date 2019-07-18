<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jul-19
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Authorization page</title>
    <%@include file="header.html"%>
<%--    <spring:theme code="styleSheet" var="app css" />--%>
<%--    <spring:url value="/${app_css)" var="app_css_url" />--%>
<%--    <link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}" />--%>
<%--    <link type="text/css" href="../../resources/style.css"/>--%>
<%--    <link type="text/css" href="resources/style.css"/>--%>
<%--    <link type="text/css" href="/resources/style.css"/>--%>
<%--    <link type="text/css" href="style.css"/>--%>
<%--    <base href="/">--%>
<%--    <link type="text/css" href="../../resources/style.css"/>--%>
<%--    <link type="text/css" href="resources/style.css"/>--%>
<%--    <link type="text/css" href="/resources/style.css"/>--%>
<%--    <link type="text/css" href="style.css"/>--%>
    <style>
        <%@include file="resources/style.css"%>
    </style>
</head>
<body>
    <div>
        <div class="container-fluid">
            <div class="row align-items-center justify-content-center fullscreen">
                <div class="col-md-3">
                    <form action="/authorization" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        <c:if test="${error == 'error'}">
                            <span style="color: red">Не правильный email или пароль</span>
                        </c:if>
                        <div class="form-group">
                            <label for="email">Адрес электронной почты</label>
                            <input required name="username" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input required name="password" type="password" class="form-control" id="password" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-primary">Вход</button>
                        <a href="signUp.jsp">Зарегистрироваться</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
