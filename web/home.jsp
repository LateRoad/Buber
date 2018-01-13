<%--
  Created by IntelliJ IDEA.
  com.lateroad.buber.logic.entity.User: Roula
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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="js/googlemap.js"></script>
    <title>Home</title>
</head>
<body>
<div id="left"><ctg:sideMenu user="${user}"/></div>

<div id="right">
    <div id="map"></div>
    <form method="POST" action="/userServlet" style="width: 500px">
        <div class="form-group">
            <label for="pointFrom">Ваше местоположение</label>
            <input type="text" class="form-control" id="pointFrom"
                   placeholder="Беларусь, Минск, Сморговский проезд, 29" name="pointFrom">
        </div>
        <div class="form-group">
            <label for="pointTo">Место назначения</label>
            <input type="text" class="form-control" id="pointTo" placeholder="Беларусь, Минск, проспект Пушкинский, 19"
                   name="pointTo">
        </div>
    </form>
    <button type="button" id="getRouteInfoBtn"  class="btn btn-primary" disabled onclick="calculateAndDisplayRoute()">Рассчитать стоимость поездки</button>

    <c:if test="${user.role == \"client\" }">
    </c:if>
</div>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYpnDCcwayuy4jZ-1IzCUpg3AHFVO80Is&callback=initMap&language=ru">
</script>
</body>
</html>
