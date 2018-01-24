<%--
  Created by IntelliJ IDEA.
  User: Roula
  Date: 11.01.2018
  Time: 16:40
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
    <title>Payments</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
            <h2>Ваши поездки</h2>
            <div class="panel-body">
                <div class="row">
                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-trip-information">
                            <tbody>
                            <c:if test="${user.role == \"client\" }">
                                <tr>
                                    <td>Ваш водитель</td>
                                    <td>Денег затрачено</td>
                                    <td>Дата</td>
                                    <td>Статус выполнения</td>
                                </tr>
                                <c:forEach var="trip" items="${trips}">
                                    <tr>
                                        <td><c:out value="${trip.driverLogin}"/></td>
                                        <td><c:out value="${trip.money}"/></td>
                                        <td><c:out value="${trip.date}"/></td>
                                            ${trip.orderType}
                                        <c:if test="${trip.orderType eq 'UNDONE' }">
                                            <td>Активно</td>
                                        </c:if>
                                        <c:if test="${trip.orderType eq 'DONE' }">
                                            <td>Выполнено</td>
                                        </c:if>
                                        <c:if test="${trip.orderType eq 'CANCELLED' }">
                                            <td>Отменено</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            <c:if test="${user.role == \"driver\" }">
                                <tr>
                                    <td>Ваш пассажир</td>
                                    <td>Денег получено</td>
                                    <td>Дата</td>
                                    <td>Статус выполнения</td>
                                </tr>
                                <c:forEach var="trip" items="${trips}">
                                    <tr>
                                        <td><c:out value="${trip.clientLogin}"/></td>
                                        <td><c:out value="${trip.money}"/></td>
                                        <td><c:out value="${trip.date}"/></td>
                                        <c:if test="${trip.orderType eq 'UNDONE' }">
                                            <td>Активно</td>
                                        </c:if>
                                        <c:if test="${trip.orderType eq 'DONE' }">
                                            <td>Выполнено</td>
                                        </c:if>
                                        <c:if test="${trip.orderType eq 'CANCELLED' }">
                                            <td>Отменено</td>
                                        </c:if>
                                    </tr>
                                </c:forEach>
                            </c:if>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<ctg:footer project="<small>b</small>Uber" developer="LateRoad" year="2018"/>

<script data-main="js/config" src="js/require.js"></script>
<script>
    require(['config']), function () {

    }</script>
</body>
</html>
