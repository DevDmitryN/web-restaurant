<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 01-Jul-19
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Authorization page</title>
    <%@include file="header.html"%>
</head>
<body>
    <div>
        <div class="container-fluid">
            <div class="row align-items-center justify-content-center fullscreen">
                <div class="col-md-3">
                    <form action="frontController" method="get">
                        <input hidden name="command" value="authorization">
                        <c:if test="${error == true}">
                            <span style="color: red">Не правильный email или пароль</span>
                        </c:if>
                        <div class="form-group">
                            <label for="email">Адрес электронной почты</label>
                            <input required name="email" type="email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="password">Пароль</label>
                            <input required name="password" type="password" class="form-control" id="password" placeholder="Password">
                        </div>
                        <button type="submit" class="btn btn-primary">Вход</button>
                        <a href="">Зарегистрироваться</a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
