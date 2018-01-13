<%--
  Created by IntelliJ IDEA.
  com.lateroad.buber.logic.entity.User: Roula
  Date: 24.12.2017
  Time: 19:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Войти как клиент</title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/index.jsp">Buber</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><a href="#">Ехать</a></li>
                <li><a href="#">Идти</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">русский
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">русский</a></li>
                        <li><a href="#">беларускі</a></li>
                        <li><a href="#">english</a></li>
                    </ul>
                </li>
                <c:if test="${user != null }">
                    <li><a href="/home.jsp">${user.login}</a></li>
                    <li><a href="/userServlet?action=signOut" name="action" value="signOut"><span
                            class="glyphicon glyphicon-log-out"></span> Выход</a></li>
                </c:if>
                <c:if test="${user == null }">
                    <li><a href="/signin.jsp"><span class="glyphicon glyphicon-log-in"></span> Вход</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="col-md-4 col-md-offset-4">
        <h2>Входите как водитель</h2>
        <form method="POST" action="/userServlet?role=driver" style="width: 500px">
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