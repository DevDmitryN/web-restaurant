<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.serviceSystem.entity.Dish" %>
<%@ page import="java.util.List" %>
<%@ page import="com.serviceSystem.entity.RestaurantTable" %>
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
    <%@include file="/view/header.html" %>
</head>
<body>

<%@include file="/view/menu-nav-bar.jsp" %>
<div class="default-padding-top">
    <div class="container">
        <div class="row">
            <div class="col-md-10">
                <h1>Заполнение заказа</h1>
                <form method="post">
                    <div>
                        <label>Меню:</label>
                        <c:forEach var="dish" items="${orderDishComposites}">
                            <div>
                                    <%--<input type="checkbox" name=${dish.id} value=${dish.id}>--%>
                                <input type="number" min="0" max="20" value="0" style=" width: 50px;"
                                       name="dish_${dish.id}" <%--name=<c:out value="dish_${dish.id}"/>--%>>
                                    ${dish.name} Цена: ${dish.price}, Описание: ${dish.description}<br>
                            </div>
                        </c:forEach>
                    </div>

                    <div class="default-padding-top">
                        <label for="table">Выберите столик:</label>
                        <select required name="table" id="table">
                            <c:forEach var="table" items="${tables}">
                                <option value=${table.id}>id: ${table.id} Мест: ${table.capacity}
                                    Свободен: ${table.getFreeStatusAsString()}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="default-padding-top">Укажите время заказа:
                        <label for="hour">Час</label>
                        <select name="hour" id="hour">
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                        </select>
                        <label for="minuts">Минуты</label>
                        <select name="minutes" id="minuts">
                            <option value="0">00</option>
                            <option value="30">30</option>
                        </select>
                    </div>
                    <div class="default-padding-top">
                        <label for="month">Месяц</label>
                        <select name="month" id="month">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                        </select>
                        <label for="day">День</label>
                        <select name="day" id="day">
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                            <option value="10">10</option>
                            <option value="11">11</option>
                            <option value="12">12</option>
                            <option value="13">13</option>
                            <option value="14">14</option>
                            <option value="15">15</option>
                            <option value="16">16</option>
                            <option value="17">17</option>
                            <option value="18">18</option>
                            <option value="19">19</option>
                            <option value="20">20</option>
                            <option value="21">21</option>
                            <option value="22">22</option>
                            <option value="23">23</option>
                            <option value="24">24</option>
                            <option value="25">25</option>
                            <option value="26">26</option>
                            <option value="27">27</option>
                            <option value="28">28</option>
                            <option value="29">29</option>
                            <option value="30">30</option>
                            <option value="31">31</option>
                        </select>
                        <label>Год: ${year}</label>
                    </div>
                    <div class="default-padding-top">
                        <button  type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
