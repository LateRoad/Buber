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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Войти как клиент</title>
</head>
<body>

<ctg:side-menu user="${user}"/>

<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <h2>Входите как клиент</h2>
        <form method="POST" action="/userServlet?role=client" style="width: 500px">
            <div class="form-group">
                <label for="inputLogin">Логин</label>
                <input type="text" class="form-control" id="inputLogin" aria-describedby="loginHelp"
                       placeholder="Введите логин" name="login">
                <small id="loginHelp" class="form-text text-muted">Мы сохраним ваши данные в приватную базу данных.
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
</body>
</html>