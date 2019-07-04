<%@ page import="com.serviceSystem.entity.Client" %>
<%@ page import="com.serviceSystem.entity.Worker" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02-Jul-19
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>This is profile page</title>
    <%@include file="header.html"%>
</head>
<body>
    <c:if test="${role == 'client'}">
        <h2>Hello client ${user.name}</h2>
    </c:if>
    <c:if test="${role == 'worker'}">
        <h2>hello worker ${user.name}</h2>
    </c:if>
    some text ${user.name}
</body>
</html>
