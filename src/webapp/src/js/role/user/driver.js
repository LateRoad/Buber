function acceptOrder(orderId) {
    $.ajax({
        url: "/userOperation?action=acceptOrder&id=" + orderId,
        data: {name: 'abc'},
        type: 'POST',
        cache: false,
        success: function (data) {
            alert(data)
            $('#activeOrders').load("/home.jsp" + ' #activeOrders');
            alert(origin);
            showDirection(origin, );
        },
        error: function () {
            alert('error');
        }
    });
}

function updateActiveOrders() {
    $.ajax({
        url: "/userOperation?action=updateActiveOrders",
        data: {name: 'abc'},
        type: 'post',
        cache: false,
        success: function () {
            $('#activeOrders').load("/home.jsp" + ' #activeOrders');
        },
        error: function () {
            alert('error');
        }
    });
}
