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
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Вход</title>
</head>
<body>

<ctg:side-menu user="${user}"/>

<div class="container">
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
    </div>
</div>
</body>
</html>
