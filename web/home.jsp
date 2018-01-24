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
    <!-- Bootstrap core CSS-->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin.css" rel="stylesheet">
    <title>Home</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <%--<div class="layout-content">--%>
        <%--<div class="layout-content-body">--%>
        <%--<div id="map"></div>--%>
        <%--</div>--%>
        <%--</div>--%>


        <%--<div id="routeClientInfo">--%>
        <%--<c:if test="${user.role == \"client\" }">--%>
        <%--<div class="form-group">--%>
        <%--<label for="pointFrom">Ваше местоположение</label>--%>
        <%--<input type="text" class="form-control" id="pointFrom"--%>
        <%--placeholder="Беларусь, Минск, Сморговский проезд, 29" name="pointFrom">--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
        <%--<label for="pointTo">Место назначения</label>--%>
        <%--<input type="text" class="form-control" id="pointTo"--%>
        <%--placeholder="Беларусь, Минск, проспект Пушкинский, 19"--%>
        <%--name="pointTo">--%>
        <%--</div>--%>
    </div>
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
<ctg:footer project="<small>b</small>Uber" developer="LateRoad" year="2018"/>

<script data-main="js/config" src="js/require.js"></script>
<script>
    require(['config']), function () {

    }
</script>
<script src="js/googlemap.js"></script>
<script src="js/routeInfo.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYpnDCcwayuy4jZ-1IzCUpg3AHFVO80Is&callback=initMap&language=ru">
</script>
</body>
</html>
