function takeTaxi(driver, money) {
    $.ajax({
        url: "/userServlet?action=takeTaxi&driver=" + driver + "&money=" + money,
        data: {name: 'abc'},
        type: 'POST',
        cache: false,
        success: function () {
            $('#routeClientInfo').load("/home.jsp" + ' #routeClientInfo');
        },
        error: function () {
            alert('error');
        }
    });
}