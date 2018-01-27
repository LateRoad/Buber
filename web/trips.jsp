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

        <c:if test="${user.role == \"client\" }">
            <!-- Example DataTables Card-->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fa fa-table"></i> Data Table Example
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Your driver</th>
                                <th>Price</th>
                                <th>Date</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Your driver</th>
                                <th>Price</th>
                                <th>Date</th>
                                <th>Status</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach var="trip" items="${trips}">
                                <tr>
                                    <td><c:out value="${trip.driverLogin}"/></td>
                                    <td><c:out value="${trip.money}"/></td>
                                    <td><c:out value="${trip.date}"/></td>
                                    <c:if test="${trip.status eq 'UNDONE' }">
                                        <td>Активно</td>
                                    </c:if>
                                    <c:if test="${trip.status eq 'DONE' }">
                                        <td>Выполнено</td>
                                    </c:if>
                                    <c:if test="${trip.status eq 'CANCELLED' }">
                                        <td>Отменено</td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${user.role == \"driver\" }">
            <!-- Example DataTables Card-->
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fa fa-table"></i> Data Table Example
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                            <thead>
                            <tr>
                                <th>Your passenger</th>
                                <th>Money</th>
                                <th>Date</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th>Your passenger</th>
                                <th>Money</th>
                                <th>Date</th>
                                <th>Status</th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach var="trip" items="${trips}">
                                <tr>
                                    <td><c:out value="${trip.clientLogin}"/></td>
                                    <td><c:out value="${trip.money}"/></td>
                                    <td><c:out value="${trip.date}"/></td>
                                    <c:if test="${trip.status eq 'UNDONE' }">
                                        <td>Активно</td>
                                    </c:if>
                                    <c:if test="${trip.status eq 'DONE' }">
                                        <td>Выполнено</td>
                                    </c:if>
                                    <c:if test="${trip.status eq 'CANCELLED' }">
                                        <td>Отменено</td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>

<ctg:footer project="<small>b</small>Uber" developer="LateRoad" year="2018"/>

<!-- Bootstrap core JavaScript-->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="vendor/jquery-easing/jquery.easing.min.js"></script>
<script src="vendor/datatables/jquery.dataTables.js"></script>
<script src="vendor/datatables/dataTables.bootstrap4.js"></script>
<!-- Custom scripts for all pages-->
<script src="js/sb-admin.js"></script>
<script src="js/sb-admin-datatables.min.js"></script>
</body>
</html>
