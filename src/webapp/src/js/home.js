function takeTaxi(driver, money) {
    $.ajax({
        url: "/userServlet?action=takeTaxi&driver=" + driver + "&money=" + money,
        data: {name: 'abc'},
        type: 'POST',
        cache: false,
        success: function () {
            $('#routeClientInfo').hide();
            $('#map').hide();
        },
        error: function () {
            alert('error');
        }
    });
}

function acceptOrder(orderId) {
    $.ajax({
        url: "/userServlet?action=acceptOrder&id=" + orderId,
        data: {name: 'abc'},
        type: 'POST',
        cache: false,
        success: function () {
            $('#activeOrders').load("/home.jsp" + ' #activeOrders');
        },
        error: function () {
            alert('error');
        }
    });
}