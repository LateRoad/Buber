<%--
  Created by IntelliJ IDEA.
  com.lateroad.buber.entity.User: Roula
  Date: 24.12.2017
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/sidemenu.css">
    <link rel="stylesheet" href="css/googlemap.css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,400italic,500,700">
    <link rel="stylesheet" href="css/vendor.min.css">
    <link rel="stylesheet" href="css/elephant.min.css">
    <link rel="stylesheet" href="css/docs.css">
    <title>Home</title>
</head>
<body>

<ctg:side-menu user="${user}"/>

<div class="layout-content">
    <div class="layout-content-body">
        <div id="map"></div>
    </div>
</div>


<div id="routeClientInfo">
<c:if test="${user.role == \"client\" }">
<div class="form-group">
<label for="pointFrom">Ваше местоположение</label>
<input type="text" class="form-control" id="pointFrom"
placeholder="Беларусь, Минск, Сморговский проезд, 29" name="pointFrom">
</div>
<div class="form-group">
<label for="pointTo">Место назначения</label>
<input type="text" class="form-control" id="pointTo"
placeholder="Беларусь, Минск, проспект Пушкинский, 19"
name="pointTo">
</div>

<%--<button type="button" id="getRouteInfoBtn" class="btn btn-primary" disabled--%>
<%--onclick="calculateAndDisplayRoute()">--%>
<%--Рассчитать стоимость поездки--%>
<%--</button>--%>
<%--Расстояние: ${distance}--%>
<%--<br>--%>
<%--Время: ${time}--%>
<%--<br>--%>
<%--Цена: ${price}.р--%>
<%--<br>--%>

<%--<tr>--%>
<%--<td>Водитель</td>--%>
<%--<td>Имя</td>--%>
<%--<td>Фамилия</td>--%>
<%--<td>Отчество</td>--%>
<%--<td>Репутация</td>--%>
<%--<td>Кол-во поездок</td>--%>
<%--</tr>--%>
<%--<c:forEach var="driver" items="${nearestDrivers}">--%>
<%--<form method="POST" action="/userServlet?driver=${driver.login}&money=${price}" style="width: 500px">--%>
<%--<c:out value="${driver.login}"/>--%>
<%--<c:out value="${driver.userInfo.name}"/>--%>
<%--<c:out value="${driver.userInfo.surname}"/>--%>
<%--<c:out value="${driver.userInfo.lastname}"/>--%>
<%--<c:out value="${driver.userInfo.driverInfo.reputation}"/>--%>
<%--<c:out value="${driver.userInfo.driverInfo.tripsNumber}"/>--%>

<%--<button name="action" value="takeTaxi" type="submit" class="btn btn-primary">Заказать такси--%>
<%--</button>--%>
<%--</form>--%>
<%--</c:forEach>--%>
<%--</c:if>--%>


<%--<c:if test="${user.role == \"driver\" }">--%>
<%--<button type="button" id="updateOrders" class="btn btn-primary" onclick="updateActiveOrders()">--%>
<%--Обновить доступные заказы--%>
<%--</button>--%>
<%--<div id="activeOrders">--%>
<%--<c:forEach var="order" items="${activeOrders}">--%>
<%--<form method="POST" action="/userServlet?orderId=${order.id}&client=${order.clientLogin}"--%>
<%--style="width: 500px">--%>
<%--<c:out value="${order.clientLogin}"/>--%>
<%--<button name="action" value="acceptOrder" type="submit" class="btn btn-primary">Принять заказ--%>
<%--</button>--%>
<%--</form>--%>
<%--</c:forEach>--%>
<%--</div>--%>
<%--</c:if>--%>
<%--</div>--%>
<%--</div>--%>
<script src="js/vendor.min.js"></script>
<script src="js/elephant.min.js"></script>
<script src="js/docs.js"></script>
<script src="js/demo.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/googlemap.js"></script>
<script src="js/routeInfo.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYpnDCcwayuy4jZ-1IzCUpg3AHFVO80Is&callback=initMap&language=ru">
</script>
</body>
</html>
