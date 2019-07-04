<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 21-Jun-19
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update order ${order.id}</title>
    <%@include file="header.html"%>
<%--    <link href='${pageContext.request.contextPath}/veiw/style.css'--%>
<%--          rel='stylesheet' type='text/css' />--%>
</head>
<body>
    <%@include file="menu-nav-bar.jsp"%>
    <div class="updated-block">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <form action="updateOrder" method="post">
                        <label>id: ${order.id}</label>
                        <div class="form-group">
                            <label for="table">Столик</label>
                            <select class="form-control form-selector" id="table" name="tableId">
                                <option value="${order.table.id}">id: ${order.table.id}, вместимость:${order.table.capacity} </option>
                                <c:forEach var="table" items="${tables}">
                                    <c:if test="${table.id != order.table.id}">
                                        <option value="${table.id}">id: ${table.id}, вместимость: ${table.capacity},свободен:${table.freeStatus}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>id клиента:</label>
                            <label>${order.client.id}</label>
                        </div>
                        <div class="form-group">
                            <label for="worker">Работник</label>
                            <select class="form-control form-selector" id="worker" name="workerId">
                                <c:if test="${order.worker.id != 0}">
                                    <option value="${order.worker.id}">${order.worker.id},${order.worker.surname} ${order.worker.name} </option>
                                </c:if>
                                <c:forEach var="worker" items="${workers}">
                                    <c:if test="${worker.id != order.worker.id}">
                                        <option value="${worker.id}">${worker.id},${worker.surname} ${worker.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>время создания:</label>
                            <label>${order.creationTime}</label>
                        </div>
                        <div class="form-group">
                            <label for="bookingTime">Время бронирования:</label>
                            <input id="bookingTime" type="time" name="bookingTime" style="width: 10em; text-align: center;">
                            <small class="form-text text-muted">>Если не надо менять время, оставить без изменений</small>
                        </div>
                        <button type="submit" class="btn btn-outline-primary">Принять</button>
                    </form>
                    <form action="frontController">
                        <input hidden name="command" value="show_orders">
                        <button type="submit" class="btn btn-outline-dark">Отмена</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
