<%--
  Created by IntelliJ IDEA.
  java.lateroad.buber.entity.User: Roula
  Date: 25.12.2017
  Time: 2:25
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
    <title><fmt:message key="title-signin"/></title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">
        <h2><fmt:message key="title-signin"/></h2>
        <br>
        <br>
        <div class="row">
            <div class="col-md-4">
                <form action="/auth-driver.jsp">
                    <h4><fmt:message key="driver"/></h4>
                    <br>
                    <p><fmt:message key="driver-description"/></p>
                    <br>
                    <button type="submit" class="btn btn-primary"><fmt:message key="come-in-driver"/></button>
                </form>
            </div>

            <div class="col-md-4 col-md-offset-4">
                <form action="/auth-client.jsp">
                    <h4><fmt:message key="client"/></h4>
                    <br>
                    <p><fmt:message key="client-description"/></p>
                    <br>
                    <button type="submit" class="btn btn-primary"><fmt:message key="come-in-client"/></button>
                </form>
            </div>

            <div class="col-md-4">
                <form action="/auth-admin.jsp">
                    <h4><fmt:message key="admin"/></h4>
                    <br>
                    <p><fmt:message key="admin-description"/></p>
                    <br>
                    <button type="submit" class="btn btn-primary"><fmt:message key="come-in-admin"/></button>
                </form>
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
<script src="src/js/sb-admin.js"></script>
<script src="src/js/commonOperations.js"></script>
</body>
</html>
