function calculateRouteInfo() {
    $.ajax({
        url: "/userOperation?action=getRouteInfo&from=" + $originInput.val() + "&to=" + $destinationInput.val() + "&fromLat=" + origin.position.lat() + "&fromLng=" + origin.position.lng() + "&toLat=" + destination.position.lat() + "&toLng=" + destination.position.lng(),
        type: 'post',
        cache: false,
        success: function () {
            $('#routeClientInfo').load("/home.jsp" + ' #routeClientInfo');
        },
        error: function (data) {
            $('.modal-title').text(data.responseText);
            $('#modal').modal('show');
        }
    });
}


function takeTaxi(driver, money) {
    $.ajax({
        url: "/userOperation?action=takeTaxi&driver=" + driver + "&money=" + money,
        type: 'POST',
        cache: false,
        success: function (data) {
            $('#routeClientInfo').hide();
            $('#modal').modal('show');
        },
        error: function (data) {
            $('.modal-title').text(data.responseText);
            $('#modal').modal('show');
        }
    });
}
