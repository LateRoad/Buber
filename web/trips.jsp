<%--
  Created by IntelliJ IDEA.
  User: Roula
  Date: 11.01.2018
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/sidemenu.css">
    <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Поездки</title>
</head>
<body>
<ctg:sideMenu user="${user}"/>

<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
    <h2>Ваши поездки</h2>
    <div class="panel-body">
        <div class="row">
            <div class=" col-md-9 col-lg-9 ">
                <table class="table table-trip-information">
                    <tbody>
                    <c:if test="${user.role == \"client\" }">
                        <tr>
                            <td>Ваш водитель</td>
                            <td>Денег затрачено</td>
                            <td>Дата</td>
                            <td>Статус выполнения</td>
                        </tr>
                        <c:forEach var="trip" items="${trips}">
                            <tr>
                                <td><c:out value="${trip.driverLogin}"/></td>
                                <td><c:out value="${trip.money}"/></td>
                                <td><c:out value="${trip.date}"/></td>
                                <c:if test="${trip.done == false }">
                                    <td>Активно</td>
                                </c:if>
                                <c:if test="${trip.done == true }">
                                    <td>Выполнено</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${user.role == \"driver\" }">
                        <tr>
                            <td>Ваш пассажир</td>
                            <td>Денег получено</td>
                            <td>Дата</td>
                            <td>Статус выполнения</td>
                        </tr>
                        <c:forEach var="trip" items="${trips}">
                            <tr>
                                <td><c:out value="${trip.clientLogin}"/></td>
                                <td><c:out value="${trip.money}"/></td>
                                <td><c:out value="${trip.date}"/></td>
                                <c:if test="${trip.done == false }">
                                    <td>Активно</td>
                                </c:if>
                                <c:if test="${trip.done == true }">
                                    <td>Выполнено</td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </c:if>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
