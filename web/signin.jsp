<%--
  Created by IntelliJ IDEA.
  com.lateroad.buber.entity.User: Roula
  Date: 25.12.2017
  Time: 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Вход</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <h2>Вход</h2>
        <br>
        <br>
        <div class="row">
            <div class="col-md-4">
                <form action="/auth-driver.jsp">
                    <h4>Водитель</h4>
                    <br>
                    <p>Следите за своим заработком, работайте в свободное для себя время.</p>
                    <br>
                    <button type="submit" class="btn btn-primary">Войти как водитель</button>
                </form>
            </div>

            <div class="col-md-4 col-md-offset-4">
                <form action="/auth-client.jsp">
                    <h4>Клиент</h4>
                    <br>
                    <p>Управляйте способами оплаты, просматривайте историю поездок и многое другое.</p>
                    <br>
                    <button type="submit" class="btn btn-primary">Войти как клиент</button>
                </form>
            </div>

            <div class="col-md-4">
                <form action="/auth-admin.jsp">
                    <h4>Администратор</h4>
                    <br>
                    <p>Следите за заказами, выставляйте бонусы клиентам и контролируйте процессы.</p>
                    <br>
                    <button type="submit" class="btn btn-primary">Войти как администратор</button>
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
</body>
</html>
