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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale.language}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="translation"/>

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
    <title><fmt:message key="title-payments"/></title>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top">
<ctg:side-menu user="${user}"/>

<div class="content-wrapper">
    <div class="container-fluid">
        <div class="card mb-3">
            <div class="card-header"><i class="fa fa-table"></i> <fmt:message key="nav-payments"/></div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">

                        <c:if test="${cards != null }">
                            <thead>
                            <tr>
                                <th><fmt:message key="card_number"/></th>
                                <th><fmt:message key="actions"/></th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <th><fmt:message key="card_number"/></th>
                                <th><fmt:message key="actions"/></th>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach var="card" items="${cards}">
                                <tr>
                                    <td>${card.hashNumber}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </c:if>

                        <c:if test="${cards == null }">
                            <td>Способы оплаты отсутствуют.</td>
                        </c:if>
                    </table>
                </div>
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
<script src="src/js/script.js"></script>
<script src="src/js/role/admin.js"></script>
<script src="src/js/role/commonOperation.js"></script>
</body>
</html>
