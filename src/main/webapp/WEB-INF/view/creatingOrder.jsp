<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 07-Jun-19
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Page for sending order</title>
    <%@include file="header.jsp" %>
</head>
<body>

<%@include file="/WEB-INF/view/menu-nav-bar.jsp" %>
<div class="default-padding-top">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <h1>Заполнение заказа</h1>
                <form:form modelAttribute="creatingOrderForm" method="post" action="/order/creating">
                    <div>
                        <form:errors path="orderDishDtoList" cssClass="error"/><br>
                        <label>Меню:</label>
                        <c:forEach var="dish" items="${creatingOrderForm.orderDishDtoList}" varStatus="status">
                            <div>
                                <input hidden name="orderDishDtoList[${status.index}].id" value="${dish.id}" readonly>
                                <input type="number" name="orderDishDtoList[${status.index}].amount" value="${dish.amount}" min="0" max="20">
                                ${dish}
                                <br>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="default-padding-top">
                        <form:errors path="tables" cssClass="error"/><br>
                        <label for="tableId">Выберите столик:</label>
                        <form:select path="tableId">
                            <form:options items="${creatingOrderForm.tables}" itemValue="id"/>
                        </form:select>
                    </div>
                    <div class="default-padding-top">
                        <form:errors path="bookingTime" cssClass="error"/>
                        <p>Укажите время заказа:</p>
                        <label for="hour">Час</label>
                        <form:select path="hour">
                            <c:forEach begin="10" end="21" var="varHour">
                                <option value="${varHour}">${varHour}</option>
                            </c:forEach>
                        </form:select>
                        <label for="minutes">Минуты</label>
                        <form:select path="minutes">
                            <option value="0">00</option>
                            <option value="30">30</option>
                        </form:select>
                    </div>
<%--                    <div class="default-padding-top">--%>
<%--                        <label for="month">Месяц</label>--%>
<%--                        <form:select path="month">--%>
<%--                            <c:forEach begin="1" end="12" var="varMonth">--%>
<%--                                <option value="${varMonth}">${varMonth}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </form:select>--%>
<%--                        <label for="day">День</label>--%>
<%--                        <form:select path="day">--%>
<%--                            <c:forEach begin="1" end="31" var="varDay">--%>
<%--                                <option value="${varDay}">${varDay}</option>--%>
<%--                            </c:forEach>--%>
<%--                        </form:select>--%>
<%--                        <label>Год: ${creatingOrderForm.year}</label>--%>
<%--                        <form:input hidden="true" path="year" value="${creatingOrderForm.year}"/>--%>
<%--                    </div>--%>
                    <div class="default-padding-top">
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
