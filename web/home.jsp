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
    <link href="src/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="src/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="src/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="src/css/sb-admin.css" rel="stylesheet">
    <title>Home</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-map-marker"></i> Location settings
            </div>
            <div class="card-body">
                <div id="map"></div>
                <c:if test="${user.role == \"client\" }">
                    <div class="form-group">
                        <label for="origin">Origin</label>
                        <input type="text" class="form-control" id="origin"
                               placeholder="Беларусь, Минск, Сморговский проезд, 29" name="origin">
                    </div>
                    <div class="form-group">
                        <label for="destination">Destination</label>
                        <input type="text" class="form-control" id="destination"
                               placeholder="Беларусь, Минск, проспект Пушкинский, 19"
                               name="destination">
                    </div>
                    <button type="button" id="getRouteInfoBtn" class="btn btn-primary"
                            onclick="calculateAndDisplayRoute()">
                        Рассчитать стоимость поездки
                    </button>
                </c:if>
            </div>
        </div>
        <c:if test="${user.role == \"client\" }">
        <div id="routeClientInfo">
            <div class="row">

                <div class="col-lg-8">
                    <!-- Drivers info Card-->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fa fa-car"></i> Drivers nearby
                        </div>
                        <div class="card-body">
                            <!-- Example Social Card-->
                            <c:forEach var="driver" items="${nearestDrivers}">
                                <div class="card mb-3">
                                    <div class="card-body">
                                        <h6 class="card-title mb-0"><a
                                                href="#">${driver.userInfo.name} ${driver.userInfo.surname}</a></h6>
                                    </div>
                                    <hr class="my-0">
                                    <div class="card-body py-2 small">
                                        <i class="fa fa-fw fa-thumbs-o-up"></i>
                                        Reputation: ${driver.userInfo.driverInfo.reputation}
                                        <br>
                                        <i class="fa fa-fw fa-handshake-o"></i> Trips
                                        count: ${driver.userInfo.driverInfo.tripsNumber}
                                    </div>
                                    <hr class="my-0">
                                    <div class="card-footer small text-muted">
                                        <button onclick="takeTaxi('${driver.login}', '${price}')" type="button"
                                                class="btn btn-primary">Take taxi
                                        </button>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">
                    <!-- Example Pie Chart Card-->
                    <div class="card mb-3">
                        <div class="card-header">
                            <i class="fa fa-location-arrow"></i> Route info
                        </div>
                        <div class="card-body">
                            Distance: ${distance}
                            <br>
                            Travel time: ${time}
                            <br>
                            Price: ${price}$
                            <br>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>
        </div>
    </div>
</div>


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

<!-- Bootstrap core JavaScript-->
<script src="src/vendor/jquery/jquery.min.js"></script>
<script src="src/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="src/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="src/js/sb-admin.js"></script>
<script src="src/js/googlemap.js"></script>
<script src="src/js/routeInfo.js"></script>
<script src="src/js/home.js"></script>
<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYpnDCcwayuy4jZ-1IzCUpg3AHFVO80Is&libraries=places&callback=initMap&language=ru">
</script>
</body>
</html>
