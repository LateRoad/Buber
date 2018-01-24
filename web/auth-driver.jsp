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
    <title>Войти как клиент</title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">

<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">

        <div class="col-md-4 col-md-offset-4">
            <h2>Входите как водитель</h2>
            <form method="POST" action="/userServlet?role=driver" style="width: 500px">
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
    <ctg:footer project="<small>b</small>Uber" developer="LateRoad" year="2018"/>

    <script data-main="js/config" src="js/require.js"></script>
    <script>
        require(['config']), function () {

        }
    </script>
</div>
</body>
</html>