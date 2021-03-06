<%--
  Created by IntelliJ IDEA.
  java.lateroad.buber.entity.User: Roula
  Date: 24.12.2017
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translation"/>

<html lang="${language}">
<head>
    <!-- Bootstrap core CSS-->
    <link href="src/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="src/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="src/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="src/css/sb-admin.css" rel="stylesheet">
    <title><fmt:message key="title-home"/></title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-map-marker"></i><fmt:message key="location-settings"/>
            </div>
            <div class="card-body">
                <div id="map"></div>
                <c:if test="${user.role == \"CLIENT\" }">
                    <div id="location-settings">
                        <div class="form-group">
                            <label for="originInput"><fmt:message key="origin"/></label>
                            <input type="text" class="form-control" id="originInput"
                                   placeholder="<fmt:message key="origin-placeholder"/>" name="originInput">
                        </div>
                        <div class="form-group">
                            <label for="destinationInput"><fmt:message key="destination"/></label>
                            <input type="text" class="form-control" id="destinationInput"
                                   placeholder="<fmt:message key="destination-placeholder"/>"
                                   name="destinationInput">
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <c:if test="${user.role == \"CLIENT\" }">
            <div id="routeClientInfo">
                <div class="row">

                    <div class="col-lg-8">
                        <!-- Drivers info Card-->
                        <div class="card mb-3">
                            <div class="card-header">
                                <i class="fa fa-car"></i><fmt:message key="drivers-nearby"/>
                            </div>
                            <div class="card-body">
                                <c:forEach var="driver" items="${nearestDrivers}">
                                    <ctg:user-card user="${driver}">
                                        <button onclick="takeTaxi('${driver.getLogin()}', '${price}')" type="button"
                                                class="btn btn-primary"><fmt:message key="button-take-taxi"/></button>
                                    </ctg:user-card>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-4">
                        <ctg:route-info-card distance="${distance}" price="${price}" time="${time}"/>
                    </div>
                </div>
            </div>
        </c:if>


        <c:if test="${user.role == \"DRIVER\" }">
            <div id="activeOrders">
                <div class="card mb-3">
                    <div class="card-header">
                        <i class="fa fa-group"></i><fmt:message key="active-orders"/>
                    </div>
                    <div class="card-body">
                        <c:forEach var="order" items="${activeOrders}">
                            <ctg:active-order orderID="${order.key}" client="${order.value}"/>
                        </c:forEach>
                    </div>
                    <button type="button" id="updateOrders" class="btn btn-primary" onclick="updateActiveOrders()">
                        <fmt:message key="update-available-orders"/>
                    </button>
                </div>
            </div>
        </c:if>
    </div>
</div>

<!--Modal -->
<div id="modal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!--Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Заказ успешно выполнен.</h4>
            </div>
            <%--<div class="modal-body">--%>
            <%--<p>Наш водитель приедет за вами в ближайшее время.</p>--%>
            <%--</div>--%>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>

<ctg:footer project="<small>b</small>Uber" developer="LateRoad" year="2018"/>
<!-- Bootstrap core JavaScript-->
<script src="src/vendor/jquery/jquery.min.js"></script>
<script src="src/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="src/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="src/js/script.js"></script>
<script src="src/js/googlemap.js"></script>
<script src="src/js/role/user/driver.js"></script>
<script src="src/js/role/user/client.js"></script>
<script src="src/js/role/user/user.js"></script>
<script src="src/js/role/commonOperation.js"></script>

<script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAYpnDCcwayuy4jZ-1IzCUpg3AHFVO80Is&libraries=places&callback=initMap&language=${language}"></script>
</body>
</html>
