<%--
  Created by IntelliJ IDEA.
  User: Roula
  Date: 11.01.2018
  Time: 16:39
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
    <script src="/js/editInfo.js"></script>
    <title>Profile</title>
</head>
<body>
<ctg:sideMenu user="${user}"/>
<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
    <h2>Общая информация</h2>
    <div class="panel-body">
        <div class="row">
            <div class=" col-md-9 col-lg-9 ">
                <table class="table table-user-information">
                    <tbody>
                    <tr>
                        <td>Логин:</td>
                        <td id="login">${user.login}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>Имя:</td>
                        <td id="name">${user.userInfo.name}</td>
                        <td>
                            <button onclick="change('name', 'editNameBtn')" id="editNameBtn" class="btn btn-primary">Редактировать</button>
                        </td>
                    </tr>
                    <tr>
                        <td>Фамилия:</td>
                        <td id="surname">${user.userInfo.surname}</td>
                        <td>
                            <button onclick="change('surname', 'editSurnameBtn')" id="editSurnameBtn" class="btn btn-primary">Редактировать</button>
                        </td>
                    </tr>
                    <tr>
                        <td>Отчество:</td>
                        <td id="lastname">${user.userInfo.lastname}</td>
                        <td>
                            <button onclick="change('lastname', 'editLastnameBtn')" id="editLastnameBtn" class="btn btn-primary">Редактировать</button>
                        </td>
                    </tr>

                    <tr>
                        <td>E-mail:</td>
                        <td id="email">${user.userInfo.email}</td>
                        <td>
                            <button onclick="change('email', 'editEmailBtn')" id="editEmailBtn" class="btn btn-primary">Редактировать</button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<c:if test="${user.role == \"client\"}">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
        <h4>Информация клиента</h4>
        <div class="panel-body">
            <div class="row">
                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td>Количество поездок:</td>
                            <td>${user.userInfo.clientInfo.tripsNumber}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Репутация:</td>
                            <td>${user.userInfo.clientInfo.reputation}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Телефон:</td>
                            <td id="clientInfo.phoneNumber">${user.userInfo.clientInfo.phoneNumber}</td>
                            <td>
                                <button onclick="change('clientInfo.phoneNumber', 'editClientPhoneBtn')" id="editClientPhoneBtn" class="btn btn-primary">Редактировать</button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</c:if>

<c:if test="${user.role == \"driver\"}">
    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad">
        <h4>Информация водителя</h4>
        <div class="panel-body">
            <div class="row">
                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td>Номер автомобиля:</td>
                            <td>${user.userInfo.driverInfo.carNumber}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Репутация:</td>
                            <td>${user.userInfo.driverInfo.reputation}</td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Телефон:</td>
                            <td id="driverInfo.phoneNumber">${user.userInfo.driverInfo.phoneNumber}</td>
                            <td>
                                <button onclick="change('driverInfo.phoneNumber', 'editDriverPhoneBtn')" id="editDriverPhoneBtn" class="btn btn-primary">Редактировать</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Водительская лицензия:</td>
                            <td id="license">${user.userInfo.driverInfo.driverLicense}</td>
                            <td>
                                <button onclick="change('license', 'editLicenseBtn')" id="editLicenseBtn" class="btn btn-primary">Редактировать</button>
                            </td>
                        </tr>
                        <tr>
                            <td>Количество поездок:</td>
                            <td>${user.userInfo.driverInfo.tripsNumber}</td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</c:if>

</body>
</html>
