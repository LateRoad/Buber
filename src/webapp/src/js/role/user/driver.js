function acceptOrder(orderId) {
    $.ajax({
        url: "/userOperation?action=acceptOrder&id=" + orderId,
        type: 'POST',
        cache: false,
        success: function (data) {
            $('#activeOrders').load("/home.jsp" + ' #activeOrders');
            $('.modal-title').text("Заказ успешно принят.");
            $('#modal').modal('show');
            alert(origin);
            // showDirection(origin, );
        },
        error: function (data) {
            $('.modal-title').text(data.responseText);
            $('#modal').modal('show');
        }
    });
}

function updateActiveOrders() {
    $.ajax({
        url: "/userOperation?action=updateActiveOrders",
        type: 'post',
        cache: false,
        success: function () {
            $('#activeOrders').load("/home.jsp" + ' #activeOrders');
        },
        error: function (data) {
            $('.modal-title').text(data.responseText);
            $('#modal').modal('show');
        }
    });
}
