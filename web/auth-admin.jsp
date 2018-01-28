<%--
  Created by IntelliJ IDEA.
  User: Roula
  Date: 25.01.2018
  Time: 19:28
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
    <title>Admin Entrance</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <div class="mx-auto mt-5">
            <h2>Входите как админ</h2>
            <form method="POST" action="/userServlet?role=admin"
                  style="width: 500px">
                <div class="form-group">
                    <label for="inputLogin">Логин</label>
                    <input type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp"
                           placeholder="Введите логин" name="login">
                    <small id="loginHelp" class="form-text text-muted">Мы сохраним ваши данные в приватную базу
                        данных.
                    </small>
                </div>
                <div class="form-group">
                    <label for="inputPassword">Пароль</label>
                    <input type="password" class="form-control" id="inputPassword" placeholder="Пароль"
                           name="password">
                </div>
                <button type="submit" name="action" value="signIn" class="btn btn-primary">Войти</button>
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
<script src="src/js/sb-admin.js"></script>
</body>
</html>