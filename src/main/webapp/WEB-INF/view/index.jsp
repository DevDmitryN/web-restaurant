<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07-Jun-19
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <%@include file="header.html"%>
<%--    <sec:csrfMetaTags />--%>
</head>
<body>
    <%@include file="menu-nav-bar.jsp"%>
    <sec:authentication property="principal.authorities" var="authorities" />
    <c:forEach items="${authorities}" var="authority" varStatus="vs">
        <p>${authority.authority}</p>
    </c:forEach>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
<%--    <h2>${user.name} ${user.surname}</h2><br>--%>
<%--    <h3>${user.email} ${user.phoneNumber}</h3>--%>
</body>
</html>