<%--
  Created by IntelliJ IDEA.
  com.lateroad.buber.entity.User: Roula
  Date: 24.12.2017
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<html lang="en">

<head>
    <link rel="shortcut icon" href="src/image/favicon.ico"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Buber</title>
    <!-- Bootstrap core CSS-->
    <link href="src/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom fonts for this template-->
    <link href="src/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- Page level plugin CSS-->
    <link href="src/vendor/datatables/dataTables.bootstrap4.css" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="src/css/sb-admin.css" rel="stylesheet">
</head>

<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="card card-register mx-auto mt-5">
            <div class="card-header">Register an Account</div>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="exampleInputName">First name</label>
                                <input class="form-control" id="exampleInputName" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter first name">
                            </div>
                            <div class="col-md-6">
                                <label for="exampleInputLastName">Last name</label>
                                <input class="form-control" id="exampleInputLastName" type="text"
                                       aria-describedby="nameHelp" placeholder="Enter last name">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input class="form-control" id="exampleInputEmail1" type="email" aria-describedby="emailHelp"
                               placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="exampleInputPassword1">Password</label>
                                <input class="form-control" id="exampleInputPassword1" type="password"
                                       placeholder="Password">
                            </div>
                            <div class="col-md-6">
                                <label for="exampleConfirmPassword">Confirm password</label>
                                <input class="form-control" id="exampleConfirmPassword" type="password"
                                       placeholder="Confirm password">
                            </div>
                        </div>
                    </div>
                    <a class="btn btn-primary btn-block" href="login.html">Register</a>
                </form>
                <div class="text-center">
                    <a class="d-block small mt-3" href="login.html">Login Page</a>
                    <a class="d-block small" href="forgot-password.html">Forgot Password?</a>
                </div>
            </div>
        </div>
    </div>

    <ctg:footer project="<small>b</small>Uber" developer="LateRoad" year="2018"/>
</div>
<!-- Bootstrap core JavaScript-->
<script src="src/vendor/jquery/jquery.min.js"></script>
<script src="src/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Core plugin JavaScript-->
<script src="src/vendor/jquery-easing/jquery.easing.min.js"></script>
<!-- Custom scripts for all pages-->
<script src="src/js/sb-admin.js"></script>
</body>

</html>


<%--<div class="layout-content">--%>
<%--<div class="layout-content-body">--%>
<%--<div class="doc">--%>
<%--<div class="col-sm-7 equal-height p-a-lg" style="height: 320px;">--%>
<%--<h4>Sign up</h4>--%>
<%--<form action="/">--%>
<%--<div class="form-group">--%>
<%--<input class="form-control" type="text" placeholder="First Name">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<input class="form-control" type="text" placeholder="Last Name">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<input class="form-control" type="email" placeholder="Email">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<input class="form-control" type="password" placeholder="Password">--%>
<%--</div>--%>
<%--<button type="submit" class="btn btn-primary btn-block">Sign up</button>--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="progress progress-xs">--%>
<%--<div class="progress-bar progress-bar-indicating active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">--%>
<%--<span class="sr-only">60% Complete (success)</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="progress">--%>
<%--<div class="progress-bar progress-bar-indicating active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">--%>
<%--<span class="sr-only">60% Complete (success)</span>--%>
<%--<span class="progress-value">60%</span>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="slider" data-slider="default" data-start="80"></div>--%>
