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


    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,400italic,500,700">
    <link rel="stylesheet" href="css/vendor.min.css">
    <link rel="stylesheet" href="css/elephant.min.css">
    <link rel="stylesheet" href="css/docs.css">
    <title>Buber</title>
</head>
<body>

<ctg:side-menu user="${user}"/>


<div class="layout-content">
    <div class="layout-content-body">
        <div class="doc">
            <div class="col-sm-7 equal-height p-a-lg" style="height: 320px;">
                <h4>Sign up</h4>
                <form action="/">
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="First Name">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="text" placeholder="Last Name">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="email" placeholder="Email">
                    </div>
                    <div class="form-group">
                        <input class="form-control" type="password" placeholder="Password">
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Sign up</button>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="progress progress-xs">
    <div class="progress-bar progress-bar-indicating active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
        <span class="sr-only">60% Complete (success)</span>
    </div>
</div>
<div class="progress">
    <div class="progress-bar progress-bar-indicating active" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%;">
        <span class="sr-only">60% Complete (success)</span>
        <span class="progress-value">60%</span>
    </div>
</div>
<div class="slider" data-slider="default" data-start="80"></div>


<script src="js/docs.js"></script>
<script src="js/demo.js"></script>
<script data-main="js/main" src="js/require.js"></script>
</body>
</html>
