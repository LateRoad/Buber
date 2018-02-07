<%--
  Created by IntelliJ IDEA.
  java.lateroad.buber.entity.User: Roula
  Date: 24.12.2017
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translation"/>

<html lang="${language}">
<head>
    <link rel="shortcut icon" href="src/image/favicon.ico"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title><fmt:message key="buber"/></title>
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
            <div class="card-header"><fmt:message key="register-form-header"/></div>
            <div class="card-body">
                <form>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputName"><fmt:message key="input-name-label"/></label>
                                <input class="form-control" id="inputName" type="text"
                                       aria-describedby="nameHelp" placeholder="<fmt:message key="input-name-placeholder"/>">
                            </div>
                            <div class="col-md-6">
                                <label for="inputName"><fmt:message key="input-surname-label"/></label>
                                <input class="form-control" id="inputSurname" type="text"
                                       aria-describedby="nameHelp" placeholder="<fmt:message key="input-surname-placeholder"/>">
                            </div>
                            <div class="col-md-6">
                                <label for="inputLastName"><fmt:message key="input-lastname-label"/></label>
                                <input class="form-control" id="inputLastName" type="text"
                                       aria-describedby="nameHelp" placeholder="<fmt:message key="input-lastname-placeholder"/>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail"><fmt:message key="input-email-label"/></label>
                        <input class="form-control" id="inputEmail" type="email" aria-describedby="emailHelp"
                               placeholder="<fmt:message key="input-email-placeholder"/>">
                    </div>
                    <div class="form-group">
                        <div class="form-row">
                            <div class="col-md-6">
                                <label for="inputPassword"><fmt:message key="input-password-label"/></label>
                                <input class="form-control" id="inputPassword" type="password"
                                       placeholder="<fmt:message key="input-password-placeholder"/>">
                            </div>
                            <div class="col-md-6">
                                <label for="confirmPassword"><fmt:message key="input-confirmPassword-label"/></label>
                                <input class="form-control" id="confirmPassword" type="password"
                                       placeholder="<fmt:message key="input-confirmPassword-placeholder"/>">
                            </div>
                        </div>
                    </div>
                    <a class="btn btn-primary btn-block" href=""><fmt:message key="button-register"/></a>
                </form>
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
<script src="src/js/script.js"></script>
<script src="src/js/role/admin.js"></script>
<script src="src/js/role/commonOperation.js"></script>
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
