<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 7/18/2019
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 th:text="#{label.form.title}">form</h1>
<form action="/" th:object="${user}" method="POST" enctype="utf8">
    <div>
        <label th:text="#{label.user.firstName}">first</label>
        <input th:field="*{firstName}"/>
        <p th:each="error: ${#fields.errors('firstName')}"
           th:text="${error}">Validation error</p>
    </div>
    <div>
        <label th:text="#{label.user.lastName}">last</label>
        <input th:field="*{lastName}"/>
        <p th:each="error : ${#fields.errors('lastName')}"
           th:text="${error}">Validation error</p>
    </div>
    <div>
        <label th:text="#{label.user.email}">email</label>
        <input type="email" th:field="*{email}"/>
        <p th:each="error : ${#fields.errors('email')}"
           th:text="${error}">Validation error</p>
    </div>
    <div>
        <label th:text="#{label.user.password}">password</label>
        <input type="password" th:field="*{password}"/>
        <p th:each="error : ${#fields.errors('password')}"
           th:text="${error}">Validation error</p>
    </div>
    <div>
        <label th:text="#{label.user.confirmPass}">confirm</label>
        <input type="password" th:field="*{matchingPassword}"/>
    </div>
    <button type="submit" th:text="#{label.form.submit}">submit</button>
</form>
</body>
</html>
