<%--
  Created by IntelliJ IDEA.
  java.lateroad.buber.entity.User: Roula
  Date: 24.12.2017
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translate"/>

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
    <title><fmt:message key="client-entrance"/></title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <div class="mx-auto mt-5">
            <h2><fmt:message key="entering-as-client"/></h2>
            <form style="width: 500px">
                <div class="form-group">
                    <label for="inputLogin"><fmt:message key="login"/></label>
                    <input type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp"
                           placeholder="<fmt:message key="login-placeholder"/>" name="login">
                    <small id="loginHelp" class="form-text text-muted"><fmt:message key="login-input-help"/></small>
                </div>
                <div class="form-group">
                    <label for="inputPassword"><fmt:message key="password"/></label>
                    <input type="password" class="form-control" id="inputPassword"
                           placeholder="<fmt:message key="password-placeholder"/>"
                           name="password">
                </div>
                <button onclick="signIn('client')" type="submit" class="btn btn-primary"><fmt:message
                        key="come-in"/></button>
            </form>
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
<script async src="src/js/commonOperations.js"></script>
<script src="src/js/sb-admin.js"></script>
</body>
</html>